package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import util.TransactionManager;
import domain.ExamType;

public class ExamTypeDaoImp implements ExamTypeDao {

	@Override
	public List<ExamType> getExamType() {
		// TODO Auto-generated method stub
		String sql = "select * from examtype";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanListHandler<ExamType>(ExamType.class));
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
