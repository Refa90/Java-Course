package presneter;

import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.SimpleMaze3dGenerator;
import model.Model;

public class Generate3dMazeCommand implements Command {
	
	private Model model;
	
	public Generate3dMazeCommand (Model model){
		this.model = model;
	}
	
	@Override
	public void doCommand(Object param){
		String[] params = (String[])param;
		
		algorithms.mazeGenerators.Maze3dGenerator generator = null;
		
		Boolean generatorType = params[2].equals("0");
		
		if(generatorType){
			generator  = new SimpleMaze3dGenerator();
		}else{
			generator  = new GrowingTreeGenerator();
		}
		
		int height = Integer.parseInt(params[3]);
		int length = Integer.parseInt(params[4]);
		int width = Integer.parseInt(params[5]);
		
		Position configuration = new Position(height, length, width);
		
		String name = params[1];
		
		model.generate3dMaze(generator, configuration, name);
		
		
	}
}
