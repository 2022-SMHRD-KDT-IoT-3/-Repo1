package arduino;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BatteryDAO;
import Model.BatteryDTO;
import Model.device_infoDAO;
import Model.device_infoDTO;

@WebServlet("/module2")
public class module2 extends HttpServlet {
	
private float power1;
private float power2;

protected void service(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

      response.setContentType("text/html; charset=UTF-8");
      request.setCharacterEncoding("UTF8");
      PrintWriter out = response.getWriter();

      String pow1 = request.getParameter("pow1");	//1�� �ܼ�Ʈ �������
      String pow2 = request.getParameter("pow2");	//2�� �ܼ�Ʈ �������
      String pow3 = request.getParameter("pow3");	//3�� �ܼ�Ʈ �������
      String timecnt = request.getParameter("timecnt");
      String totalpower1 = request.getParameter("totalpower_mW_1");	//3�� �ܼ�Ʈ �������
      String totalpower2 = request.getParameter("totalpower_mW_2");	//3�� �ܼ�Ʈ �������
      String totalpower3 = request.getParameter("totalpower_mW_3");	//3�� �ܼ�Ʈ �������
      String port = request.getParameter("port");
      

      
      
      device_infoDAO dao = new device_infoDAO();
      device_infoDTO dto = new device_infoDTO();
      
      
      dto.setMb_portserial(port);
      dto.setDv_usage1(Double.parseDouble(totalpower1));
      dto.setDv_usage2(Double.parseDouble(totalpower2));
      dto.setDv_usage3(Double.parseDouble(totalpower3));


      
      
      
      if(timecnt.equals("9")) {
    	  
    	 dao.dbconn();
         dao.insertUsage1(dto);
         dao.insertUsage2(dto);
         dao.insertUsage3(dto);
         dao.dbclose();
    	 
      }
    	  
      
      String con1 = send.con1;
      String con2 = send.con2;
      String con3 = send.con3;
      
      
      if(con1.equals("1")) {
			out.print("{\"CON1\":\"1\","); //�޸��� ���ٽ� "�տ� �ڵ����� \�־���
		}else if(con1.equals("0")) {
			out.print("{\"CON1\":\"0\",");
		}
      if(con2.equals("1")) {
    	  out.print("\"CON2\":\"1\","); //�޸��� ���ٽ� "�տ� �ڵ����� \�־���
      }else if(con2.equals("0")) {
    	  out.print("\"CON2\":\"0\",");
      }
      if(con3.equals("1")) {
    	  out.print("\"CON3\":\"1\"}"); //�޸��� ���ٽ� "�տ� �ڵ����� \�־���
      }else if(con3.equals("0")) {
    	  out.print("\"CON3\":\"0\"}");
      }

      
      
      
      System.out.println("1�� �ܼ�Ʈ : " + pow1 + "mW");
      System.out.println("2�� �ܼ�Ʈ : " + pow2 + "mW");
      System.out.println("3�� �ܼ�Ʈ : " + pow3 + "mW");
      System.out.println("ī��Ʈ : "+timecnt);

      
   
   }


}