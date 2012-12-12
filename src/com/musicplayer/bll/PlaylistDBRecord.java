/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musicplayer.bll;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Rocky
 */
public class PlaylistDBRecord {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLAYLIST_SONG_NUMBER", unique = true)
    private int playlistSongNum;
    
    private String playlistUser;
    private String playlistName;
    private int librarySongNum;

    public PlaylistDBRecord(String user, String playlistName, int librarySongNum) {
        this.playlistUser = user;
        this.playlistName = playlistName;
        this.librarySongNum = librarySongNum;
    }
    
    public PlaylistDBRecord(int playlistSongNum, String user, String playlistName, int librarySongNum) {
        this.playlistSongNum = playlistSongNum;
        this.playlistUser = user;
        this.playlistName = playlistName;
        this.librarySongNum = librarySongNum;
    }

    public int getLibrarySongNum() {
        return librarySongNum;
    }

    public void setLibrarySongNum(int librarySongNum) {
        this.librarySongNum = librarySongNum;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public int getPlaylistSongNum() {
        return playlistSongNum;
    }

    public void setPlaylistSongNum(int playlistSongNum) {
        this.playlistSongNum = playlistSongNum;
    }

    public String getPlaylistUser() {
        return playlistUser;
    }

    public void setPlaylistUser(String playlistUser) {
        this.playlistUser = playlistUser;
    }
    
    
}
