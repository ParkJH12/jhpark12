<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<script type="text/javascript">
	function login() {
		var form = document.loginForm
		if(form.m_num.value == "") {
			alert("학번을 입력하세요")
			form.m_num.focus()
		} else if(form.m_name.value == "") {
			alert("이름을 입력하세요")
			form.m_name.focus()
		} else {
			form.submit()
		}
	}
	
	function join() {
		location.href="join.jsp"
	}
	
	function keyevent() {
		if (event.keyCode == 13) {
			login();
		}
	}
</script>
</head>
<body>
	<form action="${pageContext.request.contextPath }/MemberController" name="loginForm" method="POST">
		<p>학번: <input type="text" name="m_num" onkeydown="keyevent()"></p>
		<p>이름: <input type="text" name="m_name" onkeydown="keyevent()"></p>
		<input type="hidden" name="type" value="login">
		<p><input type="button" value="로그인" onClick="login()"> <input type="button" value="회원가입" onClick="join()"></p>
	</form>
	<table border="2">
		<tr>
			<td rowspan="2"></td>
			<td></td>
		</tr>
		<tr>
			<td ></td>
		</tr>
	</table>
</body>
</html>