package servlet;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Response extends Print implements HTTPServletResponse{
	Map<String, String> map = new HashMap<String, String>();
	int i = 0;
	OutputStream out = null;
	String html = "";
	String xieyi = "HTTP/1.1 200 OK\r\nDate:"+new Date()+"\r\n"
			+ "Content-type:text/html\r\nContent-length:";
	
	public Response(OutputStream out) {
		this.out = out;
	}
	public Response() {}

	@Override
	public String getHtml() {
		return html;
	}
	
	@Override
	public String setHtml(String html) {
		this.html = html;
		xieyi = xieyi+html.length()+"\r\n\r\n"+html;
		return html;
	}

	@Override
	public Map<String, String> header() {
		setHtml("");
		String responseXieyi[] = xieyi.split("\r\n\r\n");
		
		String xieyiHead[] = responseXieyi[0].split("\r\n");
		
		map.put("xieyiHead", xieyiHead[0]);
		map.put("Date", xieyiHead[1].split(":")[1]);
		map.put("Content-type", "text/html");
		map.put("Content-length", xieyiHead[3].split(":")[1]);
		
		return map;
	}

	@Override
	public String getHeader(String name) {
		header();
		return map.get(name);
	}
	
	@Override
	public void addHeader(String name, String value) {
		header();
		map.put(name, value);
	}
	
	@Override
	public Print getWriter() {
		return this;
	}

	
	
	@Override
	public void write(String name) {
		setHtml(name);
		
		PrintWriter p = new PrintWriter(out,false,Charset.forName("utf-8"));
		
		p.write(xieyi);
		
		p.flush();
		
		p.close();
	}

}
