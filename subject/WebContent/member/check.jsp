<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Check ID</title>
<script type="text/javascript">
	function keyevent() {
		if (event.keyCode == 13) {
			check()
		}
	}
	
	function check() {
		var form = document.checkForm
		if(form.m_num.value == "") {
			alert("학번을 입력하세요.")
			form.m_num.focus()
		} else {
			form.type.value="checkID"
			form.submit()
		}
	}
	
	function reload(isOk) {
		if(isOk == "T") {
			// 사용해도 괜찮으면
			alert("사용해도 좋습니다.")
		} else if(isOk == "F"){
			// 사용 불가능하면
			alert("다른 학번을 사용하세요.")
		}
	}
	
	function useNum(isOk) {
		var form = document.checkForm
		if(isOk == "T") {
			opener.document.joinForm.m_num.value=form.m_num.value;
			window.self.close()
		} else if(isOk == "F") {
			alert("다시 중복확인을 하세요.")
		} else {
			alert("중복확인을 눌러주세요.")
		}
	}
	
</script>
</head>
<body onload="reload('${ISOK}')">
<form action="${pageContext.request.contextPath }/MemberController" name="checkForm">
	<input type="hidden" name="type">
	<p>학번을 입력하세요: <input type="text" name="m_num" onkeydown="keyevent()" value="${NUM }"> <input type="button" value="중복확인" onclick="check()"></p>
	<p><input type="button" value="번호사용" onclick="useNum('${ISOK}')"></p>
</form>
</body>
</html>