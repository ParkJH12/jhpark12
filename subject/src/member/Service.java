package member;

public interface Service {
	void addMember(Member m);
	Member getMember(int num);
	boolean login(int num, String name);
	void editInfo(Member m);
	void delMember(int num);
}
