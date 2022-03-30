package Model;

public class ProductDTO {
	int p_seq;
	int p_power;
	int p_ma;
	String mb_id;
	public ProductDTO(int p_seq, int p_power, int p_ma, String mb_id) {
		super();
		this.p_seq = p_seq;
		this.p_power = p_power;
		this.p_ma = p_ma;
		this.mb_id = mb_id;
	}
	public int getP_seq() {
		return p_seq;
	}
	public void setP_seq(int p_seq) {
		this.p_seq = p_seq;
	}
	public int getP_power() {
		return p_power;
	}
	public void setP_power(int p_power) {
		this.p_power = p_power;
	}
	public int getP_ma() {
		return p_ma;
	}
	public void setP_ma(int p_ma) {
		this.p_ma = p_ma;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}


}
