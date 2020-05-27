package controller;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;



@WebFilter("/*")
public class Filter implements javax.servlet.Filter {
	
	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Jedis j = new Jedis("127.0.0.1",6379);
		
		boolean flag = false;
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		String uri = req.getRequestURI();
		
		Cookie[] cookies = req.getCookies();
		
		if(uri.equals("/sanji/WebStart") || uri.equals("/sanji/Login")) {
			chain.doFilter(req, resp);
		}else if(uri.contains("jquery.min.js") || uri.contains(".jsp")){
			chain.doFilter(req, resp);
		}else {
			if(cookies != null) {
				for(Cookie c : cookies) {
					if(j.hgetAll(c.getName()) != null) {
						flag = true;	
						j.close();
					}
				}
				if(flag) {
					chain.doFilter(request, response);
				}else {
					resp.sendRedirect("/sanji/WebStart");
				}
			}else {
				resp.sendRedirect("/sanji/WebStart");
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {}

}
