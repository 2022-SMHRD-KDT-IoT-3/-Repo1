package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {
	// �޼��带 �� ���巯��?

	// �������� ����
	PreparedStatement psmt = null;
	Connection conn = null;
	ResultSet rs = null;
	int cnt = 0;
	MemberDTO dto = null;

	// DB���� �޼ҵ�
	public void dbconn() {
		// 1. DB����
		// 1-1. class ã�� : DB�� ��Ŭ������ �������ִ� class
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 1-2. DB�� �����ϱ� ���� �ּ�, ���̵�, �н����� ����
			String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
			String dbid = "campus_b_0310_4";
			String dbpw = "smhrd4";

			// 1-3. Connection��ü ����ؼ� DB ����!
			conn = DriverManager.getConnection(url, dbid, dbpw);
			System.out.println("db���� ����");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("db���� ����");
		}
	}

	// DB close �޼ҵ�
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

	// ȸ������ �޼ҵ�
	public int join(MemberDTO dto) {
		// 1. DB����
		// 1-1. class ã�� : DB�� ��Ŭ������ �������ִ� class
		try {

			dbconn();
			// 2. SQL�� �ۼ�
			String sql = "insert into tbl_member values(?, ?, ?, 'a', sysdate)";

			// sql�� DB�� ����
			psmt = conn.prepareStatement(sql);

			// ?�� �� ä���
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPw());
			psmt.setString(3, dto.getName());
//			psmt.setString(4, dto.getType());
//			psmt.setString(5, dto.getReg_date());

			// sql�� ����(����� ���� ���ڷ� ��ȯ)
			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbclose();
		}
		return cnt;
	}

	// ���̵� �ߺ�üũ �޼ҵ�
	public boolean idCheck(String id) {
		boolean result = false;
		try {
			dbconn();
			String sql = "select email from tbl_member where id = ?";

			psmt = conn.prepareStatement(sql);

			psmt.setString(1, id);
			rs = psmt.executeQuery();

			if (rs.next()) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbclose();

		}
		return result;
	}
	
		public MemberDTO login(String id, String pw) {
			dbconn();
			try {
				String sql = "select * from tbl_member where mb_id = ? and mb_pw = ? ";
				psmt =conn.prepareStatement(sql);
				psmt.setString(1, id);
				psmt.setString(2, pw);
				rs = psmt.executeQuery();  
				
				if(rs.next()) {
					id= rs.getString(1);
					pw = rs.getString(2);
					String name = rs.getString(3);
					String type =rs.getString(4);
					String reg_date = rs.getString(5);
					// ������
					 dto = new MemberDTO(id, pw, name, type, reg_date);
					}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				dbclose();
			}return dto;
		}

}
