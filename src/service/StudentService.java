package service;

import java.util.List;

import domain.Student;

public interface StudentService extends Service {
	/**
	 * 获取学生
	 * @return
	 */
	public List<Student> getStudents();
	
	/**
	 * 添加学生
	 */
	public void addStudent(Student stu);
	
	/**
	 * 修改学生
	 * @param stu
	 * @return
	 */
	public Student updateStudent(Student stu);
}
