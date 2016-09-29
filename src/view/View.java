package view;

import java.io.File;
import java.util.Map;
import java.util.Observer;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import general.Startable;
import presneter.Command;

public interface View extends Startable, Display {
	void display(String content);
	
	void start();
	
	void displayDirContent(File[] files);
	
	void displayMaze(Maze3d maze);
	
	void notifyGeneratingMaze(String mazeName);
	
	void notifyMazeGenerated(String mazeName);
	
	void displaySavedMaze(String mazeName, String fileName);
	
	void displayLoadMaze(String mazeName, String fileName);
	
	void displayCrossSection(byte[][] section);
	
	void notifyMazeSolutionReady(String mazeName);
	
	void displaySolution(Solution<Position> sol);
	
	void terminate();
	
	void addObserver(Observer o);
}
