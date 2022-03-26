package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BoardDAO {

	PreparedStatement psmt = null;
	Connection conn = null;
	ResultSet rs = null;
	int cnt = 0;

	// DB���� �޼ҵ�
	public void dbconn() {
		try {
			System.out.println("�׽�Ʈ");
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
			String dbid = "campus_b_0310_4";
			String dbpw = "smhrd4";
			conn = DriverManager.getConnection(url, dbid, dbpw);
			System.out.println("db���� ����");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("db���� ����");
		}
	}

	public void dbclose() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �Խñ��ۼ� �޼ҵ�
	public int insertBoard(BoardDTO dto) {
		dbconn();
		try {
			String sql = "insert into tbl_qna values(tbl_qna_seq.nextval,?,?,?,sysdate,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getQna_title());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getFile());
			psmt.setString(4, dto.getMb_id());
			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbclose();

		}
		return cnt;
	}

	// �Խñ� ��ȸ �޼ҵ�
	public ArrayList<BoardDTO> showBoard() {
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		// �������� dto�� �������
		dbconn();
		try {
			String sql = "select * from tbl_qna order by qna_date desc";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				int qna_seq = rs.getInt(1);
				String qna_title = rs.getString(2);
				String content = rs.getString(3);
				String file = rs.getString(4);
				String date = rs.getString(5);
				String mb_id = rs.getString(6);
				BoardDTO dto = new BoardDTO(qna_seq, qna_title, content, file, date, mb_id);

				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbclose();

		}
		return list;
	}

	// �Խñ� �ϳ��� �������� �޼ҵ�
	public BoardDTO boardSelectOne(int num) {

		BoardDTO dto = null;
		dbconn();

		try {
			String sql = "select * from tbl_qna where qna_seq = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, num);
			rs = psmt.executeQuery();

			if (rs.next()) {
				int qna_seq = rs.getInt(1);
				String qna_title = rs.getString(2);
				String content = rs.getString(3);
				String file = rs.getString(4);
				String date = rs.getString(5);
				String mb_id = rs.getString(6);

				dto = new BoardDTO(qna_seq, qna_title, content, file, date, mb_id);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbclose();
		}

		return dto;
	}

	// �Խñ� ���� �޼ҵ�

	public int boardDelete(int qna_seq, String mb_id) {
		dbconn();
		try {
			String sql = "delete from tbl_qna where qna_seq=? and mb_id=?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, qna_seq);
			psmt.setString(2, mb_id);
			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbclose();

		}

		return cnt;
	}

	
	
	
	
	// �Խñ� ���� �޼ҵ�
	public int boardUpdate(BoardDTO dto) {
		dbconn();
		try {
			
			String sql = "update tbl_qna set qna_title=?, qna_content=?, qna_file=?, qna_date=sysdate where tbl_qna_seq=? and mb_id=?";

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getQna_title());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getFile());
			psmt.setInt(4, dto.getQna_seq());
			psmt.setString(5, dto.getMb_id());
			
			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbclose();

		}

		return cnt;
	}

}