package gui;

import java.awt.Graphics;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;

import algorithms.mazeGenerators.Position;

public class Character {
	private Position pos;
	private Image img;
	
	public Character() {
		img = new Image(null, "images/character.jpg");
	}

	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}
	
	public void draw(int cellWidth, int cellHeight, GC gc) {
		gc.drawImage(img, 0, 0, img.getBounds().width, img.getBounds().height, 
				cellWidth * pos.getWidth(), cellHeight * pos.getLength(), cellWidth, cellHeight);
	}
	
	public void moveRight() {
		pos.setWidth(pos.getWidth() + 1);
		System.out.println("move right");
	}
	
	public void moveLeft() {
		pos.setWidth(pos.getWidth() - 1);
		System.out.println("move left");
	}
	
	public void moveFarward(){
		pos.setLength(pos.getLength() - 1);
		System.out.println("move farward");
	}
	
	public void moveBackward(){
		pos.setLength(pos.getLength() + 1);
		System.out.println("move backward");
	}
	
	public void moveUp(){
		pos.setHeight(pos.getHeight() + 1);
		System.out.println("move up");
	}
	
	public void moveDown(){
		pos.setHeight(pos.getHeight() - 1);
		System.out.println("move down");
	}
	

}
