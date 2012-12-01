/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musicplayer.bll;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

/**
 *
 * @author owner
 */
public class AddSongDialog {
    private JFileChooser ch;
    
    public void showDialog(){
        ch = new JFileChooser();
        ch.setFileFilter(new MusicFileFilter());
        int returnVal = ch.showDialog(ch, "Add Song");
        addSong(returnVal);        
    }
    
    private void addSong(int returnVal){
        if (returnVal == JFileChooser.APPROVE_OPTION){
            try {
                File song = ch.getSelectedFile();
                MP3File mp3 = (MP3File)AudioFileIO.read(song);
                Tag tag = mp3.getTag();
                Song newSong = new Song(tag.getFirst(FieldKey.TITLE), tag.getFirst(FieldKey.ARTIST), Paths.get(song.getPath()));
                
                System.out.println("Song selected: " + newSong.toString());
            } catch (CannotReadException ex) {
                Logger.getLogger(AddSongDialog.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(AddSongDialog.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TagException ex) {
                Logger.getLogger(AddSongDialog.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ReadOnlyFileException ex) {
                Logger.getLogger(AddSongDialog.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidAudioFrameException ex) {
                Logger.getLogger(AddSongDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("No song selected");
        }
    }
}