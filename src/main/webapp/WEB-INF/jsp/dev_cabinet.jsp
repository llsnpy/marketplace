<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Cabinet.Developer</title>

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
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Languages
                        <strong class="caret"></strong></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">English</a></li>
                        <li><a href="#">German</a></li>
                        <li><a href="#">Russian</a></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-right">
                <li class="active"><a href="${pageContext.request.contextPath}/controller/logout">
                    <input type="button" class="btn btn-info" value="Log out">
                </a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <ul class="nav nav-tabs" role="tablist">
        <li class="active"><a href="#info" role="tab" data-toggle="tab">Info</a></li>
        <li><a href="#current_dev_library" role="tab" data-toggle="tab">My library</a></li>
        <li><a href="#developing" role="tab" data-toggle="tab">Developing</a></li>
    </ul>
    <div class="tab-content">
        <div role="tabpanel" class="tab-pane active" id="info">
            <jsp:useBean id="dev" scope="request" type="by.mironenko.marketplace.entity.Developer"/>
            <div class="col-lg-6 container-fluid">
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr class="active">
                            <th>Name</th>
                            <th>Money</th>
                            <th>Rating</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><c:out value="${dev.name}"/></td>
                            <td><c:out value="${dev.money}"/></td>
                            <td><c:out value="${dev.rating}"/></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <div role="tabpanel" class="tab-pane" id="current_dev_library">
            <jsp:useBean id="current_dev_games" scope="request" type="java.util.List" />
            <div class="col-lg-6 container-fluid">
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr class="active">
                            <td>Name</td>
                            <td>Genre</td>
                            <td>Date</td>
                            <td>Price</td>
                            <td>Pre sale</td>
                            <td>Sale price</td>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${current_dev_games}" var="game">
                        <tr>
                            <td><c:out value="${game.name}"/></td>
                            <td><c:out value="${game.genre}"/></td>
                            <td><c:out value="${game.date}"/></td>
                            <td><c:out value="${game.price}"/></td>
                            <td><c:out value="${game.preSale}"/></td>
                            <td><c:out value="${game.salePrice}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <div role="tabpanel" class="tab-pane" id="developing">
                <br>
                <form action="${pageContext.request.contextPath}/controller/dev_create_new_game" method="post">
                    <br>
                    <div>
                        <label for="name">Game name</label>
                        <input type="text" id="name" name="gameName"
                               placeholder="Game name">
                    </div>
                    <div>
                        <label for="genre">Game genre</label>
                        <input type="text" id="genre" name="genre"
                               placeholder="Game genre">
                    </div>
                    <div>
                        <label for="date">Release date</label>
                        <input type="text" id="date" name="releaseDate"
                               placeholder="Release">
                    </div>
                    <div>
                        <label for="price">Game price</label>
                        <input type="text" id="price" name="price"
                               placeholder="Game price">
                    </div>
                    <div>
                        <label for="sale">Sale status</label>
                        <input type="text" id="sale" name="saleStatus"
                               placeholder="Sale status">
                    </div>
                    <div>
                        <label for="salePrice">Sale price</label>
                        <input type="text" id="salePrice" name="salePrice"
                               placeholder="Sale price">
                    </div>
                    <button class="btn btn-success btn-sm">Create game</button>
                </form>

                <br>

                <form action="${pageContext.request.contextPath}/controller/dev_release_game">
                    <div>
                        <label for="releaseGame">Game for release</label>
                        <input type="text" id="releaseGame" name="releaseGameName"
                               placeholder="Game name">
                        <button class="btn btn-success btn-sm">Release game</button>
                    </div>
                </form>

                <form action="${pageContext.request.contextPath}/controller/dev_update_game">
                    <div>
                        <label for="updateGame">Game for update</label>
                        <input type="text" id="updateGame" name="updateGameName"
                               placeholder="Game name">
                        <button class="btn btn-success btn-sm">Update game</button>
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
