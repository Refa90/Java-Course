package presneter;

import algorithms.mazeGenerators.Maze3d;
import view.View;

public class ViewDisplayMazeCommand implements Command {

	private View view;
	
	public ViewDisplayMazeCommand (View view){
		this.view = view;
	}
	
	@Override
	public void doCommand(Object param) throws Exception {
		Maze3d maze = (Maze3d)param;
		
		view.displayMaze(maze);
	}

}
