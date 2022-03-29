package Service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Inter.Command;
import Model.BoardDAO;
import Model.DeviceDAO;

public class DeviceDeleteService implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int dv_seq = Integer.parseInt(request.getParameter("dv_seq"));
		String mb_id = request.getParameter("mb_id");

		int cnt = new DeviceDAO().deviceDelete(dv_seq, mb_id);
		
		if(cnt>0) {
			System.out.println("dv 삭제 성공");
		} else {
			System.out.println("dv 삭제 실패");
		}
		
		String nextpage = "main.jsp";
		return nextpage;
		
	}

}
