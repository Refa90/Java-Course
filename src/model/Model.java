package model;

import java.io.FileNotFoundException;
import java.io.IOException;

import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.CommonSearcher;
import presneter.Properties;

public interface Model {
	void getDirContent(String path) throws IOException;
	
	void generate3dMaze(Maze3dGenerator generator, Position configuration, String mazeName);
	
	void displayMaze(String mazeName);
	
	void displayCrossSection(String axis, int value, String mazeName);
	 
	 void saveMaze(String mazeName, String fileName) throws FileNotFoundException, IOException;
	 
	 void loadMaze(String mazeName, String fileName) throws FileNotFoundException, IOException;
	 
	 void solve(String mazeName, CommonSearcher<Position> searcher);
	 
	 void displaySolution(String mazeName);
	 
	 void saveProperties(Properties props, String filePath);
	 
	 void loadProperties(String filePath);
	 
	 void terminate();
}
