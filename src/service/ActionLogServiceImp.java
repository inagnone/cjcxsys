package service;

import java.util.List;
import java.util.Map;

import dao.ActionLog;
import domain.StudentActionLog;
import factory.BasicFactory;

public class ActionLogServiceImp implements ActionLogService{

	ActionLog dao = BasicFactory.getFactory().getDao(ActionLog.class);
	@Override
	public List<StudentActionLog> getStuActionLog(Map<String, String[]>map) {
		// TODO Auto-generated method stub
		return dao.getStulogs(map);
	}
	@Override
	public List<StudentActionLog> getStuActionLog(Map<String, String[]> map,
			int page, int rows) {
		// TODO Auto-generated method stub
		List<StudentActionLog> list = dao.getStulogs(map,page,rows);
		return list;
	}
	@Override
	public int countStuActionLog(Map<String, String[]>map) {
		// TODO Auto-generated method stub
		return dao.countStuLog(map);
	}

}
