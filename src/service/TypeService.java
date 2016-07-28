package service;

import java.util.List;
import java.util.Map;

import domain.ExamType;

public interface TypeService extends Service {
	
	/**
	 * 
	 * 获取考试类型
	 * @return
	 */
	public List<ExamType> getType(Map<String, String[]> map);
	
	/**
	 * 添加类型
	 * @param type
	 */
	public void addType(ExamType type);
	
	/**
	 * 修改类型名称
	 * @param type
	 * @return
	 */
	public ExamType updateTypename(ExamType type);
	
}
