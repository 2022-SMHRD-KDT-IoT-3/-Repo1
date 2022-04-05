package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ElectricDAO {
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

	// DB연결 해제 메소드
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
	
	// DB 데이터 삽입 메소드
	public void insertUseElectric(ElectricDTO dto) {
		String sql = "INSERT INTO tbl_electric\n"
				+ "    (mb_portserial,\n"
				+ "    electric_useage, \n"
				+ "    electric_date) \n"
				+ "VALUES\n"
				+ "    (?,\n"
				+ "    ?, \n"
				+ "    sysdate) \n";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getMb_portserial());
			psmt.setDouble(2, dto.getElectric_useage());
			
			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 월별 electric (포트 - 고유제품) 사용량 조회 메소드
	public int monthElectric() {
		dbconn();
		try {
			String sql = "SELECT * FROM MONTHLY_USAGE where 날짜='202203'";
			psmt = conn.prepareStatement(sql);
			
			rs = psmt.executeQuery();
			if (rs.next()) {
				String month  = rs.getString(1);
				String serialport = rs.getString(2);
				int usage = rs.getInt(3);
				cnt=usage;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			dbclose();
		}
		return cnt;
	}
	
	public double monthFare() {
		double fare = 0;
		monthElectric();
		
		// 들어오는 데이터 mW 이나 W라고 가정하고 계산함
		double wh = cnt/3600;
		
		if (wh<=300) {
			fare = wh * 88.3+910;
		} else if(wh<450) {
			fare = (wh-300)*182.9 + 1600 + (300*88.3);
					
		} else {
			fare = (wh-450)*275.6 + 7300 + (300*88.3) + (150*182.9);
		}
		
		return fare;
	}
}
