package arduino;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/send")
public class send extends HttpServlet {
	public static String select ="0";
	public static String con1 ="0";
	public static String con2 ="0";
	public static String con3 ="0";
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		select = request.getParameter("select");
		con1 = request.getParameter("con1");
		con2 = request.getParameter("con2");
		con3 = request.getParameter("con3");
		
		response.sendRedirect("test.jsp");
	}

}
