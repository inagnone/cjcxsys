package web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import service.StudentService;
import util.ExcelUtil;
import util.IOUtils;
import domain.Student;
import factory.BasicFactory;

public class ExportExcel extends HttpServlet {

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
		
		List<Student> stus = new ArrayList<Student>();
		String ids = request.getParameter("id");
		if(ids!=null){
			String[] id = ids.split(",");
			for(String i:id){
				stus.add(service.getStudentbyId(Integer.valueOf(i)));
			}
		}else{
			stus = service.getStudentforadmin(request.getParameterMap());
		}
		File dir = new File(this.getServletContext().getRealPath("WEB-INF/temp"));
		if(!dir.exists())
			dir.mkdirs();
		File temp = new File(this.getServletContext().getRealPath("WEB-INF/temp/temp.xls"));
		try{
			HSSFWorkbook workbook = service.ExportExcel(stus);
			
			FileOutputStream fos = null ;
			try {
				byte[] bs = workbook.getBytes();	
				fos = new FileOutputStream(temp);
				workbook.write(fos);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e);
			}finally{
				try {
					if(fos!=null)
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode("result.xls","utf-8"));
			response.setContentType(this.getServletContext().getMimeType("result.xls"));//MIME类型
			InputStream in = new FileInputStream(temp);
			OutputStream out = response.getOutputStream();
			IOUtils.In2Out(in, out);
			IOUtils.close(in, null);
			temp.delete();
		}catch (Exception e) {
			// TODO: handle exception
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
