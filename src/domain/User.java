package domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class User implements Serializable {
	private int id;
	private String name;
	private String password;
	private String role;
	private String areaid;
	private String areaname;
	private String actionpassword;
//	private String nickname;
//	private int state;
//	private String activecode;
//	private Timestamp updatetime;
	
	
	
	public int getId() {
		return id;
	}
	public String getActionpassword() {
		return actionpassword;
	}
	public void setActionpassword(String actionpassword) {
		this.actionpassword = actionpassword;
	}
	public String getAreaname() {
		return areaname;
	}
	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}
	public String getAreaid() {
		return areaid;
	}
	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}
	public String getName() {
		return name;
	}
	public void setName(String username) {
		this.name = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
