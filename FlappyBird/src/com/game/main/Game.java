package com.game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Game extends Canvas implements Runnable{
	
	/**
	 *@author ludwigpramer
	 */
	private static final long serialVersionUID = 5420209024354289119L;
	private static final int X = 600;
	private static final int Y = 600;
	private static int count = 0;
	Handler handler;
	Thread t1;
	Window window;
	Player p;
	static boolean running;
	static boolean paused;
	private static long START_TIME = 0;
	private ArrayList<Obstacle> obstacles = new ArrayList<>();
	public Game() {
		START_TIME = System.nanoTime();
		handler = new Handler();
		p = new Player( 150, Y-(25+40), ID.Player, X, START_TIME, handler);
		handler.add(p);
		window = new Window(X, Y, "Hello", this, handler, p);
		
	}
	@Override public void run() {
		long now;
	    long updateTime;
	    long wait;

	    final int TARGET_FPS = 120;
	    final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
	    @SuppressWarnings("unused")
		long frameCounter = 0L;
	    while (running) {
	    	if(paused) break;
	        now = System.nanoTime();
	        
	        frameCounter++;
	        update();
	        render();
	        addObstacle();
	        //System.out.println("frame rendered");
	        updateTime = System.nanoTime() - now;
	        wait = (OPTIMAL_TIME - updateTime) / 1000000;
	        if(wait >= 0) {
	        try {
	            Thread.sleep(wait);
	        } catch (Exception e) {
	            e.printStackTrace();
	        	}
	        }
	    }
	    if(!paused) {
	    	try {
	    		stop();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    }
	}
	public synchronized void start() {
		t1 = new Thread(this);
		System.out.println("Starting up ...");
		running = true;
		t1.start();
	}
	public synchronized void stop() throws InterruptedException {
		running = false;
		System.out.println("Shutting down ...");
		
			t1.join();
			System.exit(0);
		
		
	}
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, X, Y);
		handler.render(g);
		p.render(g);
		g.dispose();
		//System.out.println("hal");
		bs.show();
	}
	public void update() {
		handler.update();
		for(Obstacle o : obstacles ) {
			if(p.collideWith(o) && !p.dead) {
				p.kill();
				System.out.println("DEAD");
			}
		}
		
	}
	public void addObstacle() {

		count++;
		if(((int)(Math.random()*1000)) < 8 && count >= 240 && !p.isDead()) {
			int gap =((int) (Math.random()*600));
			Obstacle tempObstacle = new Obstacle(X-(400 - gap/2)+20, handler);
			Obstacle tempObstacle2 = new Obstacle(-(400 - gap/2), handler);
			handler.add(tempObstacle, tempObstacle2);
			obstacles.add(tempObstacle); obstacles.add(tempObstacle2);
			count = 0;
		}
	}
	public static void pause() {
		paused = !paused;
	}
}
