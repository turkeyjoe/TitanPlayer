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
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.title != null ? this.title.hashCode() : 0);
        hash = 83 * hash + (this.artist != null ? this.artist.hashCode() : 0);
        hash = 83 * hash + (this.filePath != null ? this.filePath.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Song other = (Song) obj;
        if ((this.title == null) ? (other.title != null) : !this.title.equals(other.title)) {
            return false;
        }
        if ((this.artist == null) ? (other.artist != null) : !this.artist.equals(other.artist)) {
            return false;
        }
        if (this.filePath != other.filePath && (this.filePath == null || !this.filePath.equals(other.filePath))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString(){
        return this.title + " - " + this.artist + " " + this.filePath;
    }
}
