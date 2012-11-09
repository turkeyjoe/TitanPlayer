/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musicplayer.bll;

/**
 *
 * @author TJ
 */
public class Song {
    private String title;
    private String artist;
    
    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }  

    public String getTitle(){
        return title;
    }
    
    public String getArtist(){
        return artist;
    }
}
