package register;

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
		String sql = "SELECT SEQ_REGISTER.NEXTVAL FROM DUAL";
		
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
	public int insert(Register r) {
		Connection conn = db.getConnection();
		PreparedStatement pStmt = null;
		String sql = "INSERT INTO register(num, open_num, stud_num, sub_year, quarter) VALUES(?, ?, ?, ?, ?)";
		int num = makeNum();
		
		try {
			pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, num);
			pStmt.setInt(2, r.getOpenNum());
			pStmt.setInt(3, r.getStudNum());
			pStmt.setInt(4, r.getSubYear());
			pStmt.setInt(5, r.getQuarter());
			pStmt.executeUpdate();
			
			pStmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return num;
	}

	@Override
	public void update(Register r) {

	}

	@Override
	public void delete(int openNum, int studNum) {
		Connection conn = db.getConnection();
		Statement stmt = null;
		String sql = "DELETE FROM register WHERE open_num = " + openNum + " AND stud_num = " + studNum;
		
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Lecture select(int num) {
		Connection conn = db.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		Lecture l = null;
		String sql = "SELECT o.num, s.name AS sub_name, sub_day, sub_time, m.name AS prof_name "
				+ "FROM open o, member m, subject s "
				+ "WHERE 1=1 "
				+ "AND o.prof_num = m.num "
				+ "AND o.subject_num = s.num "
				+ "AND o.num = " + num + " "
				+ "ORDER BY o.num";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				l = new Lecture();
				l.setNum(rs.getInt("num"));
				l.setName(rs.getString("sub_name"));
				l.setDays(rs.getString("sub_day"));
				l.setTimes(rs.getString("sub_time"));
				l.setProfName(rs.getString("prof_name"));
			}
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return l;
	}
	
	@Override
	public List<Lecture> selectAll(int studNum) {
		Connection conn = db.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<Lecture> list = null;
		String sql = "SELECT o.num, s.name AS sub_name, sub_day, sub_time, m.name AS prof_name "
				+ "FROM open o, member m, subject s "
				+ "WHERE 1=1 "
				+ "AND o.prof_num = m.num "
				+ "AND o.subject_num = s.num "
				+ "AND o.num IN (SELECT open_num FROM register WHERE stud_num = " + studNum + ") "
				+ "ORDER BY o.num";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			list = new ArrayList<Lecture>();
			
			while(rs.next()) {
				Lecture l = new Lecture();
				l.setNum(rs.getInt("num"));
				l.setName(rs.getString("sub_name"));
				l.setDays(rs.getString("sub_day"));
				l.setTimes(rs.getString("sub_time"));
				l.setProfName(rs.getString("prof_name"));
				list.add(l);
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

	@Override
	public List<Lecture> selectAll() {
		Connection conn = db.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<Lecture> list = null;
		String sql = "SELECT o.num, s.name AS sub_name, sub_day, sub_time, m.name AS prof_name "
				+ "FROM open o, member m, subject s "
				+ "WHERE o.prof_num = m.num AND o.subject_num = s.num "
				+ "ORDER BY o.num";

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			list = new ArrayList<Lecture>();
			
			while(rs.next()) {
				Lecture l = new Lecture();
				l.setNum(rs.getInt("num"));
				l.setName(rs.getString(2));
				l.setDays(rs.getString("sub_day"));
				l.setTimes(rs.getString("sub_time"));
				l.setProfName(rs.getString("prof_name"));
				list.add(l);
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
