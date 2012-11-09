/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MusicPlayerTest;

import com.musicplayer.bll.Library;
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
public class PlaylistTest {

    public PlaylistTest() {
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
    public void createPlaylistTest() {
        Playlist newList = new Playlist("My Songs");
        assertEquals("new empty playlist", 0, newList.songCount());
        assertEquals("playlist has a name", "My Songs", newList.getName());
    }

    @Test
    public void addSongToPlaylistTest() {
        Playlist pl = new Playlist("My Music");
        pl.addSong(new Song("Send The Pain Below", "Chevelle"));
        assertEquals(1, pl.songCount());
    }

    @Test
    public void addSongFromLibraryTest() {
        Library myLibrary = new Library();
        myLibrary.addSong(new Song("The Sign", "Ace of Base"));
        Playlist myPlaylist = new Playlist("Middle School");
        myPlaylist.addSong(myLibrary.getSong(0));
        assertEquals(1, myPlaylist.songCount());
    }
}
