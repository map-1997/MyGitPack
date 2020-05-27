package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

@WebServlet("/AddSheng")
public class AddSheng extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
	
		Jedis j = new Jedis("127.0.0.1",6379);
		
		int fidd = Integer.parseInt(j.get("id"));

		String str = "[\u4e00-\u9fa5]{1,}[省]";
		
		Pattern p = Pattern.compile(str);
		
		Matcher m = p.matcher(name);
		
		boolean nameFlag = m.matches();
		
		Log l1 = new Log(0, fidd, "查询所有省份", new Timestamp(System.currentTimeMillis()));
		Log l2 = new Log(0, fidd, "添加省份", new Timestamp(System.currentTimeMillis()));
		
		response.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html; charset=utf-8");
		
		ServiceInterface ss = new Service();
		
		Utils u = new Utils();
		
		ServiceInterface s = (ServiceInterface)u.getProxy(ss);
		
		boolean index = true;
		
		List<Area> list = s.selectSheng(l1);
		
		for(Area a : list) {
			if(a.getName().equals(name)) {
				index = false;
			}
		}
		
		if (index) {
			if (name != "" && nameFlag) {
					Area a = new Area(0, name, 0);
					boolean flag = s.add(a,l2);
					if (flag) {
						PrintWriter writer = response.getWriter();

						writer.write("yes");

						writer.flush();

						writer.close();
					} else {

						PrintWriter writer = response.getWriter();

						writer.write("no");

						writer.flush();

						writer.close();
					}

			} else {
				PrintWriter writer = response.getWriter();

				writer.write("您输入的内容为空或输入的格式不对，请重新输入");

				writer.flush();

				writer.close();
			}
		} else {
			PrintWriter writer = response.getWriter();

			writer.write("您添加的地区已存在");

			writer.flush();

			writer.close();
		}
	}

}
