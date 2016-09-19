package gui;
import java.util.Observable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

import general.NotificationParam;

public class GenerateMazeWindow extends DialogWindow {
	
	private Observable observable;
	
	public GenerateMazeWindow (Observable observable){
		this.observable = observable;
	}
	
	private void note(Object arg){
		this.observable.notifyObservers(arg);
	}
	
	@Override
	protected void initWidgets() {
		shell.setText("Generate maze window");
		shell.setSize(300, 200);		
				
		shell.setLayout(new GridLayout(2, false));	
				
		Label lblRows = new Label(shell, SWT.NONE);
		lblRows.setText("Rows: ");
		
		Text txtRows = new Text(shell, SWT.BORDER);
		txtRows.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label lblCols = new Label(shell, SWT.NONE);
		lblCols.setText("Cols: ");
		
		Text txtCols = new Text(shell, SWT.BORDER);
		txtCols.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label lblHeight = new Label(shell, SWT.NONE);
		lblHeight.setText("Height: ");
		
		Text txtHeight = new Text(shell, SWT.BORDER);
		txtHeight.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label lblMazeName = new Label(shell, SWT.NONE);
		lblMazeName.setText("Maze name: ");
		
		Text txtMazeName = new Text(shell, SWT.BORDER);
		txtMazeName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label lblAlgName = new Label(shell, SWT.NONE);
		lblAlgName.setText("Choose Algorithm: ");
		
		Combo comboDropDown = new Combo(shell, SWT.DROP_DOWN | SWT.BORDER);
		comboDropDown.add("Simple maze generator");
		comboDropDown.add("Growing tree generator");
					
		Button btnGenerateMaze = new Button(shell, SWT.PUSH);
		shell.setDefaultButton(btnGenerateMaze);
		btnGenerateMaze.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1));
		btnGenerateMaze.setText("Generate maze");
		
		btnGenerateMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {				
				//MessageBox msg = new MessageBox(shell, SWT.OK);
				//msg.setText("Title");
				//msg.setMessage("Button was clicked");
				
				int rows = Integer.parseInt(txtRows.getText());
				int cols = Integer.parseInt(txtCols.getText());
				int height = Integer.parseInt(txtHeight.getText());
				String mazeName = txtMazeName.getText();
				String command = "generate_3d_maze";
				int algorithmId = comboDropDown.getSelectionIndex(); 
				
				String[] params = new String[]{
					command,
					mazeName,
					String.valueOf(algorithmId),
					String.valueOf(height),
					String.valueOf(rows),
					String.valueOf(cols)
				};
				
				NotificationParam notifyParam = new NotificationParam(params, command); 
				
				//msg.setMessage("Generating maze with rows: " + rows + " cols: " + cols);
				
				//msg.open();
				
				note(notifyParam);
				
				shell.close();
				
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {			
				
			}
		});	
	}
}
