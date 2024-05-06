<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Job Seeker Dashboard</title>
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
    <h1>Welcome, Job Seeker!</h1>

    <h2>Available Jobs</h2>
    <c:if test="${not empty availableJobs}">
        <table border="1">
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Location</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${availableJobs}" var="job">
                    <tr>
                        <td>${job.title}</td>
                        <td>${job.description}</td>
                        <td>${job.location}</td>
                        <td>
                            <a href="/apply-job/${job.id}">Apply</a>

                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty availableJobs}">
        <p>No available jobs at the moment.</p>
    </c:if>

    <h2>Applied Jobs</h2>
    <c:if test="${not empty appliedJobs}">
        <table border="1">
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Location</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${appliedJobs}" var="job">
                    <tr>
                        <td>${job.title}</td>
                        <td>${job.description}</td>
                        <td>${job.location}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty appliedJobs}">
        <p>You haven't applied to any jobs yet.</p>
    </c:if>

    <a href="/logout">Logout</a>
</body>
</html>
