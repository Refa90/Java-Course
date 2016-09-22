package gui;

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
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.Position;

public class MazeDisplay extends Canvas {

	/*private int[][] mazeData = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 1 }, { 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 1, 0, 1, 1 },
			{ 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1 }, { 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1 },
			{ 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1 }, { 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1 },
			{ 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1 } };*/
	
	private static byte[][] mazeData;

	private static Maze3d maze;
	private static int currentHeight;
	private String mazeName;
	private Character character;
	
	public void setMaze(Maze3d maze){
		MazeDisplay.maze = maze;
		maze.print();
		//int height = maze.getStartPosition().getHeight() == 0 ? 0 : maze.getStartPosition().getHeight() + 1;
		MazeDisplay.mazeData = maze.getCrossSectionByZ(maze.getStartPosition().getHeight());  
		MazeDisplay.currentHeight = maze.getStartPosition().getHeight();
		Position startPos = maze.getStartPosition();
		System.out.println("start pos: " + startPos);
		character.setPos(new Position(startPos.getHeight(), startPos.getLength(), startPos.getWidth()));
	}

	public MazeDisplay(Composite parent, int style) {
		super(parent, style);

		//maze.print();

		character = new Character();
		

		this.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				Position pos = character.getPos();
				switch (e.keyCode) {
				case SWT.ARROW_RIGHT:
					character.moveRight();
					break;
				case SWT.ARROW_LEFT:
					character.moveLeft();
					break;
				case SWT.ARROW_UP:
					character.moveFarward();
					break;
				case SWT.ARROW_DOWN:
					character.moveBackward();
					break;
					//w
				case 119:
					character.moveUp();
					//store current location - raise height
					//paint new like maze
					//position charatcer in correct place
					break;
					//s
				case 115:
					character.moveDown();
					//store current location - decrease height
					//paint new like maze
					//position charatcer in correct place
					break;
				}

				redraw();
			}
		});

		this.addPaintListener(new PaintListener() {
			//Boolean isFirstRun = true;
			@Override
			public void paintControl(PaintEvent e) {
				//System.out.println("in paint..");
				
				if(MazeDisplay.mazeData != null){
					
					
					/*Display display = Display.getCurrent();
					Color blue = display.getSystemColor(SWT.COLOR_BLUE);
					Color white = display.getSystemColor(SWT.COLOR_WHITE);
					Color black = display.getSystemColor(SWT.COLOR_BLACK);
					Color green = display.getSystemColor(SWT.COLOR_GREEN);
					Color red = display.getSystemColor(SWT.COLOR_RED);

					e.gc.setBackground(black);
					// e.gc.setForeground(blue);

					/*
					 * int width = getSize().x; int height = getSize().y;
					 * 
					 * int w = width / mazeData[0].length; int h = height /
					 * mazeData.length;
					 * 
					 * for (int i = 0; i < mazeData.length; i++) for (int j = 0; j <
					 * mazeData[i].length; j++) { int x = j * w; int y = i * h; if
					 * (mazeData[i][j] != 0) e.gc.fillRectangle(x, y, w, h); }
					 * 
					 * character.draw(w, h, e.gc);
					 */

					/////////////////////////////////////////////////////

					/*int scrennWidth = getSize().x;
					int screenHeight = getSize().y;

					int squareWidth = (scrennWidth / (((maze.getConfiguration().getWidth() / 2) + 1)));
					int squareLength = (screenHeight / (((maze.getConfiguration().getLength() / 2) + 1)));

					int  l = 0;

						int x = 0;
						int y = 0;
						for (int w = 0; w < maze.getConfiguration().getWidth(); w++) {

							Position pos = new Position(MazeDisplay.currentHeight, l, w);
							
							e.gc.drawText("w: " + String.valueOf(w) + ", l: " + String.valueOf(l), x, style);

							if (maze.getCellValue(pos) == 0) {
								if (l % 2 != 0 || w % 2 != 0) {
									e.gc.setForeground(green);
									e.gc.drawLine(x, y, x, y + squareLength * 2);
								} else {
									x = x + squareWidth;
									y = y + squareLength;
									e.gc.setBackground(white);
									e.gc.fillRectangle(x, y, squareWidth, squareLength);
								}
							} else {
								if (l % 2 != 0 || w % 2 != 0) {
									e.gc.setForeground(red);
									e.gc.drawLine(x, y, x, y + squareLength * 2);
								}else{
									x = x + squareWidth;
									y = y + squareLength;
									e.gc.setBackground(black);
									e.gc.fillRectangle(x, y, squareWidth, squareLength);	
								}
							}
						}
						e.gc.setForeground(new Color(null, 0, 0, 0));
						e.gc.setBackground(new Color(null, 0, 0, 0));

						int width = getSize().x;
						int height = getSize().y;

						int w = width / mazeData[0].length;
						int h = height / mazeData.length;

						for (int i = 0; i < mazeData.length; i++)
							for (int j = 0; j < mazeData[i].length; j++) {
								int x = j * w;
								int y = i * h;
								if (mazeData[i][j] != 0)
									e.gc.fillRectangle(x, y, w, h);
							}

						character.draw(w, h, e.gc);
					
					character.draw(squareWidth, squareLength, e.gc);*/
					
					e.gc.setForeground(new Color(null, 0, 0, 0));
					e.gc.setBackground(new Color(null, 0, 0, 0));

					int width = getSize().x;
					int height = getSize().y;

					int w = width / mazeData[0].length;
					int h = height / mazeData.length;

					for (int i = 0; i < mazeData.length; i++)
						for (int j = 0; j < mazeData[i].length; j++) {
							int x = j * w;
							int y = i * h;
							if (mazeData[i][j] != 0)
								e.gc.fillRectangle(x, y, w, h);
						}

					//character.draw(w, h, e.gc);
					
					/*if(isFirstRun){
						character.draw(MazeDisplay.maze.getStartPosition().getWidth(), MazeDisplay.maze.getStartPosition().getLength(), e.gc);
					}else{*/
						character.draw(w, h, e.gc);
						/*character.draw(MazeDisplay.maze.getStartPosition().getWidth(), MazeDisplay.maze.getStartPosition().getLength(), e.gc);
					}*/
					
					
					//isFirstRun = false;
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
						
						/*Notification notification = (Notification)NotificationQueue.GetInstance().poll();
						
						if(notification != null){
							MessageBox msg = new MessageBox(new Shell(), SWT.OK);
							if(notification.getIsError()){
								msg.setText("Error");	
							}else{
								msg.setText("Notification");	
							}
							
							msg.setMessage(notification.getContent());
							msg.open();	
						}*/
					}
				});

			}
		};
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(task, 0, 1000);
	}

}
