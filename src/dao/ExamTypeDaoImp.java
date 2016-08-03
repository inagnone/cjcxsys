package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import util.TransactionManager;
import domain.ExamType;

public class ExamTypeDaoImp implements ExamTypeDao {
	private Logger log = LogManager.getRootLogger();
	@Override
	public List<ExamType> getExamType(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder("select * from examtype where");
		if(map.get("typeid")!=null && map.get("typeid")[0] != null && !map.get("typeid")[0].equals("")){
			sql.append(" typeid="+map.get("typeid")[0]+" and ");
		}
		if(map.get("typename")!=null && map.get("typename")[0] != null && !map.get("typename")[0].equals("")){
			sql.append(" examname='"+map.get("typename")[0]+"' and ");
		}
		sql.delete(sql.length()-5, sql.length());
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql.toString(), new BeanListHandler<ExamType>(ExamType.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ExamType updateExamType(ExamType type) {
		// TODO Auto-generated method stub
		String sql = "update examtype set examname = ? where typeid = ?";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			runner.update(sql,type.getExamname(),type.getTypeid());
			return type;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addExamType(ExamType type) {
		// TODO Auto-generated method stub
		String sql = "insert into examtype values(null,?,?)";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			runner.update(sql,type.getExamname(),type.getTypeid());
			log.info("添加类型");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
