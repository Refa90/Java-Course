package presneter;

import java.util.concurrent.Callable;

import algorithms.mazeGenerators.Position;
import algorithms.search.BFS;
import algorithms.search.CommonSearcher;
import algorithms.search.DFS;
import model.Model;

public class SolveMazeCommand implements Command {

	private Model model;
	
	public SolveMazeCommand (Model model){
		this.model = model;
	}
	
	@Override
	public void doCommand(Object param) throws Exception {
		Object[] params = (Object[])param;
		
		String mazeName = params[0].toString();
		
		CommonSearcher searcher = (CommonSearcher)params[1];
				
		model.solve(mazeName, searcher);
		
	}

}
