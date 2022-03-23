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
	// 2. 인터페이스에 있는 execute() 메소드 오버라이딩
	// 자동완성 -> 편리함
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 3. 페이지 이동 response ... -> 삭제
		// String nextpage = "이동할 주소"
		String nextpage = "";

		// 회원가입 기능
		// 3-1. post방식 인코딩
		request.setCharacterEncoding("UTF-8");
		System.out.println("로그인 서비스 실행!");
		// 3-2. 회원가입 데이터 받아오기
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

		// 3-3. 데이터 DTO로 묶기
		MemberDTO dto = new MemberDAO().login(id, pw);

		boolean userCheck = false;

		if (dto != null) {
			// 관리자인지 판별
			if (dto.getId().equals("admin")) {
				userCheck = true;
			}
			if (userCheck) {
				HttpSession session = request.getSession();
				session.setAttribute("info", dto);
				System.out.println("===관리자 로그인 성공===");
				nextpage = "admin_member.jsp";
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("info", dto);
				System.out.println("===사용자 로그인 성공===");
				nextpage = "main.jsp";
			}

		} else {
			System.out.println(dto);
			System.out.println("===로그인 실패===");
			nextpage = "login.html";
		}

		return nextpage;

	}
}
