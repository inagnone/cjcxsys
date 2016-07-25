package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.sun.org.apache.regexp.internal.REUtil;

import util.TransactionManager;
import domain.Student;

public class StudentDaoImp implements StudentDao {

	@Override
	public List<Student> getStudents() {
		// TODO Auto-generated method stub
		String sql = "select cj.id,name,company,personid,examname,examtype,exampc,sgqycj,sgdwcj,xmfrcj,zynlcj,examtime from cj left join examtype on cj.examtype = examtype.typeid";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanListHandler<Student>(Student.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addStudent(Student stu) {
		// TODO Auto-generated method stub
		String sql = "insert into cj values (null,?,?,?,?,?,?,?,?,?,?)";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			runner.update(sql,stu.getName(),stu.getCompany(),stu.getPersonid(),stu.getExamtype(),stu.getExampc(),stu.getSgqycj(),stu.getSgdwcj(),stu.getXmfrcj(),stu.getZynlcj(),stu.getExamtime());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteStudent(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from cj where id =?";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			runner.update(sql,id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Student updateStudent(Student stu) {
		// TODO Auto-generated method stub
		String sql = "update cj set name=?,company=?,personid=?,examtype=?,exampc=?,sgqycj=?,sgdwcj=?,xmfrcj=?,zynlcj=? where id = ?";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			runner.update(sql,stu.getName(),stu.getCompany(),stu.getPersonid(),stu.getExamtype(),stu.getExampc(),stu.getSgqycj(),stu.getSgdwcj(),stu.getXmfrcj(),stu.getZynlcj(),stu.getId());
			return stu;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Student getStudentbyId(int id) {
		// TODO Auto-generated method stub
		String sql = "select cj.id,name,company,personid,examname,examtype,exampc,sgqycj,sgdwcj,xmfrcj,zynlcj,examtime from cj left join examtype on cj.examtype = examtype.typeid where cj.id=?";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanHandler<Student>(Student.class),id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
