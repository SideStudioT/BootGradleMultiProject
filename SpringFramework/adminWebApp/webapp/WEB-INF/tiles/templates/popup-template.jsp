<%--
  Created by IntelliJ IDEA.
  User: logan
  Date: 2016. 3. 7.
  Time: 오후 8:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<html>
<head>
    <title><tiles:getAsString name="title"/></title>
</head>
<body>
    <tiles:insertAttribute name="body" />
</body>
</html>
