package domain;

import java.sql.Date;

public class Log {
	private int id;
	private String diplomaid;
	private String name;
	private String action;
	private String user;
	private String ip;
	private Date actionat;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDiplomaid() {
		return diplomaid;
	}
	public void setDiplomaid(String diplomaid) {
		this.diplomaid = diplomaid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getActionat() {
		return actionat;
	}
	public void setActionat(Date actionat) {
		this.actionat = actionat;
	}
	
	
}
