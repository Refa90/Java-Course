package gui;

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
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.Position;
import general.NotificationParam;
import general.ThreadsManager;

public class MazeWindow extends BaseWindow {

	private MazeDisplay mazeDisplay;
	private Observable observable;
	
	public MazeWindow(Observable observable){
		this.observable = observable;
	}
	
	@Override
	protected void initWidgets() {
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
				GenerateMazeWindow win = new GenerateMazeWindow(observable);				
				win.start(display);
				
				/*Composite buttons2 = new Composite(shell, SWT.NONE);
				RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
				buttons2.setLayout(rowLayout);*/
				
				//NotificationParam param = new NotificationParam(arg0, "generate_3d_maze");
				//observable.notifyObservers(param);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				int x = 5;
				
				x = x -1;
			}
		});
		
		Button btnSolveMaze = new Button(buttons, SWT.PUSH);
		btnSolveMaze.setText("Solve maze");
		
		btnSolveMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("solve maze");
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
		
		Button btnSaveMaze = new Button(buttons, SWT.PUSH);
		btnSaveMaze.setText("save maze");
		
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
		
		Button btnLoadMaze = new Button(buttons, SWT.PUSH);
		btnLoadMaze.setText("load maze");
		
		btnLoadMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("load maze");
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
		
		Position configuration = new Position(4,4,4);
		Maze3dGenerator mazeGenerator = new GrowingTreeGenerator();
		String mazeName = "maze";
		
		mazeDisplay = new MazeDisplay(shell, SWT.BORDER, mazeGenerator, configuration, mazeName);	
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
		NotificationQueue.GetInstance().add(content);
		
		

	}

}

