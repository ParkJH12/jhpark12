#Session 실습
####kr.co.stardust.cont.LoginController.java
로그인을 처리하는 컨트롤러. request의 parameter중 p_code의 값에 따라 분기 생성
- p_code가 null: key를 "def"로 설정하고 login_form.jsp를 호출한다.
- p_code가 login_chk: 로그인을 할 때로 클라이언트로부터 받아온 request객체의 p_empno, p_ename 파라미터로부터 값을 가져온다. 가져온 값을 DB와 비교하여 존재할 경우(chk_id가 1) session에 setAttribute로 저장하고 추가로 "code"를 저장하여 다음번 login_form.jsp 호출시 code를 확인하여 로그인 페이지를 건너 뛸 수 있도록 한다. 존재하지 않을 경우(chk_id가 0) 다시 로그인 페이지로 돌아간다.
- p_code가 login_join: 회원가입시 처리할 코드로 구현하지 않음
- p_code가 log_out: 로그아웃시 현재 세션을 초기화 하는 invalidate()메소드를 호출하고 다시 login_form.jsp로 돌아간다.

####kr.co.stardust.model.EmpDao.java
DB에 연결하여 사원 정보에 접근하는 모델.

####index.jsp
기본 페이지로 jsp의 forward 태그를 통해 자동으로 컨트롤러를 호출한다.

####login_form.jsp
로그인 페이지로 아이디, 비밀번호를 입력받고 로그인과 회원가입 버튼을 제공한다.
- 페이지가 로드되면 body태그에 onLoad에 지정되어있는 자바스크립트 isLogin()메소드가 호출되어 현재 세션의 code에 login_chk를 저장하고 있는지 확인한다. 저장하고 있다면 이미 로그인 된 사용자이므로 바로 login_ok.jsp로 페이지를 이동시킨다.
- 로그인 버튼이 눌리면 자바스크립트에서 login()메소드가 호출되며 정상적으로 입력하면 p_code값에 login_chk를 저장한후 submit()메소드를 호출하여 컨트롤러에 값을 전달한다.
- 회원가입 버튼이 눌리면 자바스크립트에서 join()메소드가 호출되며 p_code값에 login_join값을 저장하여 submit()메소드를 호출후 컨트롤러에 값을 전달한다.

####login_ok.jsp
로그인 결과 페이지 입니다.


##160519 수정사항
###추가
####kr.co.stardust.model.EmpController.java
회원정보 수정을 처리하는 컨트롤러
- p_code가 "": 현재 세션의 empno에 해당하는 정보를 EmpDao클래스를 통해 EmpDto객체로 가져오고 modify_emp.jsp로 연결한다.
- p_code가 emp_update: modify_emp.jsp에서 사용자가 수정한 데이터를 다시 새로운 EmpDto객체에 저장하여 EmpDao클래스의 modEmp(EmpDto) 메소드를 활용하여 저장한다.

####kr.co.stardust.model.EmpDto.java
회원정보를 저장하는 객체입니다.

####modify_emp.jsp
사원의 개인 정보를 수정하는 텍스트 필드를 제공한다.

####emp_mod_ok.jsp
특별한 출력 없이 수정된 행의 수만 alert명령어를 사용하여 알림창으로 알려주고 다시 LoginController에 접근하여 login_ok.jsp페이지로 이동하도록 유도한다.

###수정
####LoginController.java
- 로그인 상태 유지를 위한 코드가 추가되었다. session의 empno값이 존재하는지 아닌지 여부(request.getSession().getAttribute("empno"))에 따라 다른 행동을 취할 수 있게 설정하였다.
- 기타 변수의 위치를 조정하였다.

####login_form.jsp
- isLogin(): 로그인 되었는지 여부를 판단하여 login_ok.jsp로 옮겨주는 script 메소드였으나 해당 기능을 컨트롤러에서 수행하게 하였으므로 삭제
- keyEvent(): 사용자가 text input에서 엔터를 눌렀을때 반응하게 하는 메소드이다.

####login_ok.jsp
- empmod() 스크립트 메소드가 추가되었다. 정보수정 버튼을 추가하여 버튼이 눌릴경우 form의 action을 emp.do로 바꾸고 submit()을 하는 역할을 한다.

####kr.co.stardust.model.EmpDao.java
- getEmp(String empno) 메소드를 추가하였다. 파라미터로 전달되는 empno의 값에 해당하는 employees테이블의 정보를 SELECT하여 EmpDto 객제에 저장하여 반환하는 함수이다.
- modEmp(EmpDto ed) 메소드를 추가하였다. 파라미터로 전달되는 사용자의 정보를 가지고 있는 EmpDto객체를 가지고 와서 employees테이블에 update한다. 수업과정에서 한것과는 달리 다시 이 메소드 안에서 getEmp()메소드를 호출하여 old값과 현재 파라미터로 받아온 new값을 비교하여 수정된 부분만 update하도록 sql문 정의를 세분화 하였다.
- getName(String empno) 메소드를 추가하였다. 로그인이 유지된 상태에서 다시 index페이지로 접근할 경우 login_ok.jsp페이지에서 session의 attribute값인 empno만 출력되고 이름인 ename은 출력되지 않기 때문에 LoginController에서 다시 이름만 검색하여 출력하게 할 수 있도록 별도의 이름만 가져오는 메소드를 추가하였다.

####DB의 Trigger 수정
DB의 기존 트리거는 다음과 같이 정의되어 있다.
<pre><code>
    CREATE OR REPLACE TRIGGER update_job_history
    AFTER
    UPDATE OF job_id, department_id ON employees
    FOR EACH ROW
    BEGIN
      add_job_history(:old.employee_id, :old.hire_date, SYSDATE, :old.job_id, :old.department_id);
    END;
</code></pre>
위 Trigger는 employees테이블에서 직원의 job(또는 department)가 바뀌면 자동으로 job_history 테이블에 자료를 추가하는 기능이다.
허나 문제가 있는데 위와 같은 trigger로 row가 추가된 후에 다시 한 번 job이 바뀌면 에러가 뜨게 된다. 왜냐하면 위 trigger는 employees테이블의 hire_date를 start_date로 삼고 추가하게 되는데 job이 바뀌어도 hire_date는 바뀌지 않기 때문이다.
그 상태에서 다시 추가를 하면 job_history 테이블의 기본키는 employee_id와 start_date로 삼고 있어 동일한 employee_id, hire_date를  사용하려 하면 기본키 제약조건에 위배되어 추가되지 않게 된다.

(job_history 테이블의 101번 사원의 예를 보자. 93/10/27에 job이 바뀌었지만 employees테이블에서 hire_date는 바뀌지 않는다.)

즉 job_history 테이블에 처음 입력할 때에는 start_date를 employees테이블의 hire_date를 사용해야 하지만, 두번째 입력되기 위해서는 이전의 end_date 값을 start_date값으로 입력할 필요가 있다.
따라서 다음과 같이 trigger를 수정하였다.
<pre><code>
    CREATE OR REPLACE TRIGGER update_job_history
    AFTER
    UPDATE OF job_id, department_id ON employees
    FOR EACH ROW
    DECLARE
      LATEST_DATE	DATE;
    BEGIN
      SELECT MAX(END_DATE) INTO LATEST_DATE
      FROM job_history
      WHERE employee_id = :old.employee_id;

      IF LATEST_DATE IS NULL THEN
        add_job_history(:old.employee_id, :old.hire_date, SYSDATE, :old.job_id, :old.department_id);
      ELSE
        add_job_history(:old.employee_id, LATEST_DATE, SYSDATE, :old.job_id, :old.department_id);
      END IF;
    END;
</code></pre>
