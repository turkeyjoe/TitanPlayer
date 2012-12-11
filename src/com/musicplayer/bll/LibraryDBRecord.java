/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musicplayer.bll;

import TitanPlayer.util.HibernateUtil;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.persistence.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.dialect.DerbyDialect;

/**
 *
 * @author Rocky
 */
//public class LibraryDBRecord implements Serializable{
public class LibraryDBRecord {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SONG_NUMBER", unique = true)
    private int songNum;
    
    //@Basic(optional = false)
    //@Column(name = "USERID")
    private String userId;
    //@Basic(optional = false)
    //@Column(name = "SONG_NAME")
    private String songName;
    //@Basic(optional = false)
    //@Column(name = "SONG_ARTIST")
    private String songArtist;
    //@Basic(optional = false)
    //@Column(name = "SONG_PATH")
    private String songPath;
    
    
    //private String userid;
    //private String songName;
    //private String songArtist;
    //private String songPath;

    public LibraryDBRecord(String user, String songName, String songArtist, String songPath) {
        //this.songNum = 0;
        this.userId = user;
        this.songName = songName;
        this.songArtist = songArtist;
        this.songPath = songPath;
    }
    
    /*public LibraryDBRecord(int nextSongNum, String user, String songName, String songArtist, String songPath) {
        //this.songNum = nextSongNum;
        this.userid = user;
        this.songName = songName;
        this.songArtist = songArtist;
        this.songPath = songPath;
    }
    * 
    */

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

    public String getSongPath() {
        
        return songPath;
    }
    
    public String getStringSongPath() {
        String strSongPath = this.getSongPath().toString();
        return strSongPath;
    }

    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUser() {
        return userId;
    }

    public void setUser(String user) {
        this.userId = user;
    }
    
    
    /*public int getSongNum() {
        List maxLibID = null;
        //int nextNum = 0;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
                org.hibernate.Query q = session.createQuery("Select lib.songNum from LibraryDBRecord lib");
                
                maxLibID = q.list();

                System.out.println("Inside getSongNum: Size of maxLibID = " + maxLibID.size());
                songNum = (Integer) maxLibID.get(maxLibID.size() - 1);
                System.out.println(songNum);

            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        
        return songNum;
    }
    * 
    */
    
    public String getUserid() {
        return userId;
    }

    public int getSongNum() {
        return songNum;
    }

    public void setSongNum(int songNum) {
        this.songNum = songNum;
    }
    
    public int sgetSongNum() {
         return songNum;
    }

    public void setUserid(String userid) {
        this.userId = userid;
    }
    
    public void writeLibRecToDB(LibraryDBRecord libRec){
        
    }

    public boolean addSong(LibraryDBRecord addRec) {
        boolean success = false;
        System.out.println("Now inside addSong method:");
        //LibraryDBRecord addRec = new LibraryDBRecord(newSongNum,userid,songName,songArtist,songPath);
        
        System.out.println("Record to be added: ");
        System.out.println(addRec.userId+","+addRec.songName+","+addRec.songArtist+","+addRec.songPath);
        
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();
            //session.save(addRec);
            session.persist(addRec);
            //session.refresh(addRec);
            session.getTransaction().commit();
            success = true;
        } catch (HibernateException he) {
            success = false;
            he.printStackTrace();
        }
        if(!success){
            System.out.println("Add was not successful!!!");
        }
        return success;
    }
    
}
