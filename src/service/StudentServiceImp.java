package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import util.ExcelUtil;
import dao.StudentDao;
import domain.Student;
import factory.BasicFactory;

public class StudentServiceImp implements StudentService {

	private StudentDao studentdao = BasicFactory.getFactory().getDao(StudentDao.class);
	@Override
	public List<Student> getStudents() {
		// TODO Auto-generated method stub
		return studentdao.getStudents();
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
			if(stu.getExampc() != 0)
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
	public ArrayList<Student> LoadExcel(String excelpath, File messfile) {
		// TODO Auto-generated method stub
		InputStream is = null;
		FileOutputStream fos = null;
		ArrayList<Student> result = new ArrayList<Student>();
		try {
			is = new FileInputStream(excelpath);
			fos = new FileOutputStream(messfile);
			String pagemess;
			HSSFWorkbook hssfworkboot = new HSSFWorkbook(is);
			List<List<Student>> stus = parseExcel1(hssfworkboot);
			boolean accessable = true;
			for(int i=0;i<stus.size();i++){
				pagemess = "第"+(i+1)+"页:\r\n";
				fos.write(pagemess.getBytes());
				List<Student> page = stus.get(i);
				for(int j=0;j<page.size();j++){
					String msg;
					Student stu = page.get(j);
					if(( msg = stu.valid()) == null){
						result.add(stu);
						fos.write("导入成功\r\n".getBytes());
					}else{
						msg = msg+"\r\n";
						fos.write(msg.getBytes());
						accessable = false;
					}
				}
			}
			fos.flush();
			fos.close();
			if(!accessable){
				throw new RuntimeException("成绩信息不规范");
			}
			return result;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
		if(title.getCell(2) == null || !title.getCell(2).getStringCellValue().contains("工作单位")){
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
		if(title.getCell(7) == null || !title.getCell(7).getStringCellValue().contains("施工企业成绩")){
			throw new RuntimeException("excel文件格式与模板不一致:第八列应是施工企业成绩列");
		}
		if(title.getCell(8) == null || !title.getCell(8).getStringCellValue().contains("水管单位成绩")){
			throw new RuntimeException("excel文件格式与模板不一致:第九列应是水管单位成绩列");
		}
		if(title.getCell(9) == null || !title.getCell(9).getStringCellValue().contains("项目法人成绩")){
			throw new RuntimeException("excel文件格式与模板不一致:第十列应是项目法人成绩列");
		}
		if(title.getCell(10) == null || !title.getCell(10).getStringCellValue().contains("专业能力成绩")){
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
					if(hssfRow.getCell(1) == null|| ExcelUtil.getcellvalue(hssfRow.getCell(1)) == null)break;
				}
				Student stu = new Student();
				
				//姓名
				HSSFCell cell1 = hssfRow.getCell(1);
				if(cell1 != null){
					String name = ExcelUtil.getcellvalue(cell1);
					stu.setName(name);							
				}
				//工作单位
				HSSFCell cell2 = hssfRow.getCell(2);
				if(cell2 != null){
					String company = ExcelUtil.getcellvalue(cell2);
					stu.setCompany(company);							
				}
				//身份证
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
				//考试类型
				HSSFCell cell4 = hssfRow.getCell(4);
				if(cell4 != null){
					String examtype = ExcelUtil.getcellvalue(cell4);
					stu.setExamtype(examtype);							
				}
				//考试批次
				HSSFCell cell5 = hssfRow.getCell(5);
				if(cell5 != null){
					String exampc = ExcelUtil.getcellvalue(cell5);
					stu.setExamtype(exampc);							
				}
				//考试时间
				HSSFCell cell6 = hssfRow.getCell(6);
				if(cell6 != null && !(val = (ExcelUtil.getCellDate(cell6))).equals("")){
					stu.setExamtime(java.sql.Date.valueOf(val));
				}
				//施工企业成绩
				HSSFCell cell7 = hssfRow.getCell(7);
				if(cell7 != null){
					stu.setSgqycj((int) cell7.getNumericCellValue());
				}
				//施工水管单位成绩
				HSSFCell cell8 = hssfRow.getCell(8);
				if(cell8 != null ){
					stu.setSgqycj((int) cell8.getNumericCellValue());
				}
				//项目法人成绩
				HSSFCell cell9 = hssfRow.getCell(9);
				if(cell9 != null ){
					stu.setSgqycj((int) cell9.getNumericCellValue());
				}
				//专业能力成绩
				HSSFCell cell10 = hssfRow.getCell(10);
				if(cell10 != null ){
					stu.setSgqycj((int) cell10.getNumericCellValue());
				}
				page.add(stu);
			}
			result.add(page);
		}
		return result;
	}
}
