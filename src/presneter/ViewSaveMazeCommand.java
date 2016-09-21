package presneter;

import view.View;

public class ViewSaveMazeCommand implements Command {

	private View view;
	
	public ViewSaveMazeCommand (View view){
		this.view = view;
	}
	
	@Override
	public void doCommand(Object param) throws Exception {
		String[] params = (String[])param;
		
		String mazeName = params[0];
		String fileName = params[1];
		
		view.displaySavedMaze(mazeName, fileName);
	}

}
