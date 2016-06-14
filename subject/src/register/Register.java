package register;

public class Register {
	private int num;
	private int openNum;
	private int studNum;
	private int subYear;
	private int quarter;
	
	public Register() {
		super();
	}

	public Register(int num, int openNum, int studNum, int subYear, int quarter) {
		super();
		this.num = num;
		this.openNum = openNum;
		this.studNum = studNum;
		this.subYear = subYear;
		this.quarter = quarter;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getOpenNum() {
		return openNum;
	}

	public void setOpenNum(int openNum) {
		this.openNum = openNum;
	}

	public int getStudNum() {
		return studNum;
	}

	public void setStudNum(int studNum) {
		this.studNum = studNum;
	}

	public int getSubYear() {
		return subYear;
	}

	public void setSubYear(int subYear) {
		this.subYear = subYear;
	}

	public int getQuarter() {
		return quarter;
	}

	public void setQuarter(int quarter) {
		this.quarter = quarter;
	}
}
