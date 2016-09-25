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

import general.NotificationParam;
import presneter.PropertiesManager;

public class PropertiesWindow extends DialogWindow {

	private Observable o;

	private String selectedDir;
	private Text txtNumOfThreads ;
	private Combo ddlGenerateMazeAlgorithm; 
	private Combo ddlSolveMazeAlgorithm;

	public PropertiesWindow(Observable o) {
		this.o = o;
	}

	@Override
	protected void initWidgets() {
		shell.setText("Properties window");
		shell.setLayout(new GridLayout(2, false));
		shell.setSize(400, 200);

		Label lblNumOfThreads = new Label(shell, SWT.NONE);
		lblNumOfThreads.setText("Number of threads: ");

		txtNumOfThreads = new Text(shell, SWT.BORDER);
		txtNumOfThreads.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		/*if (PropertiesManager.getInstance().getProps() != null) {
			String value = Integer.toString(PropertiesManager.getInstance().getProps().getNumOfThreads());
			txtNumOfThreads.setText(value);
		}*/

		Label lblGenerateMazeAlgorithm = new Label(shell, SWT.NONE);
		lblGenerateMazeAlgorithm.setText("Generate maze algorithm: ");

		ddlGenerateMazeAlgorithm = new Combo(shell, SWT.DROP_DOWN | SWT.BORDER | SWT.READ_ONLY);
		ddlGenerateMazeAlgorithm.add("Simple maze generator");
		ddlGenerateMazeAlgorithm.add("Growing tree generator");

		/*if (PropertiesManager.getInstance().getProps() != null) {
			Integer value = PropertiesManager.getInstance().getProps().getGenerateMazeAlgorithmId();
			ddlGenerateMazeAlgorithm.select(value);
			;
		}*/

		Label lblSolveMazeAlgorithm = new Label(shell, SWT.NONE);
		lblSolveMazeAlgorithm.setText("Solve maze algorithm: ");

		ddlSolveMazeAlgorithm = new Combo(shell, SWT.DROP_DOWN | SWT.BORDER | SWT.READ_ONLY);
		ddlSolveMazeAlgorithm.add("BFS");
		ddlSolveMazeAlgorithm.add("DFS");
		
		displayProperties();

		/*if (PropertiesManager.getInstance().getProps() != null) {
			Integer value = PropertiesManager.getInstance().getProps().getSolveMazeAlgorithmId();
			ddlSolveMazeAlgorithm.select(value);
			;
		}*/

		/*
		 * Button btnSelectDir = new Button(shell, SWT.PUSH);
		 * btnSelectDir.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true,
		 * false, 2, 1)); btnSelectDir.setText("Select directory");
		 * 
		 * 
		 * 
		 * btnSelectDir.addSelectionListener(new SelectionListener(){
		 * 
		 * @Override public void widgetSelected(SelectionEvent e) {
		 * DirectoryDialog directoryDialog = new DirectoryDialog(shell);
		 * 
		 * directoryDialog.setFilterPath(selectedDir);
		 * directoryDialog.setMessage("Please select a directory and click OK");
		 * 
		 * String dir = directoryDialog.open(); if(dir != null) {
		 * 
		 * selectedDir = dir; } }
		 * 
		 * @Override public void widgetDefaultSelected(SelectionEvent e) { //
		 * TODO Auto-generated method stub
		 * 
		 * } });
		 */

		Button btnSaveProps = new Button(shell, SWT.PUSH);
		shell.setDefaultButton(btnSaveProps);
		btnSaveProps.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1));
		btnSaveProps.setText("Save properties");

		btnSaveProps.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				int numOfThreads = Integer.parseInt(txtNumOfThreads.getText());
				int generateMazeAlgorithmId = ddlGenerateMazeAlgorithm.getSelectionIndex();
				int solveMazeAlgorithmId = ddlSolveMazeAlgorithm.getSelectionIndex();

				Object[] arr = new Object[] { numOfThreads, generateMazeAlgorithmId, solveMazeAlgorithmId };

				NotificationParam noteParam = new NotificationParam(arr, "save_properties");

				note(noteParam);
				
				shell.close();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		Button btnLoadProps = new Button(shell, SWT.PUSH);
		btnLoadProps.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1));
		btnLoadProps.setText("Load properties");

		btnLoadProps.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				NotificationParam noteParam = new NotificationParam(null, "load_properties");
				
				note(noteParam);
				
				displayProperties();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

	public void note(Object arg) {
		this.o.notifyObservers(arg);
	}
	
	private void displayProperties(){
		if (PropertiesManager.getInstance().getProps() != null) {
			String value = Integer.toString(PropertiesManager.getInstance().getProps().getNumOfThreads());
			txtNumOfThreads.setText(value);

			Integer gneerateAlgId = PropertiesManager.getInstance().getProps().getGenerateMazeAlgorithmId();
			ddlGenerateMazeAlgorithm.select(gneerateAlgId);

			Integer solveAlgId = PropertiesManager.getInstance().getProps().getSolveMazeAlgorithmId();
			ddlSolveMazeAlgorithm.select(solveAlgId);
		}
	}

}
