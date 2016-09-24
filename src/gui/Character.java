package gui;

import java.awt.Graphics;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;

import algorithms.mazeGenerators.Position;

public class Character {
	private Position realPos;
	private Position displayPos;
	private Image img;
	private int realSpacing;
	private int displaySpacing;
	
	public Character() {
		img = new Image(null, "images/character.jpg");
		realSpacing = 2;
		displaySpacing = 1;
	}

	public Position getRealPos() {
		return realPos;
	}

	public void setPos(Position pos) {
		this.realPos = pos;
		this.displayPos = new Position(realPos);
		this.displayPos.setHeight(this.displayPos.getHeight() / 2);
		this.displayPos.setLength(this.displayPos.getLength() / 2);
		this.displayPos.setWidth(this.displayPos.getWidth() / 2);
	}
	
	public void draw(int cellWidth, int cellHeight, GC gc) {
		gc.drawImage(img, 0, 0, img.getBounds().width, img.getBounds().height, 
				cellWidth * displayPos.getWidth() + 15, cellHeight * displayPos.getLength() + 15, cellWidth - 30, cellHeight - 30);
	}
	
	public void moveRight() {
		realPos.setWidth(realPos.getWidth() + realSpacing);
		displayPos.setWidth(displayPos.getWidth() + displaySpacing);
		System.out.println("move right");
	}
	
	public void moveLeft() {
		realPos.setWidth(realPos.getWidth() - realSpacing);
		displayPos.setWidth(displayPos.getWidth() - displaySpacing);
		System.out.println("move left");
	}
	
	public void moveFarward(){
		realPos.setLength(realPos.getLength() - realSpacing);
		displayPos.setLength(displayPos.getLength() - displaySpacing);
		System.out.println("move farward");
	}
	
	public void moveBackward(){
		realPos.setLength(realPos.getLength() + realSpacing);
		displayPos.setLength(displayPos.getLength() + displaySpacing);
		System.out.println("move backward");
	}
	
	public void moveUp(){
		realPos.setHeight(realPos.getHeight() + realSpacing);
		displayPos.setHeight(displayPos.getHeight() + displaySpacing);
		System.out.println("move up");
	}
	
	public void moveDown(){
		realPos.setHeight(realPos.getHeight() - realSpacing);
		displayPos.setHeight(displayPos.getHeight() - displaySpacing);
		System.out.println("move down");
	}
	

}
