package kr.co.stardust.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.util.List;

import kr.co.stardust.domain.EmpBean;

// EmpView Class : 화면 구성 요소 객체
public class EmpView {
	// 1. 기본 메뉴 출력 부분
	public void printMenu() {
		System.out.println("Emp 테스트 프로그램");
		System.out.println("원하는 메뉴를 선택하세요");
		System.out.println("1. 사원입력");
		System.out.println("2. 사원출력");
		System.out.println("3. 서버구동");
		System.out.println("4. 서버연결");
		System.out.println("5. DB서버구동");
		System.out.println("6. DB서버연결");
		System.out.println("q. 프로그램종료");
	}
	
	// 메뉴 입력 메소드
	public String inputMenu() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputMenu = "";
		try {
			inputMenu = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return inputMenu;
	}
	
	public EmpBean addEmp() {
		EmpBean eb = new EmpBean();
		System.out.println("사원번호를 입력하세요.");
		eb.setEmpno(Integer.parseInt(inputMenu()));
		System.out.println("사원이름을 입력하세요.");
		eb.setEname(inputMenu());
		System.out.println("매니저번호를 입력하세요.");
		eb.setMgr(Integer.parseInt(inputMenu()));
		System.out.println("업무명을 입력하세요.");
		eb.setJob(inputMenu());
		System.out.println("입사일자를 입력하세요.");
		eb.setHiredate(inputMenu());
		System.out.println("월급을 입력하세요.");
		eb.setSal(Integer.parseInt(inputMenu()));
		System.out.println("수당을 입력하세요.");
		eb.setComm(Integer.parseInt(inputMenu()));
		System.out.println("부서번호을 입력하세요.");
		eb.setDeptno(Integer.parseInt(inputMenu()));
		
		return eb;
	}
	
//	public void printEmp(EmpBean eb) {
//		System.out.println(eb);
//	}
	
	public void printEmp(List<EmpBean> list) {
		for(EmpBean eb : list) {
			System.out.println(eb);
		}
	}
	
	public InetSocketAddress getHostAndPort() {
		System.out.println("IP주소를 입력하세요.");
		String hostname = inputMenu();
		System.out.println("포트번호를 입력하세요.");
		int port = Integer.parseInt(inputMenu());
		return new InetSocketAddress(hostname, port);
	}
}
