package addr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.support.JdbcUtils;

public class DaoImpl2 implements Dao {

	private DataSource dataSource;
	private Connection conn;
	
	/*public DaoImpl2(DataSource dataSource){
		this.dataSource = dataSource;
	}
	*/
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void insert(Member m) {
		// TODO Auto-generated method stub
		String sql = "insert into addr values(seq_addr.nextval, ?, ?, ?)";
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getName());
			pstmt.setString(2, m.getTel());
			pstmt.setString(3, m.getAddress());
			pstmt.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.closeConnection(conn);
		}
		

	}

	@Override
	public Member select(int num) {
		// TODO Auto-generated method stub
		Member m = null;
		ResultSet rs = null;
		String sql = "select * from addr where num=?";
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()){
				 m = new Member(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			JdbcUtils.closeConnection(conn);
		}
		return m;
	}

	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		ArrayList<Member> list = new ArrayList<Member>();
		ResultSet rs = null;
		String sql = "select * from addr";
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				list.add(new Member(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			JdbcUtils.closeConnection(conn);
		}
		return list;
	}

	@Override
	public void update(Member m) {
		// TODO Auto-generated method stub
		String sql = "update addr set name=?, tel=?, address=? where num=?";
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getName());
			pstmt.setString(2, m.getTel());
			pstmt.setString(3, m.getAddress());
			pstmt.setInt(4, m.getNum());
			pstmt.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			JdbcUtils.closeConnection(conn);
		}
	}
	

	@Override
	public void delect(int num) {
		// TODO Auto-generated method stub
		String sql = "delete addr where num=?";
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			JdbcUtils.closeConnection(conn);
		}
	}
}
