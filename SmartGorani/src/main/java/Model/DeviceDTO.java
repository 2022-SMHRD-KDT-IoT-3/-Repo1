package Model;

public class DeviceDTO {
	String DV_SEQ;
	String MB_ID;
	String DV_NUM;
	String DV_DESC;
	String CONSENT_NAME;
	public DeviceDTO(String dV_SEQ, String mB_ID, String dV_NUM, String dV_DESC, String cONSENT_NAME, String p_SEQ) {
		super();
		DV_SEQ = dV_SEQ;
		MB_ID = mB_ID;
		DV_NUM = dV_NUM;
		DV_DESC = dV_DESC;
		CONSENT_NAME = cONSENT_NAME;
		P_SEQ = p_SEQ;
	}
	String P_SEQ;
	public String getDV_SEQ() {
		return DV_SEQ;
	}
	public void setDV_SEQ(String dV_SEQ) {
		DV_SEQ = dV_SEQ;
	}
	public String getMB_ID() {
		return MB_ID;
	}
	public void setMB_ID(String mB_ID) {
		MB_ID = mB_ID;
	}
	public String getDV_NUM() {
		return DV_NUM;
	}
	public void setDV_NUM(String dV_NUM) {
		DV_NUM = dV_NUM;
	}
	public String getDV_DESC() {
		return DV_DESC;
	}
	public void setDV_DESC(String dV_DESC) {
		DV_DESC = dV_DESC;
	}
	public String getCONSENT_NAME() {
		return CONSENT_NAME;
	}
	public void setCONSENT_NAME(String cONSENT_NAME) {
		CONSENT_NAME = cONSENT_NAME;
	}
	public String getP_SEQ() {
		return P_SEQ;
	}
	public void setP_SEQ(String p_SEQ) {
		P_SEQ = p_SEQ;
	}
	
	
	

}
