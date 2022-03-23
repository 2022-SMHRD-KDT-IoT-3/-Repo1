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
		// 회원정보 수정 기능
		// update.jsp 수정
		System.out.println("UpdateInfoService");

		// 1. post방식 인코딩
		request.setCharacterEncoding("UTF-8");

		// 값 5개
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String type = request.getParameter("type");
		String reg_date = request.getParameter("reg_date");

		System.out.println("name : " + name);
		System.out.println("id : " + id);
		System.out.println("pw : " + type);
		System.out.println("type : " + type);
		System.out.println("reg_date : " + reg_date);

		MemberDTO dto = new MemberDTO(id, pw, type, name, reg_date);

		int cnt = new MemberDAO().update(dto);

		if (cnt > 0) {
			System.out.println("-- 회원정보수정 성공");
			// 성공시 session에 새로운 정보로 update
			HttpSession session = request.getSession();
			session.setAttribute("info", dto);
		} else {
			System.out.println("-- 회원정보수정 실패");
		}
		String nextpage = "updateinfo.jsp";
		return nextpage;
	}
}
