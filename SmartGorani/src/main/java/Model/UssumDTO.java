package Model;

public class UssumDTO {
	private String mb_portserial;
	private int battery_capacity;
	private double battery_usage;
	
	public UssumDTO(String mb_portserial, int battery_capacity, double battery_usage) {
		super();
		this.mb_portserial = mb_portserial;
		this.battery_capacity = battery_capacity;
		this.battery_usage = battery_usage;
	}
	

	public String getMb_portserial() {
		return mb_portserial;
	}

	public void setMb_portserial(String mb_portserial) {
		this.mb_portserial = mb_portserial;
	}


	public int getBattery_capacity() {
		return battery_capacity;
	}

	public void setBattery_capacity(int battery_capacity) {
		this.battery_capacity = battery_capacity;
	}

	public double getBattery_usage() {
		return battery_usage;
	}

	public void setBattery_usage(double battery_usage) {
		this.battery_usage = battery_usage;
	}
	
	
}
