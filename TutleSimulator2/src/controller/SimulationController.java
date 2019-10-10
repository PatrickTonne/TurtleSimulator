package controller;

import model.Simulation;
import model.Territorium;

public class SimulationController {

	private volatile int speed;
	private PlayState playState;
	private Simulation simulation;
	private Object syncObject;

	public SimulationController(PlayState play) {
		this.playState = play;

	}

	public void run(Territorium ter) {
		if (playState.getState() == PlayState.NOTRUNNING) {
			start(ter);
		} else if (playState.getState() == PlayState.PAUSED) {
			System.out.println("RESUME");
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
		syncObject = new Object();
		simulation = new Simulation(ter, this, syncObject);
		simulation.start();

	}

	public PlayState getPlayState() {
		return playState;
	}

	public void resume() {
		playState.setPlayState(playState.RUNNING);
		syncObject.notifyAll();

	}

	public void end() {
		System.out.println("stop it in");
		playState.setPlayState(PlayState.NOTRUNNING);
		simulation.interrupt();
		
	}

}
