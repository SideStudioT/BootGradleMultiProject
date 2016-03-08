<%--
  Created by IntelliJ IDEA.
  User: logan
  Date: 2016. 3. 3.
  Time: 오후 5:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<h1>안녕하세요!</h1>
<%--<fmt:parseDate value="${todaytime}" pattern="yyyyMMddHHmmss" var="today"/>--%>
지금 시각 : <fmt:formatDate value="${todaytime}" pattern="yyyy년 MM월 dd일 HH시 mm분 ss초"/> </br>

<%--
  * security Taglibs 이용하여 로그인에 따른 사용자 정보를 출력 한다
--%>
<sec:authorize access="isAuthenticated()">
<sec:authentication property="details" var="userDetails"/>
<b>접속한 유저 정보</b> </br>
<p>
    ID : ${userDetails.id} </br>
    PW : ${userDetails.pw} </br>
    이름 : ${userDetails.name} </br>
    전화 번호 : ${userDetails.phone}
</p>
</sec:authorize>