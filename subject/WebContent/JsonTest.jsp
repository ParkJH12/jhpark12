<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GET JSON TEST</title>
<script type="text/javascript" src="httpRequest.js"></script>
<script type="text/javascript">
	function a() {
		sendRequest("json1.jsp", null, b, 'GET')
	}	
	
	function b() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var res = httpRequest.responseText;
				var o = eval("("+res+")");		// 자동으로 객체형태로 묶어준다
				var html = "";
				
				for(i = 0; i < o.length; i++) {
					html += "num: " + o[i].num + ", name: " + o[i].name + ", tel: " + o[i].tel + "\n";
				}
				
				alert(html);
			}
		}
	}
</script>
</head>
<body>
<input type="button" value="클릭" onclick="a()">
</body>
</html>