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
		String control = request.getParameter("control");
		System.out.println(control);
		String nextpage = "power_control.jsp";
		if(control.equals("true")) {
			
			// �Ƶ��̳� on
			
		}else if(control.equals("false")) {
			//�Ƶ��̳� off
			
		}
		PrintWriter out = response.getWriter();
		out.print(control);
		return null;
	}
}
