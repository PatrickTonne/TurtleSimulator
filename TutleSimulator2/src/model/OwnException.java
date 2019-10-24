package model;


import java.net.URL;

import javafx.scene.media.AudioClip;
public class OwnException extends RuntimeException{
	
	
	public OwnException() {
		System.out.println("ERROR");
		failSound();
	}

	public void failSound() {
		URL soundUrl = getClass().getResource("sound/deathsound.wav");
		
		AudioClip soundAudioClip = new AudioClip(soundUrl.toString());
		soundAudioClip.play();
	}

}
