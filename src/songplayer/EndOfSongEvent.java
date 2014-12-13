package songplayer;

/**
 * A new one of these objects is passed to the listener to end of song events.
 * Each EndOfSong event remembers the file name of and the time it finished.
 * 
 */

import java.util.GregorianCalendar;

public class EndOfSongEvent {

  private String fileName;
  private GregorianCalendar currentTime;
  
  /**
   * Construct a new EndOfSongEvent with the file name just finished 
   * playing and the time at which the song finished playing
   * 
   * @param fileName  The song that just finished playing
   * @param currentTime  The moment at which the song finished playing
   */
  public EndOfSongEvent(String fileName, GregorianCalendar currentTime) {
     this.fileName = fileName; 
     this.currentTime = currentTime;
  }
  
  /**
   * Provide access to the name of the audio file that just finished.
   * @return the name of the file sent to the SongPlayer
   */
  public String fileName() {
    return fileName;
  }
  
  /**
   * Provide access to time of this EndOfSongEvent
   * @return the name of the file sent to the SongPlayer
   */
  public GregorianCalendar finishedTime() {
    return currentTime;
  }
}