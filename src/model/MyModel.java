package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import algorithms.demo.Maze3dSearchableAdapter;
import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.CommonSearcher;
import algorithms.search.Solution;
import general.NotificationParam;
import general.ThreadsManager;
import gui.Notification;
import gui.NotificationQueue;
import io.MyCompressorOutputStream;
import io.MyDecompressionInputStream;
import presneter.Properties;
import presneter.PropertiesManager;

public class MyModel extends Observable implements Model {

	private Map<String, Maze3d> mazesMap;
	private Map<Maze3d, Solution<Position>> mazeSols;
	private CommonSearcher<Position> searcher;
	private String ioPath;

	public MyModel() {
		this.mazesMap = new HashMap<>();
		this.mazeSols = new HashMap<>();

		String mazesFolderName = "mazes";
		ioPath = System.getProperty("user.dir") + "\\" + mazesFolderName;

		File file = new File(ioPath);

		if (!file.exists()) {
			file.mkdir();
		}
	}

	@Override
	public void getDirContent(String path) throws IOException {
		File[] files = null;

		File folder = new File(path);

		files = folder.listFiles();

		NotificationParam param = new NotificationParam(files, "viewDir");

		notifyObservers(param);
	}

	@Override
	public void generate3dMaze(Maze3dGenerator generator, Position configuration, String mazeName) {
		NotificationParam param = new NotificationParam(mazeName, "viewGenerating3dMaze");

		notifyObservers(param);

		Callable<Maze3d> callable = new Callable<Maze3d>() {

			@Override
			public Maze3d call() throws Exception {
				Maze3d maze = generator.generate(configuration);

				mazesMap.put(mazeName, maze);

				NotificationParam param = new NotificationParam(mazeName, "viewGenerated3dMaze");

				notifyObservers(param);
				
				param = new NotificationParam(maze, "viewDisplayMaze");

				notifyObservers(param);

				return maze;
			}
		};

		Future<Maze3d> future = ThreadsManager.getInstance().RegisterCallable(callable);
	}

	@Override
	public void displayMaze(String mazeName) {
		Maze3d maze = mazesMap.get(mazeName);

		NotificationParam param = new NotificationParam(mazeName, "viewDisplayMaze");

		notifyObservers(param);
	}

	@Override
	public void displayCrossSection(String axis, int value, String mazeName) {
		Maze3d maze = mazesMap.get(mazeName);

		byte[][] mazeInBytes = null;

		switch (axis) {
		case "z":
			mazeInBytes = maze.getCrossSectionByZ(value);
			break;
		case "y":
			mazeInBytes = maze.getCrossSectionByY(value);
			break;
		case "x":
			mazeInBytes = maze.getCrossSectionByX(value);
			break;
		}

		NotificationParam param = new NotificationParam(mazeInBytes, "viewDisplayCrossSection");

		notifyObservers(param);
	}

	@Override
	public void saveMaze(String mazeName, String fileName) throws FileNotFoundException, IOException {
		try {
			Maze3d maze = mazesMap.get(mazeName);

			// TODO - remove!
			if (maze == null) {
				Maze3dGenerator generator = new GrowingTreeGenerator();
				maze = generator.generate(new Position(3, 3, 3));
			}

			byte[] bytes = maze.toByteArray();

			File file = new File(this.ioPath + "\\" + fileName);

			MyCompressorOutputStream compressor = new MyCompressorOutputStream(new FileOutputStream(file));

			compressor.write(bytes);

			NotificationParam notParam = new NotificationParam(new String[] { mazeName, fileName }, "viewSaveMaze");

			notifyObservers(notParam);
		} catch (Exception ex) {
			NotificationParam notParam = new NotificationParam(
					new Notification(true, "failed save maze " + mazeName + " from file " + fileName), "error");

			notifyObservers(notParam);
		}
	}

	@Override
	public void loadMaze(String mazeName, String fileName) throws FileNotFoundException, IOException {

		// TODO fix it!

		try {
			Maze3d maze = null;

			/*
			 * byte[] bytes = null;
			 * 
			 * MyDecompressionInputStream decompressor = new
			 * MyDecompressionInputStream(new FileInputStream(new
			 * File(fileName)));
			 * 
			 * bytes = String.valueOf(decompressor.read()).getBytes();
			 * 
			 * maze = new Maze3d(bytes);
			 */

			// TODO - remove!
			if (maze == null) {
				Maze3dGenerator generator = new GrowingTreeGenerator();
				maze = generator.generate(new Position(3, 3, 3));
			}

			mazesMap.put(mazeName, maze);

			NotificationParam notParam = new NotificationParam(new String[] { mazeName, fileName }, "viewLoadMaze");

			notifyObservers(notParam);
		} catch (Exception ex) {
			NotificationParam notParam = new NotificationParam(
					new Notification(true, "failed load maze " + mazeName + " from file " + fileName), "error");

			notifyObservers(notParam);
		}
	}

	@Override
	public void solve(String mazeName, CommonSearcher searcher) {
		this.searcher = searcher;

		Maze3d maze = mazesMap.get(mazeName);

		Solution<Position> sol = mazeSols.get(maze);

		if (sol == null) {
			Callable<Solution> callable = new Callable<Solution>() {

				@Override
				public Solution call() {
					Maze3d maze = mazesMap.get(mazeName);

					Maze3dSearchableAdapter adapter = new Maze3dSearchableAdapter(maze);

					Solution sol = searcher.search(adapter);

					mazeSols.put(maze, sol);
					
					NotificationParam param = new NotificationParam(mazeName, "viewSolveMazeReady");

					notifyObservers(param);

					return sol;
				}
			};

			Future<Solution> future = ThreadsManager.getInstance().RegisterCallable(callable);
		} else {
			NotificationParam param = new NotificationParam(mazeName, "viewSolveMazeReady");

			notifyObservers(param);
		}
	}

	// TODO - fix !
	@Override
	public void displaySolution(String mazeName) {
		Maze3d maze = mazesMap.get(mazeName);

		Solution<Position> sol = mazeSols.get(maze);

		if (sol == null) {
			Maze3dSearchableAdapter adapter = new Maze3dSearchableAdapter(maze);

			sol = this.searcher.search(adapter);
		}

		NotificationParam param = new NotificationParam(sol, "viewDisplayMazeSolution");

		notifyObservers(param);
	}
	
	@Override
	public void saveProperties(Properties props, String filePath) {
		String fullPath = System.getProperty("user.dir") + "\\" + "properties";
		
		File folderFile = new File(filePath);

		if (!folderFile.exists()) {
			folderFile.mkdir();
		}
		
		fullPath = fullPath + "\\" + filePath;
		
		File fullFile = new File(fullPath);
		
		if(fullFile.exists()){
			fullFile.delete();
		}
					
		PropertiesManager.getInstance().saveProperties(fullPath, props);
	}
	
	@Override
	public void loadProperties(String filePath) {
		String folderPath = System.getProperty("user.dir") + "\\" + "properties";
		
		File folderFile = new File(folderPath);

		if (folderFile.exists()) {
			String fullPath = folderPath + "\\props.xml";
			
			PropertiesManager.getInstance().loadProperties(fullPath );		
		}else{
			Notification note = new Notification(true, "No properties files exist, please create one first");
			
			NotificationQueue.getInstance().add(note);
		}
	}

	@Override
	public void terminate() {
		ThreadsManager.getInstance().terminate();

		// controller.terminate();
	}

	/**
	 * model to presenter
	 */
	@Override
	public void notifyObservers(Object arg) {
		super.setChanged();
		super.notifyObservers(arg);
	}
	
	
}
