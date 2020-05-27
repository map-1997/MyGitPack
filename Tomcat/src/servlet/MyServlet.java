package servlet;

public class MyServlet extends HTTPServlet{
	protected void doGet(HTTPServletRequest request, HTTPServletResponse response){
		String name = request.getParameter("name");
		
		System.out.println(name);
		
		response.getWriter().write("你好");
		
	}

}
