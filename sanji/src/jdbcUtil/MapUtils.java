package jdbcUtil;

import java.sql.Connection;

public class MapUtils {
	static ThreadLocal<Connection> map = new ThreadLocal<Connection>();
	
	public static Connection getMap() {
		return map.get();
	}
	
	public static void setMap(Connection con) {
		map.set(con);
	}
	public static void delete() {
		map.remove();
	}
}
