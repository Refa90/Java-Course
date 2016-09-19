package presneter;

import model.Model;

public class LoadMazeCommand implements Command {
	
	private Model model;
	
	public LoadMazeCommand (Model model){
		this.model = model;
	}
	
	@Override
	public void doCommand(Object param) throws Exception{
		String[] params = (String[])param;
		String mazeName = params[1];
		String fileName = params[2];
		
		model.loadMaze(mazeName, fileName);
	}
}
