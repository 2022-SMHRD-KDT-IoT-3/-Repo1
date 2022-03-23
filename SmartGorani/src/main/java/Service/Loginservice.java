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

@WebServlet("/Loginservice")
public class Loginservice implements Command {
	// 2. �������̽��� �ִ� execute() �޼ҵ� �������̵�
	// �ڵ��ϼ� -> ����
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 3. ������ �̵� response ... -> ����
		// String nextpage = "�̵��� �ּ�"
		String nextpage = "";

		// ȸ������ ���
		// 3-1. post��� ���ڵ�
		request.setCharacterEncoding("UTF-8");
		System.out.println("�α��� ���� ����!");
		// 3-2. ȸ������ ������ �޾ƿ���
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String reg_date = request.getParameter("reg_date");
		System.out.println(id + pw);
//		
//
		System.out.println("id : " + id);
		System.out.println("pw : " + pw);

		// 3-3. ������ DTO�� ����
		MemberDTO dto = new MemberDAO().login(id, pw);

		boolean userCheck = false;

		if (dto != null) {
			// ���������� �Ǻ�
			if (dto.getId().equals("admin")) {
				userCheck = true;
			}
			if (userCheck) {
				HttpSession session = request.getSession();
				session.setAttribute("info", dto);
				System.out.println("===������ �α��� ����===");
				nextpage = "admin_member.jsp";
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("info", dto);
				System.out.println("===����� �α��� ����===");
				nextpage = "main.jsp";
			}

		} else {
			System.out.println(dto);
			System.out.println("===�α��� ����===");
			nextpage = "login.html";
		}

		return nextpage;

	}
}
