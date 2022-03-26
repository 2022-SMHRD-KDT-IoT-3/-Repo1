package Service;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import Inter.Command;
import Model.BoardDAO;
import Model.BoardDTO;

public class boardUpdateService implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		try {
			request.setCharacterEncoding("UTF-8"); // POST방식인코딩
			String savePath = request.getServletContext().getRealPath("./file");
			// 여기까지는 절대경로고, 안에 string으로들어가는 건 상대경로로 들어감
			System.out.println(savePath);
			// maxsize : 이미지의 크기 지정
			int maxsize = 10 * 1024 * 1024; // 10MB
			// 1mb = 1024kb
			// 1kb = 1024byte
			// encoding : 인코딩방식
			String encoding = "UTF-8";
			// filepolicy : 파일이름중복제거 --- 파일명이 겹칠때 숫자를 증가하게 해서 파일이름이 겹치지 않게 중복을 제거해줌
			DefaultFileRenamePolicy filePolicy = new DefaultFileRenamePolicy();

			MultipartRequest multi = new MultipartRequest(request, savePath, maxsize, encoding, filePolicy);
			// 데이터들이 서버 안에 담기게 됨
			int qna_seq = Integer.parseInt(multi.getParameter("qna_seq"));
			// 데이터 꺼내오기 MultipartRequest객체로 파라미터 수집해야됨
			String qna_title = multi.getParameter("title");
			String content = multi.getParameter("content");
			String file = "";
			// fileName은 getFilesystemName로 받아와야됨
			if (multi.getFilesystemName("file") != null) {
				file = URLEncoder.encode(multi.getFilesystemName("file"), "UTF-8");
				// 파일이름에 한글이 있다면 인코딩(문자를 코드화함) 해줘야됨 문자를코드화시켜서 DB에 저장
			}

			String mb_id = multi.getParameter("mb_id");

			System.out.println("qna_title : " + qna_title);
			System.out.println("content : " + content);
			System.out.println("file : " + file);
			System.out.println("mb_id : " + mb_id);
			
			BoardDTO dto = new BoardDTO(qna_seq, qna_title, content, file, "", mb_id); // 생성자안만들고 임의의값넣어줌 어차피 메소드호출해서
																					// 디비에서 입력됨
			int cnt = new BoardDAO().boardUpdate(dto);

			if (cnt > 0) {
				System.out.println("파일업로드 성공");
			} else {
				System.out.println("파일업로드 실패");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		String nextpage = "board.jsp";
		return nextpage;
	}

}
