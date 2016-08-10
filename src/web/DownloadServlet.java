package web;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.IOUtils;


public class DownloadServlet extends HttpServlet {

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
		String filename = request.getParameter("templetfile");
		String showname = filename;
		String[] split = filename.split("/");
		if(split.length > 0){
			showname = split[split.length-1];
		}
		response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(showname,"utf-8"));
		response.setContentType(this.getServletContext().getMimeType(showname));
		
		InputStream in = new FileInputStream(this.getServletContext().getRealPath(filename));
		OutputStream out = response.getOutputStream();
		IOUtils.In2Out(in, out);
		IOUtils.close(in, null);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
