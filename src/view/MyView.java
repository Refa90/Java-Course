package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.State;
import presneter.Command;

/**
 * This class implements the view layer of the MVC architecture 
 *  
 * @author Refael Auerbach
 * @version 1.0
 * @since 2016-08-25
 * */

public class MyView extends Observable implements View, Observer {
	
	/**
	 * private member that holds pointer to the cli object, which will get the user input and process it
	 * */
	/*private CLI cli;
	private GUI gui;*/
	
	private Display display;
	
	/**
	 * pointer to the controller object which initializes the base logic of the program 
	 * */
	//private Controller controller;
	
	/**
	 * Ctor - initializes the cli member the proper input and output objects
	 * @param in - the input object
	 * @param out - the output object
	 * */
	public MyView(BufferedReader in, PrintWriter out){
		//cli = new CLI(in ,out);
		//cli.addObserver(this);
		
		display = new GUI();
		gui.addObserver(this);
	}
		
	/**
	 * set the stores the command map object
	 * @param map - the command map
	 * */

	@Override
	public void setCommandsMap(Map<String, Command> map){
		cli.setCommands(map);
	}
		
	/**
	 * set the controller
	 * @param controller - the controller
	 * */
	/*@Override
	public void setController(Controller controller){
		this.controller = controller;
	}*/
		
	
	/**
	 * start the initialization of the view by first init the controller and the the cli
	 * */
	@Override
	public void start(){
		//this.controller.init();
				
		//cli.start();
		
		gui.start();

	}
	
	/**
	 * display the content in one place through the cli
	 * @param content - the content to be displayed  
	 * */
	@Override
	public void display(String content){
		//cli.display(content);
		
		display.display(content);
	}
	
	/**
	 * display the content of the given dir - could be directories or files
	 * @param files- the files found in that dir  
	 * */
	@Override
	public void displayDirContent(File[] files){
		String content = "";
		StringBuilder sb = new StringBuilder();		
		
		for(File obj : files){
			sb.append(obj.getName() + " - ");
			if(obj.isDirectory()){
				sb.append("directroy");
			}else if(obj.isFile()){
				sb.append("file");
			}
			sb.append(" \r\n");
		}
		
		content = sb.toString();
		
		display(content);
	}
	
	/**
	 * display the maze
	 * @param maze - the maze to be displayed
	 * */
	@Override 
	public void displayMaze(Maze3d maze){
		Position config = maze.getConfiguration();
		
		for(int h = 0; h < config.getHeight(); h++){
			//cli.display("\r\n");
			display.display("\r\n");
			for(int l = 0; l < config.getLength(); l++){
				//cli.display("\r\n");
				display.display("\r\n");
				for(int w = 0; w < config.getWidth(); w++){
					Position pos = new Position(h, l, w);
					int value = maze.getCellValue(pos);
					//cli.display(Integer.toString(value));
					display.display(Integer.toString(value));
				}	
			}	
		}
		
		//cli.display("\r\n");
		display.display("\r\n");
	}
	
	/**
	 * displaying a message of generating a maze
	 * @param mazeName - the name of the maze being generated
	 * */
	@Override
	public void notifyGeneratingMaze(String mazeName) {
		//cli.display("maze " + mazeName + " is being generated... \r\n");
		display.display("maze " + mazeName + " is being generated... \r\n");
		
	}

	/**
	 * displaying a message of the maze generated 
	 * @param mazeName - the name of the generated maze
	 * */
	@Override
	public void notifyMazeGenerated(String mazeName) {
		//cli.display("maze " + mazeName + " is ready \r\n");
		display.display("maze " + mazeName + " is ready \r\n");
		
	}
	
	/**
	 * displaying specific section of the maze by the config chosen
	 * @param mazeName - the name of the generated maze
	 * */
	@Override
	public void displayCrossSection(byte[][] section){
		String content = "";
		StringBuilder stringBuilder = new StringBuilder();
		
		for(int r = 0; r < section.length; r++){
			stringBuilder.append("\r\n");
			for(int c = 0; c < section[r].length; c++){
				stringBuilder.append(section[r][c]);
				stringBuilder.append(" ");
			}
		}
		
		content = stringBuilder.toString();
		
		//cli.display(content);
		display.display(content);
		
	}
	
	@Override
	public void notifyMazeSolutionReady(String mazeName){
		String content = "solution for " + mazeName + " is ready \r\n";
		
		//cli.display(content);
		display.display(content);
	}
	
	@Override
	public void displaySolution(Solution<Position> sol){
		
		List<State<Position>> list = sol.getStates();
		
		for(State<Position> pos : list){
			//cli.display(pos.toString() + "\r\n");
			display.display(pos.toString() + "\r\n");
		}
	}
	
	@Override
	public void terminate(){
		//cli.terminate();
		display.terminate();
	}
	
	/**
	 * view to presenter
	 * */
	@Override
	public void notifyObservers(Object arg){
		super.setChanged();
		super.notifyObservers(arg);
	}


	@Override
	public void update(Observable o, Object arg) {
		notifyObservers(arg);
	}
}