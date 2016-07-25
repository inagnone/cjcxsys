package domain;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student {
	private int id;
	private String name;
	private String company;
	private String personid;
	private String examtype;
	private String examname;
	private Date examtime;
	private int exampc;
	private int sgqycj;
	private int sgdwcj;
	private int xmfrcj;
	private int zynlcj;
	
	public Date getExamtime() {
		return examtime;
	}
	public void setExamtime(Date examtime) {
		this.examtime = examtime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPersonid() {
		return personid;
	}
	public void setPersonid(String personid) {
		this.personid = personid;
	}
	public String getExamtype() {
		return examtype;
	}
	public void setExamtype(String examtype) {
		this.examtype = examtype;
	}
	public String getExamname() {
		return examname;
	}
	public void setExamname(String examname) {
		this.examname = examname;
	}
	public int getExampc() {
		return exampc;
	}
	public void setExampc(int exampc) {
		this.exampc = exampc;
	}
	public int getSgqycj() {
		return sgqycj;
	}
	public void setSgqycj(int sgqycj) {
		this.sgqycj = sgqycj;
	}
	public int getSgdwcj() {
		return sgdwcj;
	}
	public void setSgdwcj(int sgdwcj) {
		this.sgdwcj = sgdwcj;
	}
	public int getXmfrcj() {
		return xmfrcj;
	}
	public void setXmfrcj(int xmfrcj) {
		this.xmfrcj = xmfrcj;
	}
	public int getZynlcj() {
		return zynlcj;
	}
	public void setZynlcj(int zynlcj) {
		this.zynlcj = zynlcj;
	}
	
	public String valid(){
		StringBuilder builder = new StringBuilder();
		
		if(name == null || name.equals("")){
			builder.append("姓名不能为空!");
		}
		if(company == null || company.equals("")){
			builder.append("公司名称不能为空!");
		}
		if(personid == null || personid.equals("")){
			builder.append("身份证不能为空!") ;		
		}else{
			Pattern pattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])"); 
			Matcher matcher = pattern.matcher(personid);
			if(!matcher.matches()){
				builder.append("身份证格式不正确!") ;
			}
		}
		if(examtype == null || examtype.equals("")){
			builder.append("考试类型不能为空!");
		}
		if(exampc == 0){
			builder.append("考试批次不能为空!");
		}
		if(company == null || company.equals("")){
			builder.append("公司名称不能为空!");
		}
		if(examtime == null){
			builder.append("考试日期不能为空!");
		}
		if(sgqycj == 0){
			builder.append("施工企业成绩不能为空!");
		}
		if(sgdwcj == 0){
			builder.append("水管单位成绩不能为空!");
		}
		if(xmfrcj == 0){
			builder.append("项目法人成绩不能为空!");
		}
		if(zynlcj == 0){
			builder.append("专业能力成绩不能为空!");
		}
		if(builder.length()==0){
			return null;
		}else{
			return builder.toString();
		}
	}
}
