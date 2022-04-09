package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.simple.JSONArray;

public class ElectricDAO {
	
	// json 만들기 위한 함수
	private static ElectricDAO electricDAO = new ElectricDAO();

	public ElectricDAO() {

	}

	public static ElectricDAO getInstance() {

		return electricDAO;

	}

	PreparedStatement psmt = null;
	Connection conn = null;
	ResultSet rs = null;
	int cnt = 0;
	double sss =0;
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
		String sql = "INSERT INTO tbl_electric\n" + "    (mb_portserial,\n" + "    electric_useage, \n"
				+ "    electric_date) \n" + "VALUES\n" + "    (?,\n" + "    ?, \n" + "    sysdate) \n";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getMb_portserial());
			psmt.setDouble(2, dto.getElectric_useage());

			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 월별 electric (포트 - 고유제품) 사용량 조회 메소드 @최근 월의 값 불러오기
	public double monthElectric() {
		dbconn();
		try {
			String sql = "SELECT * FROM MONTHLY_USAGE where 년월='2022/04'";
			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();
			if (rs.next()) {
				String month = rs.getString(1);
				String serialport = rs.getString(2);
				double usage = rs.getInt(3);
				sss =usage;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbclose();
		}	
		return sss;
	}

	public double monthFare() {
	      double fare = 0;
	      sss = monthElectric();

	      // 들어오는 데이터 mW 이나 W라고 가정하고 계산함
	      int wh =  (int)sss;

	      if (wh <= 300) {
	         fare = wh * 88.3 + 910;
	      } else if (wh < 450) {
	         fare = (wh - 300) * 182.9 + 1600 + (300 * 88.3);

	      } else {
	         fare = (wh - 450) * 275.6 + 7300 + (300 * 88.3) + (150 * 182.9);
	      }
	      System.out.println(Math.round(fare));
	      return Math.round(fare);
	   }


	// json 구조 - 월별 사용량
	public JSONArray monthUsage() {
		dbconn();

		JSONArray jsonArray = new JSONArray();

		JSONArray colNameArray = new JSONArray(); // 컬 타이틀 설정

		colNameArray.add("년/월");
		//colNameArray.add("포트번호");
		colNameArray.add("사용량(단위:kWh)");
		jsonArray.add(colNameArray);

		// MONTHLY_USAGE view 조회
		try {

			String sql = "select TO_CHAR(ELECTRIC_DATE, 'YYYY/MM') as 년월, sum(ELECTRIC_USEAGE) AS 사용량 from TBL_ELECTRIC where ELECTRIC_DATE >='20180101' AND MB_PORTSERIAL='pt-001' GROUP BY to_char(ELECTRIC_DATE, 'YYYY/MM'), MB_PORTSERIAL ORDER BY TO_CHAR(ELECTRIC_DATE, 'YYYY/MM') asc";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				JSONArray rowArray = new JSONArray();
				rowArray.add(rs.getString("년월"));
				//rowArray.add(rs.getString("포트번호"));
				rowArray.add(rs.getInt("사용량"));
				jsonArray.add(rowArray);

			}
		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			dbclose();

		}

		return jsonArray;

	}
}
