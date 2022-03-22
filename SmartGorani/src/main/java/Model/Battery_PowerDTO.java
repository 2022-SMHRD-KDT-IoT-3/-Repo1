package Model;

public class Battery_PowerDTO {
	String GENERATION_SEQ;
	String BATTERY_SEQ;
	String GENERATION_DATE;
	String GENERATION_POWER;
	
	public Battery_PowerDTO(String gENERATION_SEQ, String bATTERY_SEQ, String gENERATION_DATE,
			String gENERATION_POWER) {
		super();
		GENERATION_SEQ = gENERATION_SEQ;
		BATTERY_SEQ = bATTERY_SEQ;
		GENERATION_DATE = gENERATION_DATE;
		GENERATION_POWER = gENERATION_POWER;
	}

	public String getGENERATION_SEQ() {
		return GENERATION_SEQ;
	}

	public void setGENERATION_SEQ(String gENERATION_SEQ) {
		GENERATION_SEQ = gENERATION_SEQ;
	}

	public String getBATTERY_SEQ() {
		return BATTERY_SEQ;
	}

	public void setBATTERY_SEQ(String bATTERY_SEQ) {
		BATTERY_SEQ = bATTERY_SEQ;
	}

	public String getGENERATION_DATE() {
		return GENERATION_DATE;
	}

	public void setGENERATION_DATE(String gENERATION_DATE) {
		GENERATION_DATE = gENERATION_DATE;
	}

	public String getGENERATION_POWER() {
		return GENERATION_POWER;
	}

	public void setGENERATION_POWER(String gENERATION_POWER) {
		GENERATION_POWER = gENERATION_POWER;
	}
	
	
	
}
