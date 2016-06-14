<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<script type="text/javascript">
	function chk_pass_script(){
		alert("test");
		var chk_form = document.chk_pass;
		chk_form.submit();
	}
</script>
<title>:: Check Password ::</title>
</head>
<body>
<form name="chk_pass" action="read.do" method="POST">
패스워드 : <input type="password" name="p_pass">
<br>
<input type="hidden" value="chk_pass" name="p_code">
<input type="hidden" value="${P_BID}" name="p_bid">
<input type="button" value="확인" onclick="chk_pass_script()"/>
</form>
</body>
</html>