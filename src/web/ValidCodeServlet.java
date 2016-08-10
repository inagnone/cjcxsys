package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import domain.Student;
import service.StudentService;
import factory.BasicFactory;

public class ValidCodeServlet extends HttpServlet {

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
		
		String validcode = request.getParameter("validcode");
		response.setContentType("text/json");
		JSONObject o = new JSONObject();
		try {
		if(validcode == null || validcode.equals("")){
			o.put("success", false);
			o.put("msg", "验证码不能为空");
			response.getWriter().write(o.toString());
			return;
		}else if(!validcode.equals(request.getSession().getAttribute("validcode"))){
			o.put("success", false);
			o.put("msg", "验证码不正确");
			response.getWriter().write(o.toString());
			return;
		}
		if(request.getParameter("name")!=null && !request.getParameter("name").equals("") && request.getParameter("personid")!=null && !request.getParameter("personid").equals("")){
			Student student = studentService.getStudent(request.getParameter("name"), request.getParameter("personid"));
			if(student == null){
				o.put("success", false);
				o.put("msg", "未能找到对应姓名和身份证的成绩信息");
				response.getWriter().write(o.toString());
				return;
			}
		}else{
			o.put("success", false);
			o.put("msg", "请给定用户姓名和身份证");
			response.getWriter().write(o.toString());
			return;
		}
		o.put("success", true);
		response.getWriter().write(o.toString());
		} catch (JSONException e) {
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
