package Service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Inter.Command;
import Model.DeviceDAO;
import Model.DeviceDTO;
import Model.port_serialDTO;
import Model.ProductInfoDAO;
import Model.Product_infoDTO;

public class DeviceInsertService implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextpage = "";
		request.setCharacterEncoding("utf-8");

	

		String mb_id = request.getParameter("mb_id");
		String p_serial = request.getParameter("mb_portserial");
		String dv_name = request.getParameter("dv_name");
		
		//Product_infoDTO infodto = new ProductInfoDAO().pseqFind(p_serial);
		
		System.out.println(mb_id + ", " + p_serial  + ", " + dv_name);
		
		
		
		DeviceDTO d_dto = new DeviceDTO(p_serial, dv_name,0.0,"");
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
