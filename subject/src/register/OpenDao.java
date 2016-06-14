package register;

import java.util.List;

public interface OpenDao {
	int insert(Open o);
	int update(Open o);
	void delete(int num);
	Open select(int num);
	List<Open> selectAll();
	List<Open> selectAll(int num);
	boolean isOpened(int num);
}
