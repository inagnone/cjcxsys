package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import domain.ExamType;
import domain.Student;
import service.StudentService;
import util.JsonUtil;
import factory.BasicFactory;

public class GetStuServlet extends HttpServlet {

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
		StudentService studentService = BasicFactory.getFactory().getService(StudentService.class);
		List<Student> list = (List<Student>) request.getSession().getAttribute("result");
		if(request.getParameterMap().containsKey("search")){
			list = studentService.getStudents(request.getParameterMap());
			if(request.getParameterMap().get("user")!=null && request.getParameterMap().get("user").equals("admin")){
				request.getSession().setAttribute("result", list);	
			}
		}else{
			if(list== null)
			list = new ArrayList<Student>();		
		}
		JSONArray jsonArray = JSONArray.fromObject(list,JsonUtil.getConfig());
		response.setContentType("text/json");
		response.getWriter().write(jsonArray.toString());
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
