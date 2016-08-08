package domain;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student {
	private int id;
	private char sex;
	private String name;
	private String company;
	private String personid;
	private String examtype;
	private String examname;
	private Date examtime;
	private String exampc;
	private float sgqycj;
	private float sgdwcj;
	private float xmfrcj;
	private float zynlcj;
	
	
	
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
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
	public String getExampc() {
		return exampc;
	}
	public void setExampc(String exampc) {
		this.exampc = exampc;
	}
	public float getSgqycj() {
		return sgqycj;
	}
	public void setSgqycj(float sgqycj) {
		this.sgqycj = sgqycj;
	}
	public float getSgdwcj() {
		return sgdwcj;
	}
	public void setSgdwcj(float sgdwcj) {
		this.sgdwcj = sgdwcj;
	}
	public float getXmfrcj() {
		return xmfrcj;
	}
	public void setXmfrcj(float xmfrcj) {
		this.xmfrcj = xmfrcj;
	}
	public float getZynlcj() {
		return zynlcj;
	}
	public void setZynlcj(float zynlcj) {
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
		if(company == null || company.equals("")){
			builder.append("公司名称不能为空!");
		}
		if(builder.length()==0){
			return null;
		}else{
			return builder.toString();
		}
	}
}
