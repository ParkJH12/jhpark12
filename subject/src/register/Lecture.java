package register;

public class Lecture {
	private int num;
	private String name;
	private String days;
	private String times;
	private String profName;
	
	public Lecture() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Lecture(int num, String name, String days, String times, String profName) {
		super();
		this.num = num;
		this.name = name;
		this.days = days;
		this.times = times;
		this.profName = profName;
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

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public String getProfName() {
		return profName;
	}

	public void setProfName(String profName) {
		this.profName = profName;
	}

	
}
