package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;

import arduino.MemberDao;

public class device_infoDAO {
	
	// json 만들기 위한 함수
	private static device_infoDAO Device_infoDAO = new device_infoDAO();

	public device_infoDAO() {

	}

	public static device_infoDAO getInstance() {

		return Device_infoDAO;

	}
	
	
	// 초기 변수 선언
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
	
	// device 1번 삽입 메소드
		public void insertUsage1(device_infoDTO dto) {
			
			String sql = "INSERT INTO tbl_device\n"
					+ "    (mb_portserial,\n"
					+ "		dv_name, \n"
					+ "    dv_usage, \n"
					+ "    dv_date) \n"
					+ "VALUES\n"
					+ "    (?,\n"
					+ "		1, \n"
					+ "    ?, \n"
					+ "    sysdate) \n";

					
			try {
				psmt = conn.prepareStatement(sql);
				
				psmt.setString(1, dto.getMb_portserial());
				psmt.setDouble(2, dto.getDv_usage1());

						
				psmt.executeUpdate();
						
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		// device 2번 삽입 메소드
				public void insertUsage2(device_infoDTO dto) {
					
					String sql = "INSERT INTO tbl_device\n"
							+ "    (mb_portserial,\n"
							+ "		dv_name, \n"
							+ "    dv_usage, \n"
							+ "    dv_date) \n"
							+ "VALUES\n"
							+ "    (?,\n"
							+ "		2, \n"
							+ "    ?, \n"
							+ "    sysdate) \n";

							
					try {
						psmt = conn.prepareStatement(sql);
						
						psmt.setString(1, dto.getMb_portserial());
						psmt.setDouble(2, dto.getDv_usage2());

								
						psmt.executeUpdate();
								
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				
				// device 3번 삽입 메소드
				public void insertUsage3(device_infoDTO dto) {
					
					String sql = "INSERT INTO tbl_device\n"
							+ "    (mb_portserial,\n"
							+ "		dv_name, \n"
							+ "    dv_usage, \n"
							+ "    dv_date) \n"
							+ "VALUES\n"
							+ "    (?,\n"
							+ "		3, \n"
							+ "    ?, \n"
							+ "    sysdate) \n";

							
					try {
						psmt = conn.prepareStatement(sql);
						
						psmt.setString(1, dto.getMb_portserial());
						psmt.setDouble(2, dto.getDv_usage3());

								
						psmt.executeUpdate();
								
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				
				// @ json 구조로 데이터 출력하기
				public JSONArray getDevice() {
					dbconn();

					JSONArray jsonArray = new JSONArray();

					JSONArray colNameArray = new JSONArray(); // 컬 타이틀 설정

//					colNameArray.add("포트시리얼번호");
					colNameArray.add("디바이스 이름");
					colNameArray.add("디바이스 사용량");
//					colNameArray.add("디바이스 사용일자");
					jsonArray.add(colNameArray);
					
					// 현재 dv_name =1 인것만 조회하는 중
					try {
						
						
						String sql = "SELECT dv_name, sum(dv_usage) FROM TBL_DEVICE WHERE DV_NAME ='1' GROUP BY dv_name";
						psmt = conn.prepareStatement(sql);
						rs = psmt.executeQuery();
						while (rs.next()) {

							JSONArray rowArray = new JSONArray();

//							rowArray.add(rs.getString("MB_PORTSERIAL"));
							rowArray.add(rs.getString("DV_NAME"));
							rowArray.add(rs.getInt("sum(DV_USAGE)"));
//							rowArray.add(rs.getString("DV_DATE"));

							jsonArray.add(rowArray);

						} // while

					} catch (Exception e) {

						e.printStackTrace();

					} finally {

						dbclose();

					}

					return jsonArray;
	
				}
}
