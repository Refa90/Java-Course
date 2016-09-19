package view;

import java.io.File;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

public interface Display {
	void display(String content);
	
	void displayDirContent(File[] files);
	
	void displayMaze(Maze3d maze);
	
	void notifyGeneratingMaze(String mazeName);
	
	void notifyMazeGenerated(String mazeName);
	
	void displayCrossSection(byte[][] section);
	
	void notifyMazeSolutionReady(String mazeName);
	
	void displaySolution(Solution<Position> sol);
	
	void terminate();
}
