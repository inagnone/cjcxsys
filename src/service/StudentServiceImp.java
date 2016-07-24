package service;

import java.util.List;

import dao.StudentDao;
import domain.Student;
import factory.BasicFactory;

public class StudentServiceImp implements StudentService {

	private StudentDao studentdao = BasicFactory.getFactory().getDao(StudentDao.class);
	@Override
	public List<Student> getStudents() {
		// TODO Auto-generated method stub
		return studentdao.getStudents();
	}

	@Override
	public void addStudent(Student stu) {
		// TODO Auto-generated method stub
		studentdao.addStudent(stu);
	}

	@Override
	public Student updateStudent(Student stu) {
		// TODO Auto-generated method stub
		return studentdao.updateStudent(stu);
	}

}
