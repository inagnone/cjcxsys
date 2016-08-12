package domain;

import java.sql.Date;



public class StudentActionLog {
	String user;
	String cjname;
	String action;
	Date createdat;
	String ip;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getCjname() {
		return cjname;
	}
	public void setCjname(String cjname) {
		this.cjname = cjname;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Date getCreatedat() {
		return createdat;
	}
	public void setCreatedat(Date createdat) {
		this.createdat = createdat;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
