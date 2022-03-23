package Model;

public class ReplyDTO {

	int REPLY_SEQ;
	int QNA_SEQ;
	String REPLY_CONTENT;
	String REPLY_FILE;
	String REPLY_DATE;
	String MB_ID;
	public ReplyDTO(int rEPLY_SEQ, int qNA_SEQ, String rEPLY_CONTENT, String rEPLY_FILE, String rEPLY_DATE,
			String mB_ID) {
		super();
		REPLY_SEQ = rEPLY_SEQ;
		QNA_SEQ = qNA_SEQ;
		REPLY_CONTENT = rEPLY_CONTENT;
		REPLY_FILE = rEPLY_FILE;
		REPLY_DATE = rEPLY_DATE;
		MB_ID = mB_ID;
	}
	public int getREPLY_SEQ() {
		return REPLY_SEQ;
	}
	public void setREPLY_SEQ(int rEPLY_SEQ) {
		REPLY_SEQ = rEPLY_SEQ;
	}
	public int getQNA_SEQ() {
		return QNA_SEQ;
	}
	public void setQNA_SEQ(int qNA_SEQ) {
		QNA_SEQ = qNA_SEQ;
	}
	public String getREPLY_CONTENT() {
		return REPLY_CONTENT;
	}
	public void setREPLY_CONTENT(String rEPLY_CONTENT) {
		REPLY_CONTENT = rEPLY_CONTENT;
	}
	public String getREPLY_FILE() {
		return REPLY_FILE;
	}
	public void setREPLY_FILE(String rEPLY_FILE) {
		REPLY_FILE = rEPLY_FILE;
	}
	public String getREPLY_DATE() {
		return REPLY_DATE;
	}
	public void setREPLY_DATE(String rEPLY_DATE) {
		REPLY_DATE = rEPLY_DATE;
	}
	public String getMB_ID() {
		return MB_ID;
	}
	public void setMB_ID(String mB_ID) {
		MB_ID = mB_ID;
	}

	
	
	
}
