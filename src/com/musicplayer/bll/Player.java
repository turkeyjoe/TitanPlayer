/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musicplayer.bll;

//NIKKI import java.sql.Time;
//NIKKI import javax.swing.JProgressBar;
/**
 *
 * @author TJ
 */
public class Player {
    private Playlist playlist;
    private int currentSong;
    private boolean isPlaying = false;
    private boolean pause;
    //NIKKI private JProgressBar progressBar = null;
    //NIKKI private Player player;

    public void loadPlaylist(Playlist pl) {
        playlist = pl;
        currentSong = 0;
        isPlaying = true;
    }

    public Song currentSong() {
        return playlist.getSong(currentSong);
    }

    public void nextSong() {
        currentSong++;
    }

    public void stop() {
        if (isPlaying) {
            isPlaying = false;
//NIKKI            progressBar.setValue(0);
        }
    }

    public boolean isPlaying() {
        return isPlaying;
    }
        
    public void paused() {
        if (isPlaying) {
            isPlaying = false;
        }
        
    }
    /* NIKKI
    public void play() {
        while(isPlaying) {
            Time time = player.getMediaTime();
            progressBar.setValue((int) time.getSeconds());
                
            }
        }
        * */
    }