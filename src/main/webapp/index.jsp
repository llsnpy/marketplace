<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
    <title>Language</title>
    <link rel="startpage" href="statrpage.css">
</head>
<body>
    <h3>Welcome to Marketplace</h3>
    <form action="choose" method="post">
        <select name="language" title="Choose language">
            <option selected="ru" value="ru">Russian</option>
            <option value="en">English</option>
            <option value="de">Germany</option>
        </select>
        <input type="submit" value="Select">
    </form>
</body>
</html>
