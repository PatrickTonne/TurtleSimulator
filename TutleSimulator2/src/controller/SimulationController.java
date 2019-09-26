package controller;

import model.Simulation;
import model.Territorium;

public class SimulationController {

	private volatile int speed;
	private PlayState playState;
	private Simulation simulation;
	
	public SimulationController(PlayState play) {
		this.playState = play;
	}

	public void run(Territorium ter) {
		if (playState.getState() == PlayState.NOTRUNNING) {
			start(ter);
		} else if (playState.getState() == PlayState.PAUSED) {
			resume();
		}
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		System.out.println(speed);
		this.speed = speed;
	}

	public void start(Territorium ter) {
		simulation = new Simulation(ter, this);
		simulation.start();
		
	}
	public PlayState getPlayState() {
		return playState;
	}

	public void resume() {
		simulation.notify();

	}
	public void end() {
		simulation.stop();
		playState.setPlayState(PlayState.NOTRUNNING); 
	}

}
