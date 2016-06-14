<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function redirect(cnt) {
		alert(cnt + "건의 자료가 수정되었습니다.")
		location.href="login.do"
	}
</script>
</head>
<body onLoad="redirect(${cnt})">

</body>
</html>