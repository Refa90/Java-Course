package presneter;

import view.View;

public class ViewGenerated3dMazeCommand implements Command {
	
	private View view;
	
	public ViewGenerated3dMazeCommand (View view){
		this.view = view;
	}

	@Override
	public void doCommand(Object param) throws Exception {
		String mazeName = (String)param;
		
		view.notifyMazeGenerated(mazeName);
	}

}
