package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {
	// 메서드를 함 만드러바?

	// 전역변수 선언
	PreparedStatement psmt = null;
	Connection conn = null;
	ResultSet rs = null;
	int cnt = 0;
	MemberDTO dto = null;

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

	// 회원가입 메소드
	public int join(MemberDTO dto) {
		// 1. DB연결
		// 1-1. class 찾기 : DB와 이클립스를 연결해주는 class
		try {

			dbconn();
			// 2. SQL문 작성
			String sql = "insert into tbl_member values(?, ?, ?, 'a', sysdate)";

			// sql문 DB에 전달
			psmt = conn.prepareStatement(sql);

			// ?에 값 채우기
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPw());
			psmt.setString(3, dto.getName());
//			psmt.setString(4, dto.getType());
//			psmt.setString(5, dto.getReg_date());

			// sql문 실행(실행된 행의 숫자로 반환)
			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbclose();
		}
		return cnt;
	}

	// 아이디 중복체크 메소드
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
					// 실행결과
					 dto = new MemberDTO(id, pw, name, type, reg_date);
					}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				dbclose();
			}return dto;
		}

}
