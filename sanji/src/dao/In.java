package dao;

import java.util.List;

import javabean.Area;
import javabean.Log;
import javabean.User;

public interface In {
	
	public List<Area> select(int nowPage,String name);
	
	public List<Area> selectSheng();
	
	public int count(int id);
	
	public int count(String name);
	
	public List<Area> selectCityArea(int id);
	
	public List<Area> selectCityArea(int id,int nowPage);

	public int delete(int id);
	
	public int add(Area a);
	
	public int update(Area a);
	
	public User selectLogin(String name);
	
	public int addLog(Log l);

	
}
