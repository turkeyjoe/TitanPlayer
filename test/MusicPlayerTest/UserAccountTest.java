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
import org.junit.Rule;
import org.junit.rules.ExpectedException;

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
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void createUserAccountTest(){
        UserAccount testAccount = new UserAccount("TurkeyJoe", "$tj1982", "thurman8r82@hotmail.com");
        assertEquals("TurkeyJoe", testAccount.username());
        assertEquals("$tj1982", testAccount.password());
        assertEquals("thurman8r82@hotmail.com", testAccount.email());
    }
    
    @Test
    public void createAccountPasswordException(){
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid Password");
        new UserAccount("TurkeyJoe", "tj1982", "thurman8r82@hotmail.com");
    }
    
    @Test
    public void createAccountEmailException(){
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid Email Address");
        new UserAccount("TurkeyJoe", "$tj1982", "thurman8r82@hotmail.cm");
    }
    
    @Test
    public void accountsEqualTest(){
        UserAccount acct1 = new UserAccount("TJN", "$tj1982", "a@b.com");
        UserAccount acct2 = new UserAccount("TJN", "$tj1982", "a@b.com");
        UserAccount acct3 = new UserAccount("TJN", "$45lh", "b@c.com");
        assertTrue(acct1.equals(acct2));
        assertEquals(acct1.hashCode(), acct2.hashCode());
        assertTrue(acct1.equals(acct3));
    }
}