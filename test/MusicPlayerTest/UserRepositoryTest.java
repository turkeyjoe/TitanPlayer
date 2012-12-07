/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MusicPlayerTest;

import com.musicplayer.bll.UserAccount;
import com.musicplayer.bll.UserRepository;
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
public class UserRepositoryTest {
    
    public UserRepositoryTest() {
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
    public void populateRepoTest() throws Exception{
        UserRepository repo = new UserRepository();
        assertEquals(2, repo.userCount());
    }
    
    @Test
    public void addUserToRepoTest() throws Exception{
        UserRepository repo = new UserRepository();
        UserAccount acct = new UserAccount("TJN", "$tj82", "a@b.com");
        repo.addUser(acct);
        assertEquals(3, repo.userCount());
    }
}
