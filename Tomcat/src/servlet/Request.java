package servlet;

import java.util.HashMap;
import java.util.Map;

public class Request implements HTTPServletRequest {
	
	String str = null;
	
	Map<String, Object> attributeMap = new HashMap<String, Object>();
	
	Map<String, String> map = new HashMap<String, String>();
	public Request() {}
	
	public Request(String str) {
		this.str = str;
	}

	@Override
	public  String[] split(String str){
		String HTTP[] = str.split("\r\n\r\n");
		return HTTP;
	}

	//该方法是获取协议头和协议首行
	@Override
	public Map<String, String> getHead(String[] a){
		
		String method = null;//请求方法
		String uri = null;	
		String servletUrl = null;//servlet的路径
		String host = null;//主机地址和端口号	
		String port = null;//端口号 
		
		String b[] = str.split("\r\n\r\n");
		
		String HTTP1[] = b[0].split("\r\n");//HTTP1[]是协议首行和协议头
		
		if(HTTP1[0].indexOf("?") >=0) {
			servletUrl = HTTP1[0].substring(HTTP1[0].indexOf(" ")+1,HTTP1[0].indexOf("?"));
		}else {
			servletUrl = HTTP1[0].substring(HTTP1[0].indexOf(" ")+1,HTTP1[0].lastIndexOf(" "));
		}
		
		//请求头中的字段切割
		host = HTTP1[1].split(":")[1];
		port = HTTP1[1].split(":")[2];
		method = HTTP1[0].split(" ")[0];
		host = host.trim();	
		uri = HTTP1[0].substring(HTTP1[0].indexOf(" ")+1,HTTP1[0].lastIndexOf(" "));
		
		for(int i = 2; i < HTTP1.length; i++) {
			String j[] = HTTP1[i].split(":");
			j[1] = j[1].trim();
			map.put(j[0], j[1]);
		}
		
		map.put("method", method);
		map.put("uri", uri);
		map.put("servletUrl", servletUrl);
		map.put("host", host);
		map.put("port", port);
		
		return map;
	}
	
	@Override
	public Map<String, String> getData(String[] a){
		Map<String, String> map = new HashMap<String, String>();
		String data = null;//get方法传输的数据
		String HTTP1[] = a[0].split("\r\n");//HTTP1[]是协议首行和协议头
		
		
		if(HTTP1[0].contains("?") && HTTP1[0].contains("=")) {
			int i = HTTP1[0].indexOf("?");
			int j = HTTP1[0].lastIndexOf(" ");
			data = HTTP1[0].substring(i+1,j);
			
			//dataKV是以name=lisi 的形式存放的字符串
			String dataKV[] = data.split("&");
			
			for(String k : dataKV) {
				String key = k.split("=")[0];
				String value = k.split("=")[1];
				map.put(key, value);
			}
		}else {
			String HTTP2[] = a[1].split("\r\n\r\n");//HTTP1[]是协议首行和协议头
			System.out.println(HTTP2[0]);
		}
		return map;
	}
	
	@Override
	public String getParameter(String name) {
		String a[] = this.split(str);
		return this.getData(a).get(name);
	}

	@Override
	public void setStr(String str) {
		this.str = str;
	}

	@Override
	public String getStr() {
		return str;
	}

	@Override
	public String getHeader(String name) {
		return this.getHead(this.split(str)).get(name);
	}

	@Override
	public String getRequestURI() {
		String uri = this.getHead(this.split(str)).get("uri");
		
		if(uri.contains("?")) {
			uri = uri.substring(0,uri.indexOf("?"));
		}
		return uri;
	}

	@Override
	public String getRequestURL() {
		String host = this.getHead(this.split(str)).get("host");
		String port = this.getHead(this.split(str)).get("port");
		String uri = this.getRequestURI();
		
		String url = "http://"+host+":"+port+uri;
		return url;
	}

	@Override
	public void setAttribute(String name, Object o) {
		attributeMap.put(name, o);
	}

	@Override
	public Object getAttribute(String name) {
		return attributeMap.get(name);
	}

	@Override
	public String getMethod() {
		String[] split = split(str);
		return getHead(split).get("method");
	}
}
