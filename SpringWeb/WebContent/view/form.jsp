<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h3>등록</h3>
<form action="${pageContext.request.contextPath }/addMember.do" method="post">
<br>
<table border="1">
<tr><th>name</th><td><input type="text" name="name"></td></tr>
<tr><th>tel</th><td><input type="text" name="tel"></td></tr>
<tr><td><th>address</th><td><input type="text" name="address"></td></tr>
<tr><td colspan=2><input type="submit" value="저장"></td></tr>
</table>
</form>
</body>
</html>