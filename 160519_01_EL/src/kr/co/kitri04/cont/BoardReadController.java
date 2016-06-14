package kr.co.kitri04.cont;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.kitri04.model.BoardDao;
import kr.co.kitri04.model.BoardDto;

/**
 * Servlet implementation class BoardReadController
 */
@WebServlet("/read.do")
public class BoardReadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardDao bd = new BoardDao();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println("ReadController 호출");
		// 코드 값 호출
		String p_code = request.getParameter("p_code");
		
		if(p_code.equals("list")) {
			// 1. 글 목록 호출: DB에 접근, Database에 전체 값 받아오기.
			List<BoardDto> board_list = bd.getList();
			
			String url = "board_list.jsp";
			request.setAttribute("BL", board_list);
			move(request, response, url);
		} else if(p_code.equals("contents")) {
			// 2. 해당 게시글 출력
			String b_id = request.getParameter("b_id");
			
			// 조회수 증가
			bd.increaseCnt(b_id);
			
			// DB에 접근 게시글 내용 받아오기.
			BoardDto bDto = bd.getContents(b_id);
			String url = "board_view.jsp";
			request.setAttribute("BDTO", bDto);
			move(request, response, url);
		} else if(p_code.equals("write")) {
			// 3. 글쓰기 페이지 호출
			String url = "board_input.jsp";
			move(request, response, url);
		} else if(p_code.equals("delete")) {
			// 4. 삭제 페이지 호출
			String url = "board_password_chk.jsp";
			request.setAttribute("BID", request.getParameter("p_bid"));
			request.setAttribute("CODE", "delete");
			move(request, response, url);
		} else if(p_code.equals("modify")) {
			// 5. 수정 전 패스워드 확인
			String url = "board_password_chk.jsp";
			String b_id = request.getParameter("p_bid");
			request.setAttribute("BID", b_id);
			request.setAttribute("CODE", "modify");
			move(request, response, url);
		} else if(p_code.equals("modify_ok")) {
			// 6. 수정용 글쓰기 페이지 호출
			String password = bd.getPassword(request.getParameter("p_bid"));
			if(password.equals(request.getParameter("p_password"))) {
				String url = "board_input.jsp";
				String b_id = request.getParameter("p_bid");
				BoardDto bDto = bd.getContents(b_id);
				request.setAttribute("BDTO", bDto);
				request.setAttribute("CODE", "modify");
				move(request, response, url);
			} else {
				String url = "read.do?p_code=modify";
				move(request, response, url);
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

	
	private void move(HttpServletRequest request, HttpServletResponse response, String url) {
		RequestDispatcher rd = request.getRequestDispatcher(url);
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
