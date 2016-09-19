package view;

import java.io.File;
import java.util.Map;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import general.Startable;
import presneter.Command;

public interface View extends Startable, Display {
	//void setCommandsMap(Map<String, Command> map);
	
	/*void display(String content);
	
	void displayDirContent(File[] files);
	
	void displayMaze(Maze3d maze);
	
	void notifyGeneratingMaze(String mazeName);
	
	void notifyMazeGenerated(String mazeName);
	
	void displayCrossSection(byte[][] section);
	
	void notifyMazeSolutionReady(String mazeName);
	
	void displaySolution(Solution<Position> sol);
	
	void terminate();*/
}
