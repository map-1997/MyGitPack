package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

@WebServlet("/ChaXunCity")
public class ChaXunCity extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idd = request.getParameter("id");
		
		Jedis j = new Jedis("127.0.0.1",6379);
		
		int fidd = Integer.parseInt(j.get("id"));
		
		int id = Integer.parseInt(idd);
		
		ServiceInterface ss = new Service();
		
		Utils u = new Utils();
		
		ServiceInterface s = (ServiceInterface)u.getProxy(ss);
		
		Log l = new Log(0, fidd, "查询城市或地区", new Timestamp(System.currentTimeMillis()));
		
		List<Area> list = s.selectCityArea(id,l);
		
		response.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html; charset=utf-8");
		
		JSONArray json = JSONArray.fromObject(list);
		
		PrintWriter writer = response.getWriter();
		
		writer.print(json);
		
		writer.flush();
		
		writer.close();
	}

}
