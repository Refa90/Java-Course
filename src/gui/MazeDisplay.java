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

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.Position;

public class MazeDisplay extends Canvas {

	private int[][] mazeData = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 1 }, { 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 1, 0, 1, 1 },
			{ 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1 }, { 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1 },
			{ 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1 }, { 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1 },
			{ 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1 } };

	private Maze3d maze;

	private Character character;

	public MazeDisplay(Composite parent, int style, Maze3dGenerator mazeGenerator, Position mazeConfiguration) {
		super(parent, style);

		this.maze = mazeGenerator.generate(mazeConfiguration);

		maze.print();

		character = new Character();
		character.setPos(new Position(1, 1, 1));

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
				case 119:
					character.moveUp();
					break;
				case 115:
					character.moveDown();
					break;
				}

				redraw();
			}
		});

		this.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent e) {
				Display display = Display.getCurrent();
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

				int scrennWidth = getSize().x;
				int screenHeight = getSize().y;

				int squareWidth = (scrennWidth / (((maze.getConfiguration().getWidth() / 2) + 1)));
				int squareLength = (screenHeight / (((maze.getConfiguration().getLength() / 2) + 1)));

				int  l = 0;

				//for (int l = 0; l < maze.getConfiguration().getLength(); l++) {
					int x = 0;
					int y = 0;
					for (int w = 0; w < maze.getConfiguration().getWidth(); w++) {

						Position pos = new Position(0, l, w);

						// int x = w * squareWidth;
						// int y = l * squareLength;
						
						e.gc.drawText("w: " + String.valueOf(w) + ", l: " + String.valueOf(l), x, style);

						if (maze.getCellValue(pos) == 0) {
							if (l % 2 != 0 || w % 2 != 0) {
								// e.gc.setBackground(blue);
								// e.gc.setBackground(green);
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
				//}
				
				character.draw(squareWidth, squareLength, e.gc);
			}
		});

		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				getDisplay().syncExec(new Runnable() {

					@Override
					public void run() {
						//redraw();
					}
				});

			}
		};
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(task, 0, 1000);
	}

}
