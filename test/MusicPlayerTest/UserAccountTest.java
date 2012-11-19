/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MusicPlayerTest;

import com.musicplayer.bll.UserAccount;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author owner
 */
public class UserAccountTest {
    
    public UserAccountTest() {
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
    public void createUserAccountTest(){
        UserAccount testAccount = new UserAccount("TurkeyJoe", "tj1982", "thurman8r82@hotmail.com");
        assertEquals("TurkeyJoe", testAccount.username());
        assertEquals("tj1982", testAccount.password());
        assertEquals("thurman8r82@hotmail.com", testAccount.email());

    }
    
    @Test
    public void validatePasswordTest(){
        assertTrue(UserAccount.validatePassword("$tj1982"));
        assertFalse(UserAccount.validatePassword("1982"));
        assertFalse(UserAccount.validatePassword("tjnoblit"));
        assertFalse(UserAccount.validatePassword("$!tjn"));
        assertFalse(UserAccount.validatePassword("19$82"));
        assertTrue(UserAccount.validatePassword("TJ-1982"));
    }
    
    @Test
    public void validateEmailTest(){
        assertTrue(UserAccount.validateEmail("thurman8r82@hotmail.com"));
    }
}