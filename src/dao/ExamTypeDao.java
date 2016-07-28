package dao;

import java.util.List;
import java.util.Map;

import domain.ExamType;

public interface ExamTypeDao extends Dao{
	
	/**
	 * 获取考试类型
	 * @return
	 */
	public List<ExamType> getExamType(Map<String, String[]> map);
	
	/**
	 * 更改考试类型名称
	 * @param type
	 * @return
	 */
	public ExamType updateExamType(ExamType type);
	
	/**
	 * 添加考试类型
	 * @param type
	 */
	public void addExamType(ExamType type);
}
