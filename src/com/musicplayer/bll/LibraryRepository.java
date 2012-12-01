/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musicplayer.bll;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kyle
 */
public class LibraryRepository {
    List<Library> libraries;
    
    public LibraryRepository() {
        libraries = new ArrayList<Library>();
    }
    
    public void addLibrary(Library libraryToAdd) {
        libraries.add(libraryToAdd);
    }
    
    public int libraryCount() {
        return libraries.size();
    }
    
    public Library getUserLibrary(UserAccount user) {
        Library userLibrary = new Library();
        for (int i = 0; i < libraries.size(); i++) {
            if (libraries.get(i).getUser() == user) {
                userLibrary = libraries.get(i);
            }
        }
        return userLibrary;
    }
}