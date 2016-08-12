package service;

import java.util.List;
import java.util.Map;

import domain.StudentActionLog;

public interface ActionLogService extends Service{
	
	/**
	 * 获取成绩日志记录
	 * @return
	 */
	public List<StudentActionLog> getStuActionLog(Map<String, String[]>map);
}
