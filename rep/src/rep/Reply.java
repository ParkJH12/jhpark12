package rep;

public class Reply {
	private int num;
	private String name;
	private String content;
	
	
	@Override
	public String toString() {
		return "Reply [num=" + num + ", name=" + name + ", content=" + content + "]";
	}

	public Reply() {
	}
	
	public Reply(int num, String name, String content) {
		super();
		this.num = num;
		this.name = name;
		this.content = content;
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
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
