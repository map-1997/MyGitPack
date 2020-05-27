package javabean;

public class Area {
	int id;
	
	String name;
	
	int fid;

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

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public Area(int id, String name, int fid) {
		super();
		this.id = id;
		this.name = name;
		this.fid = fid;
	}

	public Area() {}
	
}
