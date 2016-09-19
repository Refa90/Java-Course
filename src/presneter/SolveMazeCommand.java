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
		String[] params = (String[])param;
		
		String mazeName = params[1];
		int algorithmId = Integer.parseInt(params[2]);
		CommonSearcher searcher = null;
				
		switch(algorithmId){
		case 0:
			searcher = new BFS<Position>();
			break;
		case 1:
			searcher = new DFS<Position>();
			break;
		default:
			throw new Exception("bad algorithm input");
		}
		
		model.solve(mazeName, searcher);
		
	}

}
