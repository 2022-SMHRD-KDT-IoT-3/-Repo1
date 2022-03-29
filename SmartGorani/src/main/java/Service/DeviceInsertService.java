package Service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Inter.Command;
import Model.DeviceDAO;
import Model.DeviceDTO;

public class DeviceInsertService implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mb_id = request.getParameter("mb_id");
		int dv_num = Integer.parseInt(request.getParameter("dv_num"));
		String dv_desc = request.getParameter("dv_desc");
		String consent = request.getParameter("consent");
		
		DeviceDTO pdto = new DeviceDTO(dv_num, mb_id, dv_num, dv_desc, consent, 0);
		int cnt = new DeviceDAO().deviceInsert(pdto);
		if(cnt>0) {
			System.out.println("추가 성공");
		} else {
			System.out.println("추가 성공");
			
		}
		
		String nextpage = "main.jsp";
		return nextpage;
	}

}
