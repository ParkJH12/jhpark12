package open;

import java.util.List;

public interface Service {
	void addOpen(Open m);
	Open getOpen(int num);
	void editOpen(Open m);
	void delOpen(int num);
	List<Open> getAll(int prof_num);
}
