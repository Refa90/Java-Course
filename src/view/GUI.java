package view;

import java.io.File;
import java.util.Observable;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import general.Startable;
import gui.MazeWindow;

public class GUI extends Observable implements Startable, Display {
	
	private MazeWindow mazeWindow;

	@Override
	public void start() {
		mazeWindow = new MazeWindow(this);
		
		mazeWindow.start();
	}
	
	@Override
	public void notifyObservers(Object arg){
		super.setChanged();
		super.notifyObservers(arg);
	}

	@Override
	public void display(String content) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayDirContent(File[] files) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayMaze(Maze3d maze) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyGeneratingMaze(String mazeName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyMazeGenerated(String mazeName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayCrossSection(byte[][] section) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyMazeSolutionReady(String mazeName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displaySolution(Solution<Position> sol) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void terminate() {
		// TODO Auto-generated method stub
		
	}

}
