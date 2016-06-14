<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	function editInfo() {
		document.mainForm.type.value = "editInfo"
		document.mainForm.submit()
	}
	
	function delete_mem() {
		document.mainForm.type.value = "delete"
		document.mainForm.submit()
	}
	
	function logout() {
		document.mainForm.type.value = "logout"
		document.mainForm.submit()
	}
	
	function showAllMembers() {
//		xml로 요청
//		var params = "type=getMembersByXML";
//		sendRequest('${pageContext.request.contextPath }/MemberController', params, printAllMembersByXML, 'GET')

//		JSON으로 요청
		var params = "type=getMembersByJSON";
		sendRequest('${pageContext.request.contextPath }/MemberController', params, printAllMembersByJSON, 'GET')
	}
	
	function printAllMembersByXML() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var xmlDoc = httpRequest.responseXML;
				var memberList = xmlDoc.getElementsByTagName("member");
				
				for(var i = 0; i < memberList.length; i++) {
					var member = memberList.item(i);
					var num = member.getElementsByTagName("num").item(0).firstChild.nodeValue;
					var name = member.getElementsByTagName("name").item(0).firstChild.nodeValue;
					var tel = member.getElementsByTagName("tel").item(0).firstChild.nodeValue;
					var email = member.getElementsByTagName("email").item(0).firstChild.nodeValue;
					var dept = member.getElementsByTagName("dept").item(0).firstChild.nodeValue;
					var type = member.getElementsByTagName("type").item(0).firstChild.nodeValue;
					outMsg("viewMembers", "num: " + num + " name: " + name + " tel: " + tel + " email: " + email + " dept: " + dept + " type: " + type);
				}
			}
		}
	}
	
	function printAllMembersByJSON() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var jsonResult = httpRequest.responseText;
				var memberList = eval("(" + jsonResult + ")")
				var message = "";
				
				for(var i = 0; i < memberList.length; i++) {
					var member = memberList[i];
					var num = member.num;
					var name = member.name;
					var tel = member.tel;
					var email = member.email;
					var dept = member.dept;
					var type = member.type;
					message += "<tr><td onmouseover='getDetails(" + num + ")' onmouseout='removeDetails("+num+")'>" + name + "</td> <td id='viewDetails" + num + "'></td></tr>";
				}
				
				outMsg("viewMembers", message)
			}
		}
	}
	
	function getDetails(num) {
		var params = "type=getDetails&m_num="+num;
		sendRequest('${pageContext.request.contextPath }/MemberController', params, printDetails, 'GET')
	}
	
	function printDetails() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var detailResult = httpRequest.responseText;
				var details = eval("(" + detailResult +")")
				var message = ""
				
				var num = details.num;
				var name = details.name;
				var tel = details.tel;
				var email = details.email;
				var dept = details.dept;
				var type = "";
				if(details.type == 1) {
					type = "교직원";
				} else if(details.type == 2) {
					type = "교수";
				} else if(details.type == 3) {
					type = "학생"
				}
				message += "----> 학번: " + num + " / 이름: " + name + " / 전화번호: " + tel + " / email: " + email + " / 전공: " + dept + " / 신분: " + type;
				
				outMsg("viewDetails" + num, message)
			}
		}
	}
	
	function removeDetails(num) {
		outMsg("viewDetails" + num, "");
	}
	
	function outMsg(id, msg) {
		var console = document.getElementById(id);
		if (console != null) {
			console.innerHTML = msg;
		}
	}