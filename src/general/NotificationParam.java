package general;

import java.util.Date;

public class NotificationParam {
	private Object arg;
	private String commandName;
	private Date date;
	public Object getArg() {
		return arg;
	}
	public void setArg(Object arg) {
		this.arg = arg;
	}
	public String getCommandName() {
		return commandName;
	}
	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}
	
	public NotificationParam(Object arg, String commandName){
		this.arg = arg;
		this.commandName = commandName;
		this.date = new Date();
	}	
}
