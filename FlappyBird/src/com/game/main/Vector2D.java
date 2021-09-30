package com.game.main;

public class Vector2D {
	int x; 
	int y;
	public Vector2D(int x, int y){
		 this.x = x;
		 this.y = y;
	}
	public Vector2D() {
		this.x = 0;
		this.y = 0;
	}
	public void nullate() {
		this.x = 0;
		this.y = 0;

	}
	public void addC(int x) {
		this.x += x;
	}
	public void addY(int y) {
		this.y += y;
	}
	public void add(int x, int y) {
		this.x += x;
		this.y += y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	
	
	
}
