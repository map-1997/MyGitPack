package servlet;

import java.util.Map;

public interface HTTPServletResponse {
	public Print getWriter();
	
	public String getHtml();
	
	public String setHtml(String html);
	
	public void addHeader(String name,String value);
	
	public Map<String, String> header();
	
	public String getHeader(String name);

	
}
