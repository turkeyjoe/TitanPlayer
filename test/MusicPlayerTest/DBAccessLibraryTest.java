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
import org.hibernate.Transaction;
import org.hibernate.hql.ast.util.SessionFactoryHelper;
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
    
    /*@Test
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
    * 
    */
    
    @Test
    public void deleteSongFromLibTest() {

        //LibraryDBRecord recToDelete = new LibraryDBRecord("TRock5150","Delete This Song","Delete","Delete");
        String queryID = "select lib.songNum from LibraryDBRecord lib where lib.userId = 'TRock5150' and lib.songName = 'Delete This Song' and lib.songArtist = 'Delete'";
        List resultID = null;
        int songID = 0;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
                org.hibernate.Query qN = session.createQuery(queryID);       
                resultID = qN.list();
                
                if(!resultID.isEmpty()){
                    songID = (Integer)resultID.get(0);
                    System.out.println(songID);
                }
                
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        
        LibraryDBRecord delRec = new LibraryDBRecord(songID,"TRock5150","Delete This Song","Delete","Delete");
        
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            delRec.setSongNum(songID);
            session.delete(delRec);
            session.flush();
            session.getTransaction().commit();

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        
        try {
            String hql = "SELECT lib.songNum from LibraryDBRecord lib where lib.userId = 'TRock5150' and lib.songName = 'Delete'";
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            org.hibernate.Query qN = session.createQuery(hql);       
                resultID = qN.list();
                //if(resultID.isEmpty())
                
                //String strsongID = (String)resultID.get(0);
            assertTrue(resultID.isEmpty());
        } catch (HibernateException he) {
            he.printStackTrace();
        }

    }
}
