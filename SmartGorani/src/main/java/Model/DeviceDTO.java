package Model;

public class DeviceDTO {
	int dv_seq;
	String mb_id;
	int db_num;
	String dv_desc;
	String consent_name;
	int p_seq;
	public DeviceDTO(int dv_seq, String mb_id, int db_num, String dv_desc, String consent_name, int p_seq) {
		super();
		this.dv_seq = dv_seq;
		this.mb_id = mb_id;
		this.db_num = db_num;
		this.dv_desc = dv_desc;
		this.consent_name = consent_name;
		this.p_seq = p_seq;
	}
	public int getDv_seq() {
		return dv_seq;
	}
	public void setDv_seq(int dv_seq) {
		this.dv_seq = dv_seq;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public int getDb_num() {
		return db_num;
	}
	public void setDb_num(int db_num) {
		this.db_num = db_num;
	}
	public String getDv_desc() {
		return dv_desc;
	}
	public void setDv_desc(String dv_desc) {
		this.dv_desc = dv_desc;
	}
	public String getConsent_name() {
		return consent_name;
	}
	public void setConsent_name(String consent_name) {
		this.consent_name = consent_name;
	}
	public int getP_seq() {
		return p_seq;
	}
	public void setP_seq(int p_seq) {
		this.p_seq = p_seq;
	}
	
	
}
