<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/httpRequest.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/getMember.js.jsp"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/register.js.jsp"></script>
<script type="text/javascript">
	var isFormOpened = false;
	
	function showOpens() {
		if(!isShown) {
			isShown = true
			// 과목 리스트 출력 (부모객체, 타입, 자식객체, 출력내용)
			makeObject('main', 'div', 'profList', null)
			makeObject('profList', 'table', 'profTable', null)
			makeObject('profTable', 'tr', 'line1', null)
			
			// 내 개설 목록 출력
			makeObject('line1', 'td', 'mysubform', null)
			makeObject('mysubform', 'div', 'mySubjectList', null)
			makeObject('mySubjectList', 'h2', null, '내 개설 목록')
			makeObject('mySubjectList', 'table', 'mySubjectListTable', null)
			makeObject('mySubjectListTable', 'tr', 'myColumns', null)
			makeObject('myColumns', 'th', null, '과목번호')
			makeObject('myColumns', 'th', null, '과목명')
			makeObject('myColumns', 'th', null, '정보보기')
			makeObject('myColumns', 'th', null, '개설취소')
			
			// 내 개설목록 예시
			/* makeObject('mysubjectListTable', 'tr', 'myExample', null)
			makeObject('myExample', 'td', null, '1')
			makeObject('myExample', 'td', null, '알고리즘')
			makeObject('myExample', 'td', null, makeButton('정보수정', 'editOpen', 0))
			makeObject('myExample', 'td', null, makeButton('개설취소', 'removeOpen', 0)) */
			
			
			makeObject('line1', 'td', 'subjectform', null)
			makeObject('subjectform', 'div', 'subjectList', null)
			makeObject('subjectList', 'h2', null, '전체 과목 목록')
			makeObject('subjectList', 'table', 'subjectListTable', null)
			makeObject('subjectListTable', 'tr', 'subjectColumns', null)
			makeObject('subjectColumns', 'th', null, '과목번호')
			makeObject('subjectColumns', 'th', null, '과목명')
			makeObject('subjectColumns', 'th', null, '개설여부')
			//makeObject('columns', 'th', 'score', '학점')
			//makeObject('columns', 'th', 'limitNum', '총 인원')
			//makeObject('columns', 'th', 'currNum', '현재 수강 인원')
			//makeObject('columns', 'th', 'remainNum', '잔여 인원')
			makeObject('subjectColumns', 'th', null, '개설')
						
			// 개설 예시 출력
			/* makeObject('subjectListTable', 'tr', 'example', null)
			makeObject('example', 'td', null, '1')
			makeObject('example', 'td', null, '알고리즘')
			makeObject('example', 'td', null, 'No')
			makeObject('example', 'td', null, makeButton('개설', 'myExample', 0)) */
			
			

			//강의 개설
			makeObject('profTable', 'tr', 'line2', null)
			makeObject('line2', 'td', 'editform', null)
			makeObject('editform', 'div', 'editOpen', null)
			makeObject('editOpen', 'h2', null, '강의 정보')
			makeObject('editOpen', 'form', 'editForm', null, ['action', '${pageContext.request.contextPath }/MemberController'])
			makeObject('editForm', 'p', 'room', '강의실: ')
			
			/* makeObject('room', 'select', 'building', null)
			makeObject('building', 'option', null, '건물 선택', ['value', ''])
			makeObject('building', 'option', null, 'A', ['value', 'A'])
			makeObject('building', 'option', null, 'B', ['value', 'B'])
			makeObject('building', 'option', null, 'C', ['value', 'C'])
			makeObject('building', 'option', null, 'D', ['value', 'D'])
			makeObject('room', 'select', 'roomNum', null)
			
			makeObject('roomNum', 'option', null, '강의실 선택', ['value', ''])
			for(var i = 1; i < 5; i++) {
				for(var j = 1; j < 5; j++) {
					makeObject('roomNum', 'option', null, i + '0' + j, ['value', i + '0' + j])					
				}
			} */
			
			makeObject('room', 'input', null, null, ['type', 'text'], ['name', 'room'])
			
			makeObject('editForm', 'p', 'day', '강의 요일: ')
			/* makeObject('day', 'select', 'firstDay', null)
			makeObject('firstDay', 'option', null, '필수입력', ['value', 'MON'])
			makeObject('firstDay', 'option', null, '월', ['value', 'MON'])
			makeObject('firstDay', 'option', null, '화', ['value', 'TUE'])
			makeObject('firstDay', 'option', null, '수', ['value', 'WED'])
			makeObject('firstDay', 'option', null, '목', ['value', 'THU'])
			makeObject('firstDay', 'option', null, '금', ['value', 'FRI'])
			makeObject('day', 'select', 'secondDay', null)
			makeObject('secondDay', 'option', null, '선택입력', ['value', ''])
			makeObject('secondDay', 'option', null, '월', ['value', 'MON'])
			makeObject('secondDay', 'option', null, '화', ['value', 'TUE'])
			makeObject('secondDay', 'option', null, '수', ['value', 'WED'])
			makeObject('secondDay', 'option', null, '목', ['value', 'THU'])
			makeObject('secondDay', 'option', null, '금', ['value', 'FRI']) */
			makeObject('day', 'input', null, null, ['type', 'text'], ['name', 'days'])
			
			makeObject('editForm', 'p', 'time', '강의 시각: ')
			/* makeObject('time', 'select', 'firstTimeStart', null)
			makeObject('firstTimeStart', 'option', null, '시작시간', ['value', ''])
			for(var i = 1; i < 10; i++) {
				makeObject('firstTimeStart', 'option', null, i+'교시', ['value', i])
			}
			makeObject('time', 'select', 'firstTimeEnd', null)
			makeObject('firstTimeEnd', 'option', null, '종료시간', ['value', ''])
			for(var i = 1; i < 10; i++) {
				makeObject('firstTimeEnd', 'option', null, i+'교시', ['value', i])
			}
			
			makeObject('time', 'select', 'secondTimeStart', null)
			makeObject('secondTimeStart', 'option', null, '시작시간', ['value', ''])
			for(var i = 1; i < 10; i++) {
				makeObject('secondTimeStart', 'option', null, i+'교시', ['value', i])
			}
			makeObject('time', 'select', 'secondTimeEnd', null)
			makeObject('secondTimeEnd', 'option', null, '종료시간', ['value', ''])
			for(var i = 1; i < 10; i++) {
				makeObject('secondTimeEnd', 'option', null, i+'교시', ['value', i])
			} */
			makeObject('time', 'input', null, null, ['type', 'text'], ['name', 'time'])
			makeObject('editForm', 'input', null, null, ['type', 'hidden'], ['name', 'subjectNum'])
			makeObject('editForm', 'input', null, null, ['type', 'hidden'], ['name', 'num'])
			document.getElementById('editOpen').style.display='none'
			
			// 과목 검색 및 내가 개설한 목록 출력 -> viewSubjects() 메서드 호출
			sendRequest("${pageContext.request.contextPath}/RegisterController", "type=getSubjects", viewSubjects, 'POST');
		}
	}
	
	// 과목과 내가 개설한 강의 출력
	function viewSubjects() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var subjectResult = httpRequest.responseText;
				var subjectList = eval("("+ subjectResult +")");
				
				// 모든 과목 출력
				for(var i = 0; i < subjectList[0].length; i++) {
					makeObject('subjectListTable', 'tr', 'subject_'+subjectList[0][i].num, null)
					makeObject('subject_'+subjectList[0][i].num, 'td', null, subjectList[0][i].num)
					makeObject('subject_'+subjectList[0][i].num, 'td', null, subjectList[0][i].name)
					makeObject('subject_'+subjectList[0][i].num, 'td', null, subjectList[0][i].flag)
					makeObject('subject_'+subjectList[0][i].num, 'td', 'subjectBtn_'+subjectList[0][i].num, makeButton('개설', 'showCreateOpenForm', subjectList[0][i].num))
				}
				
				// 내가 개설한 강좌 출력
				for(var i = 0; i < subjectList[1].length; i++) {
					makeObject('mySubjectListTable', 'tr', 'mine_'+subjectList[1][i].num, null)
					makeObject('mine_'+subjectList[1][i].num, 'td', null, subjectList[1][i].num)
					makeObject('mine_'+subjectList[1][i].num, 'td', null, subjectList[1][i].subjectName)
					makeObject('mine_'+subjectList[1][i].num, 'td', null, makeButton('정보보기', 'editOpen', subjectList[1][i].num))
					makeObject('mine_'+subjectList[1][i].num, 'td', null, makeButton('개설취소', 'removeOpen', subjectList[1][i].num))
				}
			}
		}
	}
	
	// '개설' 버튼 클릭시 개설과 관련한 정보 입력 폼 출력
	function showCreateOpenForm(num) {
		var form = document.getElementById('editForm')
		form.num.value = ''
		form.room.value = ''
		form.days.value = ''
		form.time.value = ''
		form.subjectNum.value = '' 
		if(isFormOpened) {
			form.removeChild(form.lastChild)
		}
		isFormOpened = true
		document.getElementById('editOpen').style.display=''
		document.getElementById('editForm').subjectNum.value = num
		makeObject('editForm', 'p', 'btn', makeButton('개설하기', 'createOpen', ''))
	}
	
	// 개설 정보 입력폼에서 '개설하기' 버튼 수행 메서드
	function createOpen() {
		var element = document.getElementById('editForm')
		var room = element.room.value
		var days = element.days.value
		var time = element.time.value
		var subjectNum = element.subjectNum.value
		var params = "type=createOpen&o_room="+room+"&o_days="+days+"&o_time="+time+"&o_subNum="+subjectNum;
		
		var div = document.getElementById('editOpen')
		div.style.display = 'none'
		var form = div.lastChild 
		form.removeChild(form.lastChild)
		isFormOpened = false
			
		sendRequest("${pageContext.request.contextPath}/RegisterController", params, addMyList, 'POST');
	}
	
	// 내 개설 목록 리스트에 새로 개설한 강의 추가
	function addMyList() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var openResult = httpRequest.responseText;
				var newOpen = eval("("+ openResult +")");
				
				makeObject('mySubjectListTable', 'tr', 'mine_' + newOpen.num, null)
				makeObject('mine_'+newOpen.num, 'td', null, newOpen.num)
				makeObject('mine_'+newOpen.num, 'td', null, newOpen.subjectName)
				makeObject('mine_'+newOpen.num, 'td', null, makeButton('정보수정', 'editOpen', newOpen.num))
				makeObject('mine_'+newOpen.num, 'td', null, makeButton('개설취소', 'removeOpen', newOpen.num))
				
				document.getElementById('subject_' + newOpen.subjectNum).childNodes[2].innerHTML = 'true'
			}
		}
	}
	
	// 개설 목록중 정보 수정시 호출되는 메서드
	function editOpen(num) {
		var form = document.getElementById('editForm')
		form.num.value = ''
		form.room.value = ''
		form.days.value = ''
		form.time.value = ''
		form.subjectNum.value = ''
		if(isFormOpened) {
			form.removeChild(form.lastChild)
		}
		sendRequest("${pageContext.request.contextPath}/RegisterController", "type=getOpen&o_num="+num, showEditOpenForm, 'POST');
	}
	
	// 수정을 위한 강의 정보를 정보 입력폼에 출력
	function showEditOpenForm() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				document.getElementById('editOpen').style.display=''
				makeObject('editForm', 'p', 'btn', makeButton('수정하기', 'editOpen_ok', ''))
				
				var form = document.getElementById('editForm')
				var openResult = httpRequest.responseText;
				var open = eval("("+ openResult +")");
				
				form.num.value = open.num
				form.room.value = open.room
				form.days.value = open.subDay
				form.time.value = open.subTime
				form.subjectNum.value = open.subjectNum
			}
		}
	}
	
	// 정보 입력폼에서 '수정하기'를 누르면 호출되는 메서드
	function editOpen_ok() {
		var element = document.getElementById('editForm')
		var num = element.num.value
		var room = element.room.value
		var days = element.days.value
		var time = element.time.value
		var subjectNum = element.subjectNum.value
		var params = "type=editOpen&o_num="+num+"&o_room="+room+"&o_days="+days+"&o_time="+time+"&o_subNum="+subjectNum;
		
		var div = document.getElementById('editOpen')
		div.style.display = 'none'
		var form = div.lastChild 
		form.removeChild(form.lastChild)
		isForOpened = false
		
		sendRequest("${pageContext.request.contextPath}/RegisterController", params, null, 'POST');
	}
	
	// 개설 취소버튼을 눌렀을때 호출되는 메서드
	function removeOpen(num) {
		sendRequest("${pageContext.request.contextPath}/RegisterController", "type=removeOpen&o_num="+num, removeMyList, 'POST');
	}
	
	// 내 개설 목록에서 개설 취소된 강의를 지워주는 메서드
	function removeMyList() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var oldOpenResult = httpRequest.responseText;
				var oldOpen = eval('('+oldOpenResult+')')
				
				var element = document.getElementById('mine_' + oldOpen.openNum)
				element.parentNode.removeChild(element)
				document.getElementById('subject_' + oldOpen.subNum).childNodes[2].innerHTML = oldOpen.isOpened
			}
		}
	}
</script>
<style type="text/css">
	table {
		border: 1px;
	}
	tr {
		height: 30px;
	}
	td:HOVER {
		background-color:#f5f5f5
	}
	#openList {
		float: left;
		width: 50%;
		height: auto;
	}
	
	#myList, #timeTable {
		float: right;
		width: 50%;
		margin-bottom: 10px;
	}
	
	#timeTable {
		height: 400px;
	}
	#subjectform {
		overflow:auto;
	}
	#mysubform {
		overflow:auto;
	}
	#profTable {
		width:100%;
		height:auto;
	}
</style>
</head>
<body>
	<form action="${pageContext.request.contextPath }/MemberController" name="mainForm">
		<input type="hidden" name="type">
		<input type="button" value="회원정보수정" onClick="editInfo()">
		<input type="button" value="회원탈퇴" onClick="delete_mem()">
		<input type="button" value="로그아웃" onClick="logout()">
		<input type="button" value="전체회원보기" onclick="showAllMembers()">

		<c:if test="${TYPE eq 1 }"><input type="button" value="과목관리" onclick=""></c:if>
		<c:if test="${TYPE eq 2 }"><input type="button" value="강의관리" onclick="showOpens()"></c:if>
		<c:if test="${TYPE eq 3 }"><input type="button" value="수강신청" onclick="showRegister()"></c:if>

		
	</form>
	
	<table id="viewMembers">
	</table>
	
	<div id="main">
	</div>
</body>
</html>