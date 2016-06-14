package kr.co.kitri04.cont;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.kitri04.model.BoardDao;
import kr.co.kitri04.model.BoardDto;

/**
 * Servlet implementation class BoardReadCont
 */
@WebServlet("/read.do")
public class BoardReadCont extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getRemoteAddr());
		request.setCharacterEncoding("EUC-KR");
		response.setCharacterEncoding("EUC-KR");
		// Code값 호출 = p_code
		//
		String p_code = request.getParameter("p_code");

		// 1. 글 목록 호출(list)
		if (p_code.equals("list")) {
			// 1. 글 목록 호출(list)
			// DB 접근하여 Database에 전체 값 받아오기
			BoardDao bd = new BoardDao();
			// 쿼리문을 통해 Board Table에 값을 받아오기
			// 전체 Board Table 글번호, 제목, 작성자, 작성시간, 추천수, 조회수
			// Page 처리
			String strPageNo = request.getParameter("page_no");
			int pageNo;
			if (strPageNo == null) {
				pageNo = 1;
			} else {
				pageNo = Integer.parseInt(strPageNo);
			}
			//List<BoardDto> board_list = bd.selectListBoard(pageNo);
			List<BoardDto> board_list = bd.getList();
			//System.out.println(pageNo);
			//System.out.println(board_list.get(0).getPageCount());
			String url = "board_list.jsp";
			request.setAttribute("BL", board_list);
			move(request, response, url);
		} else if (p_code.equals("contents")) {
			// 2. 해당 게시글 출력할 수 있도록 구성
			// 선택한 게시글의 b_id 불러호기
			String b_id = request.getParameter("b_id");
			// DB 접근해서 게시글 내용 받아오기
			BoardDto bDto = null;
			BoardDao bd = new BoardDao();
			bDto = bd.getContents(b_id);
			bd.setReadCnt(b_id);
			// DB 가져온 bDto jsp에 출력
			// board_view.jsp
			request.setAttribute("BDTO", bDto);
			move(request, response, "board_view.jsp");
		} else if (p_code.equals("write")) {
			// 글 작성할 수 있는 페이지 호출
			System.out.println("write 실행");
			move(request, response, "board_input.jsp");
		} else if (p_code.equals("modify")) {
			// 글 수정할 수 있는 페이지 호출
			String b_id = request.getParameter("p_bid");
			// DB 에 접근해서 b_id를 통한 값 받아오기
			BoardDao bDao = new BoardDao();
			BoardDto bDto = bDao.getContents(b_id);
			// DB에서 가져온 bDto 값을 수정페이지에 전달
			request.setAttribute("BDTO", bDto);
			// View Page에서 수정페이지로 활용할 내용이냐
			// 선택
			// request.setAttribute("MODIFY", "modify");
			move(request, response, "board_input.jsp");
		} else if (p_code.equals("delete")) {
			// 삭제할 수 있도록 구성하기
			// Password 부터 물어볼 수 있도록 구성하기
			request.setAttribute("P_BID", request.getParameter("p_bid"));
			String url = "check_pass.jsp";
			move(request, response, url);
		} else if (p_code.equals("chk_pass")) {
			// 패스워드 체크 관련 처리
			String p_pass = request.getParameter("p_pass");
			String b_id = request.getParameter("p_bid");

			BoardDao bd = new BoardDao();
			BoardDto bDto = bd.getContents(b_id);
			if (p_pass.equals(bDto.getPassword())) {
				// Pass 워드 일치
				// 삭제 진행
				String url = "write.do?p_code=delete_ok&p_bid=" + b_id;
				move(request, response, url);
			} else {
				// Pass 워드 불일치
				String url = "read.do?p_code=contents&b_id=" + b_id;
				move(request, response, url);
			}
		} else if (p_code.equals("reply")) {
			String url = "board_input.jsp";
			String p_bid = request.getParameter("p_bid");
			request.setAttribute("REP", "reply");
			request.setAttribute("P_BID", p_bid);
			move(request, response, url);
		} else if (p_code.equals("download")) {
			System.out.println("DownloadModule");
			String pds_value = request.getParameter("pds_value");
			String p_bid = request.getParameter("p_bid");
			BoardDao bd = new BoardDao();
			String pds_link = bd.getContents(p_bid).getPds_link();

			String root = getServletContext().getRealPath("/");
			File path = new File(root + "/uploads");

			// 다운받을 파일의 전체 경로를 filePath에 저장
			String filePath = path.toString() + "\\" + pds_value;

			try {
				// 다운받을 파일을 불러옴
				// File file = new File(filePath);
				byte b[] = new byte[4096];

				// page의 ContentType등을 동적으로 바꾸기 위해 초기화시킴
				response.reset();
				response.setContentType("application/octet-stream");

				// 한글 인코딩
				// String Encoding = new String(fileName.getBytes("UTF-8"),
				// "8859_1");
				// 파일 링크를 클릭했을 때 다운로드 저장 화면이 출력되게 처리하는 부분
				response.setHeader("Content-Disposition", "attachment; filename = " + pds_link);

				// 파일의 세부 정보를 읽어오기 위해서 선언
				FileInputStream in = new FileInputStream(filePath);

				// 파일에서 읽어온 세부 정보를 저장하는 파일에 써주기 위해서 선언
				ServletOutputStream out2 = response.getOutputStream();

				int numRead;
				// 바이트 배열 b의 0번 부터 numRead번 까지 파일에 써줌 (출력)
				while ((numRead = in.read(b, 0, b.length)) != -1) {
					out2.write(b, 0, numRead);
				}

				out2.flush();
				out2.close();
				in.close();
			} catch (Exception e) {
			}

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void move(HttpServletRequest req, HttpServletResponse resp, String url) {
		RequestDispatcher rd = req.getRequestDispatcher(url);
		try {
			rd.forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
