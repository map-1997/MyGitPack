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

@WebServlet("/ChaXunOne")
public class ChaXunOne extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idd = request.getParameter("id");
		
		String page = request.getParameter("nowPage");
		
		Jedis j = new Jedis("127.0.0.1",6379);
		
		int fidd = Integer.parseInt(j.get("id"));
		
		int nowPage = 1;
		if(page == null) {
			page = "1";
		}
		
		nowPage = new Integer(page);	
		
		int id = Integer.parseInt(idd);
		
		Log l1 = new Log(0, fidd, "查询一条记录下的所有地区", new Timestamp(System.currentTimeMillis()));
		Log l2 = new Log(0, fidd, "查询指定id下的所有地区的数量", new Timestamp(System.currentTimeMillis()));
		
		Utils u = new Utils();
		
		ServiceInterface ss = new Service();
		
		ServiceInterface s = (ServiceInterface)u.getProxy(ss);
		
		List<Area> user = s.selectCityArea(id,nowPage,l1);
		
		int count = s.count(id,l2);
		
		PageBean<Area> p = new PageBean<Area>(nowPage, count, 5, user,"");
		
		request.setAttribute("p", p);
		request.setAttribute("id", idd);
		
		request.getRequestDispatcher("/WEB-INF/selectOne.jsp").forward(request, response);
	}

}
