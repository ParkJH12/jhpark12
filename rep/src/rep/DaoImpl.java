package rep;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conn.DBConnect;

public class DaoImpl implements Dao {
	private DBConnect db;
	
	public DaoImpl() {
		db = DBConnect.getInstance();
	}
	
	private int makeNum() {
		Connection con = db.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		int num = 0;
		String sql = "SELECT seq_rep.NEXTVAL FROM DUAL";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				num = rs.getInt(1);
			}
			
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return num;
	}
	

	@Override
	public int insert(Reply r) {
		Connection con = db.getConnection();
		PreparedStatement pStmt = null;
		int num = makeNum();
		String sql = "INSERT INTO rep(num, name, content) VALUES(?, ?, ?)";
		
		try {
			pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, num);
			pStmt.setString(2, r.getName());
			pStmt.setString(3, r.getContent());
			pStmt.executeUpdate();
		
			pStmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return num;
	}

	@Override
	public Reply select(int num) {
		Connection con = db.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		Reply r = null;
		String sql = "SELECT * FROM rep WHERE num = " + num;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				r = new Reply(rs.getInt("num"), rs.getString("name"), rs.getString("content"));
			}
			
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return r;
	}

	@Override
	public List<Reply> selectAll() {
		Connection con = db.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		
		List<Reply> list = null;
		String sql = "SELECT * FROM rep ORDER BY num";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			list = new ArrayList<Reply>();
			
			while(rs.next()) {
				Reply r = new Reply(rs.getInt("num"), rs.getString("name"), rs.getString("content"));
				list.add(r);
			}
			
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int update(Reply r) {
		Connection con = db.getConnection();
		PreparedStatement pStmt = null;
		String sql = "UPDATE rep SET name = ?, content = ? WHERE num = ?";
		System.out.println("이름: " + r.getName());
		System.out.println("내용: " + r.getContent());
		
		try {
			pStmt = con.prepareStatement(sql);
			pStmt.setString(1, r.getName());
			pStmt.setString(2, r.getContent());
			pStmt.setInt(3, r.getNum());
			pStmt.executeUpdate();
			
			pStmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		return 0;
		return r.getNum();
	}

	@Override
	public int delete(int num) {
		Connection con = db.getConnection();
		Statement stmt = null;
		String sql = "DELETE FROM rep WHERE num = " + num;
		int rows = 0;
		
		try {
			stmt = con.createStatement();
			rows = stmt.executeUpdate(sql);
			
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rows;
	}

}
