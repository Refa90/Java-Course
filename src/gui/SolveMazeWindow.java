package gui;

import java.util.Observable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import algorithms.mazeGenerators.Position;
import algorithms.search.BFS;
import algorithms.search.CommonSearcher;
import algorithms.search.DFS;
import general.NotificationParam;

public class SolveMazeWindow extends DialogWindow {

	private Observable observable;
	private String mazeName;

	public SolveMazeWindow(Observable o, String mazeName) {
		this.observable = o;
		this.mazeName = mazeName;
	}

	private void note(Object arg) {
		this.observable.notifyObservers(arg);
	}

	@Override
	protected void initWidgets() {
		shell.setText("Solve maze window");
		shell.setSize(300, 200);

		shell.setLayout(new GridLayout(2, false));

		Label lblAlgorithm = new Label(shell, SWT.NONE);
		lblAlgorithm.setText("Choose algorithm");

		Combo comboDropDown = new Combo(shell, SWT.DROP_DOWN | SWT.BORDER);
		comboDropDown.add("BFS");
		comboDropDown.add("DFS");

		Button btnSolveMaze = new Button(shell, SWT.PUSH);
		shell.setDefaultButton(btnSolveMaze);
		btnSolveMaze.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1));
		btnSolveMaze.setText("Solve maze");

		btnSolveMaze.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				CommonSearcher<Position> searcher = null;
				switch (comboDropDown.getSelectionIndex()) {
				case 0:
					searcher = new BFS();
					break;
				case 1:
					searcher = new DFS();
					break;
				}

				Object[] params = new Object[] { mazeName != null ? mazeName :  "maze", searcher};
				
				NotificationParam noteParam = new NotificationParam(params, "solve");
				
				note(noteParam);
				
				shell.close();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

	}

}
