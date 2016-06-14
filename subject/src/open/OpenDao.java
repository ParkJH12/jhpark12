package open;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conn.DBConnect;

public class OpenDao implements Dao {
	private Connection conn;
	private DBConnect db;
	Statement stmt;
	PreparedStatement pStmt;
	ResultSet rs;
	
	public OpenDao(){
		db = DBConnect.getInstance();
	}
	
	@Override
	public void insert(Open m) {
		// TODO Auto-generated method stub

	}

	@Override
	public Open select(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Open m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int num) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Open> selectAll(int prof_num) {
		conn = db.getConnection();
		String sql = "SELECT * FROM open WHERE prof_num="+prof_num;
		List<Open> list = null;
		
		try {
			list = new ArrayList<Open>();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Open m = new Open();
				m.setNum(rs.getInt("num"));
				m.setSubject_num(rs.getInt("subject_num"));
				m.setRoom(rs.getString("room"));
				m.setSub_day(rs.getString("sub_day"));
				m.setSub_time(rs.getString("sub_time"));
				m.setProf_num(rs.getInt("prof_num"));
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
