/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musicplayer.bll;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author TJ
 */
public class PlaylistDataModel extends AbstractTableModel {
    private String[] columnNames = {"Title", "# Of Tracks"};
    private List data;
    
    public PlaylistDataModel(List l){
        setData(l);
    }
    
    public void setData(List l){
        this.data = l;
    }
    
    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int i) {
        return columnNames[i];
    }
    
    @Override
    public Object getValueAt(int row, int col) {
        Playlist pl = (Playlist) data.get(row);
        switch (col) {
            case 0:
                return pl.getName();
            case 1:
                return pl.songCount();
            default:
                return 1;
        }
    }
}
