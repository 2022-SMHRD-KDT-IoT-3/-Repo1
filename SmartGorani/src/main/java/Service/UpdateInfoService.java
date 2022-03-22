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
		System.out.println("[회원정보 수정!]");
		// post 방식 인코딩
		request.setCharacterEncoding("EUC-KR");
		// 값 4개
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
		
		//  1,2,3라인을 이렇게 한줄로 표현 가능 but 가독성 떨어짐
		// 무조건 짧다고 좋은 코드가 아니다 가독성도 고려하자
		 int cnt = new MemberDAO().update(dto);
		
		if(cnt > 0 ) {
			System.out.println("=== 회원정보 수정 성공 ===");
			HttpSession session =request.getSession();
			session.setAttribute("info", dto);
		}else {
			System.out.println("===회원정보 수정 실패 ===");
		}
	nextpage= "main.jsp";
	return nextpage;
		
	}
}
