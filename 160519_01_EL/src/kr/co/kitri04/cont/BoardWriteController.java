package kr.co.kitri04.cont;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.kitri04.model.BoardDao;
import kr.co.kitri04.model.BoardDto;

/**
 * Servlet implementation class BoardWriteController
 */
@WebServlet("/write.do")
public class BoardWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardDao bd = new BoardDao();
       
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 코드 값 호출
		String p_code = request.getParameter("p_code");
		
		if(p_code.equals("write")) {
			BoardDto bDto = new BoardDto();
			bDto.setWriter(request.getParameter("p_writer"));
			bDto.setPassword(request.getParameter("p_password"));
			bDto.setTitle(request.getParameter("p_title"));
			bDto.setContents(request.getParameter("p_contents"));
			bDto.setPds_link(request.getParameter("p_link"));
			
			int cnt = bd.setContents(bDto);
			if(cnt == 1) {
				String url = "read.do?p_code=list";
				move(request, response, url);
			} else {
				String url = "read.do?p_code=write";
				move(request, response, url);
			}
			
		} else if(p_code.equals("delete")) {
			String board_id = request.getParameter("p_bid");
			String password = request.getParameter("p_password");
			int cnt = bd.removeContents(board_id, password);
			if(cnt == 1) {
				String url = "read.do?p_code=list";
				move(request, response, url);
			} else {
				String url = "read.do?p_code=delete";
				move(request, response, url);
			}
		} else if(p_code.equals("modify")) {
			BoardDto bDto = new BoardDto();
			bDto.setWriter(request.getParameter("p_writer"));
			bDto.setPassword(request.getParameter("p_password"));
			bDto.setTitle(request.getParameter("p_title"));
			bDto.setContents(request.getParameter("p_contents"));
			bDto.setPds_link(request.getParameter("p_link"));
			bDto.setBoard_id(Integer.parseInt(request.getParameter("p_bid")));
			int cnt = bd.updateContents(bDto);
			if(cnt == 1) {
				String url = "read.do?p_code=list";
				move(request, response, url);
			} else {
				String url = "read.do?p_code=contents&b_id=" + bDto.getBoard_id();
				move(request, response, url);
			}
		} else if(p_code.equals("like")) {
			String b_id = request.getParameter("p_bid");
			bd.increaseLike(b_id);
			String url = "read.do?p_code=contents&b_id=" + b_id;
			move(request, response, url);
		} else if(p_code.equals("unlike")) {
			String b_id = request.getParameter("p_bid");
			bd.increaseUnlike(b_id);
			String url = "read.do?p_code=contents&b_id=" + b_id;
			move(request, response, url);
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
