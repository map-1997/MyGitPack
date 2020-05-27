package servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ReadText {
	static Map<String, String> map = new HashMap<String, String>();
	
	static {

		try {
			BufferedReader bfr = new BufferedReader(new InputStreamReader(new FileInputStream(new File("F:/a.txt"))));
			
			String readLine = bfr.readLine();
			
			while(readLine != null) {
				map.put(readLine.split("@")[0], readLine.split("@")[1]);
				readLine = bfr.readLine();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("File找不到",e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("读取失败",e);
		}
	
	}
	
	public String getClas(String servletUrl) {
		return map.get(servletUrl);
	}
}
