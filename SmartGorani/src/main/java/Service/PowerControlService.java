package Service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Inter.Command;

@WebServlet("/PowerControlService")
public class PowerControlService extends HttpServlet {
	public static String allelec ="true";
	public static String batelec ="true";
	public static String dl1 ="true";
	public static String dl2 ="true";
	public static String dl3 ="true";

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		allelec = request.getParameter("allelec");
		System.out.println(allelec);
		batelec = request.getParameter("batelec");
		System.out.println(batelec);
		dl1 = request.getParameter("dl1");
		System.out.println(dl1);
		dl2 = request.getParameter("dl2");
		System.out.println(dl2);
		dl3 = request.getParameter("dl3");
		System.out.println(dl3);
		
		response.sendRedirect("power_control.jsp");
		
		
		
		
		
		
	}
}
