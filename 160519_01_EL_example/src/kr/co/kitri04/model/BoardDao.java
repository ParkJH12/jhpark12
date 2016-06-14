package kr.co.kitri04.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDao {

	public List<BoardDto> getList() {
		// DB������ �Խñ� ��� �ҷ�����
		// board_id, title, writer, wdate, con_like, read_cnt;
		SetupDB sd = new SetupDB();
		String sql = null;
		sql = "SELECT BOARD_ID, TITLE, WRITER, READ_CNT, TO_CHAR(WDATE,'YYYY-MM-DD HH:MI:SS'), CONT_LIKE FROM KITRI_BOARD WHERE USE_YN = 'Y' ORDER BY REF_ID DESC, REPLY_LEVEL, BOARD_ID DESC";
		ResultSet rs = sd.select(sql);
		List<BoardDto> boardList = new ArrayList<BoardDto>();
		try {
			while (rs.next()) {
				// ������ DB ���� BoardDto ��ü ���� �� ����
				BoardDto bd = new BoardDto();
				bd.setBoard_id(rs.getInt(1));
				bd.setTitle(rs.getString(2));
				bd.setWriter(rs.getString(3));
				bd.setRead_cnt(rs.getInt(4));
				bd.setWdate(rs.getString(5));
				bd.setCon_like(rs.getInt(6));
				boardList.add(bd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sd.closeDB();
		}

		return boardList;
	}

	public BoardDto getContents(String b_id) {
		// TODO Auto-generated method stub
		SetupDB sb = new SetupDB();
		String sql = "SELECT BOARD_ID, TITLE, CONTENTS, WRITER, READ_CNT, TO_CHAR(WDATE,'YYYY-MM-DD HH:MI:SS'), PDS_LINK, CONT_LIKE, CONT_UNLIKE, PASSWORD FROM KITRI_BOARD WHERE USE_YN = 'Y' AND BOARD_ID = "
				+ b_id;
		ResultSet rs = sb.select(sql);
		BoardDto bDto = new BoardDto();
		try {
			if (rs.next()) {
				bDto.setBoard_id(rs.getInt(1));
				bDto.setTitle(rs.getString(2));
				bDto.setContents(rs.getString(3));
				bDto.setWriter(rs.getString(4));
				bDto.setRead_cnt(rs.getInt(5));
				bDto.setWdate(rs.getString(6));
				bDto.setPds_link(rs.getString(7));
				bDto.setCon_like(rs.getInt(8));
				bDto.setCon_unlike(rs.getInt(9));
				bDto.setPassword(rs.getString(10));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sb.closeDB();
		}
		return bDto;
	}

	public int writeContents(BoardDto bDto) {
		// Insert���� ���� bDto�� Database�� �ۼ�
		String sql = "INSERT INTO KITRI_BOARD(BOARD_ID, TITLE, CONTENTS, WRITER, PASSWORD, WDATE, PDS_LINK, REPLY_LEVEL, REF_ID) VALUES(SEQ_KITRI_BOARD_ID.NEXTVAL, ?, ?, ?, ?, SYSDATE, ?, 0, SEQ_KITRI_BOARD_ID.CURRVAL)";
		SetupDB sdb = new SetupDB();
		PreparedStatement pstmt = null;
		int chk = 0;
		try {
			pstmt = sdb.getCon().prepareStatement(sql);
			pstmt.setString(1, bDto.getTitle());
			pstmt.setString(2, bDto.getContents());
			pstmt.setString(3, bDto.getWriter());
			pstmt.setString(4, bDto.getPassword());
			pstmt.setString(5, bDto.getPds_link());
			chk = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			sdb.closeDB();
		}
		return chk;
	}

	public int modifyContents(BoardDto bDto) {
		String sql = "UPDATE KITRI_BOARD SET TITLE = ?, CONTENTS = ?, WRITER = ?, PASSWORD = ?, PDS_LINK = ? WHERE BOARD_ID = ? ";
		SetupDB sdb = new SetupDB();
		PreparedStatement pstmt = null;
		int chk = 0;
		try {
			pstmt = sdb.getCon().prepareStatement(sql);
			pstmt.setString(1, bDto.getTitle());
			pstmt.setString(2, bDto.getContents());
			pstmt.setString(3, bDto.getWriter());
			pstmt.setString(4, bDto.getPassword());
			pstmt.setString(5, bDto.getPds_link());
			pstmt.setInt(6, bDto.getBoard_id());
			chk = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			sdb.closeDB();
		}
		return chk;
	}

	public int delContents(String b_id) {
		// �Խ��� �� �����
		String sql = "UPDATE KITRI_BOARD SET USE_YN = 'N' WHERE BOARD_ID = ? ";
		SetupDB sdb = new SetupDB();
		PreparedStatement pstmt = null;
		int chk = 0;
		try {
			pstmt = sdb.getCon().prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(b_id));
			chk = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			sdb.closeDB();
		}
		return chk;
	}

	public int rep_writeContents(BoardDto bDto) {
		// TODO Auto-generated method stub
		// Insert���� ���� bDto�� Database�� �ۼ�
		String sql = "INSERT INTO KITRI_BOARD(BOARD_ID, TITLE, CONTENTS, WRITER, PASSWORD, WDATE, PDS_LINK, REPLY_LEVEL, REF_ID, READ_CNT, CONT_LIKE, CONT_UNLIKE) VALUES(SEQ_KITRI_BOARD_ID.NEXTVAL, ?, ?, ?, ?, SYSDATE, ?, (SELECT REPLY_LEVEL+1 FROM KITRI_BOARD WHERE BOARD_ID = ?), ?, 0, 0, 0)";
		SetupDB sdb = new SetupDB();
		PreparedStatement pstmt = null;
		int chk = 0;
		try {
			pstmt = sdb.getCon().prepareStatement(sql);
			pstmt.setString(1, bDto.getTitle());
			pstmt.setString(2, bDto.getContents());
			pstmt.setString(3, bDto.getWriter());
			pstmt.setString(4, bDto.getPassword());
			pstmt.setString(5, bDto.getPds_link());
			pstmt.setInt(6, bDto.getBoard_id());
			pstmt.setInt(7, bDto.getBoard_id());
			chk = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			sdb.closeDB();
		}
		return chk;
	}

}
