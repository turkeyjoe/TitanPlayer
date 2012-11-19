/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musicplayer.bll;

/**
 *
 * @author TJ
 */
public class Player {
    private Playlist playlist;
    private int currentSong;
    private boolean isPlaying = false;

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
        }
    }

    public boolean isPlaying() {
        return isPlaying;
    }
}
