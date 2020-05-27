package dao;

import java.util.List;

import javabean.Area;
import javabean.Log;

public interface ServiceInterface {
	public boolean delete(int id,Log l);
	
	public List<Area> select(int nowPage,String name,Log l);
	
	public List<Area> selectSheng(Log l);
	
	public List<Area> selectCityArea(int id,Log l);
	
	public List<Area> selectCityArea(int id,int nowPage,Log l);
	
	public boolean add(Area a,Log l);
	
	public boolean update(Area a,Log l);
	
	public int count(int id,Log l);
	
	public int count(String name,Log l);
	
}
