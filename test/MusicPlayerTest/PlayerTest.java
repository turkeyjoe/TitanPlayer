/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MusicPlayerTest;

import com.musicplayer.bll.Player;
import com.musicplayer.bll.Playlist;
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
public class PlayerTest {
    
    public PlayerTest() {
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
    public void createMusicPlayerTest(){
        Player myPlayer = new Player();
        Playlist myList = new Playlist("Test List");
        Song song1 = new Song("The Artist In The Ambulance", "Thrice");
        Song song2 = new Song("Song 2", "Blur");
        myList.addSong(song1);
        myList.addSong(song2);
        myPlayer.loadPlaylist(myList);
        assertEquals(song1, myPlayer.currentSong());
    }
    
    @Test
    public void skipSongTest(){
        Player myPlayer = new Player();
        Playlist myList = new Playlist("Test List");
        Song song1 = new Song("The Artist In The Ambulance", "Thrice");
        Song song2 = new Song("Song 2", "Blur");
        Song song3 = new Song("Hurt", "Johnny Cash");
        myList.addSong(song1);
        myList.addSong(song2);
        myList.addSong(song3);
        myPlayer.loadPlaylist(myList);
        myPlayer.nextSong();
        assertEquals(song2, myPlayer.currentSong());
        myPlayer.nextSong();
        assertEquals(song3, myPlayer.currentSong());
    }
    
    @Test
    public void stopSongTest(){
        Player myPlayer = new Player();
        Playlist myList = new Playlist("Test List");
        myList.addSong(new Song("Hound Dog", "Elvis Presley"));
        myPlayer.loadPlaylist(myList);
        assertTrue(myPlayer.isPlaying());
        myPlayer.stop();
        assertFalse(myPlayer.isPlaying());        
    }
}