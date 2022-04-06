package Service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Inter.Command;

public class PowerControlService implements Command{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String alleleccon = request.getParameter("alleleccon");
		System.out.println(alleleccon);
		String bateleccon = request.getParameter("bateleccon");
		System.out.println(bateleccon);
		
		
		
		String nextpage = "power_control.jsp";
		
		PrintWriter out = response.getWriter();
		out.print(alleleccon);
		
		
		
		
		out.print(bateleccon);
		
		
		
		return null;
	}
}
