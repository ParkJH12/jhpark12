package register;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conn.DBConnect;
import register.Open;
import register.OpenDao;

public class OpenDaoImpl implements OpenDao {
private DBConnect db;
	
	public OpenDaoImpl() {
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
	public int insert(Open o) {
		Connection con = db.getConnection();
		PreparedStatement pStmt = null;
		String sql = "INSERT INTO open(num, subject_num, room, sub_day, sub_time, prof_num) VALUES(?, ?, ?, ?, ?, ?)";
		int num = makeNum();
		
		try {
			pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, num);
			pStmt.setInt(2, o.getSubjectNum());
			pStmt.setString(3, o.getRoom());
			pStmt.setString(4, o.getSubDay());
			pStmt.setString(5, o.getSubTime());
			pStmt.setInt(6, o.getProfNum());
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
	public int update(Open o) {
		Connection con = db.getConnection();
		PreparedStatement pStmt = null;
		String sql = "UPDATE open SET subject_num = ?, room = ?, sub_day = ?, sub_time = ?, prof_num = ? WHERE num = ?";
		
		try {
			pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, o.getSubjectNum());
			pStmt.setString(2, o.getRoom());
			pStmt.setString(3, o.getSubDay());
			pStmt.setString(4, o.getSubTime());
			pStmt.setInt(5, o.getProfNum());
			pStmt.setInt(6, o.getNum());
			pStmt.executeUpdate();
			
			pStmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return o.getNum();
	}

	@Override
	public void delete(int num) {
		Connection con = db.getConnection();
		Statement stmt = null;
		String sql = "DELETE FROM open WHERE num = " + num;
		
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
	public Open select(int num) {
		Connection con = db.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		Open o = null;
		String sql = "SELECT o.num, subject_num, room, sub_day, sub_time, prof_num, s.name FROM open o, subject s WHERE o.subject_num = s.num AND o.num = " + num;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				o = new Open();
				o.setNum(rs.getInt("num"));
				o.setSubjectNum(rs.getInt("subject_num"));
				o.setRoom(rs.getString("room"));
				o.setSubDay(rs.getString("sub_day"));
				o.setSubTime(rs.getString("sub_time"));
				o.setProfNum(rs.getInt("prof_num"));
				o.setSubjectName(rs.getString("name"));
			}
			
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return o;
	}

	@Override
	public List<Open> selectAll() {
		Connection con = db.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<Open> list = null;
		String sql = "SELECT o.num, subject_num, room, sub_day, sub_time, prof_num, s.name FROM open o, subject s WHERE o.subject_num = s.num";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			list = new ArrayList<Open>();
			
			while(rs.next()) {
				Open o = new Open();
				o.setNum(rs.getInt("num"));
				o.setSubjectNum(rs.getInt("subject_num"));
				o.setRoom(rs.getString("room"));
				o.setSubDay(rs.getString("sub_day"));
				o.setSubTime(rs.getString("sub_time"));
				o.setProfNum(rs.getInt("prof_num"));
				o.setSubjectName(rs.getString("name"));
				
				list.add(o);
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
	public List<Open> selectAll(int num) {
		Connection con = db.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<Open> list = null;
		String sql = "SELECT o.num, subject_num, room, sub_day, sub_time, prof_num, s.name FROM open o, subject s WHERE o.subject_num = s.num AND prof_num = " + num;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			list = new ArrayList<Open>();
			
			while(rs.next()) {
				Open o = new Open();
				o.setNum(rs.getInt("num"));
				o.setSubjectNum(rs.getInt("subject_num"));
				o.setRoom(rs.getString("room"));
				o.setSubDay(rs.getString("sub_day"));
				o.setSubTime(rs.getString("sub_time"));
				o.setProfNum(rs.getInt("prof_num"));
				o.setSubjectName(rs.getString("name"));
				
				list.add(o);
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
	public boolean isOpened(int num) {
		Connection con = db.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT count(*) FROM open WHERE subject_num = " + num;
		boolean flag = false;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				if(rs.getInt(1) > 0) {
					flag = true;
				} else {
					flag = false;
				}	
			}
			
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("flag = " + flag);
		return flag;
	}

}
