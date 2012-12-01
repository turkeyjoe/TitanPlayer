/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MusicPlayerTest;

import com.musicplayer.bll.Library;
import com.musicplayer.bll.Song;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author TJ
 */
public class LibraryTest {
    Path path = Paths.get("c:\\path\\");
    
    public LibraryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {      
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void newLibraryTest(){
        Library testLibrary = new Library();
        assertEquals("new library should be empty", 0, testLibrary.songCount());                
    }
    
    @Test
    public void addSongToLibraryTest(){
        Library testLibrary = new Library();
        testLibrary.addSong(new Song("Dancing Nancies", "Dave Matthews Band", path));
        assertEquals("song should be added to library", 1, testLibrary.songCount());
    }
    
    @Test
    public void removeSongFromLibraryTest(){
        Library testLibrary = new Library();
        testLibrary.addSong(new Song("Dancing Nancies", "Dave Matthews Band", path));
        testLibrary.addSong(new Song("Turn The Page", "Bob Seger", path));
        testLibrary.removeSong(testLibrary.getSong(0));
        assertEquals(1, testLibrary.songCount());
        assertEquals("Turn The Page", testLibrary.getSong(0).title());
    }
        
    @Test
    public void sortByTitleTest(){
        Library myLibrary = new Library();
        myLibrary.addSong(new Song("The Sign", "Ace of Base", path));
        myLibrary.addSong(new Song("Send The Pain Below", "Chevelle", path));
        myLibrary.addSong(new Song("Dancing Nancies", "Dave Matthews Band", path));
        assertEquals("The Sign", myLibrary.getSong(0).title());
        myLibrary.sortByTitle();
        assertEquals("Dancing Nancies", myLibrary.getSong(0).title());
    }
    
    @Test
    public void sortByArtistTest(){
        Library myLibrary = new Library();
        myLibrary.addSong(new Song("Send The Pain Below", "Chevelle", path));
        myLibrary.addSong(new Song("The Sign", "Ace of Base", path));        
        myLibrary.addSong(new Song("Dancing Nancies", "Dave Matthews Band", path));
        assertEquals("Chevelle", myLibrary.getSong(0).artist());
        myLibrary.sortByArtist();
        assertEquals("Ace of Base", myLibrary.getSong(0).artist());
    }
}
