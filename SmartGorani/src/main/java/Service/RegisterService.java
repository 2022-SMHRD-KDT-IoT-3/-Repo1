package Service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Inter.Command;
import Model.MemberDAO;
import Model.MemberDTO;



// 1. command 인터페이스 구현
public class RegisterService implements Command {
	// 2. 인터페이스에 있는 execute() 메소드 오버라이딩
	// 자동완성 -> 편리함
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 3. 페이지 이동 response ... -> 삭제
		// String nextpage = "이동할 주소"
		String nextpage ="";
		
		// 회원가입 기능
		// 3-1. post방식 인코딩
		request.setCharacterEncoding("UTF-8");

		// 3-2. 회원가입 데이터 받아오기
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String reg_date = request.getParameter("reg_date");

		System.out.println("id : " + id);
		System.out.println("pw : " + pw);


		// 3-3. 데이터 DTO로 묶기
		MemberDTO dto = new MemberDTO(id, pw, name, type, reg_date);

		// 3-4. DB연결할 DAO 호출 및 join메소드 실행
		MemberDAO dao = new MemberDAO();
		int cnt = dao.join(dto);

		// 세션 선언
		HttpSession session = request.getSession();

		// 3-5. 실행결과 확인
		if (cnt > 0) {
			System.out.println("-- 회원가입 성공");
			// id session
			session.setAttribute("id", id);
			nextpage = "main.html";
		} else {
			System.out.println("-- 회원가입 실패");
			nextpage = "login.html";
		}
		
		// 4. 메소드 return 값으로 nextpage
		return nextpage;
	}
}
