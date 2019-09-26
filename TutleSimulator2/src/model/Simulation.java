package model;

import java.util.Observable;
import java.util.Observer;

import controller.PlayState;
import controller.SimulationController;

public class Simulation extends Thread implements Observer{
	private Territorium territorium;
	private SimulationController simulationController;
	private boolean stop;
	private boolean pause;
	Object syncObject;

	public Simulation(Territorium ter, SimulationController sim) {
		territorium = ter;
		simulationController = sim;
		
	}
	@Override
	public void run() {
		territorium.addObserver(this);
		try {
			territorium.getTurtle().main();
			
			
		} catch (Exception e) {
		}finally {
			territorium.deleteObserver(this);
			simulationController.end();
			
		}
		
	}



	@Override
	public void update(Observable o, Object arg) {
		try {
			
			if(simulationController.getPlayState().getState() == 2) {
				this.wait();
			}
			else {
				Thread.sleep(1000-(simulationController.getSpeed()*10));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}}
