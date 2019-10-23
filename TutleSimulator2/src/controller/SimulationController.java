package controller;

import model.Simulation;
import model.Territorium;

public class SimulationController {
	//Das Simulationsverfahren ist in Zusammenarbeit mit Time Stein entstanden. Deshalb können hier Überschneidungen auftreten.

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
	@SuppressWarnings("deprecation")
	public void kill() {
		simulation.stop();
	}

	public PlayState getPlayState() {
		return playState;
	}

	public void resume() {
		synchronized (simulation) {
			simulation.setUnPause();
		}
		playState.setPlayState(0);

	}
	public void pause() {
		synchronized (simulation) {
			simulation.setPause();
		}
		playState.setPlayState(2);
	}

	public void end() {
		playState.setPlayState(PlayState.NOTRUNNING);
	}

}
