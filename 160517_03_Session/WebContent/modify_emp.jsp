<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modify Employee</title>
<script type="text/javascript">
	function mod_ok() {
		document.modForm.submit()
	}
</script>
</head>
<body>
	<form action="emp.do" method="POST" name="modForm">
		<p>사원번호: <input type="text" name="p_empno" value="${ed.employee_id}" readonly="readonly"></p>
		<p>이름: <input type="text" name="p_fname" value="${ed.first_name}"></p>
		<p>성: <input type="text" name="p_lname" value="${ed.last_name}"></p>
		<p>E-mail: <input type="text" name="p_email" value="${ed.email}"></p>
		<p>전화번호: <input type="text" name="p_phone" value="${ed.phone_number}"></p>
		<p>입사일자: <input type="text" name="p_hiredate" value="${ed.hire_date}"></p>
		<p>Job id: <input type="text" name="p_jobid" value="${ed.job_id}"></p>
		<p>급여: <input type="text" name="p_salary" value="${ed.salary}"></p>
		<p>상여%: <input type="text" name="p_commpct" value="${ed.commission_pct}"></p>
		<p>매니저번호: <input type="text" name="p_mgrid" value="${ed.manager_id}"></p>
		<input type="hidden" name="p_code" value="emp_update">
		<p><input type="button" value="수정" onClick="mod_ok()"></p>
	</form>
</body>
</html>