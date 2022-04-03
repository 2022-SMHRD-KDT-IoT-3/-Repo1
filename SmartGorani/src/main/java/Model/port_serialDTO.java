package Model;

public class port_serialDTO {
	String port_serial;
	String date;
	public port_serialDTO(String port_serial, String date) {
		super();
		this.port_serial = port_serial;
		this.date = date;
	}
	public String getPort_serial() {
		return port_serial;
	}
	public void setPort_serial(String port_serial) {
		this.port_serial = port_serial;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	
	
}
