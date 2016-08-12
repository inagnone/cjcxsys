package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import util.TransactionManager;
import domain.Student;
import domain.StudentActionLog;

public class ActionLogImp implements ActionLog {

	@Override
	public List<StudentActionLog> getStulogs(Map<String, String[]>map) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder("select * from actionlog where");
		if(map.get("user") != null && map.get("user")[0] !=null && !map.get("user")[0].equals("")){
			sql.append(" user='"+map.get("user")[0]+"' and ");
		}
		if(map.get("cjname") != null && map.get("cjname")[0] !=null && !map.get("cjname")[0].equals("")){
			sql.append(" cjname='"+map.get("cjname")[0]+"' and ");
		}
		if(map.get("action") != null && map.get("action")[0] !=null && !map.get("action")[0].equals("")){
			sql.append(" action='"+map.get("action")[0]+"' and ");
		}
		if(map.get("ip") != null && map.get("ip")[0] !=null && !map.get("ip")[0].equals("")){
			sql.append(" ip='"+map.get("ip")[0]+"' and ");
		}
		if(map.get("st") != null && map.get("st")[0] !=null && !map.get("st")[0].equals("")){
			if(map.get("et") != null && map.get("et")[0] !=null && !map.get("et")[0].equals("")){
				sql.append(" createdat between '"+map.get("st")[0]+"' and '"+map.get("et")[0]+"' and ");
			}
		}
		sql.delete(sql.length()-5, sql.length());
		sql.append(" order by createdat desc");
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql.toString(), new BeanListHandler<StudentActionLog>(StudentActionLog.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int countStuLog(Map<String, String[]>map) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder("select count(*) from actionlog where");
		if(map.get("user") != null && map.get("user")[0] !=null && !map.get("user")[0].equals("")){
			sql.append(" user='"+map.get("user")[0]+"' and ");
		}
		if(map.get("cjname") != null && map.get("cjname")[0] !=null && !map.get("cjname")[0].equals("")){
			sql.append(" cjname='"+map.get("cjname")[0]+"' and ");
		}
		if(map.get("action") != null && map.get("action")[0] !=null && !map.get("action")[0].equals("")){
			sql.append(" action='"+map.get("action")[0]+"' and ");
		}
		if(map.get("ip") != null && map.get("ip")[0] !=null && !map.get("ip")[0].equals("")){
			sql.append(" ip='"+map.get("ip")[0]+"' and ");
		}
		if(map.get("st") != null && map.get("st")[0] !=null && !map.get("st")[0].equals("")){
			if(map.get("et") != null && map.get("et")[0] !=null && !map.get("et")[0].equals("")){
				sql.append(" createdat between '"+map.get("st")[0]+"' and '"+map.get("et")[0]+"' and ");
			}
		}
		sql.delete(sql.length()-5, sql.length());
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return ((Long)runner.query(sql.toString(), new ScalarHandler())).intValue();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<StudentActionLog> getStulogs(Map<String, String[]> map,
			int page, int rows) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder("select * from actionlog where");
		if(map.get("user") != null && map.get("user")[0] !=null && !map.get("user")[0].equals("")){
			sql.append(" user='"+map.get("user")[0]+"' and ");
		}
		if(map.get("cjname") != null && map.get("cjname")[0] !=null && !map.get("cjname")[0].equals("")){
			sql.append(" cjname='"+map.get("cjname")[0]+"' and ");
		}
		if(map.get("action") != null && map.get("action")[0] !=null && !map.get("action")[0].equals("")){
			sql.append(" action='"+map.get("action")[0]+"' and ");
		}
		if(map.get("ip") != null && map.get("ip")[0] !=null && !map.get("ip")[0].equals("")){
			sql.append(" ip='"+map.get("ip")[0]+"' and ");
		}
		if(map.get("st") != null && map.get("st")[0] !=null && !map.get("st")[0].equals("")){
			if(map.get("et") != null && map.get("et")[0] !=null && !map.get("et")[0].equals("")){
				sql.append(" createdat between '"+map.get("st")[0]+"' and '"+map.get("et")[0]+"' and ");
			}
		}
		sql.delete(sql.length()-5, sql.length());
		sql.append(" order by createdat desc limit "+(page-1)*rows+","+rows);
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql.toString(), new BeanListHandler<StudentActionLog>(StudentActionLog.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	

}
