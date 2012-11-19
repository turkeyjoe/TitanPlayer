/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musicplayer.bll;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author owner
 */
public class AddSongDialog extends JFrame {
    private JFileChooser ch;
    
    public AddSongDialog(){
        initComponents();
    }
    
    private void initComponents(){
        ch = new JFileChooser();
        ch.setFileFilter(new MusicFileFilter());
        int returnVal = ch.showDialog(ch, "Add Song");

        if (returnVal == JFileChooser.APPROVE_OPTION){
            File song = ch.getSelectedFile();
            System.out.println("Song selected: " + song.getName());
        } else {
            System.out.println("No song selected");
        }
    }
}