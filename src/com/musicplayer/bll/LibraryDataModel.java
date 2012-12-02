/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musicplayer.bll;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author TJ
 */
public class LibraryDataModel extends AbstractTableModel {
    private String[] columnNames = {"Artist", "Title"};
    private Object[][] data = {{"bob", "song"}};
    
    public LibraryDataModel(){
        //data = loadData(lib);
    }
    
    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public String getColumnName(int i){
        return columnNames[i];
    }
    
    @Override
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }
    
    public void loadData(Library lib){
        Object[][] temp = new Object[lib.songCount()][2];
        Song[] songs = lib.getSongs();
        for (int i = 0; i < songs.length; i++){
            temp[i][0] = songs[i].artist();
            temp[i][1] = songs[i].title();
        }
        data = temp;
    }
}
