package arduino;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import Model.ElectricDAO;
import Model.ElectricDTO;
import Service.PowerControlService;
import Model.BatteryDAO;
import Model.BatteryDTO;
import arduino.send;



@WebServlet("/module1")
public class module1 extends HttpServlet {
	
	
   protected void service(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {                                       
	  	  
      response.setContentType("text/html; charset=UTF-8");
      request.setCharacterEncoding("UTF-8");
      PrintWriter out = response.getWriter();

      

      String battery = request.getParameter("battery");		//배터리잔량
      String power1 = request.getParameter("power1");	//배터리사용전력
      String power2 = request.getParameter("power2");	//일반전기사용전력
      String timecnt = request.getParameter("timecnt");	 //1초마다 카운트증가
      String battotalpower = request.getParameter("battotalpower"); //배터리전력총합
      String electotalpower = request.getParameter("electotalpower"); //일반전기전력총합
      String port = request.getParameter("port");
      
      
      BatteryDTO dto = new BatteryDTO(port,Integer.parseInt(battery),Double.parseDouble(battotalpower));
      BatteryDAO dao = new BatteryDAO();
      ElectricDTO edto = new ElectricDTO(port,Double.parseDouble(electotalpower));
      ElectricDAO edao = new ElectricDAO();
      BatteryDTO controldto = new BatteryDTO();
      
      
      if(timecnt.equals("9")) {
			dao.dbconn();
			dao.insertUsage(dto);
			dao.dbclose();
			edao.dbconn();
			edao.insertUseElectric(edto);
			edao.dbclose();
      	
      }
      
     
      
     
	 String select = PowerControlService.batelec;
      System.out.println(select);
      if(select.equals("true")) {
			out.print("{\"SELECT\":\"1\"}"); //메모장 복붙시 "앞에 자동으로 \넣어줌
			
		}else if(select.equals("false")) {
			out.print("{\"SELECT\":\"0\"}");
		}
     
      
     

      System.out.println("servlet 배터리 잔량: " + battery + "%");
      System.out.println("servlet 배터리 사용 전력 : " + power1 + "mW");
      System.out.println("servlet 일반전기 사용 전력 : " + power2 + "mW");
      System.out.println("카운트 : "+timecnt);
      System.out.println("servlet 배터리전력 총합 :"+battotalpower+"mW");
      System.out.println("servlet 일반전기전력 총합 :"+electotalpower+"mW");
      System.out.println("port넘버 : "+port);

	     
   }
   
   
}