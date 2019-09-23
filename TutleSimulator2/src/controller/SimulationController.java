package controller;

import model.Simulation;

public class SimulationController {

	private volatile int speed;
	private PlayState playState;
	private Simulation simulation;

	public void run() {
		if(playState.getPlayState() == playState.NOTRUNNING) {
			start();}
			else if(playState.getPlayState() == playState.PAUSED) {
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

	public void start() {
		//simulation = new Simulation(ter)
		

	}

	public void resume() {

	}

}
