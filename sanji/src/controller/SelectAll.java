package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Service;
import dao.ServiceInterface;
import handler.Utils;
import javabean.Area;
import javabean.Log;
import javabean.PageBean;
import redis.clients.jedis.Jedis;

@WebServlet("/SelectAll")
public class SelectAll extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String page = request.getParameter("nowPage");
		String name = request.getParameter("name");
		
		Jedis j = new Jedis("127.0.0.1",6379);
		
		int fidd = Integer.parseInt(j.get("id"));
		
		int nowPage = 1;
		if(page == null) {
			page = "1";
		}
		
		if(name == null) {
			name = "";
		}
		
		nowPage = new Integer(page);
		
		Log l1 = new Log(0, fidd, "查询所有", new Timestamp(System.currentTimeMillis()));
		Log l2 = new Log(0, fidd, "按名字模糊查询记录条数", new Timestamp(System.currentTimeMillis()));
		
		ServiceInterface ss = new Service();
		
		Utils u = new Utils();
		
		ServiceInterface s = (ServiceInterface)u.getProxy(ss);
		
		int count = s.count(name,l2);
		
		List<Area> user = s.select(nowPage, name, l1);
		
		
		PageBean<Area> p = new PageBean<Area>(nowPage, count, 5, user,name);
		
		request.setAttribute("p", p);
		
		request.getRequestDispatcher("/WEB-INF/select.jsp").forward(request, response);
		return;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
