package register;

import java.util.List;

public interface Dao {
	int insert(Register r);
	void update(Register r);
	void delete(int openNum, int studNum);
	Lecture select(int num);
	List<Lecture> selectAll(int studNum);
	List<Lecture> selectAll();
}
