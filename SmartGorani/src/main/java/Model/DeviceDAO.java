package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.json.simple.JSONArray;

public class DeviceDAO {

	// json ����� ���� �Լ�
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

	// DB���� �޼ҵ�
	public void dbconn() {
		try {
			System.out.println("�׽�Ʈ");
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
			String dbid = "campus_b_0310_4";
			String dbpw = "smhrd4";
			conn = DriverManager.getConnection(url, dbid, dbpw);
			System.out.println("db���� ����");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("db���� ����");
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

	// ����̽� �߰� �޼ҵ�
	public int deviceInsert(DeviceDTO dto) {
		dbconn();
		try {
			String sql = "insert into tbl_device values(?,?,'','')";
			// �� mb_portserial, Dv name, Mb_id �ֱ�
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

	// ����̽� ���� �޼ҵ�

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

	// ����̽� ��ȸ �޼ҵ�

	public ArrayList<DeviceDTO> DeviceSelect(String serial) {

		ArrayList<DeviceDTO> dlist = new ArrayList<DeviceDTO>();
		dbconn();

		try {
			String sql = "select distinct dv_name from tbl_device where MB_PORTSERIAL = ?";
			// �Ѱ��� ��ǰ�� ���� �ܼ�Ʈ ��
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

	// ����̽��� ���� ��ȸ �޼ҵ� ------------�����ؾߵ�

	public ArrayList<DeviceDTO> DeviceSum(String serial) {

		ArrayList<DeviceDTO> dlist = new ArrayList<DeviceDTO>();
		dbconn();

		try {
			String sql = "select * from tbl_device where MB_PORTSERIAL = ?";

			// ���� sum���� �ٽ� �����ϱ�
			// �Ѱ��� ��ǰ�� ���� �ܼ�Ʈ ��
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

	// ����̽��� ���� ���·� ��ȸ json --(4��6�ϲ��� ��ȸ�� �����ؾ���)
	public JSONArray devicemonthUsage() {
		dbconn();

		JSONArray jsonArray = new JSONArray();

		JSONArray colNameArray = new JSONArray(); // �� Ÿ��Ʋ ����

		colNameArray.add("����̽��̸�");
		colNameArray.add("��뷮(����:w)");
		jsonArray.add(colNameArray);

		// ������ ��ȸ
		try {

			String sql = "select dv_name as ����̽��̸�, sum(dv_usage) as ��뷮\r\n"
					+ "from tbl_device\r\n"
					+ "where mb_portserial = 'pt-001' AND TO_CHAR(DV_DATE , 'YYYY/MM') >='2022/04'\r\n"
					+ "group by dv_name";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				JSONArray rowArray = new JSONArray();
				rowArray.add(rs.getString("����̽��̸�"));
				// rowArray.add(rs.getString("��Ʈ��ȣ"));
				rowArray.add(rs.getInt("��뷮"));
				jsonArray.add(rowArray);

			}
		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			dbclose();

		}

		return jsonArray;

	}
	// ����̽��� �ǽð� ���� ��ȸ (�ʴ���) json-- 4/6�ϰŸ� ��ȸ�� �����ؾ���
	public JSONArray devicesecUsage() {
		dbconn();

		JSONArray jsonArray = new JSONArray();

		JSONArray colNameArray = new JSONArray(); // �� Ÿ��Ʋ ����

		colNameArray.add("DATE");
		colNameArray.add("����̽� �̸�");
		colNameArray.add("��뷮(����:w)");
		jsonArray.add(colNameArray);

		// ������ ��ȸ
		try {

			String sql = "SELECT TO_CHAR(DV_DATE, 'YYYY-MM-DD HH24:MI:SS') as �ð�, dv_name as ����̽��̸�, dv_usage as ��뷮 \r\n"
					+ "FROM TBL_DEVICE \r\n"
					+ "where mb_portserial = 'pt-001'and TO_CHAR(DV_DATE, 'YYYY-MM-DD HH24:MI:SS')>='2022-04-03 00:00:00'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				JSONArray rowArray = new JSONArray();
				rowArray.add(rs.getString("�ð�"));
				rowArray.add(rs.getString("����̽��̸�"));
				rowArray.add(rs.getInt("��뷮"));
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
