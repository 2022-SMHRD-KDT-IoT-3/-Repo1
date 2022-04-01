package Service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Inter.Command;
import Model.DeviceDAO;
import Model.DeviceDTO;
import Model.ProductDTO;
import Model.ProductInfoDAO;
import Model.Product_infoDTO;

public class DeviceInsertService implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextpage = "";
		request.setCharacterEncoding("utf-8");
		String mb_id = request.getParameter("mb_id");
		String p_serial = request.getParameter("p_serial");
		int dv_num = Integer.parseInt(request.getParameter("dv_num"));
		String dv_desc = request.getParameter("dv_desc");
		String consent = request.getParameter("consent");
		
		Product_infoDTO infodto = new ProductInfoDAO().pseqFind(p_serial);
		
		System.out.println(mb_id + ", " + p_serial  + ", " + dv_num  + ", "+ dv_desc  + ", "+ consent  + ", " + infodto.getP_seq());
		
		
		
		DeviceDTO d_dto = new DeviceDTO(0, mb_id, dv_num, dv_desc, consent, infodto.getP_seq());
		int cnt = new DeviceDAO().deviceInsert(d_dto);
		if(cnt>0) {
			System.out.println("추가 성공");
			nextpage="main.jsp";
		} else {
			System.out.println("추가 실패");
			nextpage="port.jsp";
			
		}
		
		
		return nextpage;
	}

}
