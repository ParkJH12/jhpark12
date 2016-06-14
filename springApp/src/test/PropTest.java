package test;

import java.util.Properties;

public class PropTest {
	private Properties p;

	public Properties getP() {
		return p;
	}

	public void setP(Properties p) {
		this.p = p;
	}
	public void printInfo(){
		System.out.println("ip: "+p.getProperty("ip"));
		System.out.println("id: "+p.getProperty("id"));
		System.out.println("pwd: "+p.getProperty("pwd"));

	}
	
}
