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
	private View display;
	
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
		
		display = new GUI();
		display.addObserver(this);
	}
		
	/**
	 * set the stores the command map object
	 * @param map - the command map
	 * */

	/*@Override
	public void setCommandsMap(Map<String, Command> map){
		//cli.setCommands(map);
	}*/
		
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
		
		display.start();

	}
	
	/**
	 * display the content in one place through the cli
	 * @param content - the content to be displayed  
	 * */
	@Override
	public void display(String content){
		display.display(content);
	}
	
	/**
	 * display the content of the given dir - could be directories or files
	 * @param files- the files found in that dir  
	 * */
	@Override
	public void displayDirContent(File[] files){
		display.displayDirContent(files);
	}
	
	/**
	 * display the maze
	 * @param maze - the maze to be displayed
	 * */
	@Override 
	public void displayMaze(Maze3d maze){
		display.displayMaze(maze);
	}
	
	/**
	 * displaying a message of generating a maze
	 * @param mazeName - the name of the maze being generated
	 * */
	@Override
	public void notifyGeneratingMaze(String mazeName) {
		display.notifyGeneratingMaze(mazeName);
		
	}

	/**
	 * displaying a message of the maze generated 
	 * @param mazeName - the name of the generated maze
	 * */
	@Override
	public void notifyMazeGenerated(String mazeName) {
		display.notifyMazeGenerated(mazeName);
		
	}
	
	/**
	 * displaying specific section of the maze by the config chosen
	 * @param mazeName - the name of the generated maze
	 * */
	@Override
	public void displayCrossSection(byte[][] section){
		display.displayCrossSection(section);
	}
	
	@Override
	public void notifyMazeSolutionReady(String mazeName){
		display.notifyMazeSolutionReady(mazeName);
	}
	
	@Override
	public void displaySolution(Solution<Position> sol){
		display.displaySolution(sol);
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

	@Override
	public void displaySavedMaze(String mazeName, String fileName) {
		display.displaySavedMaze(mazeName, fileName);
	}

	@Override
	public void displayLoadMaze(String mazeName, String fileName) {
		display("maze " + mazeName + " loaded from file " + fileName);
		
	}
}
