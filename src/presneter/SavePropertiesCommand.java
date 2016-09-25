package presneter;

import java.io.File;

import model.Model;

public class SavePropertiesCommand implements Command {

	private Model model;
	
	public SavePropertiesCommand(Model model){
		this.model = model;
	}
	
	@Override
	public void doCommand(Object param) throws Exception {
		Object[] args = (Object[])param;
		
		Properties props = new Properties();
		props.setNumOfThreads(Integer.parseInt(args[0].toString()));
		props.setGenerateMazeAlgorithmId(Integer.parseInt(args[1].toString()));
		props.setSolveMazeAlgorithmId(Integer.parseInt(args[2].toString()));
		
		
		
		model.saveProperties(props, "props.xml");
	}
	
}
