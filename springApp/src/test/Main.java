package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		String[] config = new String[]{"config.xml"};
		ApplicationContext context = new ClassPathXmlApplicationContext(config);   
		SayHello s = (SayHello)context.getBean("myBean");
		PropTest p = (PropTest)context.getBean("propTest");
		s.printMsg();
		p.printInfo();
		}

	}


