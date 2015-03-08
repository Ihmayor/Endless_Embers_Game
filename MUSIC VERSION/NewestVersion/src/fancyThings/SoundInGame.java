package fancyThings;
	import java.io.IOException;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.util.ResourceLoader;

public class SoundInGame {
	 
		/** The wav sound effect */
		private static Audio wavEffect;
	    public SoundInGame(String filename) {
	 
	        try {
	 
		    //load Wav sound
		    wavEffect = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(filename));
			wavEffect.playAsMusic(1.0f,1.0f, true);  
	        } catch (IOException e) {
		    e.printStackTrace();
		}
	    	SoundStore.get().poll(0);
			
	    }
	 
		public void update() {
			wavEffect.playAsMusic(1.0f,1.0f, true);  
			SoundStore.get().poll(0);
		}
		
		public static void changeSound (String filename){
			try{
			wavEffect = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(filename));
			wavEffect.playAsMusic(1.0f,1.0f, true);  
			}
			catch (IOException e){
				e.printStackTrace();
			}
		}
		
	 
	 
}

