package dao;

import java.sql.BatchUpdateException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.MDC;

import com.mysql.jdbc.PreparedStatement;

import util.TransactionManager;
import domain.Student;

public class StudentDaoImp implements StudentDao {
	private Log log = LogFactory.getLog(this.getClass());
	
	@Override
	public List<Student> getStudents(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder("select cj.id,name,company,personid,examname,examtype,exampc,sgqycj,sgdwcj,"
				+ "xmfrcj,zynlcj,examtime from cj left join examtype on cj.examtype = examtype.typeid where");
		if(map.get("name") != null && map.get("name")[0] != null && !map.get("name")[0].equals("")){
			sql.append(" name='"+map.get("name")[0]+"' and ");
		}
		if(map.get("personid") != null && map.get("personid")[0] != null && !map.get("personid")[0].equals("")){
			sql.append(" personid='"+map.get("personid")[0]+"' and ");
		}
		sql.delete(sql.length()-5, sql.length());
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql.toString(), new BeanListHandler<Student>(Student.class));
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
			log.info("添加学生"+stu.getName());
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
			log.info("删除学生");
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
			log.warn("更新");
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

	@Override
	public int addStudents(List<Student> stus) {
		// TODO Auto-generated method stub
		String sql = "insert into cj values (null,?,?,?,?,?,?,?,?,?,?)";
		QueryRunner runner;
		Object[][] params;
		int i = 0;
		DataSource source;
		try {
			runner = new QueryRunner(TransactionManager.getSource());
			params = new Object[stus.size()][10];
			for(i=0;i<stus.size();i++){
				params[i][0] = stus.get(i).getName();
				params[i][1] = stus.get(i).getCompany();
				params[i][2] = stus.get(i).getPersonid();
				params[i][3] = stus.get(i).getExamtype();
				params[i][4] = stus.get(i).getExampc();
				params[i][5] = stus.get(i).getSgqycj();
				params[i][6] = stus.get(i).getSgdwcj();
				params[i][7] = stus.get(i).getXmfrcj();
				params[i][8] = stus.get(i).getZynlcj();
				params[i][9] = stus.get(i).getExamtime();
				log.info("添加学生"+stus.get(i).getName());
			}
			return runner.batch(sql, params).length;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getClass());
			e.printStackTrace();
			String[] mess = e.getMessage().split(":");
			if(mess.length>1){
				if(mess[0].contains("Duplicate entry")){
					String[] reason = mess[0].split(" ");
					String[] personid = reason[2].split("'");
					throw new RuntimeException("身份证为："+personid[1]+"的数据存在重复，请核对信息并保证数据身份证字段唯一后重新导入原文件");
				}
			}
		}
		return 0;
	}

	@Override
	public List<Student> getStudentforadmin(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder("select cj.id,name,company,personid,examname,examtype,exampc,sgqycj,sgdwcj,"
				+ "xmfrcj,zynlcj,examtime from cj left join examtype on cj.examtype = examtype.typeid where");
		if(map.get("name") != null && map.get("name")[0] != null && !map.get("name")[0].equals("")){
			sql.append(" name like '%"+map.get("name")[0]+"%' and ");
		}
		if(map.get("personid") != null && map.get("personid")[0] != null && !map.get("personid")[0].equals("")){
			sql.append(" personid = '"+map.get("personid")[0]+"' and ");
		}
		sql.delete(sql.length()-5, sql.length());
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql.toString(), new BeanListHandler<Student>(Student.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Student getStudentbypersonId(String personid){
		String sql = "select cj.id,name,company,personid,examname,examtype,exampc,sgqycj,sgdwcj,xmfrcj,zynlcj,examtime from cj left join examtype on cj.examtype = examtype.typeid where cj.personid=?";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanHandler<Student>(Student.class),personid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Student getStudentByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student getStudentByPersonId(String personid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student getStudent(String name, String personid) {
		// TODO Auto-generated method stub
		String sql = "select cj.id,name,company,personid,examname,examtype,exampc,sgqycj,sgdwcj,xmfrcj,zynlcj,examtime from cj left join examtype on cj.examtype = examtype.typeid where name=? and personid=?";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanHandler<Student>(Student.class),name,personid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
