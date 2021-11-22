<%@page isErrorPage="true" contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="c"%>
<html title="Ошибка" message="${message}">
<head>
    <link rel="errorpage" href="error.css">
</head>
<body>
<h1>Error during executing request</h1>
   <%-- Request from ${pageContext.request.getRequestUri} if failed
    <br/>
    Servlet name or type: ${pageContext.getServletContext}
    <br/>
    Status code: ${pageContext.errorData.statusCode}
    <br/>
    Exception: ${pageContext.getException}--%>
</body>
</html>
