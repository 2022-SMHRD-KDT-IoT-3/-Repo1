package Model;

public class BatteryDTO {
	private String BATTERY_SEQ;
	private String BATTERY_CAPACITY;
	private String BATTERY_OWNER;
	private String BATTERY_INST_DATE;
	
	public BatteryDTO(String bATTERY_SEQ, String bATTERY_CAPACITY, String bATTERY_OWNER, String bATTERY_INST_DATE) {
		super();
		BATTERY_SEQ = bATTERY_SEQ;
		BATTERY_CAPACITY = bATTERY_CAPACITY;
		BATTERY_OWNER = bATTERY_OWNER;
		BATTERY_INST_DATE = bATTERY_INST_DATE;
	}

	public String getBATTERY_SEQ() {
		return BATTERY_SEQ;
	}

	public void setBATTERY_SEQ(String bATTERY_SEQ) {
		BATTERY_SEQ = bATTERY_SEQ;
	}

	public String getBATTERY_CAPACITY() {
		return BATTERY_CAPACITY;
	}

	public void setBATTERY_CAPACITY(String bATTERY_CAPACITY) {
		BATTERY_CAPACITY = bATTERY_CAPACITY;
	}

	public String getBATTERY_OWNER() {
		return BATTERY_OWNER;
	}

	public void setBATTERY_OWNER(String bATTERY_OWNER) {
		BATTERY_OWNER = bATTERY_OWNER;
	}

	public String getBATTERY_INST_DATE() {
		return BATTERY_INST_DATE;
	}

	public void setBATTERY_INST_DATE(String bATTERY_INST_DATE) {
		BATTERY_INST_DATE = bATTERY_INST_DATE;
	}

	
	
	
	
}
