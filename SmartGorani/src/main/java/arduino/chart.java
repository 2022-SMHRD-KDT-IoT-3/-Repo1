package arduino;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.simple.JSONArray;

public class chart {
	PreparedStatement psmt = null;
	Connection conn = null;
	ResultSet rs = null;
	int cnt = 0;

	// DB연결 메소드
	public void dbconn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 1-2. DB에 접속하기 위한 주소, 아이디, 패스워드 지정
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String dbid = "hr";
			String dbpw = "hr";
			conn = DriverManager.getConnection(url, dbid, dbpw);
			System.out.println("db연결 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("db연결 실패");
		}
	}

	// DB close
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

	public JSONArray jsondata() {
		dbconn();

		JSONArray jsonArray = new JSONArray();

		JSONArray colNameArray = new JSONArray(); // 컬 타이틀 설정

		colNameArray.add("주소");
		colNameArray.add("인원수");
		jsonArray.add(colNameArray);

		try {
			String sql = "SELECT address,COUNT(*) AS cnt FROM member2 GROUP BY address ORDER BY cnt ";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {

				JSONArray rowArray = new JSONArray();

				rowArray.add(rs.getString("address"));

				rowArray.add(rs.getInt("cnt"));

				jsonArray.add(rowArray);

			} // while

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			dbclose();

		}

		return jsonArray;

	}// getCountAddress

}
