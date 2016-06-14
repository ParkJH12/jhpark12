package member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/MemberController")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceImpl service = new ServiceImpl();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String code = request.getParameter("type");
		
		if(code.equals("login")) {
			// 로그인
			System.out.println("controller -> login");
			int num = Integer.parseInt(request.getParameter("m_num"));
			String name = request.getParameter("m_name");
			
			if(service.login(num, name)) {
				System.out.println("로그인 성공");
				Member m = service.getMember(num);
				request.getSession().setAttribute("num", num);

				request.getSession().setAttribute("TYPE", service.getMember(num).getType());
				String url = request.getContextPath() + "/member/main.jsp";
				response.sendRedirect(url);
			} else {
				System.out.println("로그인 실패");
				String url = request.getContextPath() + "/member/loginForm.jsp";
				response.sendRedirect(url);
			}
		} else if (code.equals("join")) {
			// 회원가입
			System.out.println("controller -> join");
			int num = Integer.parseInt(request.getParameter("m_num"));
			String name = request.getParameter("m_name");
			String tel = request.getParameter("m_tel");
			String email = request.getParameter("m_email");
			String dept = request.getParameter("m_dept");
			int type = Integer.parseInt(request.getParameter("m_type"));
			
			Member m = new Member(num, name, tel, email, dept, type);
			service.addMember(m);
			String url = request.getContextPath() + "/member/loginForm.jsp";
			response.sendRedirect(url);
		} else if (code.equals("editInfo")) {
			// 회원가입 수정
			System.out.println("controller -> editInfo");
			int num = Integer.parseInt(request.getSession().getAttribute("num").toString());
			Member m = service.getMember(num);
			
			request.setAttribute("MEM", m);
//			String url = request.getContextPath() + "/member/editInfo.jsp";
//			String url = "editInfo.jsp";
//			String url = request.getContextPath() + "/editInfo.jsp";
			String url = "/member/editInfo.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		} else if (code.equals("editInfo_ok")) {
			// 회원가입 수정요청
			System.out.println("controller -> editInfo_ok");
			int num = Integer.parseInt(request.getParameter("m_num"));
			String name = request.getParameter("m_name");
			String tel = request.getParameter("m_tel");
			String email = request.getParameter("m_email");
			String dept = request.getParameter("m_dept");
			int type = Integer.parseInt(request.getParameter("m_type"));
			
			Member m = new Member(num, name, tel, email, dept, type);
			service.editInfo(m);
			String url = request.getContextPath() + "/member/main.jsp"; 
			response.sendRedirect(url);
		} else if (code.equals("logout")) {
			// 로그아웃
			System.out.println("controller -> logout");
			request.getSession().invalidate();
			String url = request.getContextPath() + "/member/loginForm.jsp";
			response.sendRedirect(url);
		} else if (code.equals("delete")) {
			// 회원탈퇴
			System.out.println("controller -> delete");
			int num = Integer.parseInt(request.getSession().getAttribute("num").toString());
			service.delMember(num);
			String url = request.getContextPath() + "/member/loginForm.jsp";
			response.sendRedirect(url);
		} else if (code.equals("checkID")) {
			// 중복확인
			System.out.println("controller -> checkID");
			int num = Integer.parseInt(request.getParameter("m_num"));
			
//			ajax 사용 전 코드
//			String url = "/member/check.jsp";
//			RequestDispatcher rd = request.getRequestDispatcher(url);
//			request.setAttribute("NUM", num);
//			if(service.isSameNumber(num)) {
//				// 같은 번호가 있음
//				System.out.println("중복됨");
//				request.setAttribute("ISOK", "F");
//			} else {
//				// 같은 번호가 없음
//				System.out.println("중복아님");
//				request.setAttribute("ISOK", "T");
//			}
//			rd.forward(request, response);
			
//			ajax 사용 코드
			PrintWriter out = response.getWriter();
			if(service.isSameNumber(num)) {
				// 같은 번호가 있음
				System.out.println("중복 됨");
				out.print("F");
			} else {
				// 같은 번호가 없음
				System.out.println("중복 아님");
				out.print("T");
			}
		} else if (code.equals("getMembersByXML")) {
			// 전체 멤버 가져오기
			System.out.println("controller -> getMembersByXML");
			List<Member> memberList = service.getAllMembers();
			request.setAttribute("LIST", memberList);
			String url = "/member/allMembersXML.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			System.out.println("전송하기");
			rd.forward(request, response);
		} else if (code.equals("getMembersByJSON")) {
			System.out.println("controller -> getMembersByJSON");
			List<Member> memberList = service.getAllMembers();
			request.setAttribute("LIST", memberList);
			String url = "/member/allMembersJSON.jsp";
//			String url = request.getContextPath() + "/member/allMembersJSON.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		} else if (code.equals("getDetails")) {
			System.out.println("controller -> getDetails");
			Member member = service.getMember(Integer.parseInt(request.getParameter("m_num")));
			request.setAttribute("MEM", member);
			String url = "/member/getDetails.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		} else if(code.equals("getType")){
			System.out.println("controller -> getType");
			Member member = service.getMember(Integer.parseInt(request.getParameter("m_num")));
			request.setAttribute("MEM", member);
			String url = "/member/main.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
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
