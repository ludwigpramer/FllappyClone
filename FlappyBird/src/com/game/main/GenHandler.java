package com.game.main;

import java.util.ArrayList;

public class GenHandler {
	ArrayList<Obstacle> obstacles = new ArrayList<>();
	public GenHandler() {
		
	}
	public void add(Obstacle obstacle) {
		obstacles.add(obstacle);
	}
	public void remove(int index) {
		obstacles.remove(index);
	}
	public Obstacle get(int index) {
		return obstacles.get(index);
	}
}
