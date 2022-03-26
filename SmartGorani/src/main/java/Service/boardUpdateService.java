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
			int qna_seq = Integer.parseInt(multi.getParameter("qna_seq"));
			// ������ �������� MultipartRequest��ü�� �Ķ���� �����ؾߵ�
			String qna_title = multi.getParameter("title");
			String content = multi.getParameter("content");
			String file = "";
			// fileName�� getFilesystemName�� �޾ƿ;ߵ�
			if (multi.getFilesystemName("file") != null) {
				file = URLEncoder.encode(multi.getFilesystemName("file"), "UTF-8");
				// �����̸��� �ѱ��� �ִٸ� ���ڵ�(���ڸ� �ڵ�ȭ��) ����ߵ� ���ڸ��ڵ�ȭ���Ѽ� DB�� ����
			}

			String mb_id = multi.getParameter("mb_id");

			System.out.println("qna_title : " + qna_title);
			System.out.println("content : " + content);
			System.out.println("file : " + file);
			System.out.println("mb_id : " + mb_id);
			
			BoardDTO dto = new BoardDTO(qna_seq, qna_title, content, file, "", mb_id); // �����ھȸ���� �����ǰ��־��� ������ �޼ҵ�ȣ���ؼ�
																					// ��񿡼� �Էµ�
			int cnt = new BoardDAO().boardUpdate(dto);

			if (cnt > 0) {
				System.out.println("���Ͼ��ε� ����");
			} else {
				System.out.println("���Ͼ��ε� ����");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		String nextpage = "board.jsp";
		return nextpage;
	}

}
