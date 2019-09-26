package controller;

public class PlayState {
	
	static int RUNNING = 0;
	static int NOTRUNNING =1 ;
	static int PAUSED =2;
	
	public int playState = NOTRUNNING;
	
	public int getState() {
		return playState;
	}
	public void setPlayState(int playState) {
		this.playState = playState;
	}

}
