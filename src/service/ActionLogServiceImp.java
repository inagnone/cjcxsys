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

}
