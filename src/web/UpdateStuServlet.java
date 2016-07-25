package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import service.StudentService;

import com.sun.webkit.BackForwardList;

import domain.Student;
import factory.BasicFactory;

public class UpdateStuServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		StudentService service = BasicFactory.getFactory().getService(StudentService.class);
		Student stu = new Student();
		try {
			BeanUtils.populate(stu, request.getParameterMap());
			stu.setSgdwcj(Integer.valueOf(request.getParameter("sgdwcj")));
			stu.setSgqycj(Integer.valueOf(request.getParameter("sgqycj")));
			stu.setXmfrcj(Integer.valueOf(request.getParameter("xmfrcj")));
			stu.setZynlcj(Integer.valueOf(request.getParameter("zynlcj")));
			service.updateStudent(stu);
			response.sendRedirect("SearchStu.jsp");
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
