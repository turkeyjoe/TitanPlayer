/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MusicPlayerTest;

import com.musicplayer.bll.Library;
import com.musicplayer.bll.Song;
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
        testLibrary.addSong(new Song("Dancing Nancies", "Dave Matthews Band"));
        assertEquals("song should be added to library", 1, testLibrary.songCount());
    }
    
    @Test
    public void removeSongFromLibraryTest(){
        Library testLibrary = new Library();
        testLibrary.addSong(new Song("Dancing Nancies", "Dave Matthews Band"));
        testLibrary.addSong(new Song("Turn The Page", "Bob Seger"));
        testLibrary.removeSong(0);
        assertEquals(1, testLibrary.songCount());
    }
}