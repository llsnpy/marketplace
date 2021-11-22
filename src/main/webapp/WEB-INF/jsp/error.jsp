<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Error</title>
    <link rel="errorpage" href="error.css">
</head>
<body>
<h1>HAHA error</h1>
    <%--Request from ${pageContext.errorData.requestUri} if failed
    <br/>
    Servlet name or type: ${pageContext.errorData.servletName}
    <br/>
    Status code: ${pageContext.errorData.statusCode}
    <br/>
    Exception: ${pageContext.errorData.throwable}--%>
</body>
</html>
