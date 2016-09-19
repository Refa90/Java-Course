package presneter;

import java.io.File;

import view.View;

public class ViewGenerating3dMazeCommand implements Command {

private View view;
	
	public ViewGenerating3dMazeCommand(View view){
		this.view = view;
	}
	
	@Override
	public void doCommand(Object param) throws Exception {
		String mazeName = (String)param;
		
		view.notifyGeneratingMaze(mazeName);
	}

}
