/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musicplayer.bll;

import java.io.Serializable;
import java.nio.file.Path;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;

/**
 *
 * @author Rocky
 */
public class LibraryDBRecord implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SONG_NUMBER")
    private int songNum;
    @Basic(optional = false)
    @Column(name = "USERID")
    private String userid;
    @Basic(optional = false)
    @Column(name = "SONG_NAME")
    private String songName;
    @Basic(optional = false)
    @Column(name = "SONG_ARTIST")
    private String songArtist;
    @Basic(optional = false)
    @Column(name = "SONG_PATH")
    private Path songPath;
    
    
    //private String user;
    //private String songName;
    //private String songArtist;
    //private Path songPath;

    public LibraryDBRecord(String user, String songName, String songArtist, Path songPath) {
        this.userid = user;
        this.songName = songName;
        this.songArtist = songArtist;
        this.songPath = songPath;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public Path getSongPath() {
        return songPath;
    }

    public void setSongPath(Path songPath) {
        this.songPath = songPath;
    }

    public String getUser() {
        return userid;
    }

    public void setUser(String user) {
        this.userid = user;
    }
    
    
    public int getSongNum() {
        return songNum;
    }
    
    public String getUserid() {
        return userid;
    }

    public void setSongNum(int songNum) {
        this.songNum = songNum;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
    
}
