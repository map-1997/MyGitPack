package servlet;

public abstract class HTTPServlet {
	protected void doGet(HTTPServletRequest request, HTTPServletResponse response) {
		String name = "未找到相应方法";
		response.getWriter().write("405 "+name);
	}
	
	protected void doPost(HTTPServletRequest request, HTTPServletResponse response) {
		String name = "未找到相应方法";
		response.getWriter().write("405 "+name);
	}
	
	public void service(HTTPServletRequest request, HTTPServletResponse response) {
		String method = request.getMethod();

		if("GET".equals(method)) {
			doGet(request, response);
		}else {
			doPost(request, response);
		}
	}
}
