package addr;

public class Member {
	int num;
	String name;
	String tel;
	String address;
	
	
	public Member(){}
	public Member(int num, String name, String tel, String address){
		this.num = num;
		this.name = name;
		this.tel = tel;
		this.address = address;
		
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + ", address=" + address + "]";
	}
	
}
