package gui;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.State;

public class MazeDisplay extends Canvas {
	private static byte[][] mazeData;

	private static Maze3d maze;
	private static int currentHeight;
	private static Boolean displaySolution = false;
	private String mazeName;
	private Character character;
	private static Solution<Position> sol;
	//private Set<Position> solPosSet;

	public void setMaze(Maze3d maze) {
		MazeDisplay.maze = maze;

		maze.print();
		MazeDisplay.mazeData = maze.getCrossSectionByZ(maze.getStartPosition().getHeight());
		MazeDisplay.currentHeight = maze.getStartPosition().getHeight();
		Position startPos = maze.getStartPosition();
		Position goalPos = maze.getGoalPosition();
		System.out.println("start pos: " + startPos);
		System.out.println("goal pos: " + goalPos);
		character.setPos(new Position(startPos.getHeight(), startPos.getLength(), startPos.getWidth()));
	}
	
	public void setMazeSolution(Solution sol){
		MazeDisplay.sol = sol;
		MazeDisplay.displaySolution = true;
		
		List<State> states = sol.getStates();
		for(State<Position> state : states){
			Position pos = state.getValue();
			System.out.println("solution : " + pos);
		}	
	}

	public MazeDisplay(Composite parent, int style) {
		super(parent, style);

		character = new Character();

		this.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				int nextIndex;
				Position pos = character.getPos();
				Position newPos = new Position(pos);
				switch (e.keyCode) {
				case SWT.ARROW_RIGHT:
					nextIndex = pos.getWidth() + 1;
					if (nextIndex < MazeDisplay.maze.getConfiguration().getWidth()) {
						newPos.setWidth(nextIndex);
						if (MazeDisplay.maze.getCellValue(newPos) == 0) {
							character.moveRight();
						}
					}
					break;
				case SWT.ARROW_LEFT:
					nextIndex = pos.getWidth() - 1;
					if (nextIndex >= 0) {
						newPos.setWidth(nextIndex);
						if (MazeDisplay.maze.getCellValue(newPos) == 0) {
							character.moveLeft();
						}
					}
					break;
				case SWT.ARROW_DOWN:
					nextIndex = pos.getLength() + 1;
					if (nextIndex < MazeDisplay.maze.getConfiguration().getLength()) {
						newPos.setLength(nextIndex);
						if (MazeDisplay.maze.getCellValue(newPos) == 0) {
							character.moveBackward();
						}

					}
					break;
				case SWT.ARROW_UP:
					nextIndex = pos.getLength() - 1;
					if (nextIndex >= 0) {
						newPos.setLength(nextIndex);
						if (MazeDisplay.maze.getCellValue(newPos) == 0) {
							character.moveFarward();
						}
					}
					break;
				// w
				case 119:
					nextIndex = pos.getHeight() + 1;
					if (nextIndex < MazeDisplay.maze.getConfiguration().getHeight()) {
						newPos.setHeight(nextIndex);
						if (MazeDisplay.maze.getCellValue(newPos) == 0) {
							character.moveUp();
							MazeDisplay.mazeData = MazeDisplay.maze.getCrossSectionByZ(character.getPos().getHeight());
							MazeDisplay.currentHeight++;
						}
					}
					break;
				// s
				case 115:
					nextIndex = pos.getHeight() - 1;
					if (nextIndex >= 0) {
						newPos.setHeight(nextIndex);
						if (MazeDisplay.maze.getCellValue(newPos) == 0) {
							character.moveDown();
							MazeDisplay.mazeData = MazeDisplay.maze.getCrossSectionByZ(character.getPos().getHeight());
							MazeDisplay.currentHeight--;
						}
					}
					break;
				}
				
				redraw();
			}
		});

		this.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent e) {
				
				if (MazeDisplay.mazeData != null) {
					Display display = Display.getCurrent();
					
					Color blue = display.getSystemColor(SWT.COLOR_BLUE);
					Color white = display.getSystemColor(SWT.COLOR_WHITE);
					Color black = display.getSystemColor(SWT.COLOR_BLACK);
					Color green = display.getSystemColor(SWT.COLOR_GREEN);
					Color yellow = display.getSystemColor(SWT.COLOR_YELLOW);
					Color red = display.getSystemColor(SWT.COLOR_RED);

					e.gc.setForeground(new Color(null, 0, 0, 0));
					e.gc.setBackground(new Color(null, 0, 0, 0));

					int width = getSize().x;
					int height = getSize().y;

					int w = width / MazeDisplay.mazeData[0].length;
					int h = height / MazeDisplay.mazeData.length;

					for (int i = 0; i < MazeDisplay.mazeData.length; i++) {
						for (int j = 0; j < MazeDisplay.mazeData[i].length; j++) {
							e.gc.setBackground(white);

							int x = j * w;
							int y = i * h;
							Position currPosition = new Position(MazeDisplay.currentHeight, i, j);
							
							if (MazeDisplay.maze.getCellValue(currPosition) == 0) {

								Position higherPos = new Position(currPosition);
								higherPos.setHeight(currPosition.getHeight() + 1);
								Position lowerPos = new Position(currPosition);
								lowerPos.setHeight(currPosition.getHeight() - 1);

								int higherCellValue = -1;
								int lowerCellValue = -1;

								if (higherPos.getHeight() < MazeDisplay.maze.getConfiguration().getHeight()
										&& lowerPos.getHeight() >= 0) {
									higherCellValue = MazeDisplay.maze.getCellValue(higherPos);
									lowerCellValue = MazeDisplay.maze.getCellValue(lowerPos);
									if (higherCellValue == 0 && lowerCellValue == 0) {
										e.gc.setBackground(green);
									} else if (higherCellValue == 0) {
										e.gc.setBackground(blue);
									} else if (lowerCellValue == 0) {
										e.gc.setBackground(yellow);
									}
								} else if (higherPos.getHeight() < MazeDisplay.maze.getConfiguration().getHeight()) {
									higherCellValue = MazeDisplay.maze.getCellValue(higherPos);
									if (higherCellValue == 0) {
										e.gc.setBackground(blue);
									}
								} else if (lowerPos.getHeight() >= 0) {
									lowerCellValue = MazeDisplay.maze.getCellValue(lowerPos);
									if (lowerCellValue == 0) {
										e.gc.setBackground(yellow);
									}
								} else {
									e.gc.setBackground(white);
								}
							} else {
								e.gc.setBackground(black);
							}

							if (currPosition.equals(MazeDisplay.maze.getGoalPosition())) {
								e.gc.setBackground(red);
							}

							e.gc.fillRectangle(x, y, w, h);

							if(MazeDisplay.displaySolution){
								for(State<Position> state: sol.getStates()){
									Position pos = state.getCameFrom().getValue();
									if(currPosition.equals(pos)){
										System.out.println("solution position...");
										Position nextStep = state.getValue();
										String direction = "";
										if(nextStep.getHeight() > pos.getHeight()){
											direction = "higher";
										}else if(nextStep.getHeight() < pos.getHeight()){
											direction = "lower";
										}else if(nextStep.getLength() > pos.getLength()){
											direction = "up";
										}else if(nextStep.getLength() < pos.getLength()){
											direction = "down";
										}else if(nextStep.getWidth() > pos.getWidth()){
											direction = "right";
										}else if(nextStep.getWidth() < pos.getWidth()){
											direction = "left";
										}
										
										MazeWindow.SetNextStep(direction);
									}
								}
							} 
						}
					}
					
					

					character.draw(w, h, e.gc);
				}
			}
		});

		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				getDisplay().syncExec(new Runnable() {

					@Override
					public void run() {
						redraw();

						/*
						 * Notification notification =
						 * (Notification)NotificationQueue.GetInstance().poll();
						 * 
						 * if(notification != null){ MessageBox msg = new
						 * MessageBox(new Shell(), SWT.OK);
						 * if(notification.getIsError()){ msg.setText("Error");
						 * }else{ msg.setText("Notification"); }
						 * 
						 * msg.setMessage(notification.getContent());
						 * msg.open(); }
						 */
					}
				});

			}
		};
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(task, 0, 1000);
	}

}
