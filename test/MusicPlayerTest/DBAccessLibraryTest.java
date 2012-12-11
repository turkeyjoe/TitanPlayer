/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MusicPlayerTest;

import TitanPlayer.util.HibernateUtil;
import com.musicplayer.bll.LibraryDBRecord;
import com.musicplayer.bll.LibraryRepository;
import com.musicplayer.bll.Song;
import com.musicplayer.bll.UserAccount;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Rocky
 */
public class DBAccessLibraryTest {
    
    public DBAccessLibraryTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void numOfUsersInLibTest(){
        String queryName = "select distinct lib.userId from LibraryDBRecord lib";
        
        List nameList = null;
        
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
                org.hibernate.Query qN = session.createQuery(queryName);       
                nameList = qN.list();

            session.getTransaction().commit();

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        assertEquals(2,nameList.size());
    }
    
    @Test
    public void numOfSongsPerUserTest(){
        String queryName = "select distinct lib.userId from LibraryDBRecord lib";
        String querySong = "select lib.songName from LibraryDBRecord lib";
        List nameList = null;
        List songList = null;
        
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
                org.hibernate.Query qN = session.createQuery(queryName);       
                nameList = qN.list();
            
            for(int i = 0; i < nameList.size();i++){
                org.hibernate.Query qS = session.createQuery(querySong + " where lib.userId = '"+nameList.get(i)+"'");       
                songList = qS.list();
                
                if(i == 0){
                    assertEquals(2,songList.size());
                }
            }

            session.getTransaction().commit();

        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }
    
    @Test
    public void addSongToLibTableTest() {
        LibraryRepository repo = new LibraryRepository();
        Path songPath = Paths.get("c:\\mymusic\\Poison\\NuthinButAGoodTime.mp3");
        Song newSong = new Song("Nuthin But A Good Time", "Poison", songPath);
        String dbSongPath = newSong.filePath().toString();
        
        Boolean addWasSuccess = false;

        String userid = "TRock5150";
        LibraryDBRecord libRec = new LibraryDBRecord(userid, newSong.title(), newSong.artist(), dbSongPath);
        
        //System.out.println("Branching to addSong method:");
        addWasSuccess = libRec.addSong(libRec);
        //System.out.println("Back from addSong method:");
        
    }
}
