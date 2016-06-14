function print_list(num){
	var form = document.getElementById("prof_do");
	if(form.style.display == 'none'){
		form.style.display = '';
		var params = "type=getlist&prof_num="+num;
		sendRequest("OpenController", params, printInfo, "GET");
		var params2 = "type=getsubject";
		sendRequest("OpenController", params2, printsubject, "GET");
	}else{
		form.style.display = 'none';
	}
}

function printsubject() {
	if (httpRequest.readyState == 4) {
		if (httpRequest.status == 200) {
			var requestResult = httpRequest.responseText;
			var getlist = eval("(" + requestResult +")");
			var num = document.getElementById("pnum");
			var html = "<table><tr><td>"
						+ "<input type=button value='추가' onclick=insertdb("+num.value+")>"
						+ "</td></tr>"
						+ "<tr>"
						+ "<th>강의</th><th>과목번호</th><th>강의실</th><th>수업일</th><th>수업시간</th>"
						+ "</tr>";
			
			for(var i = 0 ; i < getlist.length ; i++){
				var open = getlist[i];
				var num = open.num;
				var subject_num = open.subject_num;
				var room = open.room;
				var sub_day = open.sub_day;
				var sub_time = open.sub_time;
				var prof_num = open.prof_num;
				html += "<tr>"
						+ "<td>"+num+"</td>"
						+ "<td>"+subject_num+"</td>"
						+ "<td>"+room+"</td>"
						+ "<td>"+sub_day+"</td>"
						+ "<td>"+sub_time+"</td>"
						+ "<td><input type=button value=수정 ></td>"
						+ "<td><input type=button value=삭제 ></td>"
						+ "</tr>";
			}
			html += "</table>"
			document.getElementById("open").innerHTML = html;
		}
	}
}

function printInfo() {
	if (httpRequest.readyState == 4) {
		if (httpRequest.status == 200) {
			var requestResult = httpRequest.responseText;
			var getlist = eval("(" + requestResult +")");
			var num = document.getElementById("pnum");
			var html = "<table><tr><td>"
						+ "<input type=button value='추가' onclick=insertdb("+num.value+")>"
						+ "</td></tr>"
						+ "<tr>"
						+ "<th>강의</th><th>과목번호</th><th>강의실</th><th>수업일</th><th>수업시간</th>"
						+ "</tr>";
			
			for(var i = 0 ; i < getlist.length ; i++){
				var open = getlist[i];
				var num = open.num;
				var subject_num = open.subject_num;
				var room = open.room;
				var sub_day = open.sub_day;
				var sub_time = open.sub_time;
				var prof_num = open.prof_num;
				html += "<tr>"
						+ "<td>"+num+"</td>"
						+ "<td>"+subject_num+"</td>"
						+ "<td>"+room+"</td>"
						+ "<td>"+sub_day+"</td>"
						+ "<td>"+sub_time+"</td>"
						+ "<td><input type=button value=수정 ></td>"
						+ "<td><input type=button value=삭제 ></td>"
						+ "</tr>";
			}
			html += "</table>"
			document.getElementById("open").innerHTML = html;
		}
	}
}
function insertdb(num){
	var form = document.getElementById("insert");
	if(form.style.display == 'none'){
		form.style.display = '';
		/*var params = "type=getlist&prof_num="+num;
		sendRequest("OpenController", params, printInfo, "GET");*/
	}else{
		form.style.display = 'none';
	}
}