package kr.co.stardust.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import kr.co.stardust.domain.EmpBean;

public class EmpDao {

	public void add(EmpBean eb) {
		String className = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "hr";
		String password = "hr";
		Connection conn = null;
		PreparedStatement pStmt = null;
		try {
			Class.forName(className);
			
			conn = DriverManager.getConnection(url, user, password);
			String sql = "INSERT INTO employees(employee_id, first_name, manager_id, job_id, hire_date, salary, commision_pct, department_id) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, eb.getEmpno());
			pStmt.setString(2, eb.getEname());
			pStmt.setInt(3, eb.getMgr());
			pStmt.setString(4, eb.getJob());
			pStmt.setString(5, eb.getHiredate());
			pStmt.setInt(6, eb.getSal());
			pStmt.setInt(7, eb.getComm());
			pStmt.setInt(8, eb.getDeptno());
			int updatedRows = pStmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(pStmt != null) {
				try {
					pStmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	// Database 연결 및 EmpTable 자료 가져오기
	public ArrayList<EmpBean> getEmpAll() {
		ArrayList<EmpBean> list = new ArrayList<EmpBean>();
		String className = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "hr";
		String password = "hr";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		EmpBean eb = null;
		try {
			Class.forName(className);
			
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			String sql = "SELECT * FROM employees";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				eb = new EmpBean();
				eb.setEmpno(rs.getInt("employee_id"));
				eb.setEname(rs.getString("first_name"));
				eb.setMgr(rs.getInt("manager_id"));
				eb.setJob(rs.getString("job_id"));
				eb.setHiredate(rs.getString("hire_date"));
				eb.setSal(rs.getInt("salary"));
				eb.setComm(rs.getInt("commission_pct"));
				eb.setDeptno(rs.getInt("department_id"));
				list.add(eb);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return list;
		
	}
}
