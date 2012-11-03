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
public class Library {
    
    private List<Song> songs;
    
    public Library(){
        songs = new ArrayList<Song>();
    }
        
    public int songCount(){
        return songs.size();
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void removeSong(int index) {
        songs.remove(index);
    }
}
