package Model;

public class DeviceControlDTO {
	
	String CONTROL_SEQ;
	String DV_SEQ;
	String CONTROL_TYPE;
	String CONTROL_CONTENT;
	String P_SEQ;
	String CONTROL_DATE;
	String DV_POWER;
	String DV_MA;
	
	public DeviceControlDTO(String cONTROL_SEQ, String dV_SEQ, String cONTROL_TYPE, String cONTROL_CONTENT,
			String p_SEQ, String cONTROL_DATE, String dV_POWER, String dV_MA) {
		super();
		CONTROL_SEQ = cONTROL_SEQ;
		DV_SEQ = dV_SEQ;
		CONTROL_TYPE = cONTROL_TYPE;
		CONTROL_CONTENT = cONTROL_CONTENT;
		P_SEQ = p_SEQ;
		CONTROL_DATE = cONTROL_DATE;
		DV_POWER = dV_POWER;
		DV_MA = dV_MA;
	}

	public String getCONTROL_SEQ() {
		return CONTROL_SEQ;
	}

	public void setCONTROL_SEQ(String cONTROL_SEQ) {
		CONTROL_SEQ = cONTROL_SEQ;
	}

	public String getDV_SEQ() {
		return DV_SEQ;
	}

	public void setDV_SEQ(String dV_SEQ) {
		DV_SEQ = dV_SEQ;
	}

	public String getCONTROL_TYPE() {
		return CONTROL_TYPE;
	}

	public void setCONTROL_TYPE(String cONTROL_TYPE) {
		CONTROL_TYPE = cONTROL_TYPE;
	}

	public String getCONTROL_CONTENT() {
		return CONTROL_CONTENT;
	}

	public void setCONTROL_CONTENT(String cONTROL_CONTENT) {
		CONTROL_CONTENT = cONTROL_CONTENT;
	}

	public String getP_SEQ() {
		return P_SEQ;
	}

	public void setP_SEQ(String p_SEQ) {
		P_SEQ = p_SEQ;
	}

	public String getCONTROL_DATE() {
		return CONTROL_DATE;
	}

	public void setCONTROL_DATE(String cONTROL_DATE) {
		CONTROL_DATE = cONTROL_DATE;
	}

	public String getDV_POWER() {
		return DV_POWER;
	}

	public void setDV_POWER(String dV_POWER) {
		DV_POWER = dV_POWER;
	}

	public String getDV_MA() {
		return DV_MA;
	}

	public void setDV_MA(String dV_MA) {
		DV_MA = dV_MA;
	}
	
	

	
}
