package dao;

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
}
