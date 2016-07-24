package dao;

import java.util.List;

import domain.Student;

public interface StudentDao extends Dao {
	
	/**
	 * 获取学生成绩
	 * @return
	 */
	public List<Student> getStudents();
	
	/**
	 * 添加学生成绩
	 * @param stu
	 */
	public void addStudent(Student stu);
	
	/**
	 * 删除学生成绩
	 * @param stu
	 */
	public void deleteStudent(Student stu);
	
	/**
	 * 修改学生成绩
	 * @param stu
	 * @return
	 */
	public Student updateStudent(Student stu);
}
