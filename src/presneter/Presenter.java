package presneter;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import general.MyObserver;
import general.NotificationParam;
import gui.Notification;
import gui.NotificationQueue;
import model.Model;
import view.View;

public class Presenter implements Observer {

	private Model model;
	private View view;
	private Map<String,Command> modelMap;
	private Map<String,Command> viewMap;
	
	public Presenter (View view, Model model){
		this.view = view;
		this.model = model;
		init();
	}
	
	private void init(){
		initViewMap();
		initModelMap();
	}
	
	private void initViewMap(){
		Map<String,Command> map = new HashMap();
		
		map.put("viewDir", new ViewDirCommand(this.view));
		
		map.put("viewGenerating3dMaze", new ViewGenerating3dMazeCommand(this.view));
		
		map.put("viewGenerated3dMaze", new ViewGenerated3dMazeCommand(this.view));
		
		map.put("viewDisplayMaze", new ViewDisplayMazeCommand(this.view));
		
		map.put("viewDisplayCrossSection", new ViewDisplayCrossSectionCommand(this.view));
		
		map.put("viewSolveMazeReady", new ViewSolveMazeReadyCommand(this.view));
			
		map.put("viewDisplayMazeSolution", new ViewDisplayMazeSolutionCommand(this.view));
		
		this.viewMap = map;
	}
	
	private void initModelMap(){
		
		Map<String,Command> map = new HashMap();
		
		//dir c:\program files
		map.put("dir", new DirCommand(this.model));
		
		//generate_3d_maze maze1 1 3 3 3
		map.put("generate_3d_maze", new Generate3dMazeCommand(this.model));
		
		//display maze1
		map.put("display", new DisplayMazeCommand(this.model));
		
		//display_cross_section x 1 maze1 
		map.put("display_cross_section", new DisplayCrossSectionCommand(this.model));
		
		//save_maze maze1 maze1.maz
		map.put("save_maze", new SaveMazeCommand(this.model));
		
		//load_maze maze1 maze1.maz
		map.put("load_maze", new LoadMazeCommand(this.model));
		
		//solve maze1 1 
		map.put("solve", new SolveMazeCommand(this.model));
		
		//display_solution maze1
		map.put("display_solution", new DisplaySolutionCommand(this.model));
		
		//exit
		map.put("exit", new ExitCommand(this.model));
		
		this.modelMap = map;
	}
	
	@Override
	public void update(Observable o, Object arg){
		
		NotificationParam notParam = (NotificationParam)arg;
		
		String commandName = notParam.getCommandName();
		
		if(commandName.equals("error")){
			Notification notification = (Notification)notParam.getArg();
			
			NotificationQueue.GetInstance().add(notification);
		}else{
			Command command = null;
			
			if(o instanceof View){
				command = this.modelMap.get(commandName);
			}else if(o instanceof Model){
				command = this.viewMap.get(commandName);
			}
			
			if(command == null){
				
			}else{
				try {
					command.doCommand(notParam.getArg());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
