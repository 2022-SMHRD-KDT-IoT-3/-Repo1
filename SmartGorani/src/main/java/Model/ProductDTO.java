package Model;

public class ProductDTO {
	String P_SEQ;
	String P_POWER;
	String P_MA;
	String MB_ID;
	String P_INST_DATE;
	public ProductDTO(String p_SEQ, String p_POWER, String p_MA, String mB_ID, String p_INST_DATE) {
		super();
		P_SEQ = p_SEQ;
		P_POWER = p_POWER;
		P_MA = p_MA;
		MB_ID = mB_ID;
		P_INST_DATE = p_INST_DATE;
	}
	public String getP_SEQ() {
		return P_SEQ;
	}
	public void setP_SEQ(String p_SEQ) {
		P_SEQ = p_SEQ;
	}
	public String getP_POWER() {
		return P_POWER;
	}
	public void setP_POWER(String p_POWER) {
		P_POWER = p_POWER;
	}
	public String getP_MA() {
		return P_MA;
	}
	public void setP_MA(String p_MA) {
		P_MA = p_MA;
	}
	public String getMB_ID() {
		return MB_ID;
	}
	public void setMB_ID(String mB_ID) {
		MB_ID = mB_ID;
	}
	public String getP_INST_DATE() {
		return P_INST_DATE;
	}
	public void setP_INST_DATE(String p_INST_DATE) {
		P_INST_DATE = p_INST_DATE;
	}
	
	

}
