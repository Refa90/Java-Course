package gui;

public class Notification {
	private Boolean isError;
	private String content;
	public Boolean getIsError() {
		return isError;
	}
	public void setIsError(Boolean isError) {
		this.isError = isError;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Notification(Boolean isError, String content){
		this.isError = isError;
		this.content = content;
	}
}
