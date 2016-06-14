package addr;

import java.util.List;

public interface Service {
	void addMember(Member m);
	Member getMember(int num);
	List getMembers();
	void editMember(Member m);
	void delMember(int num);
}
