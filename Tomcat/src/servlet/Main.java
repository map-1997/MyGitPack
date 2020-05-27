package servlet;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public abstract class Main {
	static HTTPServletRequest req = new Request();
	static HTTPServletResponse resp = new Response();
	public static void a(HTTPServletRequest req,HTTPServletResponse resp) {
		Main.req = req;
		Main.resp = resp;
	}
	
	
	
	public static void main(String a[]) {
		try {
		ServerSocket ser = new ServerSocket(8080);
		while(true) {
			
				Socket socket = ser.accept();
				
				ThreadText tt = new ThreadText(socket);
				
				Thread t = new Thread(tt);
				
				t.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("失败 ",e);
		} 
		
	}
}
