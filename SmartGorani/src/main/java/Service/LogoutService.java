package Service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Inter.Command;

public class LogoutService implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("[LogoutServiceCon]");
		// �α׾ƿ� : �α����� ���� ����
		HttpSession session = request.getSession();
		session.removeAttribute("info");
		System.out.println("�α׾ƿ� ����");
		String nextpage = "login.html";
		return nextpage;
		}

}
