package com.game.main;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;

public class Obstacle extends GameObject {
	/*
	 * @author ludwigpramer
	 */
	private static long speed = 1L;
	private static final int X = 600; 
	public Obstacle( int y, Handler handler) {
		super(X, y, ID.Obstacle, handler);
		this.width = 100;
		this.height = 400;
		this.rect = new Rectangle(x, y, width, height);
		//System.out.println(this.rect.toString());
		//System.out.printf("Initialized new Obstacle at %d\n", y);
	}

	
	@Override
	public void update() {
		changeRect(x, y, width, height);
		this.x -= speed;
		if(this.x <= -1) {
			this.kill();

			
		}

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
	}
	
	@Override
	public boolean collideWith(GameObject o) {
		return this.rect.intersects(o.rect);
		
	}


	@Override
	public void changeRect(int x, int y, int width, int height) {
		this.rect.x = x;
		this.rect.y = y;
		this.rect.width = width;
		this.rect.height = height;
		
	}
	



}
