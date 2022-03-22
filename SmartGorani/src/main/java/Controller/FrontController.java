package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Inter.Command;
import Service.CheckTotalPowerService;
import Service.IDcheckService;
import Service.Loginservice;
import Service.PowerControlService;
import Service.RegisterService;
import Service.ResetPwService;
import Service.UpdateInfoService;
import Service.WriteBoardService;
import Service.RealtimeInfoService;

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

		// back
		// ���̺�dto ���� -> �ع�

		// �������
		if (command.equals("Loginservice.do")) { // �α��� -> �ع� O
			com = new Loginservice();
			nextpage = com.execute(request, response);
		} else if (command.equals("RegisterService.do")) { // ȸ������ -> ���� O
			com = new RegisterService();
			nextpage = com.execute(request, response);
		} else if (command.equals("WriteBoardService.do")) { // �Խ��� -> ���� O
			com = new WriteBoardService();
			nextpage = com.execute(request, response);

		} else if (command.equals("SaveIdPwService.do")) { // ��й�ȣ, ���̵� ��� �����ϱ�
			// com = new SaveIdPwService();
			// nextpage = com.execute(request, response);

		} else if (command.equals("ResetPwService.do")) { // ��й�ȣ �ʱ�ȭ ��� -> ��ȣ O
			com = new ResetPwService();
			nextpage = com.execute(request, response);
		} else if (command.equals("IDcheckService.do")) { // ���̵� �ߺ�üũ -> ���� O
			com = new IDcheckService();
			nextpage = com.execute(request, response);
		} else if (command.equals("UpdateInfoService.do")) { // ȸ�� ���� ���� -> ����
			com = new UpdateInfoService();
			nextpage = com.execute(request, response);

		} else if (command.equals("CheckTotalPowerService.do")) { // ��ü������ȸ
			com = new CheckTotalPowerService();
			nextpage = com.execute(request, response);

		} else if (command.equals("RealtimeInfoService.do")) { // �ǽð� ���/��뷮
			com = new RealtimeInfoService();
			nextpage = com.execute(request, response);
		} else if (command.equals("PowerControlService.do")) { // ���� on/off ����
			com = new PowerControlService();
			nextpage = com.execute(request, response);
		}

		if (nextpage != null) {
			response.sendRedirect(nextpage);
		}

	}
}
