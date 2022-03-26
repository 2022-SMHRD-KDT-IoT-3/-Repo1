package Service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Inter.Command;
import Model.BoardDAO;

public class boardDeleteService implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		int qna_seq = Integer.parseInt(request.getParameter("qna_seq"));
		String mb_id = request.getParameter("mb_id");

		int cnt = new BoardDAO().boardDelete(qna_seq, mb_id);
		
		if(cnt>0) {
			System.out.println("게시글 삭제 성공");
		} else {
			System.out.println("게시글 삭제 실패");
		}
		
		String nextpage = "board.jsp";
		return nextpage;
	}

}
