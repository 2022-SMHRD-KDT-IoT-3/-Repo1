package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Inter.Command;
import Service.RegisterService;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Command com = null;
		// FrontController Pattern
		// 1. ��� ��û�� �ϳ��� servlet���� �����ϴ� ����
		// 2.�ߺ��Ǵ� �ڵ带 ���� �� �ְ�, ������ ������ �� �ϳ��� servlet���� ������ �� �ִ�.
		System.out.println("FrontController Run");
		String nextpage = "";
		// � ����� �����ϴ� ���ڿ����� �Ǵ�
		String uri = request.getRequestURI();
		System.out.println(uri);

		String path = request.getContextPath();
		System.out.println(path);

		String command = uri.substring(path.length() + 1);
		System.out.println(command);

		if (command.equals("Loginservice.do")) { // �ع�
			// com = new LoginService();
			// nextpage = com.execute(request, response);
		} else if (command.equals("RegisterService.do")) { // ����
			// ȸ������
			// com = new RegisterService();
			// nextpage = com.execute(request, response);
		} else if (command.equals("BoardService.do")) { // ����
			// com = new BoardService();
			// nextpage = com.execute(request, response);
		} else if (command.equals("SaveIdPwService.do")) { // ��й�ȣ, ���̵� ��� �����ϱ�
			// com = new BoardService();
			// nextpage = com.execute(request, response);
		} else if (command.equals("ResetPwService.do")) { // ��й�ȣ �ʱ�ȭ ��� -> ��ȣ
			// com = new ResetPwService();
			// nextpage = com.execute(request, response);
		} else if (command.equals("IDcheckService.do")) { // ���̵� �ߺ�üũ
			// com = new IDcheckService();
			// nextpage = com.execute(request, response);
		}

	}
}
