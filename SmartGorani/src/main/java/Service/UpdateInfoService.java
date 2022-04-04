package Service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Inter.Command;
import Model.MemberDAO;
import Model.MemberDTO;

@WebServlet("/UpdateInfoService")
public class UpdateInfoService implements Command {
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ȸ������ ���� ���
		// update.jsp ����
		System.out.println("UpdateInfoService");

		// 1. post��� ���ڵ�
		request.setCharacterEncoding("UTF-8");

		// �� 5��
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String serial = request.getParameter("serial");
		// String reg_date = request.getParameter("reg_date");

		System.out.println("name : " + name);
		System.out.println("id : " + id);
		System.out.println("pw : " + pw);
		System.out.println("type : " + type);
		System.out.println("serial : " + serial);
		
		//System.out.println("reg_date : " + reg_date);

		MemberDTO dto = new MemberDTO(id, pw, name, type, serial);
				
		int cnt = new MemberDAO().update(dto);
		

		if (cnt > 0) {
			System.out.println("-- ȸ���������� ����");
			// ������ session�� ���ο� ������ update
			HttpSession session = request.getSession();
			session.setAttribute("info", dto);
		} else {
			System.out.println("-- ȸ���������� ����");
		}
		String nextpage = "editinfo.jsp";
		return nextpage;
	}
}
