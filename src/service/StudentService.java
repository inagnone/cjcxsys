package service;

import java.io.File;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import domain.Student;

public interface StudentService extends Service {
	/**
	 * 获取学生
	 * @return
	 */
	public List<Student> getStudents();
	
	/**
	 * 更据id获取学生
	 * @param id
	 * @return
	 */
	public Student getStudentbyId(int id);
	
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
	
	/**
	 * 删除学生
	 * @param stu
	 */
	public void deleteStu(int id);
	
	/**
	 * 导入excel中的学生信息
	 * @param file
	 * @return
	 */
	public List<Student> LoadExcel(String excelpath,File messfile);
	
	/**
	 * 学生信息导出到excel文件
	 * @param list
	 */
	public HSSFWorkbook ExportExcel(List<Student> list);
}
