package view;

import java.io.File;
import java.util.Observable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import general.Startable;
import gui.MazeDisplay;
import gui.MazeWindow;

public class GUI extends Observable implements Startable, View {
	
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
		mazeWindow.displayNotification(content);
	}

	@Override
	public void displayDirContent(File[] files) {
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

	@Override
	public void displayMaze(Maze3d maze) {
		mazeWindow.updateMaze(maze);
		byte[][] startSection = maze.getCrossSectionByZ(maze.getStartPosition().getHeight());
		displayCrossSection(startSection);
	}

	@Override
	public void notifyGeneratingMaze(String mazeName) {
		display("maze " + mazeName + " is being generated... \r\n");
		
	}

	@Override
	public void notifyMazeGenerated(String mazeName) {
		display("maze " + mazeName + " is ready \r\n");
		
	}

	@Override
	public void displayCrossSection(byte[][] section) {
		
		
	}

	@Override
	public void notifyMazeSolutionReady(String mazeName) {
		String content = "solution for " + mazeName + " is ready \r\n";
		
		display(content);
	}

	@Override
	public void displaySolution(Solution<Position> sol) {
		
	}

	@Override
	public void terminate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displaySavedMaze(String mazeName, String fileName) {
		display("saved maze " + mazeName + " to file " + fileName);
	}

	@Override
	public void displayLoadMaze(String mazeName, String fileName) {
		display("maze " + mazeName + " loaded from file " + fileName);
	}

}
