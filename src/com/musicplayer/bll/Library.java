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

    private List<Song> songs;

    public Library() {
        songs = new ArrayList<Song>();
    }

    public int songCount() {
        return songs.size();
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void removeSong(int index) {
        songs.remove(index);
    }

    public Song getSong(int i) {
        return songs.get(i);
    }

    public void sortByTitle() {
        Collections.sort(songs, new Comparator<Song>() {
            @Override
            public int compare(Song s1, Song s2) {
                return s1.getTitle().compareToIgnoreCase(s2.getTitle());
            }
        });
    }
    
        public void sortByArtist() {
        Collections.sort(songs, new Comparator<Song>() {
            @Override
            public int compare(Song s1, Song s2) {
                return s1.getArtist().compareToIgnoreCase(s2.getArtist());
            }
        });
    }
}
