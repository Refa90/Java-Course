package view;

import java.io.File;
import java.util.Observer;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

public interface Display {
	void display(String content);
	
	void start();
	
	void displayDirContent(File[] files);
	
	void displayMaze(Maze3d maze);
	
	void notifyGeneratingMaze(String mazeName);
	
	void notifyMazeGenerated(String mazeName);
	
	void displayCrossSection(byte[][] section);
	
	void notifyMazeSolutionReady(String mazeName);
	
	void displaySolution(Solution<Position> sol);
	
	void terminate();
	
	void addObserver(Observer o);
}
