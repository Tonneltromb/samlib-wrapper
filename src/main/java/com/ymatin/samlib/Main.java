package com.ymatin.samlib;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String URL = "jdbc:postgresql://localhost:5432/postgres";
        String USER = "postgres";
        String PASS = "root";
//        Document document = Jsoup.connect("http://samlib.ru/m/metelxskij_n_a/indexvote.shtml").get();
//        Elements elements = document.select("dl a+b");
//        Element element = elements.get(0);
//        String text = element.text();
//        String sibling = element.previousElementSibling().selectFirst("b").text();
//        System.out.println(text);
//        System.out.println(sibling);

        Document document = Jsoup.connect("http://samlib.ru/m/metelxskij_n_a/wt2.shtml").get();
//        long l = System.nanoTime();
        Elements elements = document.select("body > dd");
//        Elements elements = document.select("dd");
//        long l1 = System.nanoTime();
//        System.out.println(l1-l);
//        System.out.println(elements.size());
//        System.out.println(".");
//        elements.stream().limit(4).forEach(e -> e.childNodes().forEach(n -> System.out.print(n + "|")));

        StringBuilder str = new StringBuilder();
        elements.stream().limit(4).forEach(e -> {
            str.append(e.wholeText());
//            System.out.println(e.wholeText());
//            System.out.println(e.wholeText().contains("\u00a0"));
        });
//        elements.get(1).textNodes().forEach((textNode -> System.out.print(textNode.text())));
//        elements.get(3).textNodes().forEach((textNode -> System.out.println(textNode.text())));
//        System.out.println();
//        System.out.print(".");
//        System.out.println();
//        System.out.println(elements.get(1).wholeText());

//        System.out.println(str);

//        Files.write(Paths.get("new.txt"), str.toString().getBytes());
        // вытащить все дд
        // пройтись по их текстовому содержимому
        // записать в файл
        // записать в бд
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO books (content) VALUES (?);");
            statement.setString(1, str.toString());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
