package member;

public class Member {
	private int num;
	private String name;
	private String tel;
	private String email;
	private String dept;
	private int type;
	
	public Member() {
	}
	
	public Member(int num, String name, String tel, String email, String dept, int type) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
		this.email = email;
		this.dept = dept;
		this.type = type;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}
