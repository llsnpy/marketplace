<%@page isErrorPage="true" contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="c"%>
<html>
<head>
    <title>Error</title>
    <link rel="errorpage">
</head>
<body>
<h1>Error during executing request</h1>
<jsp:useBean id="error_message" scope="request" type="java.lang.String"/>
<h5>${error_message}</h5>
</body>
</html>
