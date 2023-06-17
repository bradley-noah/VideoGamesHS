/*
 * The purpose of this class is to play a variety of sound bytes relevant 
 * to the MarioGame 
 */
import java.io.*;
	import java.net.URL;
	import javax.sound.sampled.*;
	import javax.swing.*;
	   


public class Music{

	private Clip clip;
	
	public void halt() {
		clip.stop();
	}
	// To play sound using Clip, the process need to be alive.
	// Hence, we use a Swing application.

	   
	
	   // Constructor
	   public Music(int n) {
	      //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      //this.setTitle("Test Sound Clip");
	      //this.setSize(300, 200);
	      //this.setVisible(true);
	   
	      try {

	    	  if(n == 1) {
	    		// Open an audio input stream.
	    		 URL url = this.getClass().getClassLoader().getResource("overworld.mid");
	    		 AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
	    		// Get a sound clip resource.
	 	         clip = AudioSystem.getClip();
	 	        // Open audio clip and load samples from the audio input stream.
	 	         clip.open(audioIn);
	 	         clip.loop(10);
	    	  }
	    	  else if (n == 2) {
	    		 URL url = this.getClass().getClassLoader().getResource("gameover.mid");
	    		 AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
	    		// Get a sound clip resource.
	 	         clip = AudioSystem.getClip();
	 	        // Open audio clip and load samples from the audio input stream.
	 	         clip.open(audioIn);
	 	         clip.loop(0);
	    	  }
	    	  else if (n == 3) {
		    	 URL url = this.getClass().getClassLoader().getResource("ending.mid");
		    	 AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
		    	// Get a sound clip resource.
		 	     clip = AudioSystem.getClip();
		        // Open audio clip and load samples from the audio input stream.
		         clip.open(audioIn);
	 	         clip.loop(0);
		      }
	    	  else if (n == 4) {
		    	 URL url = this.getClass().getClassLoader().getResource("koopakidsvictory.mid");
		    	 AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
		   		// Get a sound clip resource.
		 	     clip = AudioSystem.getClip();
		        // Open audio clip and load samples from the audio input stream.
		         clip.open(audioIn);
	 	         clip.loop(0);
	 	      }
	    	  else if (n == 5) {
			    	 URL url = this.getClass().getClassLoader().getResource("coin2.wav");
			    	 AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
			   		// Get a sound clip resource.
			 	     clip = AudioSystem.getClip();
			        // Open audio clip and load samples from the audio input stream.
			         clip.open(audioIn);
		 	         clip.loop(0);
		 	      }
	    	  else if (n == 6) {
			    	 URL url = this.getClass().getClassLoader().getResource("jump.wav");
			    	 AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
			   		// Get a sound clip resource.
			 	     clip = AudioSystem.getClip();
			        // Open audio clip and load samples from the audio input stream.
			         clip.open(audioIn);
		 	         clip.loop(0);
		 	      }
	    	  
	      } catch (UnsupportedAudioFileException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (LineUnavailableException e) {
	         e.printStackTrace();
	      }
	      
	   }
	   
	   //public static void main(String[] args) {
	   //   new Music();
	   //}
	
	
}
