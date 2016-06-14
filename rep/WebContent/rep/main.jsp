<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 실습</title>
<script type="text/javascript" src="../httpRequest.js"></script>
<script type="text/javascript">
	// Div를 추가하는 메서드(댓글이 추가됨)
	function makeRepDiv(rep) {
		var RepDiv = document.createElement('div');
		RepDiv.setAttribute('id', 'rep_'+rep.num);
		var html = "이름: " + rep.name + " 내용: " + rep.content + " <input type='button' value='수정' onclick='showUpdateRepForm(" + rep.num + ")'> <input type='button' value='삭제' onclick='deleteRep(" + rep.num + ")'><br />"
		RepDiv.innerHTML = html;
		var viewReply = document.getElementById('viewReply');
		viewReply.appendChild(RepDiv)
	}

	// 댓글의 내용을 수정하는 메서드
	function replaceDiv(rep) {
		var RepDiv = document.getElementById('rep_' + rep.num);
		var html = "이름: " + rep.name + " 내용: " + rep.content + " <input type='button' value='수정' onclick='showUpdateRepForm(" + rep.num + ")'> <input type='button' value='삭제' onclick='deleteRep(" + rep.num + ")'><br />"
		RepDiv.innerHTML = html;
	}
	
	// 댓글을 삭제하는 메서드
	function removeDiv(num) {
		var viewReply = document.getElementById('viewReply');
		var RepDiv = document.getElementById('rep_' + num);
		viewReply.removeChild(RepDiv);
	}

	// 페이지가 로드될때 실행되는 메서드 -> 현재 저장된 댓글을 모두 불러옴 -> viewReply() 메서드 호출
	window.onload = function() {
		sendRequest("${pageContext.request.contextPath}/RepController", "type=getAll", viewReply, 'GET');
	}
	
	// 현재 저장된 댓글을 모두 화면에 표시
	function viewReply() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var replyResult = httpRequest.responseText;
				var replyList = eval("("+ replyResult +")");
				var message = "";
				
				for(var i = 0; i < replyList.length; i++) {
					makeRepDiv(replyList[i]);
				}
			}
		}
	}
	
	// 댓글쓰기 메서드 -> viewNewReply() 메서드 호출
	function writeReply() {
		var type = "addRep";
		var name = document.f.name.value;
		var content = document.f.content.value;
		var params = "type="+type+"&r_name="+name+"&r_content="+content;
		sendRequest("${pageContext.request.contextPath}/RepController", params, viewNewReply, 'POST');
	}
	
	// 새로 쓴 댓글을 화면에 추가
	function viewNewReply() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var replyResult = httpRequest.responseText;
				var reply = eval("("+ replyResult +")");
				
				makeRepDiv(reply)
			}
		}
	}
	
	// 수정 폼을 해당 댓글의 아래에 출력
	function showUpdateRepForm(num) {
		if(document.getElementById('updateForm_' + num) == null) {
			var updateForm = document.createElement('div');
			updateForm.setAttribute('id', 'updateForm_' + num);
			var html = "이름: <input type='text' id='r_name_"+ num +"'> 내용: <input type='text' id='r_content_" + num +"'> <input type='button' value='수정하기' onclick='updateRep("+ num +")'> <input type='button' value='취소' onclick='cancelUpdate(" + num +")'>"
			updateForm.innerHTML = html;
			
			var selectedRep = document.getElementById('rep_' + num);
			selectedRep.appendChild(updateForm)
		}
	}
	
	// 수정 폼에서 '취소'를 누르면 수정폼이 사라지게 하는 메서드
	function cancelUpdate(num) {
		var updateForm = document.getElementById('updateForm_' + num);
		updateForm.parentNode.removeChild(updateForm)
	}
	
	// 수정 폼에서 '수정 하기'를 누르면 입력된 내용 대로 수정하게 하는 메서드 -> viewUpdatedRep() 호출
	function updateRep(num) {
		var name = document.getElementById('r_name_' + num).value;
		var content = document.getElementById('r_content_' + num).value;
		var params = "type=updateRep&r_num=" + num + "&r_name=" + name + "&r_content=" + content;
		sendRequest("${pageContext.request.contextPath}/RepController", params, viewUpdatedRep, 'POST');
	}
	
	// 수정한 댓글의 위치에서 내용을 바꾸는 메서드
	function viewUpdatedRep() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var replyResult = httpRequest.responseText;
				var reply = eval("("+ replyResult +")");
				
				replaceDiv(reply);
			}
		}
	}
	
	
	// '삭제'를 누르면 댓글이 삭제되게 하는 메서드 -> viewDeletedRep() 호출
	function deleteRep(num) {
		var params = "type=deleteRep&r_num=" + num
		sendRequest("${pageContext.request.contextPath}/RepController", params, viewDeletedRep, 'POST');
	}
	
	// 삭제한 댓글을 화면에서 제거
	function viewDeletedRep() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var deleteResult = httpRequest.responseText;
				var result = eval("(" + deleteResult +")")
				
				if(result.isDeleted == true) {
					removeDiv(result.num)			
				} else {
					alert("삭제 실패")
				}
			}
		}
	}
</script>
</head>
<body>
<form name="f">
	<table>
		<tr><th>name</th><td><input type="text" name="name"></td></tr>
		<tr><th>content</th><td><input type="text" name="content"></td></tr>
		<tr><td colspan="2"><input type="button" value="작성" onclick="writeReply()"></td></tr>
	</table>
</form>
	
<div id="viewReply">
</div>
</body>
</html>