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

      

      String battery = request.getParameter("battery");		//���͸��ܷ�
      String power1 = request.getParameter("power1");	//���͸��������
      String power2 = request.getParameter("power2");	//�Ϲ�����������
      String timecnt = request.getParameter("timecnt");	 //1�ʸ��� ī��Ʈ����
      String battotalpower = request.getParameter("battotalpower"); //���͸���������
      String electotalpower = request.getParameter("electotalpower"); //�Ϲ�������������
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
			out.print("{\"SELECT\":\"1\"}"); //�޸��� ���ٽ� "�տ� �ڵ����� \�־���
			
		}else if(select.equals("false")) {
			out.print("{\"SELECT\":\"0\"}");
		}
     
      
     

      System.out.println("servlet ���͸� �ܷ�: " + battery + "%");
      System.out.println("servlet ���͸� ��� ���� : " + power1 + "mW");
      System.out.println("servlet �Ϲ����� ��� ���� : " + power2 + "mW");
      System.out.println("ī��Ʈ : "+timecnt);
      System.out.println("servlet ���͸����� ���� :"+battotalpower+"mW");
      System.out.println("servlet �Ϲ��������� ���� :"+electotalpower+"mW");
      System.out.println("port�ѹ� : "+port);

	     
   }
   
   
}