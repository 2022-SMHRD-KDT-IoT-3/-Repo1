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
			// ��й�ȣ ��ġ ��� 
		// ���̵� �ߺ�üũ ���
				// 1. post��� ���ڵ�
				request.setCharacterEncoding("UTF-8");

				System.out.println("PWCheckService �����մϴ�");

				// ���̵� �ߺ�üũ ��ư�� ������ �� ajax�� ���� email �� �޾ƿ���
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
