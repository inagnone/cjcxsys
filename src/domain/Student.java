package domain;

import java.sql.Date;

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
	
	
}
