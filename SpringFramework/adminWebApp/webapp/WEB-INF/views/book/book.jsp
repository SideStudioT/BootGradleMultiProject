<%--
  Created by IntelliJ IDEA.
  User: logan
  Date: 2016. 3. 4.
  Time: 오후 3:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>유저 목록</title>
</head>
<body>
<form action="/book" method="POST">
    이름 : <input type="text" name="name" /> </br>
    가격 : <input type="text" name="price" /> </br>
    설명 : <input type="text" name="description" /> </br>
    <input type="submit" value="만들기" />
</form>

<c:forEach items="${bookList}" var="book">
<hr>
<div>
    <%--유저 일련 번호 : ${book.idx} </br>--%>
    책 이름 : ${book.name}</br>
    책 가격 : ${book.price}</br>
    책 설명 : ${book.description}
</div>
<hr>
</c:forEach>
</body>
</html>
