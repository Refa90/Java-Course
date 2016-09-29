package presneter;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import gui.Notification;
import gui.NotificationQueue;

public class PropertiesManager {
	private Properties props;
	private static PropertiesManager instance;
	private static Object locker = new Object();
	
	public Properties getProps() {
		return props;
	}

	private PropertiesManager() {

	}

	public static PropertiesManager getInstance() {
		if (instance == null) {
			synchronized (locker) {
				if (instance == null) {
					instance = new PropertiesManager();
				}
			}
		}

		return instance;
	}

	public void saveProperties(String filePath, Properties props) {
		XMLEncoder encoder = null;
		
		try {
			String path = filePath;
			
			encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(path)));
			
			if(encoder != null){
				
				props.setGenerateMazeAlgorithmId(props.getGenerateMazeAlgorithmId() + 1);
				props.setSolveMazeAlgorithmId(props.getSolveMazeAlgorithmId() + 1);
			
				try{
					encoder.writeObject(props);
					encoder.close();	
					
					this.props = props;
				}catch(Exception ex){
					Notification note = new Notification(true, "failed save file");
					
					NotificationQueue.getInstance().add(note);
				}
				
				props.setGenerateMazeAlgorithmId(props.getGenerateMazeAlgorithmId() - 1);
				props.setSolveMazeAlgorithmId(props.getSolveMazeAlgorithmId() - 1);
			}
		} catch (FileNotFoundException fileNotFound) {
			Notification note = new Notification(true, "failed save file");
			
			NotificationQueue.getInstance().add(note);
		}	
	}

	public void loadProperties(String filePath) {
		XMLDecoder decoder = null;
	
		InputStream stream = null;
		try {
			stream = new BufferedInputStream(new FileInputStream(filePath));
			
			decoder = new XMLDecoder(stream);
			
			Object result = decoder.readObject();
			
			decoder.close();
			
			this.props = (Properties) result;
			
			props.setGenerateMazeAlgorithmId(props.getGenerateMazeAlgorithmId() - 1);
			props.setSolveMazeAlgorithmId(props.getSolveMazeAlgorithmId() - 1);

		} catch (FileNotFoundException e) {
			Notification note = new Notification(true, "failed load file");
			
			NotificationQueue.getInstance().add(note);
		}		
	}
}
