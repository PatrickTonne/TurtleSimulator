package model;

import java.util.Observable;
import java.util.Observer;

import controller.SimulationController;

public class Simulation extends Thread implements Observer{
	private Territorium territorium;
	private SimulationController simulationController;
	private boolean stop;
	private boolean pause;
	Object syncObject;

	public Simulation(Territorium ter) {
		territorium = ter;
		
	}
	@Override
	public void run() {
		territorium.addObserver(this);
		try {
			territorium.getTurtle().main();
			
		} catch (Exception e) {
		}finally {
			territorium.deleteObserver(this);
		//	simulationController.ended();
			
		}
		
	}



	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}}
