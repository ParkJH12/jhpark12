package register;

public class Open {
	private int num;
	private int subjectNum;
	private String room;
	private String subDay;
	private String subTime;
	private int profNum;
	private String subjectName;
	
	public Open(int num, int subjectNum, String room, String subDay, String subTime, int profNum, String subjectName) {
		super();
		this.num = num;
		this.subjectNum = subjectNum;
		this.room = room;
		this.subDay = subDay;
		this.subTime = subTime;
		this.profNum = profNum;
		this.subjectName = subjectName;
	}

	public Open() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getSubjectNum() {
		return subjectNum;
	}

	public void setSubjectNum(int subjectNum) {
		this.subjectNum = subjectNum;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getSubDay() {
		return subDay;
	}

	public void setSubDay(String subDay) {
		this.subDay = subDay;
	}

	public String getSubTime() {
		return subTime;
	}

	public void setSubTime(String subTime) {
		this.subTime = subTime;
	}

	public int getProfNum() {
		return profNum;
	}

	public void setProfNum(int profNum) {
		this.profNum = profNum;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	
}
