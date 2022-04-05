	package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DeviceDAO {

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

	
	//디바이스 추가 메소드
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
	
	//디바이스 삭제 메소드
	
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

	//디바이스 조회 메소드
	
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

	
	//디바이스별 전력 조회 메소드 ------------수정해야됨
	
	public ArrayList<DeviceDTO> DeviceSum(String serial) {

		ArrayList<DeviceDTO> dlist = new ArrayList<DeviceDTO>();
		dbconn();

		try {
			String sql = "select * from tbl_device where MB_PORTSERIAL = ?";
			
			//조건 sum으로 다시 수정하기
			// 한개의 제품에 여러 콘센트 값
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, serial);
			rs = psmt.executeQuery();

			while (rs.next()) {
				String mb_portserial = rs.getString(1);
				String dv_name = rs.getString(2);
				Double dv_usage = rs.getDouble(3);
				String dv_date= rs.getString(4);

		DeviceDTO dto = new DeviceDTO(mb_portserial, dv_name,dv_usage, dv_date);
		dlist.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbclose();
		}

		return dlist;
	}
}
