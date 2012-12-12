/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musicplayer.bll;

import TitanPlayer.util.HibernateUtil;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author TJ
 */
public class PlaylistRepository {

    ArrayList<Playlist> playlists;

    public PlaylistRepository() throws Exception {
        playlists = loadPlaylistLib();
        
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

    public ArrayList<Playlist> loadPlaylistLib() throws Exception {
        String plUsersQuery = "select distinct pl.playlistUser from PlaylistDBRecord pl";
        playlists = getAllSongsHQLQuery(plUsersQuery);
        return playlists;
    }

    private ArrayList<Playlist> getAllSongsHQLQuery(String plUsersQuery) throws Exception {
        List plUsersList = null;
        List allPlList = null;
        List allPlSongsList = null;
        List qsongName = null;
        List qsongArtist = null;
        List qsongPath = null;
        ArrayList<Playlist> playlistRepo = new ArrayList<>();
        UserRepository allUsers = new UserRepository();
        
        plUsersList = runDBQuery(plUsersQuery);
            
        if(plUsersList.isEmpty()){
            return null;
        }else{
            for(int u = 0; u < plUsersList.size(); u++){
                //System.out.println("plUsersList current user = "+plUsersList.get(u));
                String allPlQuery = "select distinct pl.playlistName from PlaylistDBRecord pl where pl.playlistUser = '"+plUsersList.get(u)+"'";
                allPlList = runDBQuery(allPlQuery);
                 
                for(int p = 0; p < allPlList.size();p++){
                    UserAccount currentUser = allUsers.getUser((String)plUsersList.get(u));
                    //System.out.println(allPlList.get(p));
                    //currPlaylistRepo = null;
                    String allPlSongsQuery = "select distinct pl.librarySongNum from PlaylistDBRecord pl where pl.playlistUser = '"+plUsersList.get(u)+"'"
                                                +" and pl.playlistName = '"+allPlList.get(p)+"'";
                        
                    allPlSongsList = runDBQuery(allPlSongsQuery);
                    Playlist currPlaylist = new Playlist((String)allPlList.get(p));
                    
                    for(int s=0; s < allPlSongsList.size();s++){
                        //System.out.println(allPlSongsList.get(s));
                        String songNameQuery = "select lib.songName from LibraryDBRecord lib where lib.songNum = "+(Integer)allPlSongsList.get(s);
                        String songArtistQuery = "select lib.songArtist from LibraryDBRecord lib where lib.songNum = "+(Integer)allPlSongsList.get(s);
                        String songPathQuery = "select lib.songPath from LibraryDBRecord lib where lib.songNum = "+(Integer)allPlSongsList.get(s);
                        qsongName = runDBQuery(songNameQuery);
                        qsongArtist = runDBQuery(songArtistQuery);
                        qsongPath = runDBQuery(songPathQuery);
                            
                        String songName = (String)qsongName.get(0);
                        String songArtist = (String)qsongArtist.get(0);
                        Path songPath = Paths.get((String)qsongPath.get(0));
                        
                        Song newSong = new Song(songName, songArtist, songPath);
                        currPlaylist.addSong(newSong);
                    }
                    currPlaylist.addUser(currentUser);
                    playlistRepo.add(currPlaylist);
                }
            }    
        }
        //System.out.println("Playlist Repo size is: "+playlistRepo.size());
        return playlistRepo;
    }

    private List runDBQuery(String plUsersQuery) {
        List queryResults = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
                org.hibernate.Query q = session.createQuery(plUsersQuery);       
                queryResults = q.list();
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        
        return queryResults;
    }
}
