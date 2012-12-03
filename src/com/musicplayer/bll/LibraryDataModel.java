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
public class LibraryDataModel extends AbstractTableModel {

    private String[] columnNames = {"Artist", "Title"};
    private List data;

    public LibraryDataModel(Library lib) {
        data = lib.getSongs();
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
        Song s = (Song) data.get(row);
        switch (col) {
            case 0:
                return s.artist();
            case 1:
                return s.title();
            default:
                return 1;
        }
    }
}
