package open;

import java.util.List;

public class OpenService implements Service {
	private OpenDao dao;
	
	public OpenService() {
		dao = new OpenDao();
	}
	
	@Override
	public void addOpen(Open m) {
		// TODO Auto-generated method stub

	}

	@Override
	public Open getOpen(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void editOpen(Open m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delOpen(int num) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Open> getAll(int prof_num) {
		// TODO Auto-generated method stub
		return dao.selectAll(prof_num);
	}

}
