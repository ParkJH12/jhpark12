package register;

public class Subject {
	private int num;
	private String name;
	private boolean flag;
	
	public Subject(int num, String name, boolean flag) {
		super();
		this.num = num;
		this.name = name;
		this.flag = flag;
	}

	public Subject() {
		super();
		// TODO Auto-generated constructor stub
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

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	
	
	
}
