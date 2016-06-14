package member;

import java.util.ArrayList;
import java.util.List;

public class ServiceImpl implements Service {
	
	private Dao dao;
	
	public ServiceImpl() {
		dao = new DaoImpl();
	}

	@Override
	public void addMember(Member m) {
		dao.insert(m);
	}

	@Override
	public Member getMember(int num) {
		return dao.select(num);
	}

	@Override
	public boolean login(int num, String name) {
		Member m = dao.select(num);
		
		if(m != null && name.equals(m.getName())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void editInfo(Member m) {
		dao.update(m);

	}

	@Override
	public void delMember(int num) {
		dao.delete(num);
	}

	public boolean checkID(int num) {
		if(dao.select(num) == null)
			return false;
		else
			return true;
	}

	public boolean isSameNumber(int num) {
		if(dao.select(num) == null)
			return false;
		else
			return true;
	}

	public List<Member> getAllMembers() {
		return dao.selectAll();
	}

}
