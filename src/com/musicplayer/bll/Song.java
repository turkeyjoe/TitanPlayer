/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musicplayer.bll;

import java.nio.file.Path;

/**
 *
 * @author TJ
 */
public class Song {
    private String title;
    private String artist;
    private Path filePath;
    
    public Song(String title, String artist, Path path) {
        this.title = title;
        this.artist = artist;
        this.filePath = path;
    }  

    public String title(){
        return title;
    }
    
    public String artist(){
        return artist;
    }
    
    public Path filePath(){
        return filePath;
    }
    
    @Override
    public String toString(){
        return this.title + " - " + this.artist + " " + this.filePath;
    }
}
