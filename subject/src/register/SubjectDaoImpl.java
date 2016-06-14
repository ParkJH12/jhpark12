package register;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conn.DBConnect;

public class SubjectDaoImpl implements SubjectDao {
	private DBConnect db;
	
	public SubjectDaoImpl() {
		db = DBConnect.getInstance();
	}

	private int makeNum() {
		Connection con = db.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		int num = 0;
		String sql = "SELECT SEQ_SUBJECT.NEXTVAL FROM DUAL";
		
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
	public int insert(String name) {
		Connection con = db.getConnection();
		PreparedStatement pStmt = null;
		String sql = "INSERT INTO subject(num, name) VALUES(?, ?)";
		int num = makeNum();
		
		try {
			pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, num);
			pStmt.setString(2, name);
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
	public void update(Subject s) {
		Connection con = db.getConnection();
		PreparedStatement pStmt = null;
		String sql = "UPDATE subject SET name = ?, flag = ? WHERE num = ?";
		
		try {
			pStmt = con.prepareStatement(sql);
			pStmt.setString(1, s.getName());
			if(s.isFlag()) {
				pStmt.setInt(2, 1);
			} else {
				pStmt.setInt(2, 0);
			}
			pStmt.setInt(3, s.getNum());
			pStmt.executeUpdate();
			
			pStmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int num) {
		Connection con = db.getConnection();
		Statement stmt = null;
		String sql = "DELETE FROM subject WHERE num = " + num;
		
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Subject select(int num) {
		Connection con = db.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		Subject s = null;
		String sql = "SELECT num, name, flag FROM subject WHERE num = " + num;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				s = new Subject();
				s.setNum(rs.getInt("num"));
				s.setName(rs.getString("name"));
				if(rs.getInt("flag") == 1) {
					s.setFlag(true);
				} else {
					s.setFlag(false);
				}
			}
			
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return s;
	}

	@Override
	public List<Subject> selectAll() {
		Connection con = db.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<Subject> list = null;
		String sql = "SELECT num, name, flag FROM subject";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			list = new ArrayList<Subject>();
			
			while(rs.next()) {
				Subject s = new Subject();
				s.setNum(rs.getInt("num"));
				s.setName(rs.getString("name"));
				if(rs.getInt("flag") == 1) {
					s.setFlag(true);
				} else {
					s.setFlag(false);
				}
				
				list.add(s);
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

}
