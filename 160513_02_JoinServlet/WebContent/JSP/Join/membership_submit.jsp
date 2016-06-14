<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	ID: <%= request.getAttribute("user_id") %><br >
	PASS: <%= request.getAttribute("user_passwd") %><br >
	NAME: <%= request.getAttribute("user_name") %><br >
	GENDER: <%= request.getAttribute("user_gender") %><br >
	ADDRESS: <%= request.getAttribute("user_address") %><br >
</body>
</html>