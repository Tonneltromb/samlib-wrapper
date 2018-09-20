package com.ymatin.samlib.service;

import com.ymatin.samlib.common.util.NumberUtils;
import com.ymatin.samlib.dao.author.AuthorDao;
import com.ymatin.samlib.dao.author.AuthorDto;
import com.ymatin.samlib.dao.author.AuthorInfoDao;
import com.ymatin.samlib.dao.author.AuthorInfoDto;
import com.ymatin.samlib.dao.book.BookDao;
import com.ymatin.samlib.dao.book.BookDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

// todo: реализовать проверку наличия заполненных обязательных полей для записи в бд
// todo: реализовать функционал проверки изменения размера указанной книги и появления у указанного автора новых произведений

@Service
public class FillDatabaseHelper {

    private AuthorDao authorDao;
    private AuthorInfoDao authorInfoDao;
    private BookDao bookDao;

    @Autowired
    public void setAuthorDao(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Autowired
    public void setAuthorInfoDao(AuthorInfoDao authorInfoDao) {
        this.authorInfoDao = authorInfoDao;
    }

    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public Long addAuthorByPageReference(String authorPageReference) {
        Long authorId = null;
        Long authorInfoId = null;
        try {
            String url = prepareUrl(authorPageReference);
            Document document = Jsoup.connect(url).get();

            AuthorDto dto = selectAuthorFromSamlib(document, url);
            authorId = authorDao.addAuthor(dto);
            if (authorId != null) {
                AuthorInfoDto authorInfoDto = selectAuthorInfoFromSamlib(document, url);
                authorInfoDto.setAuthorId(authorId);
                authorInfoDao.addAuthorInfo(authorInfoDto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return authorId;
    }

    /**
     * Просмотр топ 40 Самиздата с записью в бд новых авторов
     */
    public void checkTop40(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements trs = document
                    .select("table")
                    .stream()
                    .filter(t -> t.select("tbody > tr").size() == 41)
                    .collect(Collectors.toCollection(Elements::new))
                    .get(0)
                    .select("tbody > tr");
            trs.stream().skip(1).forEach(tr -> {
                // todo проверяем, есть ли автор в базе
                // todo нужна таблица с записями топ 100, из которой берутся топ 40 и сначала проверяем по ней, чтобы не делать выборку по всей бд

                String b = tr.select("td b").get(0).text();
                int i = extractInteger(b);
                System.out.println(i);
            });


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private AuthorDto selectAuthorFromSamlib(Document document, String url) {
        AuthorDto dto = new AuthorDto();
        try {
            String samlibId = extractSamlibIdFromUrl(url);
            String shortName = prepareShortName(document);
            Map<String, String> authorInitials = prepareAuthorInitials(url);

            dto.setFirstName(authorInitials.get("firstName"));
            dto.setLastName(authorInitials.get("lastName"));
            dto.setFatherName(authorInitials.get("fatherName"));
            dto.setSamlibId(samlibId);
            dto.setShortName(shortName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
//        System.out.println(dl_dt_li_firstChiElements.stream().filter(e -> {
//            String text = e.select("a + b").get(0).text().split("\\D+")[0];
//            text = !text.equals("") ? text : "0" ;
//            return Integer.parseInt(text) > 100;
//        }).collect(Collectors.toList()).size());
    }

    private AuthorInfoDto selectAuthorInfoFromSamlib(Document document, String url) {
        AuthorInfoDto dto = new AuthorInfoDto();
        Map<String, Integer> birthDateParts = prepareBirthDate(document);
        String authorInfo = prepareAboutAuthorInfo(document);
//            todo String email = prepareEmail(document);
//            todo String webSite = prepareWebSite(document);

        dto.setAboutAuthor(authorInfo);
        dto.setDayOfBirth(birthDateParts.get("day"));
        dto.setMonthOfBirth(birthDateParts.get("month"));
        dto.setYearOfBirth(birthDateParts.get("year"));
//            todo dto.setEmail(email);
//            todo dto.setWebSite(webSite);
        return dto;
    }

    private String prepareWebSite(Document document) {
        String webSite = null;
        return webSite;
    }

    private String prepareEmail(Document document) {
        String email = null;
//        Optional<Element>  optional = document
//                .select("ul li b")
//                .stream()
//                .filter(element -> {
//                    String original = new String(element.text().getBytes(), StandardCharsets.UTF_8);
//                    String template = new String("Адрес:".getBytes(), StandardCharsets.UTF_8);
//                    return original.equals(template);
//        })
//                .findFirst();
//        if (optional.isPresent()) {
//            email = optional.get().text();
//        }
        return email;
    }

    private String prepareAboutAuthorInfo(Document document) {
        return document
                .select("dd i:first-child")
                .get(0)
                .text();
    }

    String extractSamlibIdFromUrl(String url) {
        String samlibId = "";
        if (url != null && !url.equals("")) {
            samlibId = url
                    .replaceAll("http://", "")
                    .replaceAll("samlib.ru", "")
                    .replaceAll("indexvote.shtml", "");
        }
        return samlibId;
    }

    private String prepareUrl(final String authorPageReference) {
        String url = authorPageReference;
        String domainNamePart = "samlib.ru";
        String protocolPart = "http://";
        String shtmlSuffixPart = "indexvote.shtml";
        boolean isContainsDomainNamePart = authorPageReference.contains(domainNamePart);
        boolean isContainsProtocolPart = authorPageReference.contains(protocolPart);
        boolean isContainsSuffixPart = authorPageReference.contains(shtmlSuffixPart);

        url = isContainsDomainNamePart ? url : domainNamePart + url;
        url = isContainsProtocolPart ? url : protocolPart + url;
        url = isContainsSuffixPart ? url : url + shtmlSuffixPart;
        return url;
    }

    private Integer extractInteger(String string) {
        // todo: добавить обработчик эксепшена
        return Integer.parseInt(string.split("\\D+")[0]);
    }

    private String prepareShortName(String string) {
        if (string != null && !string.trim().equals("") && string.length() > 0) {
            String[] splitResult = string.split(" ");
            string = splitResult[0];
            if (splitResult.length > 1) {
                StringBuilder stringBuilder = new StringBuilder(string);
                for (int i = 1; i < splitResult.length; i++) {
                    stringBuilder.append(" ").append(splitResult[i].substring(0, 1).toUpperCase()).append(".");
                }
                string = stringBuilder.toString();
            }
            return string;
        }
        return string;
    }

    private String prepareShortName(Document document) {
        String replaceResult = document
                .select("body center h3")
                .get(0)
                .textNodes()
                .get(0)
                .text()
                .replace(":", "");
        return prepareShortName(replaceResult);
    }

    private Map<String, String> prepareAuthorInitials(String url) throws IOException {
        Map<String, String> initialsMap = new HashMap<>(3);
        String firstName = null;
        String lastName = null;
        String fatherName = null;
        Document document = Jsoup.connect(url).get();
        String attr = document
                .select("dl dt li:first-child")
                .get(0)
                .select("a")
                .get(0)
                .attr("href");

        url = url.replace("indexvote.shtml", attr);
        document = Jsoup.connect(url).get();

        TextNode body_h3 = document
                .select("body h3")
                .get(0)
                .textNodes()
                .get(0);

        String[] authorInitials = body_h3
                .text()
                .trim()
                .replaceAll(":", "").split(" ");

        if (authorInitials.length > 0) {
            firstName = authorInitials[0];
        }
        if (authorInitials.length > 1) {
            lastName = authorInitials[1];
        }
        if (authorInitials.length > 2) {
            fatherName = authorInitials[2];
        }

        initialsMap.put("firstName", firstName);
        initialsMap.put("lastName", lastName);
        initialsMap.put("fatherName", fatherName);

        return Collections.unmodifiableMap(initialsMap);
    }

    private Map<String, Integer> prepareBirthDate(Document document) {
        Map<String, Integer> birthDatePartsMap = new HashMap<>(3);
        Integer day = null;
        Integer month = null;
        Integer year = null;

        String birthDateText = document
                .select("a[href*=\"/rating/bday/\"]")
                .get(0)
                .parent()
                .parent()
                .textNodes()
                .get(0)
                .text();

        if (birthDateText != null && !birthDateText.equals("") && birthDateText.contains("/")) {
            String[] splitResult = birthDateText.trim().split("/");
            if (splitResult.length > 0 && splitResult[0] != null && NumberUtils.isNumber(splitResult[0])) {
                day = Integer.parseInt(splitResult[0]);
            }
            if (splitResult.length > 1 && splitResult[1] != null && NumberUtils.isNumber(splitResult[1])) {
                month = Integer.parseInt(splitResult[1]);
            }
            if (splitResult.length > 2 && splitResult[2] != null && NumberUtils.isNumber(splitResult[2])) {
                year = Integer.parseInt(splitResult[2]);
            }
        }

        birthDatePartsMap.put("day", day);
        birthDatePartsMap.put("month", month);
        birthDatePartsMap.put("year", year);

        return Collections.unmodifiableMap(birthDatePartsMap);
    }

    /**
     * Поиск произведений автора
     */
    public void searchAndInsertBooksBySamlibId(String ref, int minimalAllowedSize) {
        String samlibId = extractSamlibIdFromUrl(ref);
//        authorDao.findAuthorBySamlibId(samlibId);
        try {
            String url = prepareUrl(ref);
            Document d = Jsoup.connect(url).get();

            Elements select = d
                    .select("body > dd")
                    .select("table + dl")
                    .select("dl dt li");

            select
                    .stream()
                    .filter(element -> {
                        String text = element.select(" > a + b").text();
                        Integer integer = extractInteger(text);
                        return integer > minimalAllowedSize;
                    })
                    .forEach(e -> {
                        BookDto dto = new BookDto();
                        // извлечь название
                        String title = e.select("> a > b").text();
                        // извлечь размер
                        Integer size = extractInteger(e.select("> a + b").text());
                        // извлечь ссылку
                        String href = e.select("> a").attr("href");
                        dto.setTitle(title);
                        dto.setSamlibRef(href);
                        dto.setSize(size);

                        // добавить в бд
                        bookDao.addBook(dto);
                    });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        String ref = "http://samlib.ru/m/metelxskij_n_a/indexvote.shtml";
//        String ref = "http://samlib.ru/p/pupkin_wasja_ibragimowich/indexvote.shtml";
//        String top = "http://samlib.ru/rating/top40/";
        FillDatabaseHelper helper = new FillDatabaseHelper();
//        helper.checkTop40(top);
//        helper.selectAuthorInfoFromSamlib(ref);
//        System.out.println(dto);
//        String s = "100k";
//        Pattern pattern = Pattern.compile("[0-9]+");
//        Matcher matcher = pattern.matcher(s);
//        String str = matcher.find() ? matcher.group() : "";
//        System.out.println(str);
//
//        System.out.println(Integer.parseInt(s.split("\\D+")[0]));
//        System.out.println(Integer.parseInt(""));
    }
}
