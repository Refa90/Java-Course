package gui;

import java.util.Observable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import general.NotificationParam;

public class LoadMazeWindow extends DialogWindow {

	private Observable observable;
	
	public LoadMazeWindow (Observable o){
		this.observable = o;
	}
	
	private void note(Object arg){
		this.observable.notifyObservers(arg);
	}
	
	@Override
	protected void initWidgets() {
		shell.setText("Load maze window");
		shell.setSize(300, 200);		
				
		shell.setLayout(new GridLayout(2, false));	
		
		Label lblFileName = new Label(shell, SWT.NONE);
		lblFileName.setText("File name: ");
		
		Text txtFileName = new Text(shell, SWT.BORDER);
		txtFileName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label lblMazeName = new Label(shell, SWT.NONE);
		lblMazeName.setText("Maze name: ");
		
		Text txtMazeName = new Text(shell, SWT.BORDER);
		txtMazeName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Button btnSaveMaze = new Button(shell, SWT.PUSH);
		shell.setDefaultButton(btnSaveMaze);
		btnSaveMaze.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1));
		btnSaveMaze.setText("load maze");
		
		btnSaveMaze.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String fileName = txtFileName.getText();
				String mazeName = txtMazeName.getText();
				
				String[] params = new String[]{
					"load_maze",
					mazeName,
					fileName
				};
				
				NotificationParam noParam = new NotificationParam(params, "load_maze"); 
				
				note(noParam);
				
				shell.close();
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

}
