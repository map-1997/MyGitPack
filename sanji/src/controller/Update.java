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
import javabean.Area;
import javabean.Log;
import redis.clients.jedis.Jedis;

@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idd = request.getParameter("id");
		String name = request.getParameter("name");
		String fidd = request.getParameter("fid");
		
		Jedis j = new Jedis("127.0.0.1",6379);
		
		int loginId = Integer.parseInt(j.get("id"));
		
		Log l = new Log(0, loginId, "修改地区", new Timestamp(System.currentTimeMillis()));

		int id = Integer.parseInt(idd);
		int fid = Integer.parseInt(fidd);
		
		ServiceInterface ss = new Service();
		
		Utils u = new Utils();
		
		ServiceInterface s = (ServiceInterface)u.getProxy(ss);
		
		Area a = new Area(id, name, fid);
		
		boolean flag = s.update(a,l);
		
		if(flag) {
			request.getRequestDispatcher("/WEB-INF/updateSuccess.jsp").forward(request, response);
		}else {
			request.setAttribute("error", "修改失败，请重试");
			request.getRequestDispatcher("/WEB-INF/update.jsp").forward(request, response);
		}
	}

}
