/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MusicPlayerTest;

import TitanPlayer.util.HibernateUtil;
import com.musicplayer.bll.Playlist;
import com.musicplayer.bll.PlaylistRepository;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Rocky
 */
public class LoadPlaylistRepositoryTest {
    
    public LoadPlaylistRepositoryTest() {
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
    public void loadRepoTest(){
        ArrayList<Playlist> playlistRepo = new ArrayList<Playlist>();
        List plUsersList = null;
        List allPlList = null;
        List allPlSongsList = null;
        
        //playlistRepo = playlistRepo.loadPlaylistLib();
        
        String plUsersQuery = "select distinct pl.playlistUser from PlaylistDBRecord pl";
        
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
                org.hibernate.Query qU = session.createQuery(plUsersQuery);       
                plUsersList = qU.list();
            session.getTransaction().commit();
            
            if(plUsersList.isEmpty()){
                //return null;
            } else{
                
                for(int i = 0; i < plUsersList.size(); i++){
                    System.out.println(plUsersList.get(i));
                    String allPlQuery = "select distinct pl.playlistName from PlaylistDBRecord pl where pl.playlistUser = '"+plUsersList.get(i)+"'";
                    session = HibernateUtil.getSessionFactory().openSession();
                    session.beginTransaction();
                        org.hibernate.Query qP = session.createQuery(allPlQuery);       
                        allPlList = qP.list();
                    session.getTransaction().commit();
                    
                    for(int p = 0; p < allPlList.size();p++){
                        System.out.println(allPlList.get(p));
                        String allPlSongsQuery = "select distinct pl.librarySongNum from PlaylistDBRecord pl where pl.playlistUser = '"+plUsersList.get(i)+"'"
                                                    +" and pl.playlistName = '"+allPlList.get(p)+"'";
                        session = HibernateUtil.getSessionFactory().openSession();
                            session.beginTransaction();
                            org.hibernate.Query qS = session.createQuery(allPlSongsQuery);       
                        allPlSongsList = qS.list();
                        
                        for(int s=0; s < allPlSongsList.size();s++){
                            System.out.println(allPlSongsList.get(s));
                        }
                        
                    session.getTransaction().commit();
                    }
                    
                }
            }
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        
    }
}
