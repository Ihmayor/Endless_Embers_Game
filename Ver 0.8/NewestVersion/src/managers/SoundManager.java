package managers;
	import java.io.IOException;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.util.ResourceLoader;

//////
// SoundManager
// Purpose: Manages the sounds in the game
// Limit: ?
//////
public class SoundManager {
	 
		/** The wav sound effect */
		private static Audio wavEffect;
	    public SoundManager(String filename) {
	 
	        try {
	 
		    //load Wav sound
		    wavEffect = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(filename));
			wavEffect.playAsMusic(1.0f,1.0f, true);  
	        } catch (IOException e) {
		    e.printStackTrace();
		}
	    	SoundStore.get().poll(0);
			
	    }
	 
	    // Updates the sound state?
		public void update() {
			wavEffect.playAsMusic(1.0f,1.0f, true);  
			SoundStore.get().poll(0);
		}
		
		// changes to a different sound
		public static void changeSound (String filename){
			try{
			wavEffect = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(filename));
			wavEffect.playAsMusic(1.0f,1.0f, true);  
			}
			catch (IOException e){
				e.printStackTrace();
			}
		}
		
		// Plays a sound effect when something happens (an attack is made, etc)
		public static void playSoundEffect(String filename){
			try{
				wavEffect = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(filename));
				wavEffect.playAsSoundEffect(1.0f,1.0f, false);  	
			}
			catch (IOException e){
				e.printStackTrace();
			}

		}
	 
		
		public static void lowerVolume (){
			
			
		}
		
}

