package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class chartTest {

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

	// DB �ݴ� �޼ҵ�
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

	// DB�� json���� �����_test��
	public int makejson() {
		// 1. DB ����
		dbconn();
		try {
			// 2. product table ��ȸ�ϱ�
			String sql = "select * from tbl_product";
			psmt = conn.prepareStatement(sql);

			// 3.����
			cnt = psmt.executeUpdate();

			// 4.
			// �������: �� 1���� �ڹٺ�ü�� �����ؼ� ArrayList �� ĭ�� ����
			// json�̿��� ���: �� 1���� jsonObject��ü�� �����ؼ� JsonArray �� ĭ�� ����

			// 5-2.JSONArray ����
			JSONArray arr = new JSONArray();
		
			// 5-1.while��
//			while (rs.next()) {
//				String p_seq = rs.getString(1);
//				String p_power = rs.getString(2);
//				String p_ma = rs.getString(3);
//				String mb_id = rs.getString(4);
//				String p_inst_date = rs.getString(5);
//				System.out.println(p_seq);
//			}
			System.out.println("��ȸ�Ϸ�");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbclose();
		}
		return cnt;
	}
}


