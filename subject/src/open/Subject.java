package open;

public class Subject {
	private int num;
	private String name;
	private int flag;
	
	public Subject(){}
	public Subject(int num, String name, int flag) {
		super();
		this.num = num;
		this.name = name;
		this.flag = flag;
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
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
}
