package open;

public class Open {
	private int num;
	private int subject_num; //fk
	private String room;
	private String sub_day;
	private String sub_time;
	private int prof_num; //fk
	
	public Open(){
		
	}
	public Open(int num, int subject_num, String room, String sub_day, String sub_time, int prof_num) {
		super();
		this.num = num;
		this.subject_num = subject_num;
		this.room = room;
		this.sub_day = sub_day;
		this.sub_time = sub_time;
		this.prof_num = prof_num;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getSubject_num() {
		return subject_num;
	}
	public void setSubject_num(int subject_num) {
		this.subject_num = subject_num;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getSub_day() {
		return sub_day;
	}
	public void setSub_day(String sub_day) {
		this.sub_day = sub_day;
	}
	public String getSub_time() {
		return sub_time;
	}
	public void setSub_time(String sub_time) {
		this.sub_time = sub_time;
	}
	public int getProf_num() {
		return prof_num;
	}
	public void setProf_num(int prof_num) {
		this.prof_num = prof_num;
	}
	
}
