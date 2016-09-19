package presneter;

import model.Model;

public class DisplayMazeCommand implements Command {
	
	private Model model;
	
	public DisplayMazeCommand (Model model){
		this.model = model;
	}
	
	@Override
	public void doCommand(Object param) throws Exception{
		String[] params = (String[])param;
		
		String mazeName = params[1];
		
		model.displayMaze(mazeName);
	}
}
