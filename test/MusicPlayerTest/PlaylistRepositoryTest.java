/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MusicPlayerTest;

import com.musicplayer.bll.Playlist;
import com.musicplayer.bll.PlaylistRepository;
import com.musicplayer.bll.UserAccount;
import java.util.List;
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
public class PlaylistRepositoryTest {
    
    public PlaylistRepositoryTest() {
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
    public void createRepositoryTest(){
        PlaylistRepository repo = new PlaylistRepository();
        assertEquals(0, repo.size());
    }
    
    @Test
    public void addPlaylistToRepositoryTest(){
        PlaylistRepository repo = new PlaylistRepository();
        Playlist list = new Playlist("Test");
        repo.addPlaylist(list);
        assertEquals(1, repo.size());
    }
    
    @Test
    public void getPlaylistTest(){
        PlaylistRepository repo = new PlaylistRepository();
        Playlist list = new Playlist("Test");
        UserAccount user = new UserAccount("TJN", "#3l", "a@b.com");
        list.addUser(user);
        repo.addPlaylist(list);
        List<Playlist> userLists = repo.getUserPlaylists(user);
        assertSame(list, userLists.get(0));
    }
    
    @Test
    public void deletePlaylistTest(){
        PlaylistRepository repo = new PlaylistRepository();
        Playlist list = new Playlist("Test");
        UserAccount user = new UserAccount("TJN", "#3l", "a@b.com");
        list.addUser(user);
        repo.addPlaylist(list);
        assertEquals(1, repo.size());
        repo.deletePlaylist(user, "Test");
        assertEquals(0, repo.size());
    }
}
