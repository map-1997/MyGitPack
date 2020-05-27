package jdbcUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcManager {
	public final static String url = "jdbc:mysql://127.0.0.1:3306/sanji?character=utf-8";
	
	public final static String username = "root";
	
	public final static String pass = "123456";
	public static Connection getconn() {
		Connection con = MapUtils.getMap();
		
		if(con == null) {

			try {
				con = DriverManager.getConnection(url,username,pass);
				
				con.setAutoCommit(false);
				
				MapUtils.setMap(con);
				
				return con;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("连接失败",e);
			}
		}else {
			return con;
		}
	}
	
	public static void closeAll(Connection con , PreparedStatement pst , ResultSet rs) {
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
