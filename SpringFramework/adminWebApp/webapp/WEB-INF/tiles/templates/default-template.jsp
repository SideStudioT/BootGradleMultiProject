<%--
  Created by IntelliJ IDEA.
  User: logan
  Date: 2016. 3. 7.
  Time: 오후 6:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<html>
<head>
    <title><tiles:getAsString name="title"/></title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Import Tiles css List -->
    <tiles:importAttribute name="cssList"/>
    <c:forEach var="value" items="${cssList}">
        <link rel="stylesheet" type="text/css" href="<c:url value="${value}"/>">
    </c:forEach>
</head>
<body>
    <tiles:insertAttribute name="header" />

    <tiles:insertAttribute name="body" flush="true"/>

    <tiles:insertAttribute name="footer" />

<!-- Import Tiles js List -->
<tiles:importAttribute name="jsList"/>
<c:forEach var="value" items="${jsList}">
    <script src="<c:url value="${value}"/>"></script>
</c:forEach>
</body>
</html>
