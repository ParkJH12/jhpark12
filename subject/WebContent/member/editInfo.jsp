<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit information</title>
<script type="text/javascript">
	function edit() {
		var form = document.joinForm
		if(form.m_num.value == "") {
			alert("학번을 입력하세요")
			form.m_num.focus()
		} else if(form.m_name.value == "") {
			alert("이름을 입력하세요")
			form.m_name.focus()
		} else if(form.m_tel.value == "") {
			alert("전화번호를 입력하세요")
			form.m_tel.focus()
		} else if(form.m_email.value == "") {
			alert("email을 입력하세요")
			form.m_email.focus()
		} else if(form.m_dept.value == "") {
			alert("학과를 입력하세요")
			form.m_dept.focus()
		} else if(form.m_type.value == "") {
			alert("신분을 선택하세요")
			form.m_type.focus()
		} else {
			form.submit()
		}
	}
	
	function keyevent() {
		if (event.keyCode == 13) {
			edit();
		}
	}
</script>
</head>
<body>
	<form action="${pageContext.request.contextPath }/MemberController" name="joinForm" method="POST">
		<p>학번: <input type="text" name="m_num" value="${MEM.num }" onKeyDown="keyevent()"></p>
		<p>이름: <input type="text" name="m_name" value="${MEM.name }" onKeyDown="keyevent()"></p>
		<p>전화번호: <input type="text" name="m_tel" value="${MEM.tel }" onKeyDown="keyevent()"></p>
		<p>E-mail: <input type="text" name="m_email" value="${MEM.email }" onKeyDown="keyevent()"></p>
		<p>부서: <input type="text" name="m_dept" value="${MEM.dept }" onKeyDown="keyevent()"></p>
		<%-- <p>신분: <input type="radio" name="m_type" value="1" checked="<c:if test="${MEM.type eq 1}">checked</c:if>"> 교직원 / <input type="radio" name="m_type" value="2" checked="<c:if test="${MEM.type eq 2}">checked</c:if>"> 교수 / <input type="radio" name="m_type" value="3" checked="<c:if test="${MEM.type eq 3}">checked</c:if>"> 학생</p> --%>
		<p>신분: <input type="radio" name="m_type" value="1" <c:if test="${MEM.type eq 1}">checked="checked"</c:if>> 교직원 / <input type="radio" name="m_type" value="2" <c:if test="${MEM.type eq 2}">checked="checked"</c:if>> 교수 / <input type="radio" name="m_type" value="3" <c:if test="${MEM.type eq 3}">checked="checked"</c:if>> 학생</p>
		<input type="hidden" name="type" value="editInfo_ok">
		<p><input type="button" value="회원정보수정" onClick="edit()"> <input type="button" value="돌아가기" onClick="history.back()"></p>
	</form>
</body>
</html>