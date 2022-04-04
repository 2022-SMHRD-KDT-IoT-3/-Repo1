package Model;

public class MemberDTO {

	private String mb_id;
	private String mb_pw;
	private String mb_name;
	private String type;
	private String mb_portserial;
	private String mb_pwcheck;
	
	public String getMb_pwcheck() {
		return mb_pwcheck;
	}



	public void setMb_pwcheck(String mb_pwcheck) {
		this.mb_pwcheck = mb_pwcheck;
	}



	public MemberDTO(String mb_id, String mb_pw, String mb_name, String type, String mb_portserial) {
		super();
		this.mb_id = mb_id;
		this.mb_pw = mb_pw;
		this.mb_name = mb_name;
		this.type = type;
		this.mb_portserial = mb_portserial;
	}
	
	
	
	public MemberDTO(String mb_id, String mb_pw, String mb_name, String type) {
		super();
		this.mb_id = mb_id;
		this.mb_pw = mb_pw;
		this.mb_name = mb_name;
		this.type = type;
	}



	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public String getMb_pw() {
		return mb_pw;
	}
	public void setMb_pw(String mb_pw) {
		this.mb_pw = mb_pw;
	}
	public String getMb_name() {
		return mb_name;
	}
	public void setMb_name(String mb_name) {
		this.mb_name = mb_name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMb_portserial() {
		return mb_portserial;
	}
	public void setMb_portserial(String mb_portserial) {
		this.mb_portserial = mb_portserial;
	}
	

	
}
