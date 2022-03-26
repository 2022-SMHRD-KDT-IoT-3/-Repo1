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

	// DB연결 메소드
	public void dbconn() {
		try {
			System.out.println("테스트");
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
			String dbid = "campus_b_0310_4";
			String dbpw = "smhrd4";
			conn = DriverManager.getConnection(url, dbid, dbpw);
			System.out.println("db연결 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("db연결 실패");
		}
	}

	// DB 닫는 메소드
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

	// DB값 json으로 만들기_test용
	public int makejson() {
		// 1. DB 연결
		dbconn();
		try {
			// 2. product table 조회하기
			String sql = "select * from tbl_product";
			psmt = conn.prepareStatement(sql);

			// 3.실행
			cnt = psmt.executeUpdate();

			// 4.
			// 기존방식: 행 1줄을 자바빈객체에 저장해서 ArrayList 한 칸에 저장
			// json이용한 방식: 행 1줄을 jsonObject객체에 저장해서 JsonArray 한 칸에 저장

			// 5-2.JSONArray 생성
			JSONArray arr = new JSONArray();
		
			// 5-1.while문
//			while (rs.next()) {
//				String p_seq = rs.getString(1);
//				String p_power = rs.getString(2);
//				String p_ma = rs.getString(3);
//				String mb_id = rs.getString(4);
//				String p_inst_date = rs.getString(5);
//				System.out.println(p_seq);
//			}
			System.out.println("조회완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbclose();
		}
		return cnt;
	}
}


