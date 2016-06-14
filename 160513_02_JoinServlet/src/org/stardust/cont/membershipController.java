package org.stardust.cont;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class membershipController
 */
@WebServlet("/submit.do")
public class membershipController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	
	// 서블렛이 처음 구동될 때 실행되는 메소드
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
//		System.out.println("aaa");
		super.init();
	}
	
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, res);
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String membership_code = request.getParameter("membership_code");
		
		if(membership_code.equals("membership_join")) {
			RequestDispatcher dis = request.getRequestDispatcher("/JSP/Join/membership_submit.jsp");
			request.setAttribute("user_id", request.getParameter("user_id"));
			request.setAttribute("user_passwd", request.getParameter("user_passwd"));
			request.setAttribute("user_name", request.getParameter("user_name"));
			request.setAttribute("user_gender", request.getParameter("user_gender"));
			request.setAttribute("user_address", request.getParameter("user_address"));
			
			dis.forward(request, response);
		}

	}

}
