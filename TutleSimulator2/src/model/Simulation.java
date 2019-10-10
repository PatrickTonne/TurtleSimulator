package model;

import util.Observable;
import util.Observer;

import controller.PlayState;
import controller.SimulationController;

public class Simulation extends Thread implements Observer {
	private Territorium territorium;
	private SimulationController simulationController;
	Object syncObject;

	public Simulation(Territorium ter, SimulationController sim, Object obj) {
		syncObject = obj;
		territorium = ter;
		simulationController = sim;

	}

	@Override
	public void run() {
		territorium.addObserver(this);
		try {
			territorium.getTurtle().main();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			territorium.deleteObserver(this);
			simulationController.end();

		}

	}

	@Override
	public void update() {
		try {

			if (simulationController.getPlayState().getState() == 2) {
				synchronized (this) {
					this.wait();
				}
			} else {
				Thread.sleep(1000 - (simulationController.getSpeed() * 10));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
