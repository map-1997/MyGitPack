package controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Service;
import dao.ServiceInterface;
import handler.Utils;
import javabean.Log;
import redis.clients.jedis.Jedis;

@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idd = request.getParameter("id");
		
		Jedis j = new Jedis("127.0.0.1",6379);
		
		int fidd = Integer.parseInt(j.get("id"));
		
		String[] idArray = request.getParameterValues("checkbox");
		
		Log l = new Log(0, fidd, "删除一条记录", new Timestamp(System.currentTimeMillis()));
		
		//删除一条内容
		if(idd != null) {
			
			int id = Integer.parseInt(idd);
			
			ServiceInterface ss = new Service();
			
			Utils u = new Utils();
			
			ServiceInterface s = (ServiceInterface)u.getProxy(ss);
			
			boolean flag = s.delete(id,l);
			
			if(flag) {
				request.getRequestDispatcher("/WEB-INF/deleteSuccess.jsp").forward(request, response);
			}else {
				 response.getWriter().write("<script>alert('删除失败');window.history.go(-1);</script>");
			}
		}else {//删除多条内容
			
			ServiceInterface ss = new Service();
			
			Utils u = new Utils();
			
			ServiceInterface s = (ServiceInterface)u.getProxy(ss);
			
			for(String a : idArray) {
				int id = Integer.parseInt(a);
				
				s.delete(id,l);
			}
			request.getRequestDispatcher("/WEB-INF/deleteSuccess.jsp").forward(request, response);
		}
		
	}

}
