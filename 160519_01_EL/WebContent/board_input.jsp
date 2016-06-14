<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>
	<c:choose>
		<c:when test="${BDTO ne null}">:: 글 수정 ::</c:when>
		<c:otherwise>:: 글 작성 ::</c:otherwise>
	</c:choose>
</title>
<script type="text/javascript">
	function isModify(code) {
		if(code == "modify") {
			document.writeForm.p_writer.value = '${BDTO.writer}'
			document.writeForm.p_title.value = "${BDTO.title}"
			document.writeForm.p_contents.value = '${BDTO.contents}'
			document.writeForm.p_link.value = '${BDTO.pds_link}'
			document.writeForm.p_bid.value = '${BDTO.board_id}'
			document.writeForm.p_code.value = "modify"
			document.writeForm.p_submit_bt.value = "수정"
		}
	}
	
	function submit_form() {
		var form = document.writeForm
		
		if(form.p_writer.value == "") {
			alert("작성자를 입력하세요")
			form.p_writer.focus()
		} else if(form.p_password.value == "") {
			alert("비밀번호를 입력하세요")
			form.p_password.focus()
		} else if(form.p_title.value == "") {
			alert("제목을 입력하세요")
			form.p_title.focus()
		} else if(form.p_contents.value == "") {
			alert("내용을 입력하세요")
			form.p_contents.focus()
		} else {
			form.submit()
		}
	}
</script>
</head>
<body onLoad="isModify('${CODE}')">
	<form action="write.do" name="writeForm" method="POST">
		<p>작성자: <input type="text" name="p_writer"></p>
		<p>비밀번호: <input type="password" name="p_password"></p>
		<p>제목: <input type="text" name="p_title"></p>
		<p>내용: <textarea rows="100" cols="100" name="p_contents"></textarea></p>
		<p>첨부주소: <input type="text" name="p_link"></p>
		<input type="hidden" name="p_bid">
		<input type="hidden" name="p_code" value="write">
		<p><input type="button" name="p_submit_bt" value="글쓰기" onClick="submit_form()"></p>
	</form>
</body>
</html>