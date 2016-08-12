package service;

import java.util.List;
import java.util.Map;

import domain.StudentActionLog;

public interface ActionLogService extends Service{
	
	/**
	 * 获取成绩日志记录(前台分页)
	 * @return
	 */
	public List<StudentActionLog> getStuActionLog(Map<String, String[]>map);
	
	/**
	 * 获取成绩日志记录(后台分页)
	 * @param map
	 * @return
	 */
	public List<StudentActionLog> getStuActionLog(Map<String, String[]> map,int page,int rows);
	
	/**
	 * 统计学生成绩总数
	 * @return
	 */
	public int countStuActionLog(Map<String, String[]>map);
}
