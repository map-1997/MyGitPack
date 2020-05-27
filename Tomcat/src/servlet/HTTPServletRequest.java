package servlet;

import java.util.HashMap;
import java.util.Map;

public interface HTTPServletRequest {
	
	Map<String, String> map = new HashMap<String, String>();
	
	public String[] split(String str);
	
	public Map<String, String> getHead(String[] a);
	
	public Map<String, String> getData(String[] a);
	
	public void setStr(String str);
	
	public String getStr();
	
	public String getParameter(String name);
	
	public String getHeader(String name);
	
	public String getRequestURI();
	
	public String getRequestURL();
	
	public void setAttribute(String name,Object o);
	
	public Object getAttribute(String name);
	
	public String getMethod();
}
