/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musicplayer.bll;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TJ
 */
public class UserRepository {

    private List<UserAccount> users;

    public UserRepository() {
        users = new ArrayList<UserAccount>();
        addUser(new UserAccount("TurkeyJoe", "$tj1982", "a@b.com"));
    }

    public void addUser(UserAccount user) {
        users.add(user);
    }

    public int userCount() {
        return users.size();
    }

    public UserAccount getUser(String name) throws Exception {
        for (UserAccount user : users) {
            if (user.username().equals(name)) {
                return user;
            }
        }
        throw new Exception("User does not exist");
    }
    
    public boolean checkForUsername(String name){
        for (UserAccount a : users){
            if (a.username().equals(name)){
                return false;
            }
        }
        return true;
    }
}
