package web;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import service.ActionLogService;
import util.JsonUtil;
import domain.StudentActionLog;
import factory.BasicFactory;

public class GetStuActionLogServlet extends HttpServlet {

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
		ActionLogService service = BasicFactory.getFactory().getService(ActionLogService.class);
		
		int page = (request.getParameter("page")!=null && !request.getParameter("page").equals("")) ? Integer.valueOf(request.getParameter("page")):0;
		int rows = (request.getParameter("rows")!=null && !request.getParameter("rows").equals("")) ? Integer.valueOf(request.getParameter("rows")):0;
		
		List<StudentActionLog> list = null;
		if(page!=0 && rows!= 0){
			list = service.getStuActionLog(request.getParameterMap(),page,rows);
		}else{
			list = service.getStuActionLog(request.getParameterMap());
		}
		Map<String, Object> jsonmap = new HashMap<String, Object>();
		jsonmap.put("total", service.countStuActionLog(request.getParameterMap()));
		jsonmap.put("rows", list);
		JSONObject result = JSONObject.fromObject(jsonmap,JsonUtil.getConfig());
		response.setContentType("text/json");
		response.getWriter().write(result.toString());
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
