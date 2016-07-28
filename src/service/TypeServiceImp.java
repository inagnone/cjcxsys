package service;

import java.util.List;
import java.util.Map;

import dao.ExamTypeDao;
import domain.ExamType;
import factory.BasicFactory;

public class TypeServiceImp implements TypeService {

	private ExamTypeDao typedao = BasicFactory.getFactory().getDao(ExamTypeDao.class);
	@Override
	public void addType(ExamType type) {
		// TODO Auto-generated method stub
		typedao.addExamType(type);
	}

	@Override
	public ExamType updateTypename(ExamType type) {
		// TODO Auto-generated method stub
		return typedao.updateExamType(type);
	}

	@Override
	public List<ExamType> getType(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return typedao.getExamType(map);
	}

}
