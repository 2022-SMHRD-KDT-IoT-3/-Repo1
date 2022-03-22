package Service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Inter.Command;
import Model.MemberDAO;

public class ResetPwService implements Command{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String nextpage ="";
		
		String id = request.getParameter("id");
		
		MemberDAO dao = new MemberDAO();
		int cnt = dao.Resetpw(id);
		if(cnt>0) {
			nextpage="login.html";
		}else {
			nextpage="findpw.html";
		}
		
		return nextpage;
	}
}