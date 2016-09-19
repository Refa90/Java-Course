package presneter;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;

public class Properties implements Serializable{
	public void XMLSerilaize(String filePath){
		XMLEncoder encoder=null;
		try{
		encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filePath)));
		
		}catch(FileNotFoundException fileNotFound){
			System.out.println("ERROR: While Creating or Opening the File: " + filePath);
		}
		encoder.writeObject(this);
		encoder.close();
	}
	
	public Properties(Properties prop){
		
	}
	
	private void XMLDeserialize(String filePath){
		XMLDecoder decoder=null;
		
		try {
			decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(filePath)));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File: " + filePath + " not found");
		}
		Properties prop =(Properties)decoder.readObject();
		
	}
}
