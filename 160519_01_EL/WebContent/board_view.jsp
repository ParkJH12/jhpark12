<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>:: KITRI BOARD VIEW ::</title>
<script type="text/javascript">
	function delete_board() {
		document.viewForm.p_code.value = "delete"
		document.viewForm.submit()
	}
	
	function modify_board() {
		document.viewForm.p_code.value = "modify"
		document.viewForm.submit()
	}
	
	function like() {
		document.viewForm.action = "write.do"
		document.viewForm.p_code.value = "like"
		document.viewForm.submit()
	}
	
	function unlike() {
		document.viewForm.action = "write.do"
		document.viewForm.p_code.value = "unlike"
		document.viewForm.submit()
	}
</script>
</head>
<body>
	<p>KITRI Board View</p>
	<p>글번호: ${BDTO.board_id }</p>
	<p>제목: ${BDTO.title }</p>
	<p>내용: ${BDTO.contents }</p>
	<p>작성자: ${BDTO.writer }</p>
	<p>작성일시: ${BDTO.wdate }</p>
	<p>게시판링크: ${BDTO.pds_link }</p>
	<form action="read.do" method="POST" name="viewForm">
		<p>좋아요: ${BDTO.cont_like } <input type="button" name="p_like_btn" value="좋아요" onClick="like()"></p>
		<p>싫어요: ${BDTO.cont_unlike } <input type="button" name="p_unlike_btn" value="시러요" onClick="unlike()"></p>
		<p>조회수: ${BDTO.read_cnt }</p>
		<input type="hidden" name="p_bid" value="${BDTO.board_id}">
		<input type="hidden" name="p_code">
		<p><input type="button" name="p_delete_btn" value="글 삭제" onClick="delete_board()">
		<input type="button" name="p_modify_btn" value="글 수정" onClick="modify_board()"></p>
	</form>
	
</body>
</html>