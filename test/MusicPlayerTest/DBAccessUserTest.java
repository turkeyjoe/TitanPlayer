/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MusicPlayerTest;

import com.musicplayer.bll.UserAccount;
import java.util.Iterator;
import java.util.List;
import TitanPlayer.util.HibernateUtil;
import java.sql.ResultSet;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Rocky
 */
public class DBAccessUserTest {
    
    public DBAccessUserTest() {
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
    public void readUserFromDB() {
        //String query = "select users.username, users.email from UserAccount users";
        String queryName = "select users.username from UserAccount users";
        String queryEmail = "select users.email from UserAccount users";
        String queryPassword = "select users.password from UserAccount users";
        
        List nameList = null;
        List emailList = null;
        List passwordList = null;
        //List users = null;
        
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
                org.hibernate.Query qN = session.createQuery(queryName);       
                nameList = qN.list();
                
                for (int i = 0; i < nameList.size(); i++) {
                    org.hibernate.Query qE = session.createQuery(queryEmail + " where users.username = '"+nameList.get(i)+"'");       
                    emailList = qE.list();
                    
                    org.hibernate.Query qP = session.createQuery(queryPassword + " where users.username = '"+nameList.get(i)+"'");       
                    passwordList = qP.list();
                    
                    assertEquals(3,nameList.size());
                    //System.out.println(nameList.get(i)+", "+emailList.get(0)+", "+passwordList.get(0));
                }

            session.getTransaction().commit();

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        
    }
}
