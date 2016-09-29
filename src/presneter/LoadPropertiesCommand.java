package presneter;

import java.io.File;

import gui.Notification;
import gui.NotificationQueue;
import model.Model;

public class LoadPropertiesCommand implements Command{
	
	private Model model;
	
	public LoadPropertiesCommand(Model model){
		this.model = model;
	} 

	@Override
	public void doCommand(Object param) throws Exception {
		model.loadProperties("props.xml");	
	}

}
