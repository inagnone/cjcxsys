package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import domain.Student;

import service.StudentService;

import factory.BasicFactory;

public class AddStuServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
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
			service.addStudent(stu);
			response.sendRedirect("SearchStu.jsp");
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
