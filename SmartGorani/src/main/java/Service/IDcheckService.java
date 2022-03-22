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
		// 아이디 중복체크 기능
		// 1. post방식 인코딩
		request.setCharacterEncoding("UTF-8");

		System.out.println("IDCheckService");

		// 아이디 중복체크 버튼을 눌렀을 때 ajax로 보낸 email 값 받아오기
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
