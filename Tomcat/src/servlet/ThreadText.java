package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class ThreadText implements Runnable {
	
	Socket socket;
	HTTPServletRequest req;
	HTTPServletResponse resp;
	String str = null;
	OutputStream out;
	public ThreadText(Socket socket) {
		this.socket = socket;
	}
	@Override
	public void run() {
		try {
			InputStream in = socket.getInputStream();
			
			out = socket.getOutputStream();
			
			byte a[] = new byte[4096];
			
			int count = in.read(a);
			
			if(count != -1) {
				str = new String(a,0,count);
			}
			
			req = new Request(str);
			
			resp = new Response(out);
			
			ReadText rt = new ReadText();
			
			String className = rt.getClas(req.getRequestURI());
			
			if(className != null) {
				try {
					Class<?> c = Class.forName(className);
					
					Constructor<?> con = c.getDeclaredConstructor();
					
					Object o = con.newInstance();
					
					Method method = c.getMethod("service", HTTPServletRequest.class,HTTPServletResponse.class);
					
					method.invoke(o, req,resp);
					
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("反射失败",e);
				} 
			}else {
				resp.getWriter().write("404");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
