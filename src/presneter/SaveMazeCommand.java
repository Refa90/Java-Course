package presneter;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.Model;

public class SaveMazeCommand implements Command{

	private Model model;
	
	public SaveMazeCommand (Model model){
		this.model = model;
	}
	
	@Override
	public void doCommand(Object param) throws Exception {
		String[] params = (String[])param;
		
		String mazeName = params[1];
		String fileName = params[2];
				
		model.saveMaze(mazeName, fileName);
		
	}

}
