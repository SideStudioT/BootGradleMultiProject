<%--
  Created by IntelliJ IDEA.
  User: logan
  Date: 2016. 3. 7.
  Time: 오후 5:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">

    <c:if test="${param.error != null}">
        <p>
            Invalid username and password.
        </p>
    </c:if>

    <form class="form-signin" action="/loginProcess" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="id" class="sr-only">ID</label>
        <input type="text" id="id" name="id" class="form-control" placeholder="ID" required="" autofocus="" >
        <label for="pw" class="sr-only">Password</label>
        <input type="password" id="pw" name="pw" class="form-control" placeholder="Password" required="">
        <div class="checkbox">
            <label>
                <input value="remember-me" type="checkbox"> Remember me
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form>

</div>