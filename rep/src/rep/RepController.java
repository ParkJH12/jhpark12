package rep;

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
 * Servlet implementation class RepController
 */
@WebServlet("/RepController")
public class RepController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Service service = new ServiceImpl();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String code = request.getParameter("type");
		
		if (code.equals("getAll")) {
			// 모든 댓글 출력
			System.out.println("RepController -> getAll");
			List<Reply> replyList = service.getAll();
			request.setAttribute("LIST", replyList);
			String url = "/rep/allRepliesJSON.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		} else if (code.equals("addRep")) {
			// 댓글 쓰기
			System.out.println("RepController -> addRep");
			String name = request.getParameter("r_name");
			String content = request.getParameter("r_content");
			
			Reply addReply = new Reply();
			addReply.setName(name);
			addReply.setContent(content);
			int num = service.addRep(addReply);
			
			Reply getReply = service.getRep(num);
			request.setAttribute("REPLY", getReply);
			String url = "/rep/replyJSON.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		} else if (code.equals("updateRep")) {
			// 댓글 수정
			System.out.println("RepController -> updateRep");
			int num = Integer.parseInt(request.getParameter("r_num"));
			String name = request.getParameter("r_name");
			String content = request.getParameter("r_content");
			Reply updateReply = new Reply(num, name, content);
			int newnum = service.editRep(updateReply);
			
			Reply getReply = service.getRep(newnum);
			request.setAttribute("REPLY", getReply);
			String url = "/rep/replyJSON.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		} else if (code.equals("deleteRep")) {
			// 댓글 삭제
			System.out.println("RepController -> deleteRep");
			int num = Integer.parseInt(request.getParameter("r_num"));
			int rows = service.delRep(num);
			request.setAttribute("NUM", num);
			if(rows > 0) {
				request.setAttribute("ISDELETED", true);
			} else {
				request.setAttribute("ISDELETED", false);
			}
			
			String url = "/rep/deleteJSON.jsp";
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
