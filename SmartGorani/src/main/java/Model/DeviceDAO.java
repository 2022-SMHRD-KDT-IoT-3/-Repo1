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
			String sql = "insert into tbl_device values(1,?,?,?,?,?)";
			//첫번째컬럼은 d_seq값이라 자동생성 -- > 1 
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getMb_id());
			psmt.setInt(2, dto.getDv_num());
			psmt.setString(3, dto.getDv_desc());
			psmt.setString(4, dto.getConsent_name());
			psmt.setInt(5, dto.getP_seq());
			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbclose();

		}
		return cnt;
	}
	
	//디바이스 삭제 메소드
	
	public int deviceDelete(int dv_seq, String mb_id) {
		dbconn();
		try {
			String sql = "delete from tbl_device where dv_seq=? and mb_id=?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, dv_seq);
			psmt.setString(2, mb_id);
			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbclose();

		}

		return cnt;
	}

	//디바이스 조회 메소드
	
	public ArrayList<DeviceDTO> DeviceSelect(String mb_id) {

		ArrayList<DeviceDTO> dlist = new ArrayList<DeviceDTO>();
		dbconn();

		try {
			String sql = "select * from tbl_device where mb_id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, mb_id);
			rs = psmt.executeQuery();

			while (rs.next()) {
				int dv_seq = rs.getInt(1);
				mb_id = rs.getString(2);
				int dv_num = rs.getInt(3);
				String dv_desc = rs.getString(4);
				String consent = rs.getString(5);
				int p_seq  = rs.getInt(6);

				DeviceDTO dto = new DeviceDTO(dv_seq, mb_id, dv_num, dv_desc, consent, p_seq);
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
