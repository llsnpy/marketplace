<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Contacts</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="/css/commonStyle.css">

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
<nav class="navbar navbar-default" style="background: #F5F5F5">
    <div class="container-fluid">
        <div class="navbar-header">
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
                <li class="active"><a href="${pageContext.request.contextPath}">
                    <input type="button" class="btn btn-success" value="Log in">
                </a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <br><br>
    <div class="row">
        <div class="col-lg-4" style="color: #000000"></div>
        <div class="col-lg-4" style="color: #000000" align="center">
            <div class="container-fluid">
                <div class="col-12 social padding">
                    <br>
                    <a href="https://www.linkedin.com/in/llsnpy/" target="_blank" style="font-size: 30px">LinkedIn</a> <br>
                    <a href="https://github.com/llsnpy" target="_blank" style="font-size: 30px">GitHub</a> <br>
                    <a href="https://www.facebook.com/mironenkops/" target="_blank" style="font-size: 30px">Facebook</a> <br>
                    <a href="https://vk.com/so_excellent" target="_blank" style="font-size: 30px">VK</a> <br>
                </div>
            </div>
            <br>
            <div class="container-fluid">
                <p>Email: pavelsmironenko@gmail.com</p>
                <p>Email: pashka271196@gmail.com</p>
                <p>Phone: +375 (29) 170 75 85 (A1)</p>
            </div>
        </div>
        <div class="col-lg-4" style="color: #000000"></div>
    </div>
</div>
<footer class="fixed-bottom">
    <div class="navbar-fixed-bottom row-fluid">
        <div class="navbar-inner">
            <div class="container-fluid padding">
                <div class="row alter text-center">
                    <h5>??2021. Powered by Pavel Mironenko (llsnpy).</h5>
                </div>
            </div>
        </div>
    </div>
</footer>
</body>
</html>
