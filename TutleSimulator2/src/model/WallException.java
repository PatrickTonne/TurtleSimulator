package model;

import java.net.URL;


import javafx.scene.media.AudioClip;

public class WallException extends RuntimeException{
	URL resource = getClass().getResource("media/death.mp3");
	AudioClip clip = new AudioClip(resource.toString());
	
	
	public WallException() {
		clip.play();
	}
}
