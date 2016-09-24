package gui;

import java.util.Observable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class PropertiesWindow extends DialogWindow {

	private Observable o;
	
	private String selectedDir;
	
	public PropertiesWindow (Observable o){
		this.o = o;
	}
	
	@Override
	protected void initWidgets() {
		shell.setText("Properties window");
		shell.setSize(300, 200);	
		
		shell.setLayout(new GridLayout(2, false));	
		
		Label lblNumOfThreads = new Label(shell, SWT.NONE);
		lblNumOfThreads.setText("Number of threads: ");
		
		Text txtNumOfThreads = new Text(shell, SWT.BORDER);
		txtNumOfThreads.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label lblGenerateMazeAlgorithm = new Label(shell, SWT.NONE);
		lblGenerateMazeAlgorithm.setText("Generate maze algorithm: ");
		
		Combo ddlGenerateMazeAlgorithm = new Combo(shell, SWT.DROP_DOWN | SWT.BORDER | SWT.READ_ONLY);
		ddlGenerateMazeAlgorithm.add("Simple maze generator");
		ddlGenerateMazeAlgorithm.add("Growing tree generator");
		
		Label lblSolveMazeAlgorithm = new Label(shell, SWT.NONE);
		lblSolveMazeAlgorithm.setText("Solve maze algorithm: ");
		
		Combo ddlSolveMazeAlgorithm = new Combo(shell, SWT.DROP_DOWN | SWT.BORDER | SWT.READ_ONLY);
		ddlSolveMazeAlgorithm.add("BFS");
		ddlSolveMazeAlgorithm.add("DFS");
		
		Button btnSelectDir = new Button(shell, SWT.PUSH);
		btnSelectDir.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1));
		btnSelectDir.setText("Select directory");
		
		
		
		btnSelectDir.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog directoryDialog = new DirectoryDialog(shell);
		        
		        directoryDialog.setFilterPath(selectedDir);
		        directoryDialog.setMessage("Please select a directory and click OK");
		        
		        String dir = directoryDialog.open();
		        if(dir != null) {
		          
		          selectedDir = dir;
		        }
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		Button btnSaveProps = new Button(shell, SWT.PUSH);
		shell.setDefaultButton(btnSaveProps);
		btnSaveProps.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1));
		btnSaveProps.setText("Save properties");
		
		btnSaveProps.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				int numOfThreads = Integer.parseInt(txtNumOfThreads.getText());
				int generateMazeAlgorithmId =  ddlGenerateMazeAlgorithm.getSelectionIndex();
				int solveMazeAlgorithmId =  ddlSolveMazeAlgorithm.getSelectionIndex();
				
				int[] arr = new int[]{
						numOfThreads, generateMazeAlgorithmId, solveMazeAlgorithmId
				};
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
}
