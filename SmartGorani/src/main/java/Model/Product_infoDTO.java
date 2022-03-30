package Model;

public class Product_infoDTO {
	int p_seq;
	String p_serial;
	String mb_id;
	String inst_date;
	String product_name;
	
	
	public Product_infoDTO(int p_seq, String p_serial, String mb_id, String inst_date, String product_name) {
		super();
		this.p_seq = p_seq;
		this.p_serial = p_serial;
		this.mb_id = mb_id;
		this.inst_date = inst_date;
		this.product_name = product_name;
	}
	
	//name이 null일때 아래 생성자 사용
	public Product_infoDTO(int p_seq, String p_serial, String mb_id, String inst_date) {
		super();
		this.p_seq = p_seq;
		this.p_serial = p_serial;
		this.mb_id = mb_id;
		this.inst_date = inst_date;
	}
	public int getP_seq() {
		return p_seq;
	}
	public void setP_seq(int p_seq) {
		this.p_seq = p_seq;
	}
	public String getP_serial() {
		return p_serial;
	}
	public void setP_serial(String p_serial) {
		this.p_serial = p_serial;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public String getInst_date() {
		return inst_date;
	}
	public void setInst_date(String inst_date) {
		this.inst_date = inst_date;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	
	
	
}
