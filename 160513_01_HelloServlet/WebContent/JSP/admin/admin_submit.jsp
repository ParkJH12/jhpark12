<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	ID : <%= request.getAttribute("adm_id") %>
	PASS : <%= request.getAttribute("adm_pass") %>
	<%= application.getServerInfo() %>
</body>
</html>