/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musicplayer.bll;

import TitanPlayer.util.HibernateUtil;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Kyle
 */
public class LibraryRepository {
    private ArrayList<Library> libraries = new ArrayList<Library>();
    
    public LibraryRepository() {
        libraries = loadLibrary();
    }
        
    public void addLibrary(Library libraryToAdd) {
        libraries.add(libraryToAdd);
    }
    
    public int libraryCount() {
        return libraries.size();
    }
    
    public Library getUserLibrary(UserAccount user) {
        Library userLibrary = new Library();
        System.out.println("Size of Overall Library = " + libraries.size());
        for (Library l : libraries) {
            if (l.getUser().equals(user)) {
                System.out.println("Found User in Library!");
                userLibrary = l;
                return userLibrary;
            }
        }
        userLibrary.addUser(user);
        libraries.add(userLibrary);
        return userLibrary;
    }

    private ArrayList<Library> loadLibrary() {
        String libUsersListQuery = "select distinct lib.userId from LibraryDBRecord lib";
        
        libraries = getAllSongsHQLQuery(libUsersListQuery);
        
        return libraries;
    }

    private ArrayList<Library> getAllSongsHQLQuery(String libUsersListQuery) {
        UserRepository userList = new UserRepository();
        Library currentLibrary = new Library();
        ArrayList<Library> allLibraries = new ArrayList<Library>();
        
        String querySong = "select lib.songName from LibraryDBRecord lib";
        String queryArtist = "select lib.songArtist from LibraryDBRecord lib";
        String queryPath = "select lib.songPath from LibraryDBRecord lib";
        String subQuery1 = " where lib.userId = '";
        String subQuery2 = " and lib.songName = '";
        
        List nameList = null;
        List songList = null;
        List artistList = null;
        List pathList = null;
        
        UserAccount currentUser = null;
        
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
                org.hibernate.Query qN = session.createQuery(libUsersListQuery);       
                nameList = qN.list();
                
                for(int i = 0; i < nameList.size(); i++){
                    currentLibrary = new Library();
                    String currentName = (String) nameList.get(i);

                    try {
                        currentUser = userList.getUser(currentName);
                    } catch (Exception ex) {
                        Logger.getLogger(LibraryRepository.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    org.hibernate.Query qS = session.createQuery(querySong + subQuery1 + nameList.get(i)+"'");       
                    songList = qS.list();

                    for(int s = 0; s < songList.size(); s++){
                        org.hibernate.Query qA = session.createQuery(queryArtist + subQuery1 + nameList.get(i) + "'" + subQuery2 + songList.get(s) + "'");       
                        artistList = qA.list();
                        
                        org.hibernate.Query qP = session.createQuery(queryPath + subQuery1 + nameList.get(i) + "'" + subQuery2 + songList.get(s) + "'");       
                        pathList = qP.list();
                        
                        String libSongName = (String) songList.get(s);
                        String libSongArtist = (String) artistList.get(0);
                        String libSongPath = (String) pathList.get(0);
                        Path libSong = Paths.get(libSongPath);
                        
                        Song addToLib = new Song(libSongName, libSongArtist, libSong);

                        try {
                            currentLibrary.addSong(addToLib, "INIT");
                        } catch (Exception ex) {
                            Logger.getLogger(LibraryRepository.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    currentLibrary.addUser(currentUser);
                    allLibraries.add(currentLibrary);
                    
                }
                
                session.getTransaction().commit();

        } catch (HibernateException he) {
            he.printStackTrace();
        }

        return allLibraries;
    }
}
