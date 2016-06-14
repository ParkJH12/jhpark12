<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
	function login() {
		if(document.loginForm.p_empno.value == "") {
			alert("사원번호를 입력하시기 바랍니다.")
			document.loginForm.p_empno.focus()
		} else if(document.loginForm.p_ename.value == "") {
			alert("사원이름을 입력하시기 바랍니다.")
			document.loginForm.p_ename.focus()
		} else {
			document.loginForm.p_code.value = "login_chk"
			document.loginForm.submit()
		}
	}
	
	function join() {
		document.loginForm.p_code.value = "login_join"
		document.loginForm.submit()
	}
	
	function keyEvent() {
		// Enter키가 눌렸을 경우
		if (event.keyCode == 13) {
			login()
		}
	}
	
</script>
<title>Login Page</title>
</head>
<body>
	<form action="login.do" method="post" name="loginForm">
		<p>사원번호 : <input name="p_empno" type="text" onKeyDown="keyEvent()"></p>
		<p>사원이름 : <input name="p_ename" type="text" onKeyDown="keyEvent()"></p>
		<input name="p_code" type="hidden">
		<p><input name="p_bt_login" type="button" value="로그인" onClick="login()"></p>
		<p><input name="p_bt_reg" type="button" value="회원가입" onClick="join()"></p>
	</form>
</body>
</html>