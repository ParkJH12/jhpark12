###MainController.java - 구현 안됨
###AdminController.java
/admin.do를 주소로 하는 서블릿 파일. html이나 jsp파일로 부터 호출되어 서버로 전송되는 값을 처리한다.
request의 parameter중 adm_code를 통해 조건분기하여 그에 맞는 역할을 수행한다.
'adm_login'시에는 adm_login_ck.jsp파일에 현재 request객체에 저장된 파라미터를 attribute로 다시 저장하여 전달한다.
'adm_login_ok'시에는 로그인이 정상적으로 처리되는 상황으로 결과를 출력하는 admin_submit.jsp에 request객체를 전달한다.


###admin_input.html
로그인 페이지

###admin_login_ck.jsp
컨트롤러부터 전송된 값을 javascript문법을 통해 아이디가 admin인지 아닌지 판별하여 다시 admin_html로 redirect시킬 것인지
adm_code에 adm_login_ck값을 저장하여 다시 컨트롤러에 전송할 것인지를 결정

###admin_submit.jsp
컨트롤러로부터 전송된 값을 출력
