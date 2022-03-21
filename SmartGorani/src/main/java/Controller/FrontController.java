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
		// 1. 모든 요청을 하나의 servlet으로 정의하는 패턴
		// 2.중복되는 코드를 줄일 수 있고, 보안을 적용할 때 하나의 servlet에서 적용할 수 있다.
		System.out.println("FrontController Run");
		String nextpage = "";
		// 어떤 기능을 수행하는 문자열인지 판단
		String uri = request.getRequestURI();
		System.out.println(uri);

		String path = request.getContextPath();
		System.out.println(path);

		String command = uri.substring(path.length() + 1);
		System.out.println(command);

		if (command.equals("Loginservice.do")) {
			//com = new LoginService();
			//nextpage = com.execute(request, response);
		} else if (command.equals("RegisterService.do")) {
			// 회원가입
			com = new RegisterService();
			nextpage = com.execute(request, response);
		} else if (command.equals("BoardService.do")) {
			
		} 
	}

}
