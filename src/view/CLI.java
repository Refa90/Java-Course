package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.State;
import general.NotificationParam;
import general.Startable;
import general.ThreadsManager;
import presneter.Command;

public class CLI extends Observable implements Startable, View {
	
	BufferedReader in;
	PrintWriter out;
	
	private Map<String,Command> map;
	private static Scanner scanner;
	
	public CLI(BufferedReader in, PrintWriter out){
		this.in = in;
		this.out = out;
	}
	
	@Override
	public void start(){
		run();
	}

	public void run() {
					
		Thread th = new Thread(new Runnable() {
			
			@Override
			public void run() {
				String exit = "exit";
				String input = null;
				scanner = new Scanner(in);
								
				do{
					out.write("\r\n");
					out.write("please enter command");
					out.write("\r\n");
					
					out.flush();
					
					input = scanner.nextLine();
					
					String[] values = input.split(" ");
					
					NotificationParam param = new NotificationParam(values, values[0]);
															
					notifyObservers(param);
				}
				while(!input.equals(exit));
				
			}
		});
		
		ThreadsManager.getInstance().RegisterRunnable(th);
	}
	
	public void display(String content){
		out.write(content);
		out.flush();
	}
	
	/*public void setCommands(Map<String,Command> map){
		this.map = map;
	}*/
	
	public void terminate(){
		scanner.close();
	}
	
	@Override
	public void notifyObservers(Object arg){
		super.setChanged();
		super.notifyObservers(arg);
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
		Position config = maze.getConfiguration();
		
		for(int h = 0; h < config.getHeight(); h++){
			display("\r\n");
			for(int l = 0; l < config.getLength(); l++){
				display("\r\n");
				for(int w = 0; w < config.getWidth(); w++){
					Position pos = new Position(h, l, w);
					int value = maze.getCellValue(pos);
					display(Integer.toString(value));
				}	
			}	
		}
				
		display("\r\n");
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
		
		display(content);
		
	}

	@Override
	public void notifyMazeSolutionReady(String mazeName) {
		String content = "solution for " + mazeName + " is ready \r\n";
			
		display(content);
		
	}

	@Override
	public void displaySolution(Solution<Position> sol) {
		List<State<Position>> list = sol.getStates();
		
		for(State<Position> pos : list){
			display(pos.toString() + "\r\n");
		}
		
	}
}
