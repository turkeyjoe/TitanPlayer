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

    public void removeSong(Song song) {
        songs.remove(song);
    }

    public Song getSong(int i) {
        return songs.get(i);
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
}
