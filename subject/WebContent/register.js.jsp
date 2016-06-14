<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>	
	// 현재 버튼을 눌렀는지 확인하는 전역변수
	var isShown = false
	
	// div를 만들어 파라미터로 받은 id를 갖는 object의 자식노드로 추가하는 메서드
	
	
	function makeObject() {
		var length = arguments.length;
		var targetID = arguments[0];
		var type = arguments[1];
		var newID = arguments[2];
		var content = arguments[3];

		var obj = document.createElement(type);
		if(newID != null) {
			obj.setAttribute('id', newID);
		}
		
		// 추가 option과 관련된 arguments가 존재하면
		if(length > 5) {
			for(var i = 4; i < length; i++) {
				obj.setAttribute(arguments[i][0], arguments[i][1])
			}
		}
		
		obj.innerHTML = content;
		var target = document.getElementById(targetID);
		target.appendChild(obj)
	}
	
	// 버튼을 만드는 문자열을 반환하는 메서드
	function makeButton(name, onClickMethodName, param) {
		return "<input type='button' value='"+name+"' onclick='"+onClickMethodName+"("+param+")'>"
	}
	
	// 오브젝트 삭제
	function removeObject(targetID, parentIDOfTarget) {
		var target = document.getElementById(targetID);
		var parent = document.getElementById(parentIDOfTarget);
		parent.removeChild(target);
	}
	
	function showRegister() {
		if(!isShown) {
			isShown = true
			// 강의 리스트 출력
			makeObject('main', 'div', 'openList', null)
			makeObject('openList', 'h2', null, '전체 강의 목록')
			makeObject('openList', 'table', 'openListTable', null)
			makeObject('openListTable', 'tr', 'openColumns', null)
			makeObject('openColumns', 'th', null, '강의번호')
			makeObject('openColumns', 'th', null, '과목명')
			makeObject('openColumns', 'th', null, '강의요일')
			makeObject('openColumns', 'th', null, '강의시각')
			makeObject('openColumns', 'th', null, '교수명')
			//makeObject('columns', 'th', 'score', '학점')
			//makeObject('columns', 'th', 'limitNum', '총 인원')
			//makeObject('columns', 'th', 'currNum', '현재 수강 인원')
			//makeObject('columns', 'th', 'remainNum', '잔여 인원')
			makeObject('openColumns', 'th', null, '수강신청')
						
			// 강의 예시 출력
			/* makeObject('openListTable', 'tr', 'example', null)
			makeObject('example', 'td', null, '1')
			makeObject('example', 'td', null, '알고리즘')
			makeObject('example', 'td', null, '월요일/수요일')
			makeObject('example', 'td', null, '09:00-10:00')
			makeObject('example', 'td', null, '김알고')
			makeObject('example', 'td', null, makeButton('신청', 'example', 0)) */
			
			// 시간표 출력
			makeObject('main', 'div', 'timeTable', null)
			makeObject('timeTable', 'h2', null, '시간표')
			
			// 내 수강 목록 출력
			makeObject('main', 'div', 'myList', null)
			makeObject('myList', 'h2', null, '내 수강신청 목록')
			makeObject('myList', 'table', 'myListTable', null)
			makeObject('myListTable', 'tr', 'myColumns', null)
			makeObject('myColumns', 'th', null, '강의번호')
			makeObject('myColumns', 'th', null, '과목명')
			makeObject('myColumns', 'th', null, '교수명')
			//makeObject('columns', 'th', 'score', '학점')
			makeObject('myColumns', 'th', null, '수강취소')
			
			// 내 수강 예시 출력
			/* makeObject('myListTable', 'tr', 'myExample', null)
			makeObject('myExample', 'td', null, '1')
			makeObject('myExample', 'td', null, '알고리즘')
			makeObject('myExample', 'td', null, '김알고')
			makeObject('myExample', 'td', null, makeButton('취소', 'myExample', 0)) */
			
			
			
			// 개설된 강의 검색 -> viewOpens() 메서드 호출
			sendRequest("${pageContext.request.contextPath}/RegisterController", "type=getOpens", viewOpens, 'POST');
		}
	}
	
	// 전체 출력
	function viewOpens() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var openResult = httpRequest.responseText;
				var openList = eval("("+ openResult +")");
				
				// 모든 강좌 출력
				for(var i = 0; i < openList[0].length; i++) {
					makeObject('openListTable', 'tr', 'open_'+openList[0][i].num, null)
					makeObject('open_'+openList[0][i].num, 'td', null, openList[0][i].num)
					makeObject('open_'+openList[0][i].num, 'td', null, openList[0][i].name)
					makeObject('open_'+openList[0][i].num, 'td', null, openList[0][i].days)
					makeObject('open_'+openList[0][i].num, 'td', null, openList[0][i].times)
					makeObject('open_'+openList[0][i].num, 'td', null, openList[0][i].profName)
					makeObject('open_'+openList[0][i].num, 'td', 'openBtn_'+openList[0][i].num, makeButton('신청', 'applyOpen', openList[0][i].num))
				}
				
				// 내가 신청한 강좌 출력
				for(var i = 0; i < openList[1].length; i++) {
					makeObject('myListTable', 'tr', 'mine_'+openList[1][i].num, null)
					makeObject('mine_'+openList[1][i].num, 'td', null, openList[1][i].num)
					makeObject('mine_'+openList[1][i].num, 'td', null, openList[1][i].name)
					makeObject('mine_'+openList[1][i].num, 'td', null, openList[1][i].profName)
					makeObject('mine_'+openList[1][i].num, 'td', null, makeButton('취소', 'cancelOpen', openList[1][i].num))
					
					var obj = document.getElementById('openBtn_' + openList[1][i].num)
					obj.innerHTML = '신청 됨'
				}
			}
		}
	}
	
	// 수강신청
	function applyOpen(num) {
		sendRequest("${pageContext.request.contextPath}/RegisterController", "type=applyOpen&o_num=" + num, viewNewOpen, 'POST');
	}
	
	// 신청되었을 때 내 목록에 새 강의 추가 및 기존 강의수강 버튼 비활성화
	function viewNewOpen() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var openResult = httpRequest.responseText;
				var open = eval("("+ openResult +")");
				
				makeObject('myListTable', 'tr', 'mine_'+open.num, null)
				makeObject('mine_'+open.num, 'td', null, open.num)
				makeObject('mine_'+open.num, 'td', null, open.name)
				makeObject('mine_'+open.num, 'td', null, open.profName)
				makeObject('mine_'+open.num, 'td', null, makeButton('취소', 'cancelOpen', open.num))
				
				var obj = document.getElementById('openBtn_' + open.num)
				obj.innerHTML = '신청 됨'
			}
		}
	}
	
	// 수강취소
	function cancelOpen(num) {
		sendRequest("${pageContext.request.contextPath}/RegisterController", "type=cancelOpen&o_num=" + num, unviewOldOpen, 'POST');
	}
	
	// 취소된 강의 제거 및 기존 목록에 '강의수강'버튼 부활
	function unviewOldOpen() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var openNum = httpRequest.responseText;
				
				removeObject('mine_' + openNum, 'myListTable')
				
				var obj = document.getElementById('openBtn_' + openNum)
				obj.innerHTML = makeButton('신청', 'applyOpen', openNum)
			}
		}
	}