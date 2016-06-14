package kr.co.stardust.cont;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.stardust.model.EmpDto;
import kr.co.stardust.model.EmpDao;

/**
 * Servlet implementation class EmpController
 */
@WebServlet("/emp.do")
public class EmpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmpDao empDao = new EmpDao();
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		if(hs.getAttribute("empno") == null) {
			System.out.println("EmpController -> session[empno] == null");
			response.sendRedirect("login.do");
		} else {
			String code = "def";
			
			if (!request.getParameter("p_code").equals("")) {
				code = request.getParameter("p_code");
			}
			
			if(code.equals("def")) {
				// DB접속 -> 수정될 사원의 값을 받아온다.
				System.out.println("EmpController -> session[empno] != null -> p_code == \"\"");
				String empno = (String) hs.getAttribute("empno");
				EmpDto ed = empDao.getEmp(empno); 
				RequestDispatcher rd = request.getRequestDispatcher("modify_emp.jsp");
				request.setAttribute("ed", ed);
				rd.forward(request, response);
			} else if(code.equals("emp_update")) {
				// 수정된 객체의 값을 DB에 update
				System.out.println("EmpController -> session[empno] != null -> p_code == \"emp_update\"");
				EmpDto ed = new EmpDto();
				ed.setEmployee_id(Integer.parseInt(request.getParameter("p_empno")));
				ed.setFirst_name(request.getParameter("p_fname"));
				ed.setLast_name(request.getParameter("p_lname"));
				ed.setEmail(request.getParameter("p_email"));
				ed.setPhone_number(request.getParameter("p_phone"));
				ed.setHire_date(request.getParameter("p_hiredate"));
				ed.setJob_id(request.getParameter("p_jobid"));
				ed.setSalary(Integer.parseInt(request.getParameter("p_salary")));
				ed.setCommission_pct(Integer.parseInt(request.getParameter("p_commpct")));
				ed.setManager_id(Integer.parseInt(request.getParameter("p_mgrid")));
				int cnt = empDao.modEmp(ed);
				
				RequestDispatcher rd = request.getRequestDispatcher("emp_mod_ok.jsp");
				request.setAttribute("cnt", cnt);
				rd.forward(request, response);
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

}
