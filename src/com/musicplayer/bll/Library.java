/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musicplayer.bll;

import TitanPlayer.util.HibernateUtil;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author TJ
 */
public class Library {

    private List<Song> songs;
    private UserAccount user;

    public Library() {
        songs = new ArrayList<Song>();
    }

    public int songCount() {
        return songs.size();
    }
            
    public void addSong(Song song, String init) throws Exception {
        if (!songs.contains(song)) {
            songs.add(song);
            if(!init.equalsIgnoreCase("INIT")){
                addSongToLibTable(song);
            }
        } else {
            throw new Exception("Song already in library");
        }
    }

    public void removeSong(String artist, String title) {
        songs.remove(getSong(artist, title));
    }

    public Song getSong(String artist, String title) {
        Song found = null;
        for (Song s : songs) {
            if (s.artist().equals(artist) && s.title().equals(title)) {
                found = s;
            }
        }
        return found;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void addUser(UserAccount user) {
        this.user = user;
    }

    public UserAccount getUser() {
        return user;
    }

    public void printLibrary() {
        for (Song s : songs) {
            System.out.println(s);
        }
    }

    public void sortByTitle() {
        Collections.sort(songs, new Comparator<Song>() {
            @Override
            public int compare(Song s1, Song s2) {
                return s1.title().compareToIgnoreCase(s2.title());
            }
        });
    }

    public void sortByArtist() {
        Collections.sort(songs, new Comparator<Song>() {
            @Override
            public int compare(Song s1, Song s2) {
                return s1.artist().compareToIgnoreCase(s2.artist());
            }
        });
    }
    
    private void addSongToLibTable(Song newSong) {
        String songName = newSong.title();
        String songArtist = newSong.artist();
        Path tmpPath = newSong.filePath();
        String songPath = tmpPath.toString();
        String userID = this.user.getUsername();
        
        LibraryDBRecord libRec = new LibraryDBRecord(userID, songName, songArtist, songPath);

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();
            session.persist(libRec);
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }
}
