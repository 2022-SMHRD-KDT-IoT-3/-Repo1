package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReplyDAO {

	
	PreparedStatement psmt = null;
	Connection conn = null;
	ResultSet rs = null;
	int cnt = 0;
	ReplyDTO rdto = null;

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

	//답글 내용 DB 등록 메소드 
	public int replyinsert(ReplyDTO rdto) {
		try {

			dbconn();
			String sql = "insert into tbl_reply values(?, ?, ?, ?, ?, ?)";
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, rdto.getREPLY_SEQ());
			psmt.setString(2, rdto.getQNA_SEQ());
			psmt.setString(3, rdto.getREPLY_CONTENT());
			psmt.setString(4, rdto.getREPLY_FILE());
			psmt.setString(5, rdto.getREPLY_DATE());
			psmt.setString(6, rdto.getMB_ID());

			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbclose();
		}
		return cnt;
	}
		
	
	//답글 가져오기
	
	
}
