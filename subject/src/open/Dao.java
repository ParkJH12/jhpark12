package open;

import java.util.List;

public interface Dao {
	void insert(Open m);
	Open select(int num);
	void update(Open m);
	void delete(int num);
	List<Open> selectAll(int prof_num);
}
