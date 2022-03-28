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

@WebServlet("/data")
public class data extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF8");
		PrintWriter out = response.getWriter();

		String bat = request.getParameter("bat");
		String powe = request.getParameter("powe");

//	      String Window = request.getParameter("Window");
//	      String Soil = request.getParameter("GG");
//	      String Temp = request.getParameter("Temp");
//	      String Humi = request.getParameter("Humi");
//	      String Depo = request.getParameter("Depo");
//	      String Co2 = request.getParameter("Co2");
//	      String Sol = request.getParameter("Sol");

		System.out.println("배터리 : " + bat + "%");
		System.out.println("전력 : " + powe + "mW");

		out.print("<h1>" + bat + "</h1>");

	}

}
