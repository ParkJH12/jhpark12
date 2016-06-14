package register;

import java.util.List;

public interface SubjectDao {
	int insert(String name);
	void update(Subject s);
	void delete(int num);
	Subject select(int num);
	List<Subject> selectAll();
}
