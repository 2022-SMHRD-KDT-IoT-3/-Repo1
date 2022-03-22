package Service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Inter.Command;
import Model.MemberDAO;

public class IDcheckService implements Command {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���̵� �ߺ�üũ ���
		// 1. post��� ���ڵ�
		request.setCharacterEncoding("UTF-8");

		System.out.println("IDCheckService");

		// ���̵� �ߺ�üũ ��ư�� ������ �� ajax�� ���� email �� �޾ƿ���
		String id = request.getParameter("id");
		System.out.println("id : " + id);
	
		boolean result = new MemberDAO().idCheck(id);

		PrintWriter out = response.getWriter();
		out.print(result);
		out.close();

		String nextpage = "main.jsp";

		return nextpage;
	}

}
