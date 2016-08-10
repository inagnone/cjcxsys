package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

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
			if(request.getParameter("examtime")==null || !request.getParameter("examtime").equals("")){
				Map<String, String[]> map = request.getParameterMap();
				BeanUtils.populate(stu, map);
			}else{
				stu.setName(request.getParameter("name"));
				stu.setPersonid(request.getParameter("personid"));
				stu.setCompany(request.getParameter("company"));
				stu.setExamtype(request.getParameter("examtype"));
				stu.setExampc(request.getParameter("exampc"));
			}
			if(request.getParameter("sgdwcj") != null && !request.getParameter("sgdwcj").equals(""))
				stu.setSgdwcj(Integer.valueOf(request.getParameter("sgdwcj")));
			if(request.getParameter("sgqycj") != null && !request.getParameter("sgqycj").equals(""))
				stu.setSgqycj(Integer.valueOf(request.getParameter("sgqycj")));
			if(request.getParameter("xmfrcj") != null && !request.getParameter("xmfrcj").equals(""))
				stu.setXmfrcj(Integer.valueOf(request.getParameter("xmfrcj")));
			if(request.getParameter("zynlcj") != null && !request.getParameter("zynlcj").equals(""))
				stu.setZynlcj(Integer.valueOf(request.getParameter("zynlcj")));
			service.addStudent(stu);
			response.sendRedirect("../SearchStu.jsp");
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
