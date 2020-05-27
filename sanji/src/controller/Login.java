package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import javabean.User;
import redis.clients.jedis.Jedis;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> map = new HashMap<String, String>();
		
		String name = request.getParameter("username");
		
		String pass = request.getParameter("pass");
		
		Dao d = new Dao();
		
		User u = d.selectLogin(name);
		
		if(u != null) {
			if(u.getPass().equals(pass)) {
				long t = System.currentTimeMillis();
				
				String time = t+"";
				
				String idd = String.valueOf(u.getId());
				
				Cookie c = new Cookie(idd,time);
				
				map.put(c.getName(), c.getValue());
				
				Jedis j = new Jedis("127.0.0.1");
				
				j.hset(c.getName(), map);
				
				j.set("id",idd);
				
				response.addCookie(c);
				
				j.close();
				
				request.getRequestDispatcher("/WEB-INF/sanji.jsp").forward(request, response);
			}else {
				request.setAttribute("passError", "密码错误，请重新登录");
				request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			}
		}else {
			request.setAttribute("nameError", "账号错误，请重新登录");
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		}
	}

}
