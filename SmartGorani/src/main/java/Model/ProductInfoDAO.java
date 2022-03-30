package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductInfoDAO {
	PreparedStatement psmt = null;
	Connection conn = null;
	ResultSet rs = null;
	int cnt = 0;
	Product_infoDTO info_dto = null;

	// DB연결 메소드
	public void dbconn() {
		// 1. DB연결
		// 1-1. class 찾기 : DB와 이클립스를 연결해주는 class
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 1-2. DB에 접속하기 위한 주소, 아이디, 패스워드 지정
			String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
			String dbid = "campus_b_0310_4";
			String dbpw = "smhrd4";

			// 1-3. Connection객체 사용해서 DB 연결!
			conn = DriverManager.getConnection(url, dbid, dbpw);
			System.out.println("db연결 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("db연결 실패");
		}
	}

	// DB close 메소드
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

	

	
	//디바이스 테이블에 디바이스를 추가하려면 p_seq값 필요함!
	//사용자에게 시퀀스값인 p_seq를 입력받을 수 없어서
	// p_seq를 참조하는 p_serial(pk)을 매개변수로 받아 info테이블에서 p_seq를 찾아오는 메소드
	public Product_infoDTO pseqFind (String p_serial) {
		dbconn();
		try {
			String sql = "select * from tbl_product_info where p_serial = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, p_serial);
			rs = psmt.executeQuery();

			if (rs.next()) {
				int p_seq = rs.getInt(1);
				p_serial = rs.getString(2);
				String mb_id = rs.getString(3);
				String date = rs.getString(4);
				String p_name = rs.getString(5);

				info_dto = new Product_infoDTO(p_seq, p_serial, mb_id, date, p_name);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbclose();
		}

		
		
		return info_dto;
	}

}
