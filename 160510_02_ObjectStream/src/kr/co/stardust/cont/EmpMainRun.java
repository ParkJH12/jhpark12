package kr.co.stardust.cont;

import java.util.ArrayList;
import java.util.List;

import kr.co.stardust.View.EmpView;
import kr.co.stardust.domain.EmpBean;
import kr.co.stardust.model.EmpDao;

public class EmpMainRun {

	public static void main(String[] args) {
		List<EmpBean> empList = new ArrayList<EmpBean>();
		EmpView ev = new EmpView();
		EmpDao ed = new EmpDao();
		EmpBean eb = null;
		String inputMenu = null;
		ServerStart ss = new ServerStart();
		
		do {
			ev.printMenu();
			inputMenu = ev.inputMenu();
			if(inputMenu.equals("1")) {
				ed.add(ev.addEmp());
			} else if(inputMenu.equals("2")) {
				ev.printEmp(empList);
			} else if(inputMenu.equals("3")) {
				ss.startServer(empList);
			} else if(inputMenu.equals("4")) {
				eb = ss.conServer(ev.getHostAndPort());
			} else if(inputMenu.equals("5")) {
				ss.startServerDB();
			} else if(inputMenu.equals("6")) {
				empList = ss.conServerGetList(ev.getHostAndPort());
			}
		} while(!inputMenu.equals("q"));
	}

}
