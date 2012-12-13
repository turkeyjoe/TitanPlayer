/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musicplayer.bll;

import TitanPlayer.util.HibernateUtil;
import com.musicplayer.exceptions.UserNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author TJ
 */
public class UserRepository {

    private ArrayList<UserAccount> users = new ArrayList<>();

    public UserRepository() {
        users = loadUserRepo();
    }

    public void addUser(UserAccount user) {
        users.add(user);
        addUserToDatabase(user);
    }

    public int userCount() {
        return users.size();
    }

    public UserAccount getUser(String name) throws UserNotFoundException {
        for (UserAccount user : users) {
            if (user.username().equals(name)) {
                return user;
            }
        }
        throw new UserNotFoundException("User not found in database.");
    }
    
    public boolean checkForUsername(String name){
        for (UserAccount a : users){
            if (a.username().equals(name)){
                return false;
            }
        }
        return true;
    }
    
    private ArrayList<UserAccount> loadUserRepo() {
        String loadUsersQuery = "select users.username from UserAccount users";
        
        users = getAllUsersHQLQuery(loadUsersQuery);
        return users;
    }
    
    private ArrayList<UserAccount> getAllUsersHQLQuery(String hql) {
        String queryEmail = "select users.email from UserAccount users";
        String queryPassword = "select users.password from UserAccount users";
        List nameList = null;
        List emailList = null;
        List passwordList = null;
        ArrayList<UserAccount> userList = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
                org.hibernate.Query q = session.createQuery(hql);
                
                nameList = q.list();
            
            for (int i = 0; i < nameList.size(); i++) {
                    org.hibernate.Query qE = session.createQuery(queryEmail + " where users.username = '"+nameList.get(i)+"'");       
                    emailList = qE.list();
                    
                    org.hibernate.Query qP = session.createQuery(queryPassword + " where users.username = '"+nameList.get(i)+"'");       
                    passwordList = qP.list();
                    
                    String userName = (String) nameList.get(i);
                    String password = (String) passwordList.get(0);
                    String email = (String) emailList.get(0);
                    
                    UserAccount newUser = new UserAccount(userName, password, email);
                    userList.add(newUser);
                }
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return userList;
    }

    private void addUserToDatabase(UserAccount user) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();
            session.persist(user);
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }
}
