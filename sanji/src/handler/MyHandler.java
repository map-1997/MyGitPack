package handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;

import jdbcUtil.JdbcManager;
import jdbcUtil.MapUtils;

public class MyHandler implements InvocationHandler {
	public Object obj;
	
	public MyHandler(Object obj) {
		this.obj = obj;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Connection con = JdbcManager.getconn();
		
		Object o = null;
		
		try {

			o = method.invoke(obj, args);

			con.commit();
			
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException ex) {
				e.printStackTrace();
				throw new RuntimeException("回滚失败", ex);
			}
			throw new RuntimeException("操作失败", e);
		} finally {
			if (con != null) {
				try {
					con.close();
					MapUtils.delete();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
