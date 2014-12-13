package songplayer;

/**
 * This one method interface allows any new type to become 
 * registered as a listener to end of song event.  
 * 
 * @author Rick Mercer
 */

public interface EndOfSongListener {
  
  // The listener will have this method called with the name
  // of the file that just finished playing. Do what you want
  // at that time. Note: you may not need audioFileName.
  //
  // Precondition: audioFileName is fully qualified and exists
  // on the file system where it is claimed to be
  public void songFinishedPlaying(EndOfSongEvent eventWithFileNameAndDateFinished);
}