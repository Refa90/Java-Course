package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.Callable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import general.NotificationParam;
import general.ThreadsManager;

public class MazeWindow extends BaseWindow {

	private MazeDisplay mazeDisplay;
	private Observable observable;
	private String mazeName;
	
	
	public MazeWindow(Observable observable){
		this.observable = observable;
	}
	
	Button btnSolveMaze = null;
	Button btnSaveMaze = null;
	Button btnLoadMaze = null;
	Button btnDisplayMazeSolution = null;
	private static Label lblNextStep = null;
	
	
	@Override
	protected void initWidgets() {
		
		/*Composite solution = new Composite(shell, SWT.NONE);
		RowLayout rowLayoutHorizontal = new RowLayout(SWT.HORIZONTAL);
		solution.setLayout(rowLayoutHorizontal);
		solution.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_CENTER));
		
		Button x = new Button(solution, SWT.PUSH);
		x.setText("solution");*/
		
		GridLayout grid = new GridLayout(2, false);
		shell.setLayout(grid);
		
		Composite buttons = new Composite(shell, SWT.NONE);
		RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
		buttons.setLayout(rowLayout);
		
		Button btnGenerateMaze = new Button(buttons, SWT.PUSH);
		btnGenerateMaze.setText("Generate maze");
		
		btnGenerateMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				System.out.println("generate maze");
				List<Button> buttons = new ArrayList<>();
				buttons.add(btnSaveMaze);
				buttons.add(btnLoadMaze);
				buttons.add(btnSolveMaze);
				buttons.add(btnDisplayMazeSolution);
				
				GenerateMazeWindow win = new GenerateMazeWindow(observable, buttons);				
				win.start(display);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				int x = 5;
				
				x = x -1;
			}
		});
		
		btnSolveMaze = new Button(buttons, SWT.PUSH);
		btnSolveMaze.setText("Solve maze");
		btnSolveMaze.setVisible(false);
		
		btnSolveMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("solve maze");
				SolveMazeWindow window = new SolveMazeWindow(observable, mazeName);
				window.start(display);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
		
		btnDisplayMazeSolution = new Button(buttons, SWT.PUSH);
		btnDisplayMazeSolution.setText("Display Maze solution guidance");
		btnDisplayMazeSolution.setVisible(false);
		
		btnDisplayMazeSolution.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("display maze solution");
				
				String mazeName = "maze";
				
				NotificationParam noteParam = new NotificationParam(mazeName, "display_solution" );
				
				observable.notifyObservers(noteParam);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
		
		
		btnSaveMaze = new Button(buttons, SWT.PUSH);
		btnSaveMaze.setText("save maze");
		btnSaveMaze.setVisible(false);
		
		btnSaveMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("save maze");
				
				SaveMazeWindow window = new SaveMazeWindow(observable);
				
				window.start(display);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
		
		btnLoadMaze = new Button(buttons, SWT.PUSH);
		btnLoadMaze.setText("load maze");
		btnLoadMaze.setVisible(false);
		
		btnLoadMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("load maze");
				
				LoadMazeWindow window = new LoadMazeWindow(observable);
				
				window.start(display);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
		
		Label lblSolution = new Label(buttons, SWT.PUSH);
		lblSolution.setText("Solution next step:");
		lblSolution.setBounds(shell.getClientArea());
	    
	    lblNextStep = new Label(buttons, SWT.PUSH);
	    lblNextStep.setText("Go left!");
	    lblNextStep.setBounds(shell.getClientArea());
	    //lblNextStep.setLayoutData((new GridData(SWT.FILL, SWT.CENTER, true, false)));
		
		mazeDisplay = new MazeDisplay(shell, SWT.BORDER);	
		mazeDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		mazeDisplay.setFocus();
		
		createMenu();
	}
	
	private void createMenu(){
		Menu menuBar, fileMenu, helpMenu;

		MenuItem fileMenuHeader, helpMenuHeader;

		MenuItem fileExitItem, fileSaveItem, helpGetHelpItem, fileOpenPropertiesItem;

		Label label;
		
		label = new Label(shell, SWT.CENTER);
	    label.setBounds(shell.getClientArea());

		
		menuBar = new Menu(shell, SWT.BAR);
	    fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
	    fileMenuHeader.setText("&File");

	    fileMenu = new Menu(shell, SWT.DROP_DOWN);
	    fileMenuHeader.setMenu(fileMenu);

	    fileSaveItem = new MenuItem(fileMenu, SWT.PUSH);
	    fileSaveItem.setText("&Save");
	    
	    fileOpenPropertiesItem = new MenuItem(fileMenu, SWT.PUSH);
	    fileOpenPropertiesItem.setText("&Open properties");
	    fileOpenPropertiesItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("open properties");
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

	    fileExitItem = new MenuItem(fileMenu, SWT.PUSH);
	    fileExitItem.setText("E&xit");
	    
	    fileExitItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("exit program");
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

	    helpMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
	    helpMenuHeader.setText("&Help");

	    helpMenu = new Menu(shell, SWT.DROP_DOWN);
	    helpMenuHeader.setMenu(helpMenu);

	    helpGetHelpItem = new MenuItem(helpMenu, SWT.PUSH);
	    helpGetHelpItem.setText("&Get Help");
	    
	    shell.setMenuBar(menuBar);
	}
	
	public void displayNotification(String content){
		Notification notification = new Notification(false, content);
		NotificationQueue.GetInstance().add(notification);
	}
	
	public void displaySolution(Solution<Position> sol){
		this.mazeDisplay.setMazeSolution(sol);
	}
	
	public void updateMaze(Maze3d maze){
		this.mazeDisplay.setMaze(maze);
	}
	
	public static void SetNextStep(String direction){
		lblNextStep.setText("Go " + direction + "!");
	}
}

