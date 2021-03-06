<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Fill database helper start page</title>
</head>
<style>
    h4 {
        width: 100px;
        height: 20px;
        display: inline-block;
        background-color: darkgrey;
        border-radius: 0 8px 8px 0;
        margin-right: 10px;
    }
</style>
<body>
<hr>
<form action="/filldb/addAuthor" method="post">
    <input type="text" name="pageRef" title="Enter author page reference"/>
    <input type="submit" value="Add author"/>
</form>
<hr>
<form action="/filldb/findAuthor">
    <input type="text" name="authorId"/>
    <input type="submit" value="Find author by ID"/>
</form>
<hr>
<form action="/filldb/searchBooks">
    <input type="number" name="authorId"/>
    <input type="submit" value="Find books"/>
</form>
<hr>
<a href="/filldb/findAll">
    <button>Find all</button>
</a>
<hr>
<a href="/filldb/getAllInfos">
    <button>Find all infos</button>
</a>
<hr>
<a href="/filldb/allBooks">
    <button>Get books</button>
</a>
<hr>
<a href="/filldb/content">
    <button>content</button>
</a>
<c:if test="${not empty author}">
    <hr>
    <div>
        <h3>Chosen author</h3>
        <h4>ID</h4><span>${author.authorId}</span><br>
        <h4>First Name</h4><span>${author.firstName}</span><br>
        <h4>Last name</h4><span>${author.lastName}</span><br>
        <h4>Father name</h4><span>${author.fatherName}</span><br>
        <h4>Short name</h4><span>${author.shortName}</span><br>
        <h4>Pseudonym</h4><span>${author.pseudonym}</span><br>
        <h4>Samlib ID</h4><span>${author.samlibId}</span><br>
    </div>
    <c:if test="${not empty info}">
        <div>
            <h3>Additional info</h3>
            <h4>Info ID</h4><span>${info.infoId}</span><br>
            <h4>Author ID</h4><span>${info.authorId}</span><br>
            <h4>Day</h4><span>${info.dayOfBirth}</span><br>
            <h4>Month</h4><span>${info.monthOfBirth}</span><br>
            <h4>Year</h4><span>${info.yearOfBirth}</span><br>
            <h4>Web site</h4><span>${info.webSite}</span><br>
            <h4>email</h4><span>${info.email}</span><br>
            <h4>About</h4><span>${info.aboutAuthor}</span><br>
        </div>
    </c:if>

</c:if>
<c:if test="${not empty authors}">
    <hr>
    <div>
        <table>
            <tr>
                <th>ID</th>
                <th>First name</th>
                <th>Last name</th>
            </tr>
            <c:forEach items="${authors}" var="tempAuthor">
                <tr>
                    <td>${tempAuthor.authorId}</td>
                    <td>${tempAuthor.firstName}</td>
                    <td>${tempAuthor.lastName}</td>
                    <td>
                        <a href="/filldb/delete?authorId=${tempAuthor.authorId}">
                            <button>Delete</button>
                        </a>
                    </td>
                    <td>
                        <a href="/filldb/searchBooks?authorId=${tempAuthor.authorId}">
                            <button>Search and add books</button>
                        </a>
                    </td>
                    <td>
                        <a href="/filldb/showBooks?authorId=${tempAuthor.authorId}">
                            <button>Show books</button>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>
<c:if test="${not empty infos}">
    <hr>
    <div>
        <table>
            <tr>
                <th>ID</th>
                <th>Author ID</th>
                <th>Day</th>
                <th>Month</th>
                <th>Year</th>
                <th>Email</th>
                <th>Web site</th>
            </tr>
            <c:forEach items="${infos}" var="tempInfo">
                <tr>
                    <td>${tempInfo.infoId}</td>
                    <td>${tempInfo.authorId}</td>
                    <td>${tempInfo.dayOfBirth}</td>
                    <td>${tempInfo.monthOfBirth}</td>
                    <td>${tempInfo.yearOfBirth}</td>
                    <td>${tempInfo.email}</td>
                    <td>${tempInfo.webSite}</td>
                </tr>
                <tr>
                    <td colspan="7">${tempInfo.aboutAuthor}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>
<c:if test="${not empty books}">
    <hr>
    <table>
        <tr>
            <th>Book Id</th>
            <th>Author Id</th>
            <th>Title</th>
            <th>Ref</th>
            <th>size</th>
        </tr>
        <c:forEach items="${books}" var="b">
            <tr>
                <td>${b.bookId}</td>
                <td>${b.authorId}</td>
                <td>${b.title}</td>
                <td>${b.samlibRef}</td>
                <td>${b.size}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${not empty content}">
    <c:forEach items="${content}" var="entry">
        <br>
        <h3>${entry.key}</h3>
        <div>
                ${entry.value.content}
        </div>
    </c:forEach>
</c:if>
</body>
</html>
