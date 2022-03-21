package Service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Inter.Command;
import Model.MemberDAO;
import Model.MemberDTO;



// 1. command �������̽� ����
public class RegisterService implements Command {
	// 2. �������̽��� �ִ� execute() �޼ҵ� �������̵�
	// �ڵ��ϼ� -> ����
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 3. ������ �̵� response ... -> ����
		// String nextpage = "�̵��� �ּ�"
		String nextpage ="";
		
		// ȸ������ ���
		// 3-1. post��� ���ڵ�
		request.setCharacterEncoding("UTF-8");

		// 3-2. ȸ������ ������ �޾ƿ���
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String reg_date = request.getParameter("reg_date");

		System.out.println("id : " + id);
		System.out.println("pw : " + pw);


		// 3-3. ������ DTO�� ����
		MemberDTO dto = new MemberDTO(id, pw, name, type, reg_date);

		// 3-4. DB������ DAO ȣ�� �� join�޼ҵ� ����
		MemberDAO dao = new MemberDAO();
		int cnt = dao.join(dto);

		// ���� ����
		HttpSession session = request.getSession();

		// 3-5. ������ Ȯ��
		if (cnt > 0) {
			System.out.println("-- ȸ������ ����");
			// id session
			session.setAttribute("id", id);
			nextpage = "main.html";
		} else {
			System.out.println("-- ȸ������ ����");
			nextpage = "login.html";
		}
		
		// 4. �޼ҵ� return ������ nextpage
		return nextpage;
	}
}
