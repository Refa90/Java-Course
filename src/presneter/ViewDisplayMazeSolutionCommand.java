package presneter;

import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import view.View;

public class ViewDisplayMazeSolutionCommand implements Command {

	private View view;
	
	public ViewDisplayMazeSolutionCommand(View view){
		this.view = view;
	}
	
	@Override
	public void doCommand(Object param) throws Exception {
		Solution<Position> sol = (Solution)param;
		
		view.displaySolution(sol);
	}

}
