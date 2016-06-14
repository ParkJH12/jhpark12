package kr.co.kitri04.model;

public class BoardDto {
	private int board_id;
	private String title;
	private String contents;
	private String writer;
	private String password;
	private String wdate;
	private int read_cnt;
	private String pds_link;
	private String pds_link_temp;
	private int pageNo;
	private int pageCount;
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public String getPds_link_temp() {
		return pds_link_temp;
	}
	public void setPds_link_temp(String pds_link_temp) {
		this.pds_link_temp = pds_link_temp;
	}
	private int con_like;
	private int con_unlike;
	private int reply_level;
	private int ref_id;
	
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	public int getRead_cnt() {
		return read_cnt;
	}
	public void setRead_cnt(int read_cnt) {
		this.read_cnt = read_cnt;
	}
	public String getPds_link() {
		return pds_link;
	}
	public void setPds_link(String pds_link) {
		this.pds_link = pds_link;
	}
	public int getCon_like() {
		return con_like;
	}
	public void setCon_like(int con_like) {
		this.con_like = con_like;
	}
	public int getCon_unlike() {
		return con_unlike;
	}
	public void setCon_unlike(int con_unlike) {
		this.con_unlike = con_unlike;
	}
	public int getReply_level() {
		return reply_level;
	}
	public void setReply_level(int reply_level) {
		this.reply_level = reply_level;
	}
	public int getRef_id() {
		return ref_id;
	}
	public void setRef_id(int ref_id) {
		this.ref_id = ref_id;
	}	
	
	
}
