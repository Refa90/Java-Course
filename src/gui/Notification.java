package gui;

import java.util.Comparator;
import java.util.Date;

public class Notification implements Comparable<Notification> {
	private Boolean isError;
	private String content;
	private Date createDate;
	
	public Boolean getIsError() {
		return isError;
	}
	public void setIsError(Boolean isError) {
		this.isError = isError;
	}
	public String getContent() {
		return content;
	}
	
	public Date getDate(){
		return createDate;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Notification(Boolean isError, String content){
		this.isError = isError;
		this.content = content;
		this.createDate = new Date();
	}
	
	@Override
	public int compareTo(Notification o) {
		return this.getDate().compareTo(o.getDate());
	}
}
