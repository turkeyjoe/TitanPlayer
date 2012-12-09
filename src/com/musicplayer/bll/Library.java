/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musicplayer.bll;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author TJ
 */
public class Library {

    //private String user;
    private List<Song> songs;
    private UserAccount user;
    

    public Library() {
        songs = new ArrayList<Song>();
        //user = userId;
    }
    
    public int songCount() {
        return songs.size();
    }

    public void addSong(Song song) throws Exception {
        if (!songs.contains(song)) {
            songs.add(song);
            //addSongToDB(song, userID);
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

    //private void addSongToDB(Song song, String userID) {
        
    //}
}
