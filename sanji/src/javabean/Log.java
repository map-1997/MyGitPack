package javabean;

import java.sql.Timestamp;

public class Log {
	int id;
	
	int fid;
	
	String action;
	
	Timestamp time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Log(int id, int fid, String action, Timestamp time) {
		this.id = id;
		this.fid = fid;
		this.action = action;
		this.time = time;
	}

	public Log() {}
	
	
}
