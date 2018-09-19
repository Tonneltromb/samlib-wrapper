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
<a href="/filldb/findAll"><button>Find all</button></a>
<c:if test="${not empty author}">
    <hr>
    <div>
        <h3>Chosen author</h3>
        <h4>ID</h4><span>${author.authorId}</span><br>
        <h4>First Name</h4><span>${author.firstName}</span><br>
        <h4>Last name</h4><span>${author.lastName}</span><br>
        <h4>Father name</h4><span>${author.fatherName}</span><br>
        <h4>Day</h4><span>${author.dayOfBirth}</span><br>
        <h4>Month</h4><span>${author.monthOfBirth}</span><br>
        <h4>Year</h4><span>${author.yearOfBirth}</span><br>
        <h4>Pseudonym</h4><span>${author.pseudonym}</span><br>
        <h4>Samlib ID</h4><span>${author.samlibId}</span><br>
    </div>
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
                        <a href="/filldb/delete?authorId=${tempAuthor.authorId}"><button>Delete</button></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>
</body>
</html>
