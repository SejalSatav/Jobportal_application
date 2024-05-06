<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
    <style>
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    background-color: #f0f0f0;
}

h1, h2 {
    color: #333;
}

form {
    width: 300px;
    margin: 0 auto;
    padding: 20px;
    background-color: #fff;
    border: 1px solid #ccc;
    border-radius: 5px;
}

label, input, textarea, select {
    display: block;
    margin: 10px auto;
    width: 90%;
}

input[type="submit"], button {
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    border: none;
    cursor: pointer;
    width: 100%;
    margin-top: 10px;
}

input[type="submit"]:hover, button:hover {
    opacity: 0.8;
}

table {
    width: 100%;
    border-collapse: collapse;
}

th, td {
    text-align: left;
    padding: 8px;
    border: 1px solid #ddd;
}

th {
    background-color: #4CAF50;
    color: white;
}

a {
    color: #4CAF50;
    text-decoration: none;
}

a:hover {
    color: #3e8e41;
}

.error {
    color: #ff0000;
}
</style>
    
</head>
<body>
<h2>Login</h2>
<form action="/login" method="POST">
    Email: <input type="text" name="email" required="required"/><br/>
    Password: <input type="password" name="password" required="required"/><br/>
    <input type="submit" value="Login"/>
</form>
</body>
</html>
