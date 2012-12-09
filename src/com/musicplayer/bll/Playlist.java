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
public class Playlist {

    private List<Song> pl;
    private String name;
    private UserAccount user;
    
    public Playlist(String name){
        pl = new ArrayList<Song>();
        this.name = name;
    }
            
    public int songCount(){
        return pl.size();
    }
    
    public String getName(){
        return this.name;
    }
    
    public void addSong(Song song){
        pl.add(song);
    }
    
    public Song getSong(int i){
        return pl.get(i);
    }
    
    public Object[] getList(){
        return pl.toArray();
    }
    
    public void addUser(UserAccount user){
        this.user = user;
    }
    
    public UserAccount getUser(){
        return user;
    }
}
