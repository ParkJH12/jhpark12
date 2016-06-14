<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>using dom</title>
<script type="text/javascript">
	function log(msg) {
		var console = document.getElementById("debugConsole");
		if (console != null) {
			console.innerHTML += msg + "<br />";
		}
	}
	
	window.onload = function() {
		var rootNode = document.documentElement;
		log("root 태그: " + rootNode.tagName);
		
		var bodyNode = document.getElementsByTagName("body").item(0);
		log("body 태그: " + bodyNode.tagName);
		
		var spanList = document.getElementsByTagName("span");
		log("span 태그의 개수: " + spanList.length);
		for(var i = 0; i < spanList.length; i++) {
			var span = spanList.item(i);
			log((i+1)+"번째 span의 id: " + span.getAttribute("id"));
		}
		
		var debugConsoleDiv = document.getElementById("debugConsole");
		log("debugConsole 요소: " + debugConsoleDiv.tagName);
		var bodyLastChild = bodyNode.lastChild;
		log("body의 마지막 자식 노드: " + bodyLastChild.nodeName);
	}
</script>
</head>
<body>
	<span id="a">a</span>
	<p>test<span id="b">b</span></p>
	<div><p>p</p><span id="c">c</span></div>
	<div id="debugConsole" style="border: 1px solid #000"></div>
</body>
</html>