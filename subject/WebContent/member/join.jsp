<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Join</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/httpRequest.js"></script>
<script type="text/javascript">
	function join() {
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
			if(form.isChecked.value == "true") {
				form.submit()				
			} else if(form.isChecked.value == "false") {
				alert("다른 아이디로 확인하세요")
			} else {
				alert("중복체크 하세요")
			}
		}
	}
	
	function keyevent() {
		if (event.keyCode == 13) {
			join();
		}
	}
	
	function checkID() {
		var num = document.getElementById("m_num").value
		var type = "checkID"
		var params = "m_num="+num+"&type="+type;
		sendRequest('${pageContext.request.contextPath }/MemberController', params, viewResult, 'GET')
	}
	
	function viewResult() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				if(httpRequest.responseText == 'T') {
					document.getElementById("resultSpan").innerHTML = "사용해도 좋습니다.";
					document.getElementById("isChecked").value = true;
				} else if(httpRequest.responseText == 'F') {
					document.getElementById("resultSpan").innerHTML = "중복된 아이디 입니다.";
					document.getElementById("isChecked").value = false;
				}
				
			} else {
				alert("실패: " + httpRequest.status);
			}
		}
	}
	
	function join_fail() {
		alert("중복 확인을 하세요");
	}
</script>
</head>
<body>
	<form action="${pageContext.request.contextPath }/MemberController" name="joinForm" method="POST">
		<p>학번: <input type="text" name="m_num" id="m_num" onKeyDown="keyevent()"> <input type="button" value="중복확인" onClick="checkID()"> <span id="resultSpan"></span></p>
		<p>이름: <input type="text" name="m_name" onKeyDown="keyevent()"></p>
		<p>전화번호: <input type="text" name="m_tel" onKeyDown="keyevent()"></p>
		<p>E-mail: <input type="text" name="m_email" onKeyDown="keyevent()"></p>
		<p>부서: <input type="text" name="m_dept" onKeyDown="keyevent()"></p>
		<p>신분: <input type="radio" name="m_type" value="1"> 교직원 / <input type="radio" name="m_type" value="2"> 교수 / <input type="radio" name="m_type" value="3"> 학생</p>
		<input type="hidden" name="type" value="join">
		<input type="hidden" id="isChecked" name="isChecked">
		<input type="button" value="회원가입" onclick="join()">
	</form>
</body>
</html>