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

		// back
		// 테이블dto 생성 -> 준범

		// 기능정의
		if (command.equals("Loginservice.do")) { // 로그인 -> 준범 O
			com = new Loginservice();
			nextpage = com.execute(request, response);
		} else if (command.equals("RegisterService.do")) { // 회원가입 -> 진옥 O
			com = new RegisterService();
			nextpage = com.execute(request, response);
		} else if (command.equals("WriteBoardService.do")) { // 게시판 -> 고은 O
			com = new WriteBoardService();
			nextpage = com.execute(request, response);

		} else if (command.equals("SaveIdPwService.do")) { // 비밀번호, 아이디 계속 저장하기
			// com = new SaveIdPwService();
			// nextpage = com.execute(request, response);

		} else if (command.equals("ResetPwService.do")) { // 비밀번호 초기화 기능 -> 준호 O
			com = new ResetPwService();
			nextpage = com.execute(request, response);
		} else if (command.equals("IDcheckService.do")) { // 아이디 중복체크 -> 진옥 O
			com = new IDcheckService();
			nextpage = com.execute(request, response);
		} else if (command.equals("UpdateInfoService.do")) { // 회원 정보 수정 -> 진옥
			com = new UpdateInfoService();
			nextpage = com.execute(request, response);

		} else if (command.equals("CheckTotalPowerService.do")) { // 전체전력조회
			com = new CheckTotalPowerService();
			nextpage = com.execute(request, response);

		} else if (command.equals("RealtimeInfoService.do")) { // 실시간 요금/사용량
			com = new RealtimeInfoService();
			nextpage = com.execute(request, response);
		} else if (command.equals("PowerControlService.do")) { // 전력 on/off 제어
			com = new PowerControlService();
			nextpage = com.execute(request, response);
		}

		if (nextpage != null) {
			response.sendRedirect(nextpage);
		}

	}
}
