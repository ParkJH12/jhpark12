<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Enumeration" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	
</head>
<body>
<%
	Enumeration<String> headEnum = request.getHeaderNames();
	while(headEnum.hasMoreElements()) {
		String headerName = (String)headEnum.nextElement();
		String headerValue = request.getHeader(headerName);
		out.print(headerName);
		out.print(" : ");
		out.println(headerValue);
	}
%>
</body>
</html>