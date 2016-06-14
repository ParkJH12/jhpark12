<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<script type="text/javascript">
	
	
	function b_modify(){
		var v_form = document.form_view;
		// 수정할 수 있도록 구성
		// p_code = modify;
		v_form.p_code.value="modify";
		// action = read.do
		v_form.action="read.do";
		v_form.submit();
	}
	
	function b_delete(){
		var v_form = document.form_view;
		// p_code = modify;
		v_form.p_code.value="delete";
		// action = read.do
		v_form.action="read.do";
		v_form.submit();
	}
	
	function b_reply(){
		var v_form = document.form_view;
		// p_code = modify;
		v_form.p_code.value="reply";
		// action = read.do
		v_form.action="read.do";
		v_form.submit();
	}

</script>
<title>:: 글 보기 ::</title>
</head>
<body>
<table border="1">
	<tr>
		<th>글번호</th>
		<th>글제목</th>
		<th>작성자</th>
		<th>조회수</th>
		<th>작성일자</th>
	</tr>
	<tr>
		<td>${BDTO.board_id}</td>
		<td>${BDTO.title}</td>
		<td>${BDTO.writer}</td>
		<td>${BDTO.read_cnt}</td>
		<td>${BDTO.wdate}</td>		
	</tr>
	<tr>
		<th colspan="3">추천</th>
		<th colspan="2">비추</th>
	</tr>
	<tr>
		<td colspan="3">${BDTO.con_like}</td>
		<td colspan="2">${BDTO.con_unlike}</td>
	</tr>
	<tr>
		<th colspan="5">첨부파일</th>		
	</tr>
	<tr>
		<td colspan="5">		
			<c:choose>
			 	<c:when test="${BDTO.pds_link eq null}">첨부 파일 없음</c:when>
				<c:otherwise>${BDTO.pds_link}</c:otherwise>
			</c:choose>
		</td>		
	</tr>
	<tr>
		<th>본문 내용</th>
		<td><textarea rows="60" cols="80" readonly="readonly">${BDTO.contents}</textarea> </td>
	</tr>
</table>
<form name="form_view" method="post">
<p><input type="button" value="댓글달기" onclick="b_reply()"> </p>
<p><input type="button" value="수정하기" onclick="b_modify()"> </p>
<p><input type="button" value="삭제하기" onclick="b_delete()"> </p>
<input type="hidden" name="p_code">
<input type="hidden" name="p_bid" value="${BDTO.board_id}">
</form>
</body>
</html>