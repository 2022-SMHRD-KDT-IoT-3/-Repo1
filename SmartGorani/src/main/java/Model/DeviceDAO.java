package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.json.simple.JSONArray;

public class DeviceDAO {

	// json 만들기 위한 함수
	private static DeviceDAO DeviceDAO = new DeviceDAO();

	public DeviceDAO() {

	}

	public static DeviceDAO getInstance() {

		return DeviceDAO;

	}

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

	// 디바이스 추가 메소드
	public int deviceInsert(DeviceDTO dto) {
		dbconn();
		try {
			String sql = "insert into tbl_device values(?,?,'','')";
			// 각 mb_portserial, Dv name, Mb_id 넣기
			psmt = conn.prepareStatement(sql);
			psmt.setString(2, dto.getMb_portserial());
			psmt.setString(3, dto.getDv_name());
			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbclose();

		}
		return cnt;
	}

	// 디바이스 삭제 메소드

	public int deviceDelete(String mb_portserial) {
		dbconn();
		try {
			String sql = "delete from tbl_device where mb_portserial=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, mb_portserial);
			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbclose();

		}

		return cnt;
	}

	// 디바이스 조회 메소드

	public ArrayList<DeviceDTO> DeviceSelect(String serial) {

		ArrayList<DeviceDTO> dlist = new ArrayList<DeviceDTO>();
		dbconn();

		try {
			String sql = "select distinct dv_name from tbl_device where MB_PORTSERIAL = ?";
			// 한개의 제품에 여러 콘센트 값
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, serial);
			rs = psmt.executeQuery();

			while (rs.next()) {
				String dv_name = rs.getString(1);

				DeviceDTO dto = new DeviceDTO(dv_name);
				dlist.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbclose();
		}

		return dlist;
	}

	// 디바이스별 전력 조회 메소드 ------------수정해야됨

	public ArrayList<DeviceDTO> DeviceSum(String serial) {

		ArrayList<DeviceDTO> dlist = new ArrayList<DeviceDTO>();
		dbconn();

		try {
			String sql = "select * from tbl_device where MB_PORTSERIAL = ?";

			// 조건 sum으로 다시 수정하기
			// 한개의 제품에 여러 콘센트 값
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, serial);
			rs = psmt.executeQuery();

			while (rs.next()) {
				String mb_portserial = rs.getString(1);
				String dv_name = rs.getString(2);
				Double dv_usage = rs.getDouble(3);
				String dv_date = rs.getString(4);

				DeviceDTO dto = new DeviceDTO(mb_portserial, dv_name, dv_usage, dv_date);
				dlist.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbclose();
		}

		return dlist;
	}

	// 디바이스별 월별 전력량 조회 json --(4월6일꺼만 조회됨 수정해야함)
	public JSONArray devicemonthUsage() {
		dbconn();

		JSONArray jsonArray = new JSONArray();

		JSONArray colNameArray = new JSONArray(); // 컬 타이틀 설정

		colNameArray.add("디바이스이름");
		colNameArray.add("사용량(단위:w)");
		jsonArray.add(colNameArray);

		// 쿼리로 조회
		try {

			String sql = "select dv_name as 디바이스이름, sum(dv_usage) as 사용량\r\n"
					+ "from tbl_device\r\n"
					+ "where mb_portserial = 'pt-001' AND TO_CHAR(DV_DATE , 'YYYY/MM') >='2022/04'\r\n"
					+ "group by dv_name";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				JSONArray rowArray = new JSONArray();
				rowArray.add(rs.getString("디바이스이름"));
				// rowArray.add(rs.getString("포트번호"));
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
	// 디바이스별 실시간 전력 조회 (초단위) json-- 4/6일거만 조회됨 수정해야함
	public JSONArray devicesecUsage() {
		dbconn();

		JSONArray jsonArray = new JSONArray();

		JSONArray colNameArray = new JSONArray(); // 컬 타이틀 설정

		colNameArray.add("DATE");
		colNameArray.add("디바이스 이름");
		colNameArray.add("사용량(단위:w)");
		jsonArray.add(colNameArray);

		// 쿼리로 조회
		try {

			String sql = "SELECT TO_CHAR(DV_DATE, 'YYYY-MM-DD HH24:MI:SS') as 시간, dv_name as 디바이스이름, dv_usage as 사용량 \r\n"
					+ "FROM TBL_DEVICE \r\n"
					+ "where mb_portserial = 'pt-001'and TO_CHAR(DV_DATE, 'YYYY-MM-DD HH24:MI:SS')>='2022-04-03 00:00:00'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				JSONArray rowArray = new JSONArray();
				rowArray.add(rs.getString("시간"));
				rowArray.add(rs.getString("디바이스이름"));
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
