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
}
