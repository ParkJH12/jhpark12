package register;

import java.util.List;

public class ServiceImpl implements Service {
	
	private Dao dao;
	private OpenDao openDao;
	private SubjectDao subDao;
	
	public ServiceImpl() {
		dao = new DaoImpl();
		openDao = new OpenDaoImpl();
		subDao = new SubjectDaoImpl();
	}

	@Override
	public List<Lecture> getAllLectures() {
		return dao.selectAll();
	}

	@Override
	public List<Lecture> getAllMyRegister(int num) {
		// TODO Auto-generated method stub
		return dao.selectAll(num);
	}
	
	@Override
	public Lecture getLecture(int num) {
		return dao.select(num);
	}

	@Override
	public int setRegister(Register r) {
		// TODO Auto-generated method stub
		return dao.insert(r);
	}

	@Override
	public void removeRegister(int openNum, int studNum) {
		// TODO Auto-generated method stub
		dao.delete(openNum, studNum);

	}
	
	@Override
	public List<Subject> getAllSubjects() {
		return subDao.selectAll();
	}
	
	@Override
	public List<Open> getAllMyOpens(int num) {
		return openDao.selectAll(num);
	}
	
	@Override
	public int setOpen(Open o) {
		Subject s = subDao.select(o.getSubjectNum());
		s.setFlag(true);
		subDao.update(s);
		return openDao.insert(o);
	}
	
	@Override
	public Open getOpen(int num) {
		return openDao.select(num);
	}
	
	@Override
	public int editOpen(Open o) {
		return openDao.update(o);
	}
	
	@Override
	public Subject delOpen(int num) {
		int subNum = openDao.select(num).getSubjectNum();
		openDao.delete(num);
		Subject s = subDao.select(subNum);
		if(!openDao.isOpened(subNum)) {
			s.setFlag(false);
			subDao.update(s);
		}
		
		return s;
	}
}
