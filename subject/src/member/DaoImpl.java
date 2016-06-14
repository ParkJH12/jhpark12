package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conn.DBConnect;

public class DaoImpl implements Dao {
	private Connection conn;
	private DBConnect db;
	Statement stmt;
	PreparedStatement pStmt;
	ResultSet rs;
	
	public DaoImpl() {
		db = DBConnect.getInstance();
	}
	
	@Override
	public void insert(Member m) {
		conn = db.getConnection();
		String sql = "INSERT INTO member(num, name, tel, email, dept, type) VALUES(?, ?, ?, ?, ?, ?)";
		PreparedStatement pStmt = null;
		int row = 0;
		try {
			pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, m.getNum());
			pStmt.setString(2, m.getName());
			pStmt.setString(3, m.getTel());
			pStmt.setString(4, m.getEmail());
			pStmt.setString(5, m.getDept());
			pStmt.setInt(6, m.getType());
			row = pStmt.executeUpdate();
			
			pStmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(row == 0) {
			System.out.println("Insert failed");
		} else {
			System.out.println("Insert succeed");
		}
	}

	@Override
	public Member select(int num) {
		conn = db.getConnection();
		Member m = null;
		String sql = "SELECT num, name, tel, email, dept, type FROM member WHERE num = " + num;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				m = new Member();
				m.setNum(rs.getInt(1));
				m.setName(rs.getString(2));
				m.setTel(rs.getString(3));
				m.setEmail(rs.getString(4));
				m.setDept(rs.getString(5));
				m.setType(rs.getInt(6));
			}
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return m;
	}

	@Override
	public void update(Member m) {
		conn = db.getConnection();
		String sql = "UPDATE member SET num = ?, name = ?, tel = ?, email = ?, dept = ?, type = ? WHERE num = ?";
		int row = 0;
		
		try {
			pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, m.getNum());
			pStmt.setString(2, m.getName());
			pStmt.setString(3, m.getTel());
			pStmt.setString(4, m.getEmail());
			pStmt.setString(5, m.getDept());
			pStmt.setInt(6, m.getType());
			pStmt.setInt(7, m.getNum());
			row = pStmt.executeUpdate();
			
			pStmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		if(row == 0) {
			System.out.println("Update failed");
		} else {
			System.out.println("Update succeed");
		}
	}

	@Override
	public void delete(int num) {
		conn = db.getConnection();
		String sql = "DELETE FROM member WHERE num = " + num;
		int row = 0;
		
		try {
			stmt = conn.createStatement();
			row = stmt.executeUpdate(sql);
			
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(row == 0) {
			System.out.println("Delete failed");
		} else {
			System.out.println("Delete succeed");
		}

	}

	@Override
	public List<Member> selectAll() {
		conn = db.getConnection();
		String sql = "SELECT * FROM member ORDER BY num";
		List<Member> list = null;
		
		try {
			list = new ArrayList<Member>();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Member m = new Member();
				m.setNum(rs.getInt("num"));
				m.setName(rs.getString("name"));
				m.setTel(rs.getString("tel"));
				m.setEmail(rs.getString("email"));
				m.setDept(rs.getString("dept"));
				m.setType(rs.getInt("type"));
				list.add(m);
			}
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return list;
	}

}
