package gui;

import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.State;
import general.NotificationParam;

public class MazeDisplay extends Canvas {
	private static byte[][] mazeData;

	private static Maze3d maze;
	private static int currentHeight;
	private static Boolean displaySolution = false;
	private String mazeName;
	private Character character;
	private static Solution<Position> sol;
	private static Boolean freezeMaze = false;
	private static String step = "";
	private int spacing = 2;

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

	public void setMazeSolution(Solution sol) {
		MazeDisplay.sol = sol;
		MazeDisplay.displaySolution = true;

		List<State> states = sol.getStates();
		for (State<Position> state : states) {
			Position pos = state.getValue();
			System.out.println("solution : " + pos);
		}
	}

	public MazeDisplay(Composite parent, int style, Button b) {

		super(parent, style);

		character = new Character();

		this.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {

				if (!MazeDisplay.freezeMaze) {
					int nextIndex;
					Position pos = character.getRealPos();
					Position newPos = new Position(pos);
					switch (e.keyCode) {
					case SWT.ARROW_RIGHT:
						nextIndex = pos.getWidth() + spacing;
						if (nextIndex < MazeDisplay.maze.getConfiguration().getWidth()) {
							newPos.setWidth(nextIndex - spacing / 2);
							if (MazeDisplay.maze.getCellValue(newPos) == 0) {
								character.moveRight();
							}
						}
						break;
					case SWT.ARROW_LEFT:
						nextIndex = pos.getWidth() - spacing;
						if (nextIndex >= 0) {
							newPos.setWidth(nextIndex + spacing / 2);
							if (MazeDisplay.maze.getCellValue(newPos) == 0) {
								character.moveLeft();
							}
						}
						break;
					case SWT.ARROW_DOWN:
						nextIndex = pos.getLength() + spacing;
						if (nextIndex < MazeDisplay.maze.getConfiguration().getLength()) {
							newPos.setLength(nextIndex - spacing / 2);
							if (MazeDisplay.maze.getCellValue(newPos) == 0) {
								character.moveBackward();
							}

						}
						break;
					case SWT.ARROW_UP:
						nextIndex = pos.getLength() - spacing;
						if (nextIndex >= 0) {
							newPos.setLength(nextIndex + spacing / 2);
							if (MazeDisplay.maze.getCellValue(newPos) == 0) {
								character.moveFarward();
							}
						}
						break;
					// w
					case 119:
						nextIndex = pos.getHeight() + spacing;
						if (nextIndex < MazeDisplay.maze.getConfiguration().getHeight()) {
							newPos.setHeight(nextIndex - spacing / 2);
							if (MazeDisplay.maze.getCellValue(newPos) == 0) {
								character.moveUp();
								MazeDisplay.mazeData = MazeDisplay.maze
										.getCrossSectionByZ(character.getRealPos().getHeight());
								MazeDisplay.currentHeight = MazeDisplay.currentHeight + spacing;
							}
						}
						break;
					// s
					case 115:
						nextIndex = pos.getHeight() - spacing;
						if (nextIndex >= 0) {
							newPos.setHeight(nextIndex + spacing / 2);
							if (MazeDisplay.maze.getCellValue(newPos) == 0) {
								character.moveDown();
								MazeDisplay.mazeData = MazeDisplay.maze
										.getCrossSectionByZ(character.getRealPos().getHeight());
								MazeDisplay.currentHeight = MazeDisplay.currentHeight - spacing;
							}
						}
						break;
					}

					redraw();
				}
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

					int w = (width / MazeDisplay.mazeData[0].length);
					int h = (height / MazeDisplay.mazeData.length);

					Color backgroundColor = display.getSystemColor(SWT.COLOR_CYAN);

					for (int i = 0; i < MazeDisplay.mazeData.length; i++) {
						for (int j = 0; j < MazeDisplay.mazeData[i].length; j++) {
							backgroundColor = white;

							int x = j / 2 * w;
							int y = i / 2 * h;
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
										backgroundColor = green;
									} else if (higherCellValue == 0) {
										backgroundColor = blue;
									} else if (lowerCellValue == 0) {
										backgroundColor = yellow;
									}
								} else if (higherPos.getHeight() < MazeDisplay.maze.getConfiguration().getHeight()) {
									higherCellValue = MazeDisplay.maze.getCellValue(higherPos);
									if (higherCellValue == 0) {
										backgroundColor = blue;
									}
								} else if (lowerPos.getHeight() >= 0) {
									lowerCellValue = MazeDisplay.maze.getCellValue(lowerPos);
									if (lowerCellValue == 0) {
										backgroundColor = yellow;
									}
								} else {
									backgroundColor = white;
								}
							} else {
								backgroundColor = black;
							}

							if (currPosition.equals(MazeDisplay.maze.getGoalPosition())) {
								backgroundColor = red;
							}

							e.gc.setBackground(backgroundColor);

							if (i % 2 == 0 && j % 2 == 0) {
								e.gc.fillRectangle(x, y, w, h);

								drawBoundaries(x, y, w, h, display, e, currPosition);
							}

							if (MazeDisplay.displaySolution) {
								for (State<Position> state : sol.getStates()) {
									State<Position> cameFrom = state.getCameFrom();
									if (cameFrom != null) {
										Position pos = state.getCameFrom().getValue();
										if (pos != null) {
											if (character.getRealPos().equals(pos)) {
												System.out.println("solution position...");
												Position nextStep = state.getValue();
												String direction = "";
												if (nextStep.getHeight() > pos.getHeight()) {
													direction = "higher";
												} else if (nextStep.getHeight() < pos.getHeight()) {
													direction = "lower";
												} else if (nextStep.getLength() > pos.getLength()) {
													direction = "down";
												} else if (nextStep.getLength() < pos.getLength()) {
													direction = "up";
												} else if (nextStep.getWidth() > pos.getWidth()) {
													direction = "right";
												} else if (nextStep.getWidth() < pos.getWidth()) {
													direction = "left";
												}

												MazeDisplay.step = direction;
											}
										}
									}
								}
							}

							if (MazeDisplay.maze.getGoalPosition().equals(character.getRealPos())) {
								System.out.println("game over");

								MazeDisplay.freezeMaze = true;

								Notification note = new Notification(false,
										"Congratulations! you have solved the maze!");
								NotificationQueue.getInstance().add(note);
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

						Notification notification = (Notification) NotificationQueue.getInstance().poll();

						if (notification != null) {
							MessageBox msg = new MessageBox(new Shell(), SWT.OK);
							if (notification.getIsError()) {
								msg.setText("Error");
							} else {
								msg.setText("Notification");
							}

							msg.setMessage(notification.getContent());
							msg.open();
						}

						b.setText("Solution next step: Go " + MazeDisplay.step);
						
						if(MazeDisplay.freezeMaze){
							cancel();
						}
					}
				});
			}
		};
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(task, 0, 1000);
	}

	private void drawBoundaries(int x, int y, int w, int h, Display display, PaintEvent e, Position currPosition) {
		Color black = display.getSystemColor(SWT.COLOR_BLACK);
		Color white = display.getSystemColor(SWT.COLOR_WHITE);

		e.gc.setBackground(black);

		Position temp = null;

		// check farward cell
		temp = new Position(currPosition);
		temp.setLength(temp.getLength() + 1);
		if (temp.getLength() < maze.getConfiguration().getLength() && maze.getCellValue(temp) == 1) {
			e.gc.fillRectangle(x, y + h - 10, w, 10);
		}

		// check backward cell
		temp = new Position(currPosition);
		temp.setLength(temp.getLength() - 1);
		if (temp.getLength() >= 0 && maze.getCellValue(temp) == 1) {
			e.gc.fillRectangle(x, y, w, 10);
		}

		// check right cell
		temp = new Position(currPosition);
		temp.setWidth(temp.getWidth() + 1);
		if (temp.getWidth() < maze.getConfiguration().getWidth() && maze.getCellValue(temp) == 1) {
			e.gc.fillRectangle(x + w - 10, y, 10, h);
		}

		// check left cell
		temp = new Position(currPosition);
		temp.setWidth(temp.getWidth() - 1);
		if (temp.getWidth() >= 0 && maze.getCellValue(temp) == 1) {
			e.gc.fillRectangle(x, y, 10, h);
		}

		e.gc.setBackground(white);

	}

}
