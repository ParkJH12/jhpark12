package kr.co.kitri04.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDao {
	private SetupDB db = new SetupDB();

	public List<BoardDto> getList() {
		// DB에서 게시글 목록 불러오기
		// board_id, title, writer, wdate, cont_like, read_cnt 
		List<BoardDto> list = new ArrayList<BoardDto>();
		String sql = "SELECT board_id, title, writer, TO_CHAR(wdate, 'YYYY-MM-DD HH:MI:SS') write_date, cont_like, read_cnt FROM kitri_board WHERE use_yn = 'Y' ORDER BY board_id DESC";
		ResultSet rs = null;
		try {
			rs = db.select(sql);
			while(rs.next()) {
				BoardDto bd = new BoardDto();
				bd.setBoard_id(rs.getInt("board_id"));
				bd.setTitle(rs.getString("title"));
				bd.setWriter(rs.getString("writer"));
				bd.setWdate(rs.getString("write_date"));
				bd.setCont_like(rs.getInt("cont_like"));
				bd.setRead_cnt(rs.getInt("read_cnt"));
				list.add(bd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.closeDB();
		}
		
		return list;
	}

	public BoardDto getContents(String b_id) {
		BoardDto bd = null;
		String sql = "SELECT board_id, title, contents, writer, read_cnt, TO_CHAR(wdate, 'YYYY-MM-DD HH:MI:SS') write_date, pds_link, cont_like, cont_unlike FROM kitri_board WHERE use_yn = 'Y' AND board_id = " + b_id;
		ResultSet rs = null;
		rs = db.select(sql);
		try {
			if(rs.next()) {
				bd = new BoardDto();
				bd.setBoard_id(rs.getInt("board_id"));
				bd.setTitle(rs.getString("title"));
				bd.setContents(rs.getString("contents"));
				bd.setWriter(rs.getString("writer"));
				bd.setWdate(rs.getString("write_date"));
				bd.setPds_link(rs.getString("pds_link"));
				bd.setCont_like(rs.getInt("cont_like"));
				bd.setCont_unlike(rs.getInt("cont_unlike"));
				bd.setRead_cnt(rs.getInt("read_cnt"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.closeDB();
		}
		return bd;
	}

	public int setContents(BoardDto bDto) {
		String sql = "INSERT INTO KITRI_BOARD (BOARD_ID, TITLE, CONTENTS, WRITER, PASSWORD, READ_CNT, WDATE, CONT_LIKE, CONT_UNLIKE, PDS_LINK, REPLY_LEVEL, REF_ID) " 
				+ "VALUES(SEQ_KITRI_BOARD_ID.NEXTVAL, ?, ?, ?, ?, 0, SYSDATE, 0, 0, ?, 0, NULL)";
		PreparedStatement pStmt = null;
		int cnt = 0;
		try {
			pStmt = db.getConnection().prepareStatement(sql);
			pStmt.setString(1, bDto.getTitle());
			pStmt.setString(2, bDto.getContents());
			pStmt.setString(3, bDto.getWriter());
			pStmt.setString(4, bDto.getPassword());
			pStmt.setString(5, bDto.getPds_link());
			cnt = pStmt.executeUpdate();
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
			
			db.closeDB();
		}

		return cnt;
	}

	public String getPassword(String board_id) {
		String sql = "SELECT password FROM kitri_board WHERE board_id = " + board_id;
		ResultSet rs = null;
		rs = db.select(sql);
		String password = null;
		try {
			if(rs.next()) {
				password = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closeDB();
		}
		
		return password;
	}

	public int removeContents(String board_id, String password) {
		String sql = "UPDATE kitri_board SET use_yn = 'N' WHERE board_id = " + board_id + " AND password = '" + password + "'";
		int cnt = 0;
		cnt = db.delete(sql);
		db.closeDB();
		
		return cnt;
	}

	public int updateContents(BoardDto bDto) {
		String sql = null;

		PreparedStatement pStmt = null;
		int cnt = 0;
		try {
			sql = "UPDATE kitri_board SET password = ?, title = ?, contents = ?, writer = ?, pds_link = ? WHERE board_id = ?";
			pStmt = db.getConnection().prepareStatement(sql);
			pStmt.setString(1, bDto.getPassword());
			pStmt.setString(2, bDto.getTitle());
			pStmt.setString(3, bDto.getContents());
			pStmt.setString(4, bDto.getWriter());
			pStmt.setString(5, bDto.getPds_link());
			pStmt.setInt(6, bDto.getBoard_id());
			cnt = pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pStmt != null) {
				try {
					pStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			db.closeDB();
		}
		
		return cnt;
	}

	public void increaseCnt(String b_id) {
		String sql = "UPDATE kitri_board SET read_cnt = read_cnt + 1 WHERE board_id = " + b_id;
		int cnt = 0;
		cnt = db.update(sql);
		db.closeDB();
	}

	public void increaseLike(String b_id) {
		String sql = "UPDATE kitri_board SET cont_like = cont_like + 1 WHERE board_id = " + b_id;
		db.update(sql);
		db.closeDB();
	}
	
	public void increaseUnlike(String b_id) {
		String sql = "UPDATE kitri_board SET cont_unlike = cont_unlike + 1 WHERE board_id = " + b_id;
		db.update(sql);
		db.closeDB();
	}
}
