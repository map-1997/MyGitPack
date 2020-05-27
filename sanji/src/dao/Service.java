package dao;

import java.util.ArrayList;
import java.util.List;

import javabean.Area;
import javabean.Log;

public class Service implements ServiceInterface {
	
	Dao d = new Dao();
	
	@Override
	public boolean delete(int id,Log l) {
		int a = d.delete(id);
		
		int b = d.addLog(l);
		
		if (a > 0 && b > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Area> select(int nowPage, String name, Log l) {
		List<Area> list = new ArrayList<Area>();
		
		list = d.select(nowPage, name);
		
		d.addLog(l);
			
		return list;
	}

	@Override
	public List<Area> selectSheng(Log l) {
		List<Area> list = new ArrayList<Area>();
		
		list = d.selectSheng();
		
		d.addLog(l);
		
		return list;
	}

	@Override
	public List<Area> selectCityArea(int id, Log l) {
		List<Area> list = new ArrayList<Area>();
		
		list = d.selectCityArea(id);
			
		d.addLog(l);
			
		return list;
	}

	@Override
	public List<Area> selectCityArea(int id, int nowPage,Log l) {
		List<Area> list = new ArrayList<Area>();
			
		list = d.selectCityArea(id, nowPage);
			
		d.addLog(l);
		
		return list;
	}

	@Override
	public boolean add(Area a, Log l) {
		int b = d.add(a);
		
		int c = d.addLog(l);
		
		if(b > 0 && c > 0) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public boolean update(Area a, Log l) {
		int b = d.update(a);
		
		int c = d.addLog(l);
		
		if(b > 0 && c > 0) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public int count(int id, Log l) {
		int count = 0;
		
		count = d.count(id);
		
		d.addLog(l);
			
		return count;
	}
	@Override
	public int count(String name, Log l) {
		int count = 0;
		
		count = d.count(name);
		
		d.addLog(l);
			
		return count;
	}
}
