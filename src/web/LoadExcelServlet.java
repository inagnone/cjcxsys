package web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import service.StudentService;
import util.IOUtils;
import domain.Student;
import domain.User;
import factory.BasicFactory;

public class LoadExcelServlet extends HttpServlet {

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
		
		String url = null;
		String upload = null;
		
		StudentService service = BasicFactory.getFactory().getService(StudentService.class);
		
		File temp = new File(this.getServletContext().getRealPath("WEB-INF/temp"));
		if(!temp.exists())
			temp.mkdirs();
		File result = new File(this.getServletContext().getRealPath("WEB-INF/temp/result.txt"));
		try {
			String encode = this.getServletContext().getInitParameter("encode");
			Map<String, String> paramMap = new HashMap<String,String>();
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setRepository(temp);
			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			fileUpload.setHeaderEncoding(encode);
			
			if(!fileUpload.isMultipartContent(request)){
				throw new RuntimeException("请使用正确的表单进行上传!");
			}
				
			List<FileItem> list = fileUpload.parseRequest(request);
			for(FileItem item : list){
				if(list == null)continue;
				if(item.isFormField()){
					//普通字段
					String name = item.getFieldName();
					String value = item.getString(encode);
					paramMap.put(name, value);
				}else{
					//文件上传项
					String realname = item.getName();
					if(realname.equals("") || realname == null)continue;
					String type = item.getContentType();
					if(type.equals("application/vnd.ms-excel") || type.equals("application/kset") || realname.contains(".xls")){
						upload = this.getServletContext().getRealPath("WEB-INF/upload/excel");
						String filetype = realname.substring(realname.indexOf(".")+1);
						if(!filetype.equals("xls")){
							request.setAttribute("msg", "请用提供的模板保存证书数据，格式只允许.xls）");
							request.getRequestDispatcher("SearchStu.jsp").forward(request, response);
							return ;
						}
						url = "/WEB-INF/upload/excel";						
						url +="/"+realname;
					}else{
						request.setAttribute("msg", "请选择正确类型的文件（成绩数据：.xls）");
						request.getRequestDispatcher("SearchStu.jsp").forward(request, response);
						return ;
					}
					
					
					File uploadFile = new File(upload);
					if(!uploadFile.exists())
						uploadFile.mkdirs();
					
					InputStream in = item.getInputStream();
					OutputStream out = new FileOutputStream(new File(upload,realname));
					
					IOUtils.In2Out(in, out);
					IOUtils.close(in, out);
					item.delete();
				}
			}
			int seccessnum = 0 ;
			if(url != null){
				String realPath = request.getServletContext().getRealPath(url);
				 seccessnum = service.LoadExcel(realPath,result);				
			}				
			response.sendRedirect("SearchStu.jsp?msg=success_"+seccessnum);
		} catch (Exception e) {
			// TODO Auto-generated catch block
				
				if(!result.exists()){
					if(e.getCause() != null){
						Throwable cause = e.getCause();
						while(cause.getCause() != null){
							cause = cause.getCause();
						}
						request.setAttribute("msg", cause.getMessage());
						request.getRequestDispatcher("adddiploma.jsp").forward(request, response);
						return ;
					}
				}
				response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode("result.txt","utf-8"));
				response.setContentType(this.getServletContext().getMimeType("result.txt"));//MIME类型
				InputStream in = new FileInputStream(result);
				OutputStream out = response.getOutputStream();
				IOUtils.In2Out(in, out);
				IOUtils.close(in, null);
				result.delete();
				User user = (User) request.getSession().getAttribute("user");
				return ;

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
