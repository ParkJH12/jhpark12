package addr;

import java.util.List;

public class ServiceImpl implements Service {

	private Dao dao;
	public ServiceImpl(){}
	public ServiceImpl(Dao dao) {
		this.dao = dao;
	}
	
	@Override
	public void addMember(Member m) {
		// TODO Auto-generated method stub
		dao.insert(m);
	}


	public void setDao(Dao dao) {
		this.dao = dao;
	}

	@Override
	public Member getMember(int num) {
		// TODO Auto-generated method stub
		return dao.select(num);
	}

	@Override
	public List getMembers() {
		// TODO Auto-generated method stub
		return dao.selectAll();
	}

	@Override
	public void editMember(Member m) {
		// TODO Auto-generated method stub
		dao.update(m);
	}

	@Override
	public void delMember(int num) {
		// TODO Auto-generated method stub
		dao.delect(num);
	}

}
