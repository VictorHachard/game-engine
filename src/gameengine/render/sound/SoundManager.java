package gameengine.render.sound;

import java.util.HashMap;
import java.util.Map;

import gameengine.constante.Constante;
import javafx.scene.media.AudioClip;

/**
 * 
 * @author AbsoluteHack
 *
 */
public class SoundManager {
	private static SoundManager INSTANCE = null;
	private Map<String,AudioClip> lstSound = new HashMap<>();
	
	public static SoundManager getSoundManager(){
		return INSTANCE;
	}
	
	public static SoundManager createSoundManager() {
		INSTANCE = new SoundManager();
		return INSTANCE;			
	}
	
	public SoundManager() {
	}
	public void add(String soundName,String nameOfFile) {
		AudioClip a = new AudioClip(this.getClass().getResource(Constante.SOUND_PATH+nameOfFile).toString());
		lstSound.put(soundName, a);
	}
	public void playSound(String soundName) {
		lstSound.get(soundName).play();
	}
	public void playSoundOnlyOneTime(String soundName) {
		if(!lstSound.get(soundName).isPlaying()) {
			lstSound.get(soundName).play();
		}
	}
	public void stopSound(String sound) {
		lstSound.get(sound).stop();
	}
}
