package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
			stu.setId(Integer.valueOf(request.getParameter("id")));
			stu.setSgdwcj(Integer.valueOf(request.getParameter("sgdwcj")));
			stu.setSgqycj(Integer.valueOf(request.getParameter("sgqycj")));
			stu.setXmfrcj(Integer.valueOf(request.getParameter("xmfrcj")));
			stu.setZynlcj(Integer.valueOf(request.getParameter("zynlcj")));
			service.updateStudent(stu);
			response.sendRedirect("../SearchStu.jsp");
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
