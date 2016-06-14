<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="httpRequest.js"></script>
<script type="text/javascript">

	function load(url) {
		var txt = document.getElementById("name");
		var params = "name="+encodeURIComponent(txt.value);
		sendRequest(url, params, viewMessage, 'GET');
	}
	
	function viewMessage() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				//alert(httpRequest.responseText);
				document.testForm.a.value = httpRequest.responseText;
			} else {
				alert("실패: " + httpRequest.status);
			}
		}
	}
	
</script>
</head>
<body>
<form action="" name="testForm">
	<p><input type="text" id="name"></p>
	<p><input type="button" value="simple.jsp" onclick="load('simple.jsp')"/></p>
	<p><input type="text" readonly="readonly" name="a"></p>
</form>
</body>
</html>