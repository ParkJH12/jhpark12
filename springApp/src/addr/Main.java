package addr;

import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.SayHello;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] config = new String[]{"config.xml"};
		ApplicationContext context = new ClassPathXmlApplicationContext(config);   
		Service service = (Service)context.getBean("addrService");
		service.addMember(new Member(0, "aaa", "111", "asdfs"));
		service.addMember(new Member(0, "bbb", "222", "qwer"));
		service.addMember(new Member(0, "ddd", "444", "wer45"));
		ArrayList<Member> list = 
				(ArrayList<Member>) service.getMembers();
		System.out.println(list);
		System.out.println(service.getMember(4));
		service.editMember(new Member(0, "qqq","1234","5678"));
		list= (ArrayList<Member>) service.getMembers();
		System.out.println(list);
		service.delMember(3);
		list= (ArrayList<Member>) service.getMembers();
		System.out.println(list);
	}

}
