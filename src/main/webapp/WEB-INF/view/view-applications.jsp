<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>View Applications</title>
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
<h1>Applications for Job: ${job.title}</h1>

<table border="1">
    <thead>
        <tr>
            <th>Job Seeker Name</th>
            <th>Application Status</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${jobApplications}" var="jobApplication">
            <tr>
                <td>${jobApplication.jobSeeker.firstName} ${jobApplication.jobSeeker.lastName}</td>
                <td>${jobApplication.status}</td>
                <td>
                    <c:choose>
                        <c:when test="${jobApplication.status eq 'PENDING'}">
                            <form action="/accept-application" method="post">
                                <input type="hidden" name="id" value="${jobApplication.id}">
                                <button type="submit">Accept</button>
                            </form>
                            <form action="/reject-application" method="post">
                                <input type="hidden" name="id" value="${jobApplication.id}">
                                <button type="submit">Reject</button>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <p>--</p>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<a href="/employer-dashboard">Back to Dashboard</a>
</body>
</html>
