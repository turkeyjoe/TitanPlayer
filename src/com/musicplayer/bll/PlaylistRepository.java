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
public class PlaylistRepository {

    List<Playlist> playlists;

    public PlaylistRepository() {
        playlists = new ArrayList();
    }

    public void addPlaylist(Playlist list) {
        playlists.add(list);
    }

    public int size() {
        return playlists.size();
    }

    public Playlist getPlaylist(UserAccount user, String listName) throws Exception {
        for (Playlist pl : playlists) {
            if (pl.getUser() == user && pl.getName().equals(listName)) {
                return pl;
            }
        }
        throw new Exception("Playlist not found");
    }

    public void deletePlaylist(UserAccount user, String listName) {
        try {
            Playlist p = getPlaylist(user, listName);
            for (Playlist pl : playlists) {
                if (pl.equals(p)) {
                    playlists.remove(pl);
                }
            }
        } catch (Exception ex) {
            
        }
    }

    public List<Playlist> getUserPlaylists(UserAccount user) {
        List<Playlist> userList = new ArrayList();
        for (Playlist pl : playlists) {
            if (pl.getUser() == user) {
                userList.add(pl);
            }
        }
        return userList;
    }
}
