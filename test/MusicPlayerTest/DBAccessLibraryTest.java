/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MusicPlayerTest;

import TitanPlayer.util.HibernateUtil;
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
        String queryName = "select distinct lib.userid from LibraryDBRecord lib";
        
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
        String queryName = "select distinct lib.userid from LibraryDBRecord lib";
        String querySong = "select lib.songName from LibraryDBRecord lib";
        List nameList = null;
        List songList = null;
        
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
                org.hibernate.Query qN = session.createQuery(queryName);       
                nameList = qN.list();
                
            //System.out.println("Number of names in library = " + nameList.size());
            
            for(int i = 0; i < nameList.size();i++){
                org.hibernate.Query qS = session.createQuery(querySong + " where lib.userid = '"+nameList.get(i)+"'");       
                songList = qS.list();
                
                if(i == 0){
                    assertEquals(2,songList.size());
                }
                //System.out.println("Number of songs for user = " + songList.size());
                //System.out.println(nameList.get(i)+", "+songList.get(0));
                
                /*for(int s=0;s<songList.size();s++){
                    //System.out.println("s = "+s);
                    org.hibernate.Query qA = session.createQuery(queryArtist + " where lib.userid = '"+nameList.get(i)+"'"+" and lib.songName = '"+songList.get(s)+"'");       
                    artistList = qA.list();
                    org.hibernate.Query qP = session.createQuery(queryPath + " where lib.userid = '"+nameList.get(i)+"'"+" and lib.songName = '"+songList.get(s)+"'");       
                    pathList = qP.list();
                    
                    System.out.println(nameList.get(i)+", "+songList.get(0)+", "+artistList.get(0)+", "+pathList.get(0));
                }
                * 
                */
                
            }

            session.getTransaction().commit();

        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }
}
