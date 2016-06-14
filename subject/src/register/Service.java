package register;

import java.util.List;

public interface Service {
	List<Lecture> getAllLectures();
	List<Lecture> getAllMyRegister(int num);
	Lecture getLecture(int num);
	int setRegister(Register r);
	void removeRegister(int openNum, int studNum);
	List<Subject> getAllSubjects();
	List<Open> getAllMyOpens(int num);
	int setOpen(Open o);
	Open getOpen(int num);
	int editOpen(Open o);
	Subject delOpen(int num);
}
