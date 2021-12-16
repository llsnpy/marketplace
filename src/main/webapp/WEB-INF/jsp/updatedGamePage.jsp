<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>D.Update Game</title>

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
<div class="container-fluid">
    <h3>Current game</h3>
    <jsp:useBean id="updatedGame" scope="request" type="by.mironenko.marketplace.entity.Game" />
    <div class="col-lg-6 container-fluid">
        <table class="table table-bordered table-hover">
            <thead>
            <tr class="active">
                <th>Name</th>
                <th>Genre</th>
                <th>Date</th>
                <th>Price</th>
                <th>Sale</th>
                <th>Sale price</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><c:out value="${updatedGame.name}"/></td>
                <td><c:out value="${updatedGame.genre}"/></td>
                <td><c:out value="${updatedGame.date}"/></td>
                <td><c:out value="${updatedGame.price}"/></td>
                <td><c:out value="${updatedGame.preSale}"/></td>
                <td><c:out value="${updatedGame.salePrice}"/></td>
            </tr>
            </tbody>
        </table>

        <form action="${pageContext.request.contextPath}/controller/dev_update_current_game" method="post">
            <br>
            <div>
                <label for="name">New name</label>
                <input type="text" id="name" name="gameName"
                       placeholder="Game name">
            </div>
            <div>
                <label for="genre">New genre</label>
                <input type="text" id="genre" name="genre"
                       placeholder="Game genre">
            </div>
            <div>
                <label for="date">New date</label>
                <input type="date" id="date" name="releaseDate"
                       placeholder="Release date">
            </div>
            <div>
                <label for="price">New price</label>
                <input type="text" id="price" name="price"
                       placeholder="Game price">
            </div>
            <div>
                <label for="sale">New sale status</label>
                <input type="text" id="sale" name="saleStatus"
                       placeholder="Sale status">
            </div>
            <div>
                <label for="salePrice">New sale price</label>
                <input type="text" id="salePrice" name="salePrice"
                       placeholder="Sale price">
            </div>
            <button class="btn btn-success btn-sm">Update</button>
        </form>
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
