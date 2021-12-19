<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${empty sessionScope.text}"><ftm:setLocale value="ru-RU"/></c:if>
<fmt:setBundle basename="text.properties" scope="session" var="bundle"/>
<head>
    <meta charset="utf-8">
    <title>marketplace</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
</head>
<body>
<nav class="navbar navbar-default" role="navigation" style="background: #F5F5F5">
    <div class="container-fluid">
        <div class="navbar-header">

            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <div class="navbar-brand">marketplace</div>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="${pageContext.request.contextPath}/controller/main">Main</a></li>
                <li class="active"><a href="${pageContext.request.contextPath}/controller/about_us">About us</a></li>
                <li class="active"><a href="${pageContext.request.contextPath}/controller/contacts">Contacts</a></li>
                <li class="active"><a href="${pageContext.request.contextPath}/controller/support">Support</a></li> 
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown=toggle" data-toggle="dropdown">Languages
                    <strong class="caret"></strong></a>
                    <ul class="dropdown-menu">
                        <li>
                            <form action="${pageContext.request.contextPath}/controller/choose_language" method="post">
                                <label class="btn btn-outline-success">
                                    <input type="hidden" name="lc" value="ru_RU">
                                    <input type="submit" class="btn" value="Russian">
                                </label>
                            </form>
                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller/choose_language" method="post">
                                <label class="btn btn-outline-success">
                                    <input type="hidden" name="lc" value="en_US">
                                    <input type="submit" class="btn" value="English">
                                </label>
                            </form>
                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller/choose_language" method="post">
                                <label class="btn btn-outline-success">
                                    <input type="hidden" name="lc" value="de_DE">
                                    <input type="submit" class="btn" value="German">
                                </label>
                            </form>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <h1 align="center">Welcome to the marketplace!</h1>
    <div class="row">
        <div class="col-lg-4" style="color: #ff9999"></div>

        <div class="col-lg-4" style="color: #000000" align="center">

            <h3>Please, enter or register</h3>
            </br>
            <form action="${pageContext.request.contextPath}/controller/log_in" method="post">
                <div>
                    <input type="text" id="login" name="login" value="${param.login}"
                        placeholder="Enter login">
                </div>
                    <br>
                 <div>
                    <input type="password" id="password" name="password"
                        placeholder="Enter password">
                 </div>
                <div>
                    <br class="container-fluid">
                    <button class="btn btn-success">
                        Log in <%--<fmt:message key="login" />--%>
                    </button>
                </div>
            </form>
            <form action="${pageContext.request.contextPath}/controller/registration" method="post">
                <button class="btn btn-info">
                    <i class="glyphicon glyphicon-pencil"></i>
                        Registration
                </button>
            </form>
        </div>
        <div class="col-lg-4" style="color: #00CC99"></div>
    </div>
</div>
<footer class="fixed-bottom">
    <div class="navbar-fixed-bottom row-fluid">
        <div class="navbar-inner">
            <div class="container-fluid padding">
                <div class="row alter text-center">
                    <h5>©2021. Powered by Pavel Mironenko (llsnpy).</h5>
                </div>
            </div>
        </div>
    </div>
</footer>
</body>
</html>
