package member;

import java.util.List;

public interface Dao {
	void insert(Member m);
	Member select(int num);
	void update(Member m);
	void delete(int num);
	List<Member> selectAll();
}
