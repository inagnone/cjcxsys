package dao;

import java.util.List;
import java.util.Map;

import domain.StudentActionLog;

public interface ActionLog extends Dao{
	
	/**
	 * 获取成绩日志信息
	 * @return
	 */
	public List<StudentActionLog> getStulogs(Map<String, String[]>map);
	
	/**
	 * 获取成绩日志（后台分页）
	 * @param map
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<StudentActionLog> getStulogs(Map<String, String[]>map,int page,int rows);
	
	/**
	 * 统计日志总数
	 * @return
	 */
	public int countStuLog(Map<String, String[]>map);
	
}
