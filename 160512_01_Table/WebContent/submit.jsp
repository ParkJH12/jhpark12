<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
1. text : <%= request.getParameter("p_name") %><br>
2. pass : <%= request.getParameter("p_pass") %><br>
3. radio : <%= request.getParameter("p_radio") %><br>
4. radio1 : <%= request.getParameter("p_radio1") %><br>
<%-- 5. chk : <% for(String s : request.getParameterValues("p_check")) { s }%> --%>
5. chk : <% String[] s = request.getParameterValues("p_check"); %>
          <% for(int i = 0; i < s.length; i++) { %>
            <%= s[i] %>,
          <% } %><br>
6. combo : <%= request.getParameter("p_combo") %><br>
7. textarea: <%= request.getParameter("p_textarea") %><br>

</body>
</html>
