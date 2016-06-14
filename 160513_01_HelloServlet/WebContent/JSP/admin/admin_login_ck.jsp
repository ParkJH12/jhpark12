<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Check</title>

<script type="text/javascript">
	function loginChk(adm_id) {
		if(adm_id == "admin") {
			var form = document.admin_form
			form.adm_id.value = '<%= request.getAttribute("adm_id") %>'
			form.adm_pass.value = '<%= request.getAttribute("adm_pass") %>'
			form.adm_code.value = "adm_login_ok"
			form.submit()
		} else {
			alert("로그인 하지 못했습니다.")
			location.href='<%= request.getContextPath() %>' + "/JSP/admin/admin_input.html"
		}
	}
</script>
</head>
<body onLoad="loginChk('<%= request.getAttribute("adm_id") %>')">
	<form action="admin.do" name="admin_form" method="post">
		<input type="hidden" name="adm_id">
		<input type="hidden" name="adm_pass">
		<input type="hidden" name="adm_code">
	</form>
</body>
</html>
