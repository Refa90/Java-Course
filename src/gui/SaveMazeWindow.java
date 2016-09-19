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

public class SaveMazeWindow extends DialogWindow {

	private Observable observable;
	
	public SaveMazeWindow (Observable o){
		this.observable = o;
	}
	
	private void note(Object arg){
		this.observable.notifyObservers(arg);
	}
	
	@Override
	protected void initWidgets() {
		shell.setText("Save maze window");
		shell.setSize(300, 200);		
				
		shell.setLayout(new GridLayout(2, false));	
				
		Label lblFilePath = new Label(shell, SWT.NONE);
		lblFilePath.setText("File path: ");
		
		Text txtFilePath = new Text(shell, SWT.BORDER);
		txtFilePath.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Button btnSaveMaze = new Button(shell, SWT.PUSH);
		shell.setDefaultButton(btnSaveMaze);
		btnSaveMaze.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1));
		btnSaveMaze.setText("save maze");
		
		btnSaveMaze.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String filePath = txtFilePath.getText();
				
				String[] params = new String[]{
					"save_maze",
					"maze",
					filePath
				};
				
				NotificationParam noParam = new NotificationParam(params, "save_maze"); 
				
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
