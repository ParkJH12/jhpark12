<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
		Cookie[] cookie = request.getCookies();
		for(Cookie c: cookie) {
			response.addCookie(c);
	%>
	<%= 	c.getName() %>의 값 <%= c.getValue() %><br>
	<% 	} %>
	
</body>
</html>