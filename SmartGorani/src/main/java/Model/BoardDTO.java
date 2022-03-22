package Model;

public class BoardDTO {
	private int qna_seq;
	private String qna_title;
	private String content;
	private String file;
	private String date;
	private String mb_id;
	public BoardDTO(int qna_seq, String qna_title, String content, String file, String date, String mb_id) {
		this.qna_seq = qna_seq;
		this.qna_title = qna_title;
		this.content = content;
		this.file = file;
		this.date = date;
		this.mb_id = mb_id;
	}
	public int getQna_seq() {
		return qna_seq;
	}
	public void setQna_seq(int qna_seq) {
		this.qna_seq = qna_seq;
	}
	public String getQna_title() {
		return qna_title;
	}
	public void setQna_title(String qna_title) {
		this.qna_title = qna_title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	
	
}