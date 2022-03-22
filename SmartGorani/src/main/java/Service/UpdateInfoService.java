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
		String nextpage ="";
		System.out.println("[ȸ������ ����!]");
		// post ��� ���ڵ�
		request.setCharacterEncoding("EUC-KR");
		// �� 4��
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name" );
		String type = request.getParameter("type");
		String reg_date = request.getParameter("reg_date");

		System.out.println("id : " + id);
		System.out.println("pw : " + pw);
		System.out.println("name : " + name);
		System.out.println("type : " + type);
		  MemberDTO dto = new MemberDTO(id, pw, name, type,reg_date);
		// 2. MemberDAO dao  = new MemberDAO();
		// 3. dao.update(dto);
		
		//  1,2,3������ �̷��� ���ٷ� ǥ�� ���� but ������ ������
		// ������ ª�ٰ� ���� �ڵ尡 �ƴϴ� �������� �������
		 int cnt = new MemberDAO().update(dto);
		
		if(cnt > 0 ) {
			System.out.println("=== ȸ������ ���� ���� ===");
			HttpSession session =request.getSession();
			session.setAttribute("info", dto);
		}else {
			System.out.println("===ȸ������ ���� ���� ===");
		}
	nextpage= "main.jsp";
	return nextpage;
		
	}
}
