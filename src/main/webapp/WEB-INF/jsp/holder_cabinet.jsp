<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Cabinet.Holder</title>

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
    <div class="container">
        <div class="navbar-header">

            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target=".navbar-collapse">
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
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Languages
                        <strong class="caret"></strong></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">English</a></li>
                        <li><a href="#">German</a></li>
                        <li><a href="#">Russian</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <ul class="nav nav-tabs" role="tablist">
        <li class="active"><a href="#buyers" role="tab" data-toggle="tab">Buyers</a></li>
        <li><a href="#developers" role="tab" data-toggle="tab">Developers</a></li>
        <li><a href="#games" role="tab" data-toggle="tab">Games</a></li>
        <li><a href="#finds" role="tab" data-toggle="tab">Find</a></li>
    </ul>
    <div class="tab-content">
        <div role="tabpanel" class="tab-pane active" id="buyers">
            <jsp:useBean id="buyers" scope="request" type="java.util.List"/>
            <div class="col-lg-5 container-fluid">
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr class="active">
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Age</th>
                        <th>Money</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${buyers}" var="buyer">
                        <tr>
                            <td><c:out value="${buyer.name}"/></td>
                            <td><c:out value="${buyer.surname}"/></td>
                            <td><c:out value="${buyer.age}"/></td>
                            <td><c:out value="${buyer.money}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <div role="tabpanel" class="tab-pane" id="developers">
            <jsp:useBean id="developers" scope="request" type="java.util.List"/>
            <div class="col-lg-5 container-fluid">
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr class="active">
                        <th>Name</th>
                        <th>Money</th>
                        <th>Rating</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${developers}" var="dev">
                        <tr>
                            <td><c:out value="${dev.name}"/></td>
                            <td><c:out value="${dev.money}"/></td>
                            <td><c:out value="${dev.rating}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <div role="tabpanel" class="tab-pane" id="games">
            <jsp:useBean id="games" scope="request" type="java.util.List"/>
            <div class="col-lg-6 container-fluid">
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr class="active">
                        <th>Name</th>
                        <th>Genre</th>
                        <th>Date</th>
                        <th>Price</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${games}" var="game">
                        <tr>
                            <td><c:out value="${game.name}"/></td>
                            <td><c:out value="${game.genre}"/></td>
                            <td><c:out value="${game.date}"/></td>
                            <td><c:out value="${game.price}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div role="tabpanel" class="tab-pane" id="finds">
            <form action="${pageContext.request.contextPath}/controller/find_holder_action_res" method="get">
                <br>
                <div>
                    <label for="searchB">Find buyer by surname</label>
                </div>
                <div>
                    <input type="text" id="searchB" name="buyer_surname"
                           placeholder="buyer surname">
                    <button class="btn btn-info btn-sm">Find</button>
                </div>
                <br>
                <div>
                    <label for="searchD">Find dev by name</label></div>
                <div>
                    <input type="text" id="searchD" name="dev_name"
                           placeholder="dev name">
                    <button class="btn btn-info btn-sm">Find</button>
                </div>
                <br>
                <div>
                    <label for="searchG">Find game by name</label></div>
                <div>
                <input type="text" id="searchG" name="game_name"
                           placeholder="game name">
                    <button class="btn btn-info btn-sm">Find</button>
                </div>
            </form>
                <br>

            <form action="${pageContext.request.contextPath}/controller/holder_sorts" method="get"> //todo add to commands
                <div>
                    <label>Sort game by price</label>
                    <button class="btn btn-info btn-sm" name="sort_by_price">Sort</button>
                </div>
                <br>
                <div>
                    <label>Sort developers by rating</label>
                    <button class="btn btn-info btn-sm" name="sort_by_rating">Sort</button>
                </div>
                <br>
                <div>
                    <label>Sort buyers by money</label>
                    <button class="btn btn-info btn-sm" name="sort_by_money">Sort</button>
                </div>
            </form>
        </div>
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
