package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.BatchUpdateException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import com.mysql.jdbc.PreparedStatement;

import util.ExcelUtil;
import dao.StudentDao;
import domain.Student;
import factory.BasicFactory;

public class StudentServiceImp implements StudentService {

	private StudentDao studentdao = BasicFactory.getFactory().getDao(StudentDao.class);
	@Override
	public List<Student> getStudents(Map<String, String[]> map,int page,int rows) {
		// TODO Auto-generated method stub
		if(map.get("user")!=null && map.get("user")[0] != null && map.get("user")[0].equals("admin")){
			return studentdao.getStudentforadmin(map,page,rows);
		}else{
			if(map.get("name") != null && map.get("name")[0] != null && !map.get("name")[0].equals("") && map.get("personid") != null && map.get("personid")[0] != null && !map.get("personid")[0].equals("")){
				return studentdao.getStudents(map,page,rows);
			}else{
				return null;
			}
		}
	}

	@Override
	public void addStudent(Student stu) {
		// TODO Auto-generated method stub
		studentdao.addStudent(stu);
	}

	@Override
	public Student updateStudent(Student stu) {
		// TODO Auto-generated method stub
		return studentdao.updateStudent(stu);
	}

	@Override
	public void deleteStu(int id) {
		// TODO Auto-generated method stub
		studentdao.deleteStudent(id);
	}

	@Override
	public Student getStudentbyId(int id) {
		// TODO Auto-generated method stub
		return studentdao.getStudentbyId(id);
	}

	@Override
	public HSSFWorkbook ExportExcel(List<Student> list) {
		// TODO Auto-generated method stub
		HSSFWorkbook workbook = new HSSFWorkbook();
		//创建页
		HSSFSheet sheet = workbook.createSheet();
		//创建行
		HSSFRow row = sheet.createRow(1);
		//设置单元格样式
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		//设置表头
		row.createCell(0).setCellValue("序号");
		row.createCell(1).setCellValue("姓名");
		row.createCell(2).setCellValue("工作单位");
		row.createCell(3).setCellValue("身份证号");
		row.createCell(4).setCellValue("考试名称");
		row.createCell(5).setCellValue("考试批次");
		row.createCell(6).setCellValue("考试时间");
		row.createCell(7).setCellValue("施工企业成绩");
		row.createCell(8).setCellValue("水管单位成绩");
		row.createCell(9).setCellValue("项目法人成绩");
		row.createCell(10).setCellValue("专业能力成绩");
		
		Iterator<Student> iterator = list.iterator();
		// 遍历把证书指定字段加入到excel中
		int index = 1;
		while(iterator.hasNext()){
			index++;
			HSSFRow nextrow = sheet.createRow(index);
			Student stu = iterator.next();
			nextrow.createCell(0).setCellValue(index-1);
			if(stu.getName() != null)
			nextrow.createCell(1).setCellValue(stu.getName());
			if(stu.getCompany() != null)
			nextrow.createCell(2).setCellValue(stu.getCompany());
			if(stu.getPersonid() != null)
			nextrow.createCell(3).setCellValue(stu.getPersonid());
			if(stu.getExamname() != null)
			nextrow.createCell(4).setCellValue(stu.getExamname());
			if(stu.getExampc() != null)
			nextrow.createCell(5).setCellValue(stu.getExampc());
			if(stu.getExamtime() != null)
			nextrow.createCell(6).setCellValue(stu.getExamtime().toString());
			if(stu.getSgqycj() != 0)
				nextrow.createCell(7).setCellValue(stu.getSgqycj());
			if(stu.getSgdwcj() != 0)
				nextrow.createCell(8).setCellValue(stu.getSgdwcj());
			if(stu.getXmfrcj() != 0)
				nextrow.createCell(9).setCellValue(stu.getXmfrcj());
			if(stu.getZynlcj() != 0)
			nextrow.createCell(10).setCellValue(stu.getZynlcj());
		}
		HSSFRow lastrow = sheet.createRow(++index);
		lastrow.createCell(0).setCellValue("一共"+list.size()+"条记录");
		return workbook;
	}

	@Override
	public List<List<Student>> LoadExcel(String excelpath) {
		// TODO Auto-generated method stub
		InputStream is = null;
		FileOutputStream fos = null;
		ArrayList<Student> result = new ArrayList<Student>();
		try {
			is = new FileInputStream(excelpath);
			String pagemess;
			HSSFWorkbook hssfworkboot = new HSSFWorkbook(is);
			List<List<Student>> stus = parseExcel1(hssfworkboot);
			return stus;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} 
	}
	
	public boolean valide(List<List<Student>> stus,File messfile){
		boolean accessable = true;
		String pagemess;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(messfile);
			for(int i=0;i<stus.size();i++){
				pagemess = "第"+(i+1)+"页:\r\n";
				fos.write(pagemess.getBytes());
				List<Student> page = stus.get(i);
				for(int j=0;j<page.size();j++){
					String msg;
					Student stu = page.get(j);
					if(( msg = stu.valid()) == null){
						fos.write("信息正确\r\n".getBytes());
					}else{
						msg = msg+"\r\n";
						fos.write(("序号为"+stu.getId()+msg).getBytes());
						accessable = false;
					}
				}
			}
			fos.write("如果有错误，请根据提示修改该行数据后重新导入源文件".getBytes());
			fos.flush();
			return accessable;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally{
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private List<List<Student>> parseExcel1(HSSFWorkbook hssfworkboot){
		int numSheet = 0;
		int rowNum = 0;
		HSSFRow title = hssfworkboot.getSheetAt(0).getRow(1);
		List<List<Student>> result = new ArrayList<List<Student>>();
		//检验输入excel文件格式是否符合要求
		if(title.getCell(0) ==null || !title.getCell(0).getStringCellValue().contains("序号")){
			throw new RuntimeException("excel文件格式与模板不一致:第一列应是序号列");
		}
		if(title.getCell(1) == null || !title.getCell(1).getStringCellValue().contains("姓名")){
			throw new RuntimeException("excel文件格式与模板不一致:第二列应是姓名列");
		}
		if(title.getCell(2) == null || !title.getCell(2).getStringCellValue().contains("单位")){
			throw new RuntimeException("excel文件格式与模板不一致:第三列应是工作单位列");
		}
		if(title.getCell(3) == null || !title.getCell(3).getStringCellValue().contains("身份证号")){
			throw new RuntimeException("excel文件格式与模板不一致:第四列应是身份证号列");
		}
		if(title.getCell(4) == null || !title.getCell(4).getStringCellValue().contains("考试类型")){
			throw new RuntimeException("excel文件格式与模板不一致:第五列应是考试类型列");
		}
		if(title.getCell(5) == null || !title.getCell(5).getStringCellValue().contains("考试批次")){
			throw new RuntimeException("excel文件格式与模板不一致:第六列应是考试批次列");
		}
		if(title.getCell(6) == null || !title.getCell(6).getStringCellValue().contains("考试时间")){
			throw new RuntimeException("excel文件格式与模板不一致:第七列应是考试时间列");
		}
		if(title.getCell(7) == null || !title.getCell(7).getStringCellValue().contains("施工")){
			throw new RuntimeException("excel文件格式与模板不一致:第八列应是施工企业成绩列");
		}
		if(title.getCell(8) == null || !title.getCell(8).getStringCellValue().contains("水管")){
			throw new RuntimeException("excel文件格式与模板不一致:第九列应是水管单位成绩列");
		}
		if(title.getCell(9) == null || !title.getCell(9).getStringCellValue().contains("法人")){
			throw new RuntimeException("excel文件格式与模板不一致:第十列应是项目法人成绩列");
		}
		if(title.getCell(10) == null || !title.getCell(10).getStringCellValue().contains("专业能力")){
			throw new RuntimeException("excel文件格式与模板不一致:第十一列应是专业能力成绩列");
		}
		
		for(numSheet=0;numSheet<hssfworkboot.getNumberOfSheets();numSheet++){
			HSSFSheet hssfsheet = hssfworkboot.getSheetAt(numSheet);
			ArrayList<Student> page = new ArrayList<Student>();
			if(hssfsheet == null){
				result.add(page);
				continue;
			}
			//循环读取每一页中的每一行
			for(rowNum=2;rowNum<=hssfsheet.getLastRowNum();rowNum++){
				HSSFRow hssfRow = hssfsheet.getRow(rowNum);
				String val;
				//读取行中各个字段的信息并生成diploma对象
				if(hssfRow != null){
					if(hssfRow.getCell(0) == null|| ExcelUtil.getcellvalue(hssfRow.getCell(0)) == null)break;
				}
				Student stu = new Student();
				String index = null;
				HSSFCell cell0 = hssfRow.getCell(0);
				if(cell0 != null){
					try{
						index = ExcelUtil.getcellvalue(cell0);
						stu.setId((int)cell0.getNumericCellValue());
					}catch (Exception e){
						throw new RuntimeException("第"+index+"行序号值不合规范");
					}
				}else{
					throw new RuntimeException("请给定各行的一个唯一序号");
				}
				//姓名
				try{
					HSSFCell cell1 = hssfRow.getCell(1);
					if(cell1 != null){
						String name = ExcelUtil.getcellvalue(cell1);
						stu.setName(name);
					}
				}catch (Exception e){
					throw new RuntimeException("第"+index+"行姓名的值不合规范");
				}
				//工作单位
				try{
					HSSFCell cell2 = hssfRow.getCell(2);
					if(cell2 != null){
						String company = ExcelUtil.getcellvalue(cell2);
						stu.setCompany(company);							
					}
				}catch (Exception e){
					throw new RuntimeException("第"+index+"行工作单位的值不合规范");
				}
				//身份证
				try {
					HSSFCell cell3 = hssfRow.getCell(3);
					String pid;
					if(cell3 != null){
						String id = ExcelUtil.getcellvalue(cell3);
						pid = id.trim();	
						
						if(pid.length()>18){
							pid = pid.substring(0, 18);
						}
						stu.setPersonid(pid);
					}
				}catch (Exception e){
					throw new RuntimeException("第"+index+"行身份证的值不合规范");
				}
				//考试类型
				try{
					HSSFCell cell4 = hssfRow.getCell(4);
					if(cell4 != null){
						String examtype = ExcelUtil.getcellvalue(cell4);
						if(examtype.length()>2){
							examtype = examtype.substring(0,2);
						}
						stu.setExamtype(examtype);							
					}
				}catch (Exception e){
					throw new RuntimeException("第"+index+"行考试类型的值不合规范");
				}
				//考试批次
				try {
					HSSFCell cell5 = hssfRow.getCell(5);
					if(cell5 != null){
						String exampc = ExcelUtil.getcellvalue(cell5);
						if(exampc != null && exampc.equals("")){
							stu.setExampc(exampc);							
						}
					}
				} catch (Exception e){
					throw new RuntimeException("第"+index+"行考试批次的值不合规范");
				}
				//考试时间
				try {
					HSSFCell cell6 = hssfRow.getCell(6);
					if(cell6 != null && !(val = (ExcelUtil.getCellDate(cell6))).equals("")){
						stu.setExamtime(java.sql.Date.valueOf(val));
					}
				}catch (Exception e){
					throw new RuntimeException("第"+index+"行考试时间的值不合规范");
				}
				//施工企业成绩
				try {
					HSSFCell cell7 = hssfRow.getCell(7);
					if(cell7 != null){
						String value = ExcelUtil.getcellvalue(cell7);
						if(value != null){
							stu.setSgqycj(Float.valueOf(value));
						}
					}
				} catch (Exception e){
					throw new RuntimeException("第"+index+"行施工单位的值不合规范");
				}
				//水管单位成绩
				try{
					HSSFCell cell8 = hssfRow.getCell(8);
					if(cell8 != null){
						String value = ExcelUtil.getcellvalue(cell8);
						if(value != null){
							stu.setSgdwcj(Float.valueOf(value));
						}
					}
				}catch (Exception e){
					throw new RuntimeException("第"+index+"行水管单位的值不合规范");
				}
				//项目法人成绩
				try {
					HSSFCell cell9 = hssfRow.getCell(9);
					if(cell9 != null){
						String value = ExcelUtil.getcellvalue(cell9);
						if(value != null){
							stu.setXmfrcj(Float.valueOf(value));
						}
					}
				} catch (Exception e){
					throw new RuntimeException("第"+index+"行项目法人成绩的值不合规范");
				}
				//专业能力成绩
				try {
					HSSFCell cell10 = hssfRow.getCell(10);
					if(cell10 != null){
						String value = ExcelUtil.getcellvalue(cell10);
						if(value != null){
							stu.setZynlcj(Float.valueOf(value));
						}
					}
				}catch (Exception e){
					throw new RuntimeException("第"+index+"行专业能力的值不合规范");
				}
				page.add(stu);
			}
			result.add(page);
		}
		return result;
	}

	@Override
	public int addStudents(List<List<Student>> stus)  {
		// TODO Auto-generated method stub
		ArrayList<Student> list = new ArrayList<Student>();
		try{
			for(int i=0;i<stus.size();i++){
				for(int j=0;j<stus.get(i).size();j++){
					list.add(stus.get(i).get(j));
				}
			}
			return studentdao.addStudents(list);
		}catch (RuntimeException e){
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public List<Student> getStudentforadmin(Map<String, String[]> map,int page,int rows) {
		// TODO Auto-generated method stub
		return studentdao.getStudentforadmin(map,page,rows);
	}
	
	@Override
	public List<Student> getStudentforadmin(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return studentdao.getStudentforadmin(map);
	}

	@Override
	public Student getStudentByName(String name) {
		// TODO Auto-generated method stub
		return studentdao.getStudentByName(name);
	}

	@Override
	public Student getStudentByPersonId(String personid) {
		// TODO Auto-generated method stub
		return studentdao.getStudentByPersonId(personid);
	}

	@Override
	public Student getStudent(String name, String personid) {
		// TODO Auto-generated method stub
		return studentdao.getStudent(name, personid);
	}

	@Override
	public int countStudents(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return studentdao.countStudents(map);
	}
}
