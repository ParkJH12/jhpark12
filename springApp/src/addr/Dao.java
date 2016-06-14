package addr;

import java.util.List;


public interface Dao {
	void insert(Member m);
	Member select(int num);
	List selectAll();
	void update(Member m);
	void delect(int num);
}
