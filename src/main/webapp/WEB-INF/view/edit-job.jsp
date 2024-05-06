<!DOCTYPE html>
<html>
<head>
    <title>Edit Job</title>
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
    <h2>Edit Job</h2>
    <form action="/update-job" method="POST">
        <input type="hidden" name="id" value="${job.id}">
        <label>Title:</label>
        <input type="text" name="title" value="${job.title}"><br>
        <label>Description:</label>
        <textarea name="description">${job.description}</textarea><br>
        <label>Location:</label>
        <input type="text" name="location" value="${job.location}"><br>
        <button type="submit">Update Job</button>
    </form>
</body>
</html>