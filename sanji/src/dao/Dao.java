package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javabean.Area;
import javabean.Log;
import javabean.User;
import jdbcUtil.JdbcManager;

public class Dao implements In {

	@Override
	public List<Area> select(int nowPage,String name) {
		List<Area> list = new ArrayList<Area>();
		
		Connection con = null;
		
		PreparedStatement pst = null;
		
		ResultSet rs = null;
		
		Area a = null;
		
		try {
			con = JdbcManager.getconn();
			
			pst = con.prepareStatement("select * from address where name like ? limit ?,5");
			
			pst.setString(1, "%"+name+"%");
			
			pst.setInt(2, (nowPage-1)*5);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name1 = rs.getString("name");
				int fid = rs.getInt("fid");
				
				a = new Area(id, name1, fid);

				list.add(a);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询失败",e);
		}finally {
			JdbcManager.closeAll(null, pst, rs);
		}
		return list;
	}

	@Override
	public List<Area> selectCityArea(int id) {
		List<Area> list = new ArrayList<Area>();
		
		Connection con = null;
		
		PreparedStatement pst = null;
		
		ResultSet rs = null;
		
		Area a = null;
		try {
			con = JdbcManager.getconn();
			
			pst = con.prepareStatement("select * from address where fid = ?");
			
			pst.setInt(1, id);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				int idd = rs.getInt("id");
				String name = rs.getString("name");
				int fid = rs.getInt("fid");
				
				a = new Area(idd, name, fid);
				
				list.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询失败",e);
		}finally {
			JdbcManager.closeAll(null, pst, rs);
		}
		return list;
	}

	@Override
	public int delete(int id) {
		
		PreparedStatement pst = null;
		
		Connection con = null;
		
		int count = 0;
		try {
			con = JdbcManager.getconn();
			
			pst = con.prepareStatement("delete from address where id = ? ");
			
			pst.setInt(1, id);
			
			count = pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("删除失败",e);
		}finally {
			JdbcManager.closeAll(null, pst, null);
		}
		return count;
	}

	@Override
	public int add(Area a) {
		
		PreparedStatement pst = null;
		
		Connection con = null;
		
		int count = 0;
		try {
			con = JdbcManager.getconn();
			
			pst = con.prepareStatement("insert into address(name,fid) values(?,?)");
			
			pst.setString(1, a.getName());
			pst.setInt(2, a.getFid());
			
			count = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("添加失败",e);
		}finally {
			JdbcManager.closeAll(null, pst, null);
		}
		return count;
	}

	@Override
	public int update(Area a) {
		Connection con = null;
		
		PreparedStatement pst = null;
		
		int count = 0;
		try {
			con = JdbcManager.getconn();
			
			pst = con.prepareStatement("update address set name=?,fid=? where id = ?");
			
			pst.setString(1, a.getName());
			pst.setInt(2, a.getFid());
			pst.setInt(3, a.getId());
			
			count = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("更新失败",e);
		}finally {
			JdbcManager.closeAll(null, pst, null);
		}
		return count;
	}

	@Override
	public int count(int id) {
		Connection con = null;
		
		PreparedStatement pst = null;
		
		ResultSet rs = null;
		
		int count = 0;
		try {
			con = JdbcManager.getconn();
			
			pst = con.prepareStatement("select count(1) from address where fid = ?");
			
			pst.setInt(1, id);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询数量失败",e);
		}finally {
			JdbcManager.closeAll(null, pst, rs);
		}
		return count;
	}

	@Override
	public int count(String name) {
		Connection con = null;
		
		PreparedStatement pst = null;
		
		ResultSet rs = null;
		
		int count = 0;
		try {
			con = JdbcManager.getconn();
			
			pst = con.prepareStatement("select count(1) from address where name like ?");
			
			pst.setString(1, "%"+name+"%");
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询数量失败",e);
		}finally {
			JdbcManager.closeAll(null, pst, rs);
		}
		return count;
	}

	@Override
	public List<Area> selectSheng() {
		Connection con = null;
		
		List<Area> list = new ArrayList<Area>();
		
		PreparedStatement pst = null;
		
		ResultSet rs = null;
		
		Area a = null;
		
		try {
			con = JdbcManager.getconn();
			
			pst = con.prepareStatement("select * from address where fid=0");
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int fid = rs.getInt("fid");
				
				a = new Area(id, name, fid);
				
				list.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询失败",e);
		}finally {
			JdbcManager.closeAll(null, pst, rs);
		}
		return list;
	}

	@Override
	public List<Area> selectCityArea(int id, int nowPage) {
		Connection con = null;
		
		List<Area> list = new ArrayList<Area>();
		
		PreparedStatement pst = null;
		
		ResultSet rs = null;
		
		Area a = null;
		try {
			con = JdbcManager.getconn();
			
			pst = con.prepareStatement("select * from address where fid = ? limit ?,5");
			
			pst.setInt(1, id);
			pst.setInt(2, (nowPage-1)*5);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				int idd = rs.getInt("id");
				String name = rs.getString("name");
				int fid = rs.getInt("fid");
				
				a = new Area(idd, name, fid);
				
				list.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询失败",e);
		}finally {
			JdbcManager.closeAll(null, pst, rs);
		}
		return list;
	}

	@Override
	public User selectLogin(String name) {
		Connection con = null;
		
		PreparedStatement pst = null;
		
		ResultSet rs = null;
		
		User u = null;
		
		try {
			con = JdbcManager.getconn();
			
			pst = con.prepareStatement("select * from login where name=?");
			
			pst.setString(1, name);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				
				String pass = rs.getString("pass");
				
				u = new User(id, name, pass);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("登录验证失败",e);
		}finally {
			JdbcManager.closeAll(null, pst, rs);
		}
		return u;
	}

	@Override
	public int addLog(Log l) {
		Connection con = null;
		
		PreparedStatement pst = null;
		
		int count = 0;
		try {
			con = JdbcManager.getconn();
			
			pst = con.prepareStatement("insert into log(lid,action,time) values(?,?,?)");
			
			pst.setInt(1, l.getFid());
			pst.setString(2, l.getAction());
			pst.setTimestamp(3, l.getTime());
			
			count = pst.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("添加失败",e);
		}finally {
			JdbcManager.closeAll(null, pst, null);
		}
		return count;
	}
}
