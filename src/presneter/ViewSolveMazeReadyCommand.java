package presneter;

import view.View;

public class ViewSolveMazeReadyCommand implements Command{

	private View view;
	
	public ViewSolveMazeReadyCommand(View view){
		this.view = view;
	}
	
	@Override
	public void doCommand(Object param) throws Exception {
		String mazeName = (String)param;
		
		view.notifyMazeSolutionReady(mazeName);
	}

}
