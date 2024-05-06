<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Job List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .job-card {
            border: 1px solid #ccc;
            padding: 15px;
            margin-bottom: 15px;
            width: 300px;
        }
        .job-card h3 {
            margin-top: 0;
        }
        .job-card a {
            color: #4CAF50;
            text-decoration: none;
            display: block;
            margin-top: 10px;
        }
        .job-card a:hover {
            color: #3e8e41;
        }
    </style>
</head>
<body>
    <c:forEach items="${jobs}" var="job">
        <div class="job-card">
            <h3>${job.title}</h3>
            <p>${job.description}</p>
            <p>${job.location}</p>
            <a href="/apply-job/${job.id}">Apply</a>
        </div>
    </c:forEach>
</body>
</html>

