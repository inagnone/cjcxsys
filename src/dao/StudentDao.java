package dao;

import java.sql.BatchUpdateException;
import java.util.List;
import java.util.Map;

import domain.Student;

public interface StudentDao extends Dao {
	
	/**
	 * 获取学生成绩
	 * @return
	 */
	public List<Student> getStudents(Map<String, String[]> map);
	
	/**
	 * 添加学生成绩
	 * @param stu
	 */
	public void addStudent(Student stu);
	
	/**
	 * 批量添加学生成绩
	 * @param stus
	 * @throws BatchUpdateException 
	 */
	public int addStudents(List<Student> stus);
	
	/**
	 * 删除学生成绩
	 * @param stu
	 */
	public void deleteStudent(int id);
	
	/**
	 * 修改学生成绩
	 * @param stu
	 * @return
	 */
	public Student updateStudent(Student stu);
	
	/**
	 * 更据id获取学生
	 * @param id
	 * @return
	 */
	public Student getStudentbyId(int id);
	
	/**
	 * 查找学生成绩，模糊搜索
	 * @param map
	 * @return
	 */
	public List<Student> getStudentforadmin(Map<String, String[]> map);
	
	/**
	 * 通过姓名获取用户
	 * @param name
	 * @return
	 */
	public Student getStudentByName(String name);
	
	/**
	 * 通过身份证获取用户
	 * @param personid
	 * @return
	 */
	public Student getStudentByPersonId(String personid);
	
	/**
	 * 通过身份证和密码获取用户
	 * @param name
	 * @param personid
	 * @return
	 */
	public Student getStudent(String name,String personid);
}
