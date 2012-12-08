/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

// Testing push with Github for this comment.
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
import org.junit.Rule;
import org.junit.rules.ExpectedException;

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
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Test
    public void newLibraryTest(){
        Library testLibrary = new Library();
        assertEquals("new library should be empty", 0, testLibrary.songCount());                
    }
    
    @Test
    public void addSongToLibraryTest() throws Exception{
        Library testLibrary = new Library();
        testLibrary.addSong(new Song("Dancing Nancies", "Dave Matthews Band", path));
        assertEquals("song should be added to library", 1, testLibrary.songCount());
    }
    
    @Test
    public void removeSongFromLibraryTest() throws Exception{
        Library testLibrary = new Library();
        testLibrary.addSong(new Song("Dancing Nancies", "Dave Matthews Band", path));
        testLibrary.addSong(new Song("Turn The Page", "Bob Seger", path));
        testLibrary.removeSong("Dave Matthews Band", "Dancing Nancies");
        assertEquals(1, testLibrary.songCount());
        assertEquals("Turn The Page", testLibrary.getSong("Bob Seger", "Turn The Page").title());
    }
        
    @Test
    public void sortByTitleTest() throws Exception{
        Library myLibrary = new Library();
        myLibrary.addSong(new Song("The Sign", "Ace of Base", path));
        myLibrary.addSong(new Song("Send The Pain Below", "Chevelle", path));
        myLibrary.addSong(new Song("Dancing Nancies", "Dave Matthews Band", path));
        assertEquals("The Sign", myLibrary.getSong("Ace of Base","The Sign").title());
        myLibrary.sortByTitle();
        assertEquals("Dancing Nancies", myLibrary.getSong("Dave Matthews Band", "Dancing Nancies").title());
    }
    
    @Test
    public void sortByArtistTest() throws Exception{
        Library myLibrary = new Library();
        myLibrary.addSong(new Song("Send The Pain Below", "Chevelle", path));
        myLibrary.addSong(new Song("The Sign", "Ace of Base", path));        
        myLibrary.addSong(new Song("Dancing Nancies", "Dave Matthews Band", path));
        assertEquals("Chevelle", myLibrary.getSong("Chevelle", "Send The Pain Below").artist());
        myLibrary.sortByArtist();
        assertEquals("Ace of Base", myLibrary.getSong("Ace of Base","The Sign").artist());
        //Song[] test = myLibrary.getSongs();
        //assertEquals(3, test.length);
    }
    
    @Test(expected = Exception.class)
    public void cantAddSongTwiceTest() throws Exception{
        Library myLibrary = new Library();
        myLibrary.addSong(new Song("Test", "Test", path));
        myLibrary.addSong(new Song("Test", "Test", path));
        assertEquals(1, myLibrary.songCount());        
    }
}
