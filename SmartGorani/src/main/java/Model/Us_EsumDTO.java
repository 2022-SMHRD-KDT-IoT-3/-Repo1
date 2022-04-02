package Model;

public class Us_EsumDTO {
	
	private String mb_portserial;
	private double electric_useage;
	
	public Us_EsumDTO(String mb_portserial, double electric_useage) {
		super();
		this.mb_portserial = mb_portserial;
		this.electric_useage = electric_useage;
	}

	public String getMb_portserial() {
		return mb_portserial;
	}

	public void setMb_portserial(String mb_portserial) {
		this.mb_portserial = mb_portserial;
	}

	public double getElectric_useage() {
		return electric_useage;
	}

	public void setElectric_useage(double electric_useage) {
		this.electric_useage = electric_useage;
	}
	
	
}
