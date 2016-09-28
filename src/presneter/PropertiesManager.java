package presneter;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

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
			encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("src\\" + filePath)));
			
			//encoder = new XMLEncoder(getClass().getClassLoader().getResourceAsStream(filePath)));

		} catch (FileNotFoundException fileNotFound) {
			System.out.println("ERROR: While Creating or Opening the File: " + filePath);
		}
		encoder.writeObject(props);
		encoder.close();

		this.props = props;
	}

	public void loadProperties(String filePath) {
		XMLDecoder decoder = null;

		// decoder=new XMLDecoder(new BufferedInputStream(new
		// FileInputStream(filePath)));

		decoder = new XMLDecoder(getClass().getClassLoader().getResourceAsStream("src/" + filePath));

		//Notification note = new Notification(true, "ERROR: File: " + filePath + " not found");

		//NotificationQueue.getInstance().add(note);

		this.props = (Properties) decoder.readObject();
	}

}
