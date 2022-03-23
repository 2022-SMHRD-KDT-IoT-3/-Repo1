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
import Model.ReplyDAO;
import Model.ReplyDTO;

public class WriteReplyService implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			request.setCharacterEncoding("UTF-8"); // POST������ڵ�
			String savePath = request.getServletContext().getRealPath("./file");
			// ��������� �����ΰ�, �ȿ� string���ε��� �� ����η� ��
			System.out.println(savePath);
			// maxsize : �̹����� ũ�� ����
			int maxsize = 10 * 1024 * 1024; // 10MB
			// 1mb = 1024kb
			// 1kb = 1024byte
			// encoding : ���ڵ����
			String encoding = "UTF-8";
			// filepolicy : �����̸��ߺ����� --- ���ϸ��� ��ĥ�� ���ڸ� �����ϰ� �ؼ� �����̸��� ��ġ�� �ʰ� �ߺ��� ��������
			DefaultFileRenamePolicy filePolicy = new DefaultFileRenamePolicy();

			MultipartRequest multi = new MultipartRequest(request, savePath, maxsize, encoding, filePolicy);
			// �����͵��� ���� �ȿ� ���� ��

			// ������ �������� MultipartRequest��ü�� �Ķ���� �����ؾߵ�
			
			String r_title = multi.getParameter("r_title");
			int q_seq = Integer.parseInt(multi.getParameter("num"));
			String r_content = multi.getParameter("content");
			String r_file = "";
			// fileName�� getFilesystemName�� �޾ƿ;ߵ�
			if (multi.getFilesystemName("file") != null) {
				r_file = URLEncoder.encode(multi.getFilesystemName("file"), "UTF-8");
				// �����̸��� �ѱ��� �ִٸ� ���ڵ�(���ڸ� �ڵ�ȭ��) ����ߵ� ���ڸ��ڵ�ȭ���Ѽ� DB�� ����
			}
			
			String mb_id = multi.getParameter("mb_id");

			System.out.println("r_title : " + r_title);
			System.out.println("q_seq : " + q_seq);
			System.out.println("content : " + r_content);
			System.out.println("file : " + r_file);
			System.out.println("mb_id : " + mb_id);
			
			ReplyDTO rdto = new ReplyDTO(0, q_seq, r_content, r_file, "", mb_id);
			new ReplyDAO().replyinsert(rdto);
//			BoardDTO dto = new BoardDTO(0, qna_title, content, file, "", mb_id); // �����ھȸ���� �����ǰ��־��� ������ �޼ҵ�ȣ���ؼ�
																					// ��񿡼� �Էµ�
			int cnt = new ReplyDAO().replyinsert(rdto);

			if (cnt > 0) {
				System.out.println("�亯���ε� ����");
			} else {
				System.out.println("�亯���ε� ����");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		
		String nextpage = "board_admin.jsp";
		return nextpage;
	}

}
