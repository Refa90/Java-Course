package presneter;

import view.View;

public class ViewDisplayCrossSectionCommand implements Command{

	private View view;
	
	public ViewDisplayCrossSectionCommand (View view){
		this.view = view;
		
	}
	
	@Override
	public void doCommand(Object param) throws Exception {
		byte[][] maze = (byte[][])param;
		
		view.displayCrossSection(maze);
		
	}

}
