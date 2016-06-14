<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>:: KITRI BOARD LIST ::</title>
<script type="text/javascript">
	function write_board() {
		location.href="read.do?p_code=write"
	}
</script>
</head>
<body>
	<p>KITRI Board List</p>
	<table>
		<tr>
			<th>게시글 번호</th>
			<th>게시글 제목</th>
			<th>작성자</th>
			<th>작성일자</th>
			<th>조회수</th>
			<th>추천수</th>
		</tr>
		<c:forEach var="item" items="${BL}" varStatus="status">
		<tr>
			<td>${item.board_id}</td>
			<td><a href="read.do?p_code=contents&b_id=${item.board_id}">${item.title}</a></td>
			<td>${item.writer}</td>
			<td>${item.wdate}</td>
			<td>${item.read_cnt}</td>
			<td>${item.cont_like}</td>
		</tr>
		</c:forEach>
		
	</table>
	<p><input type="button" name="p_write_btn" value="글쓰기" onClick="write_board()"></p>
</body>
</html>