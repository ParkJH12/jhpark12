<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Success!</title>
<script type="text/javascript">
	function logout() {
		document.logoutForm.p_code.value = "log_out"
		document.logoutForm.submit()
	}
	
	function empmod() {
		document.logoutForm.action="emp.do"
		document.logoutForm.submit()
	}
</script>
</head>
<body>
	<p>로그인에 성공하였습니다.</p>
	<p><%= session.getAttribute("empno") %></p>
	<p><%= request.getAttribute("ename") %></p>
	
	<form name="logoutForm" method="POST" action="login.do">
		<input name="p_code" type="hidden">
		<p><input name="p_bt_logout" value="로그아웃" type="button" onClick="logout()"> 
		<input name="p_bt_modify" value="정보수정" type="button" onClick="empmod()"></p>
	</form>
</body>
</html>