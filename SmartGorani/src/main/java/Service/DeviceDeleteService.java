package Service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Inter.Command;
import Model.BoardDAO;
import Model.DeviceDAO;
import Model.DeviceDTO;

public class DeviceDeleteService implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String portserial = request.getParameter("portserial");
		System.out.println(portserial);
		
		
		int cnt = new DeviceDAO().deviceDelete(portserial);
		PrintWriter out = response.getWriter();
		if(cnt>0) {
			System.out.println("dv 삭제 성공");
			out.print("true");
		} else {
			System.out.println("dv 삭제 실패");
		}
		out.close();
		return null;
		
	}

}
