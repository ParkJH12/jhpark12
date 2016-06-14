<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>:: KITRI BOARD PASSWORD CHECK ::</title>
<!--  이 페이지는 삭제, 수정시 동시에 사용되는 암호 확인 페이지입니다. JavaScript에서 code를 통해 수정, 삭제페이지를 구분합니다. -->
<script type="text/javascript">
	function passChk(code) {
		if("delete" == code) {
			document.passChkForm.p_code.value = "delete"
			document.passChkForm.submit()
		} else {
			document.passChkForm.action="read.do"
			document.passChkForm.p_code.value = "modify_ok"
			document.passChkForm.submit()
		}
	}
</script>
</head>
<body>
	<p>KITRI Board Password Check</p>
	<p>코드 : ${CODE}</p>
	<p>글번호 : ${BID}</p>
	<form action="write.do" name="passChkForm" method="post">
		<input type="hidden" name="p_bid" value="${BID}">
		<p>비밀번호를 입력하세요</p>
		<p><input type="password" name="p_password"></p>
		<input type="hidden" name="p_code">
		<p><input type="button" name="p_submit_btn" value="확인" onClick="passChk('${CODE}')"></p>
	</form>
</body>
</html>