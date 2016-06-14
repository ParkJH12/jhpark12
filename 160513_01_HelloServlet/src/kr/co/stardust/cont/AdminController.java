package kr.co.stardust.cont;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.collections.SynchronizedStack;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/admin.do")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("AdminController 시작");
		String adm_code = request.getParameter("adm_code");
		if(adm_code.equals("adm_login")) {
			System.out.println("adm_login 시작");
			String adm_id = request.getParameter("adm_id");
			String adm_pass = request.getParameter("adm_pass");
		// id가 'admin'이 아니라면 로그인 페이지로 되돌아가기
//			if(!adm_id.equals("admin")) {
//				response.sendRedirect(request.getContextPath() + "/JSP/admin/admin_login_ck.jsp");
//			} else {
			// 다른페이지에 위임하는 클래스
			RequestDispatcher dis = request.getRequestDispatcher("/JSP/admin/admin_login_ck.jsp");
			request.setAttribute("adm_id", adm_id);
			request.setAttribute("adm_pass", adm_pass);
			dis.forward(request, response);
//			}
			
			
		} else if (adm_code.equals("adm_login_ok")) {
			System.out.println("adm_login_ok 시작");
			String adm_id = request.getParameter("adm_id");
			String adm_pass = request.getParameter("adm_pass");
			
			RequestDispatcher dis = request.getRequestDispatcher("/JSP/admin/admin_submit.jsp");
			request.setAttribute("adm_id", adm_id);
			request.setAttribute("adm_pass", adm_pass);
			dis.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
