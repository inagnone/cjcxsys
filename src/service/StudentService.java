package service;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import domain.Student;

public interface StudentService extends Service {
	/**
	 * 获取学生
	 * @return
	 */
	public List<Student> getStudents(Map<String, String[]> map);
	
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
	 * 批量导入学生
	 * @param stus
	 * @return
	 */
	public int addStudents(List<List<Student>> stus);
	
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
	public List<List<Student>> LoadExcel(String excelpath);
	
	/**
	 * 学生信息导出到excel文件
	 * @param list
	 */
	public HSSFWorkbook ExportExcel(List<Student> list);
	
	/**
	 * 模糊搜索学生
	 * @param map
	 * @return
	 */
	public List<Student> getStudentforadmin(Map<String, String[]> map);
	
	/**
	 * 验证学生信息
	 * @param stus
	 * @param messfile
	 * @return
	 */
	public boolean valide(List<List<Student>> stus,File messfile);
}
