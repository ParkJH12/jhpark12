package kr.co.stardust.cont;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.stardust.model.EmpDao;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String loginFormUrl = "login_form.jsp";
	private final String loginOkUrl = "login_ok.jsp";
	private EmpDao ed = new EmpDao();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key = null;
		
		if(request.getParameter("p_code") == null)
			key = "def";
		else
			key = request.getParameter("p_code");
		
		if(request.getSession().getAttribute("empno") == null) {
			// 로그인 한 적 없이 접속한 경우
			if(key.equals("def")) {
				System.out.println("LoginController -> session[status] == null -> key == \"def\"");
				// 로그인이 안되어 있는 상태에서 컨트롤러에 접근했을 때
				move(request, response, loginFormUrl);
			} else if(key.equals("login_chk")){
				System.out.println("LoginController -> session[status] == null -> key == \"login_chk\"");
				// 로그인 버튼을 누르고 로그인 요청을 했을 때.
				String empno = request.getParameter("p_empno");
				String ename = request.getParameter("p_ename");

				// DB와 비교
				int chk_id = ed.chk_id(empno, ename);
				if(chk_id == 1) {
					// DB에 해당 값이 존재할 때.
					HttpSession hs = request.getSession();
					// 여기서 세션에 empno 애트리뷰트를 생성해서 값을 넣어준다.
					hs.setAttribute("empno", empno);
					request.setAttribute("ename", ename);
					move(request, response, loginOkUrl);
				} else if (chk_id == 0) {
					// DB에 해당 값이 존재하지 않을 때.
					move(request, response, "login_form.jsp");
				}
			} else if(key.equals("login_join")) {
				System.out.println("LoginController -> session[status] == null -> key == \"login_join\"");
				// 회원가입 버튼을 누르고 회원가입 요청을 했을 때(구현되지 않음).
			}
		} else {
			// request.getSession().getAttribute("empno") != null 일 때 이므로
			if(key.equals("def")) {
				// 로그인 한 적이 있을때, 다시 DB에 접근해서 ename값을 가져온다. 그리고 login_ok.jsp로 이동시킨다.
				System.out.println("LoginController -> session[status] != null -> key == \"def\"");
				String ename = ed.get_name((String) request.getSession().getAttribute("empno"));
				request.setAttribute("ename", ename);
				move(request, response, loginOkUrl);
			} else if(key.equals("log_out")) {
				// 로그아웃을 요청했을 때 세션을 초기화 시킨다.
				HttpSession hs = request.getSession();
				hs.invalidate();
				move(request, response, "login_form.jsp");			
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	// Dispatch forward
	private void move(HttpServletRequest request, HttpServletResponse response, String url) {
		RequestDispatcher dis = request.getRequestDispatcher(url);
		try {
			dis.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

