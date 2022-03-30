package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductDAO {

	
	// �������� ����
	PreparedStatement psmt = null;
	Connection conn = null;
	ResultSet rs = null;
	int cnt = 0;
	ProductDTO dto = null;
	Product_infoDTO info_dto = null;

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
	
	//mb_id�� �������� product�� ���� �� ����  --> �Ѿ��̵�� ��ǰ �������� --->���� �ʿ���!
	public ArrayList<Product_infoDTO> selectProduct(String mb_id) {
		ArrayList<Product_infoDTO> list = new ArrayList<Product_infoDTO>();
		
		dbconn();
		try {
			String sql = "select * from tbl_product_info where mb_id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, mb_id);
			rs = psmt.executeQuery();

			while (rs.next()) {
				int p_seq = rs.getInt(1);
				String p_serial = rs.getString(2);
				mb_id = rs.getString(3);
				String instdate = rs.getString(4);
				String p_name = rs.getString(5);
				info_dto = new Product_infoDTO(p_seq, p_serial, mb_id, instdate, p_name);
				list.add(info_dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbclose();
		}
		return list;
	}

	public int pseqFind(String p_serial) {
		int p_seq=0;
		
		
		
		
		return p_seq;
	}
	
	
}
