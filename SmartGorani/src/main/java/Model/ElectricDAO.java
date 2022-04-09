package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.simple.JSONArray;

public class ElectricDAO {
	
	// json ����� ���� �Լ�
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

	// DB���� ���� �޼ҵ�
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

	// DB ������ ���� �޼ҵ�
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

	// ���� electric (��Ʈ - ������ǰ) ��뷮 ��ȸ �޼ҵ� @�ֱ� ���� �� �ҷ�����
	public double monthElectric() {
		dbconn();
		try {
			String sql = "SELECT * FROM MONTHLY_USAGE where ���='2022/04'";
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

	      // ������ ������ mW �̳� W��� �����ϰ� �����
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


	// json ���� - ���� ��뷮
	public JSONArray monthUsage() {
		dbconn();

		JSONArray jsonArray = new JSONArray();

		JSONArray colNameArray = new JSONArray(); // �� Ÿ��Ʋ ����

		colNameArray.add("��/��");
		//colNameArray.add("��Ʈ��ȣ");
		colNameArray.add("��뷮(����:kWh)");
		jsonArray.add(colNameArray);

		// MONTHLY_USAGE view ��ȸ
		try {

			String sql = "select TO_CHAR(ELECTRIC_DATE, 'YYYY/MM') as ���, sum(ELECTRIC_USEAGE) AS ��뷮 from TBL_ELECTRIC where ELECTRIC_DATE >='20180101' AND MB_PORTSERIAL='pt-001' GROUP BY to_char(ELECTRIC_DATE, 'YYYY/MM'), MB_PORTSERIAL ORDER BY TO_CHAR(ELECTRIC_DATE, 'YYYY/MM') asc";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				JSONArray rowArray = new JSONArray();
				rowArray.add(rs.getString("���"));
				//rowArray.add(rs.getString("��Ʈ��ȣ"));
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
