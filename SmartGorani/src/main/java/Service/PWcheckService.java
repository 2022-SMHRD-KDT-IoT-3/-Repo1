package Service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Inter.Command;
import Model.MemberDAO;

@WebServlet("/PWcheckService")
public class PWcheckService implements Command {
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			// 비밀번호 일치 기능 
		// 아이디 중복체크 기능
				// 1. post방식 인코딩
				request.setCharacterEncoding("UTF-8");

				System.out.println("PWCheckService 실행합니다");

				// 아이디 중복체크 버튼을 눌렀을 때 ajax로 보낸 email 값 받아오기
				String pw = request.getParameter("pw");
				System.out.println("pw : " + pw);
				String pwcheck = request.getParameter("pwcheck");
				System.out.println("pwcheck : "+pwcheck);
				boolean result = false;
				if(pw.equals(pwcheck)) {
					result = true;
				}
				PrintWriter out = response.getWriter();
				out.print(result);
				out.close();
				String nextpage = "main.jsp";

				return nextpage;	
	
	}

}
