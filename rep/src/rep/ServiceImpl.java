package rep;

import java.util.List;

public class ServiceImpl implements Service {
	
	private Dao dao;
	
	public ServiceImpl() {
		dao = new DaoImpl();
	}

	@Override
	public int addRep(Reply r) {
		return dao.insert(r);
	}

	@Override
	public Reply getRep(int num) {
		return dao.select(num);
	}

	@Override
	public List<Reply> getAll() {
		return dao.selectAll();
	}

	@Override
	public int editRep(Reply r) {
		return dao.update(r);
	}

	@Override
	public int delRep(int num) {
		return dao.delete(num);
	}

}
