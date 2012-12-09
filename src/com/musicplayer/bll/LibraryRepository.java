/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musicplayer.bll;

import TitanPlayer.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Kyle
 */
public class LibraryRepository {
    List<Library> libraries;
    
    public LibraryRepository() {
        libraries = new ArrayList<Library>(loadLibraryRepo());
        
    }
    
    public void addLibrary(Library libraryToAdd) {
        libraries.add(libraryToAdd);
    }
    
    public int libraryCount() {
        return libraries.size();
    }
    
    public Library getSongLibrary(UserAccount user) {
        Library userLibrary = new Library();
        for (Library l : libraries) {
            if (l.getUser() == user) {
                userLibrary = l;
                return userLibrary;
            }
        }
        userLibrary.addUser(user);
        libraries.add(userLibrary);
        return userLibrary;
    }
    
    private ArrayList loadLibraryRepo() {
        String loadLibraryQuery = "select lib.username from UserAccount users";
        ArrayList<Song> libraries = new ArrayList();
        libraries = getAllSongsQuery(loadLibraryQuery);
        return libraries;
    }
    
    private ArrayList getAllSongsQuery(String hql) {
        //String queryUserLibs = "select lib.username from Library lib";
        String querySongName = "select lib.songName from Library lib where lib.user = ";
        String querySongArtist = "select lib.songArtist from Library lib where lib.user = ";
        String querySongPath = "select lib.songPath from Library lib where lib.user = ";
        
        List users = null;
        List songs = null;
        List artists = null;
        List paths = null;
        //List emailList = null;
        //List passwordList = null;
        ArrayList<Library> userList = new ArrayList();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
                org.hibernate.Query q = session.createQuery(hql);
                
                users = q.list();
            
            for (int i = 0; i < users.size(); i++) {
                    String userName = (String) users.get(i);
                    org.hibernate.Query qS = session.createQuery(querySongName + " '"+userName+"'");       
                    songs = qS.list();
                    
                    for (int a = 0; a < songs.size(); a++){
                        String title = (String) songs.get(a);
                        org.hibernate.Query qA = session.createQuery(querySongArtist + " '"+userName+"' and lib.songName = '"+title+"'");       
                        artists = qA.list();
                        
                        org.hibernate.Query qP = session.createQuery(querySongPath + " '"+userName+"' and lib.songName = '"+title+"'");       
                        paths = qP.list();
                        
                        String artist = (String) artists.get(0);
                        String path = (String) paths.get(0);
                        
                        //Song newSong = new()
                        //Library newLib = new Library(userName, title, artist, path);
                    }
        
                    //String password = (String) passwordList.get(0);
                    //String email = (String) emailList.get(0);
                    
                    //Library newLib = new Library(userName, title, artist, path);
                    //userList.add(newUser);
                }
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return userList;
    }

    /*private void addUserToDatabase(UserAccount user) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();
            session.persist(user);
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }
    * 
    */
    
}