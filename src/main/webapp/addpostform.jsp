<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>

<h1>Add New Post</h1>

<form action="addpost.jsp" method="post" enctype="multipart/form-data">
    <table>
        <tr><td>Photo:</td><td><input type="file" name="photo"></td></tr>
        <tr><td>Userid:</td><td><input type="text" name="userid"/></td></tr>
        <tr><td>Username:</td><td><input type="text" name="username"/></td></tr>
        <tr><td>Email:</td><td><input type="text" name="email"/></td></tr>
        <tr><td>Age:</td><td><input type="text" name="age"/></td></tr>
        <tr><td>Detail:</td><td><textarea cols="50" rows="5" name="detail"></textarea></td></tr>

        <tr><td><a href="posts.jsp">View All Records</a></td><td align="right"><input type="submit" value="Add Post"/></td></tr>
    </table>
</form>

</body>
</html>