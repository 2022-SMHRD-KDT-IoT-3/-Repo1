package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BatteryDAO {
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
		public void insertUsage(BatteryDTO dto) {
			
			String sql = "INSERT INTO tbl_battery\n"
					+ "    (mb_portserial,\n"
					+ "		battery_capacity, \n"
					+ "    battery_usage, \n"
					+ "    battery_date) \n"
					+ "VALUES\n"
					+ "    (?,\n"
					+ "		?, \n"
					+ "    ?, \n"
					+ "    sysdate) \n";

			
			try {
				psmt = conn.prepareStatement(sql);
				
				psmt.setString(1, dto.getMb_portserial());
				psmt.setInt(2, dto.getBattery_capacity());
				psmt.setDouble(3, dto.getBattery_usage());

				
				psmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
}
