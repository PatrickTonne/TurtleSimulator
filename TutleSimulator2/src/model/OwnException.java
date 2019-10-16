package model;


import java.net.URL;

import javafx.scene.media.AudioClip;
public class OwnException extends RuntimeException{
	
	
	public OwnException() {
		failSound();
	}

	public void failSound() {
		URL soundUrl = getClass().getResource("sound/deathsound.wav");
		
		AudioClip soundAudioClip = new AudioClip(soundUrl.toString());
		soundAudioClip.play(0.1);
	}

}
