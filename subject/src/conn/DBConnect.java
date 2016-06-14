package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	
	private static DBConnect db = new DBConnect();
	private Connection conn = null;

	String jdbc_driver = "core.log.jdbc.driver.OracleDriver"; //"oracle.jdbc.driver.OracleDriver";
	String jdbc_url = "jdbc:oracle:thin:@192.168.14.163:1521:xe";
	
	private DBConnect() {
		// TODO Auto-generated constructor stub
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url, "kitri04", "kitri04");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static DBConnect getInstance() {
		return db;
	}
	public Connection getConnection(){
		try {
			conn = DriverManager.getConnection(jdbc_url, "kitri04", "kitri04");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
}
