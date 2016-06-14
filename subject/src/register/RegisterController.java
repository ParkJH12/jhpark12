package register;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Service service = new ServiceImpl();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String code = request.getParameter("type");
		
		if(code.equals("getOpens")) {
			// 모든 강의 정보 가져오기
			System.out.println("RegisterController -> getOpens");
			
			List<Lecture> openList = service.getAllLectures();
			request.setAttribute("OPENLIST", openList);
			
			int num = Integer.parseInt(request.getSession().getAttribute("num").toString());
			List<Lecture> myList = service.getAllMyRegister(num);
			request.setAttribute("MYLIST", myList);
			
//			boolean[][] timeTable = new boolean[5][9];	// 5일에 1시간간격으로 9시부터 6시까지 9개의 시간간격
//			String[] week = {"MON", "TUE", "WED", "TUR", "FRI"};
//			for(Lecture l : myList) {
//				String[] days = l.getDays().split("/");
//				String[] times = l.getTimes().split("/");
//
//				for(int i = 0; i < days.length; i++) {
//					if(!days[i].equals("-")) {
//						int day = 0;
//						for(int j = 0; j < week.length; j++) {
//							if(week[j].equals(days[i])) {
//								day = j;
//							}
//						}
//						String[] time = times[i].split("-");
//						int startTime = Integer.parseInt(time[0].substring(0, 2)) - 9;
//						int endTime = Integer.parseInt(time[1].substring(0, 2)) - 9;
//						
//						for(int j = startTime; j < endTime + 1; j++) {
//							timeTable[day][j] = true;
//						}
//					}
//				}
//			}
			
//			request.setAttribute("TIMETABLE", timeTable);
			
			String url = "/register/opensJSON.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		} else if(code.equals("applyOpen")) {
			// 수강신청
			System.out.println("RegisterController -> applyOpen");
			
			int openNum = Integer.parseInt(request.getParameter("o_num"));
			int studNum = Integer.parseInt(request.getSession().getAttribute("num").toString());
			Register r = new Register();
			r.setOpenNum(openNum);
			r.setStudNum(studNum);
			
			Calendar calendar = Calendar.getInstance();
			r.setSubYear(calendar.get(Calendar.YEAR));
			if(calendar.get(Calendar.MONTH) < 9) {
				r.setQuarter(1);
			} else {
				r.setQuarter(2);
			}
			
			service.setRegister(r);
			Lecture lecture = service.getLecture(openNum);
			request.setAttribute("LECTURE", lecture);
			String url = "/register/openJSON.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		} else if(code.equals("cancelOpen")) {
			// 수강취소
			System.out.println("RegisterController -> cancelOpen");
			
			int openNum = Integer.parseInt(request.getParameter("o_num"));
			int studNum = Integer.parseInt(request.getSession().getAttribute("num").toString());
			service.removeRegister(openNum, studNum);
			response.getWriter().print(openNum);
		} else if(code.equals("getSubjects")) {
			// 모든 과목 정보 및 내 개설 정보 가져오기
			System.out.println("RegisterController -> getSubjects");
			
			// 모든 과목 정보 가져오기			
			List<Subject> subjectList = service.getAllSubjects();
			request.setAttribute("SUBLIST", subjectList);
			
			// 내 개설 정보 가져오기
			int profNum = Integer.parseInt(request.getSession().getAttribute("num").toString());
			List<Open> myList = service.getAllMyOpens(profNum);
			request.setAttribute("MYLIST", myList);
			
			String url = "/register/subjectsJSON.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		} else if(code.equals("createOpen")) {
			// 강의 개설
			System.out.println("RegisterController -> createOpen");
			
			int profNum = Integer.parseInt(request.getSession().getAttribute("num").toString());
			String room = request.getParameter("o_room");
			String days = request.getParameter("o_days");
			String time = request.getParameter("o_time");
			int subjectNum = Integer.parseInt(request.getParameter("o_subNum"));
			Open open = new Open();
			open.setRoom(room);
			open.setSubDay(days);
			open.setSubTime(time);
			open.setSubjectNum(subjectNum);
			open.setProfNum(profNum);
			
			int num = service.setOpen(open);
			
			Open newOpen = service.getOpen(num);
			request.setAttribute("OPEN", newOpen);
			String url = "/register/subjectJSON.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		} else if(code.equals("getOpen")) {
			// 강의 하나 정보 가져오기
			System.out.println("RegisterController -> getOpen");
			
			int openNum = Integer.parseInt(request.getParameter("o_num"));
			Open open = service.getOpen(openNum);
			request.setAttribute("OPEN", open);
			String url = "/register/subjectJSON.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		} else if(code.equals("editOpen")) {
			// 강의 정보 수정
			System.out.println("RegisterController -> editOpen");
			
			int profNum = Integer.parseInt(request.getSession().getAttribute("num").toString());
			int num = Integer.parseInt(request.getParameter("o_num"));
			String room = request.getParameter("o_room");
			String days = request.getParameter("o_days");
			String time = request.getParameter("o_time");
			int subjectNum = Integer.parseInt(request.getParameter("o_subNum"));
			Open open = new Open();
			open.setNum(num);
			open.setRoom(room);
			open.setSubDay(days);
			open.setSubTime(time);
			open.setSubjectNum(subjectNum);
			open.setProfNum(profNum);
			
			num = service.editOpen(open);
			response.getWriter().print(num);
		} else if(code.equals("removeOpen")) {
			// 강의 삭제
			System.out.println("RegisterController -> removeOpen");
			
			int num = Integer.parseInt(request.getParameter("o_num"));
			Subject s = service.delOpen(num);
			response.getWriter().print("{openNum: " + num + ", subNum: " + s.getNum() + ", isOpened: " + s.isFlag() + "}");
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
