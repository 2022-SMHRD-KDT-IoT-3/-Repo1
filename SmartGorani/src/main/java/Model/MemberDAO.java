package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDAO {

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
			String sql = "insert into tbl_member values(?, ?, ?, ?, ?, sysdate)";

			// sql�� DB�� ����
			psmt = conn.prepareStatement(sql);

			// ?�� �� ä���
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPw());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getGender());
			psmt.setString(5, dto.getType());
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
			String sql = "select mb_id from tbl_member where mb_id = ?";

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

	// �α��� �޼ҵ�
	public MemberDTO login(String id, String pw) {
		dbconn();
		try {
			String sql = "select * from tbl_member where mb_id = ? and mb_pw = ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			rs = psmt.executeQuery();

			if (rs.next()) {
				id = rs.getString(1);
				pw = rs.getString(2);
				String name = rs.getString(3);
				String type = rs.getString(4);
				String reg_date = rs.getString(5);
				// ������
				dto = new MemberDTO(id, pw, name, type, reg_date);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbclose();
		}
		return dto;
	}

	// ��й�ȣ �ʱ�ȭ
	public int Resetpw(String id) {
		dbconn();
		try {

			String sql = "update tbl_member set mb_pw='0000' where mb_id=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);

			cnt = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbclose();
		}
		return cnt;
	}

	// ȸ������ ���� �޼ҵ�
	public int update(MemberDTO dto) {
		dbconn();
		try {
			String sql = "update tbl_member set mb_name =?, mb_pw = ?, mb_type = ?, mb_gender = ? where mb_id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getPw());
			psmt.setString(3, dto.getType());
			psmt.setString(4, dto.getGender());
			psmt.setString(5, dto.getId());
			cnt = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbclose();
		}
		return cnt;
	}

	// ȸ�� ��ü��ȸ �޼ҵ�
	public ArrayList<MemberDTO> selectAll() {
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();

		dbconn();
		try {
			String sql = "select * from tbl_member";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString(1);
				String pw = rs.getString(2);
				String name = rs.getString(3);
				String type = rs.getString(4);
				String gender = rs.getString(5);
				String reg_date = rs.getString(6);
				MemberDTO dto = new MemberDTO(id, pw, name, type, gender,reg_date);
				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbclose();
		}

		return list;

	}

}
