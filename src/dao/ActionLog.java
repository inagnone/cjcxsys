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
	
}
