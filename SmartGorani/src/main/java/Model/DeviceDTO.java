package Model;

public class DeviceDTO {
	private String mb_id;
	private String mb_portserial;
	private String dv_name;
	private Double dv_usage;
	private String dv_date;
	
	
	
	public DeviceDTO(String mb_id, String mb_portserial, String dv_name, Double dv_usage, String dv_date) {
		super();
		this.mb_id = mb_id;
		this.mb_portserial = mb_portserial;
		this.dv_name = dv_name;
		this.dv_usage = dv_usage;
		this.dv_date = dv_date;
	}
	
	
	
	
	
	public DeviceDTO(String mb_id, String mb_portserial, String dv_name) {
		super();
		this.mb_id = mb_id;
		this.mb_portserial = mb_portserial;
		this.dv_name = dv_name;
	}

	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}



	public String getMb_portserial() {
		return mb_portserial;
	}
	public void setMb_portserial(String mb_portserial) {
		this.mb_portserial = mb_portserial;
	}
	public String getDv_name() {
		return dv_name;
	}
	public void setDv_name(String dv_name) {
		this.dv_name = dv_name;
	}
	public Double getDv_usage() {
		return dv_usage;
	}
	public void setDv_usage(Double dv_usage) {
		this.dv_usage = dv_usage;
	}
	public String getDv_date() {
		return dv_date;
	}
	public void setDv_date(String dv_date) {
		this.dv_date = dv_date;
	}
	
	
	
	
}
