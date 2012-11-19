/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musicplayer.bll;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author owner
 */
public class UserAccount {

    private String username;
    private String password;
    private String email;

    public UserAccount(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }

    public String email() {
        return email;
    }

    public static boolean validatePassword(String pass) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(pass);
        if (m.find()) {
            p = Pattern.compile("[a-zA-Z]+");
            m = p.matcher(pass);
            if (m.find()) {
                p = Pattern.compile("\\W+");
                m = p.matcher(pass);
                return m.find();
            }
        }
        return false;
    }
    
    public static boolean validateEmail(String email){
        Pattern p = Pattern.compile("(\\w+)\\@(\\w+)\\.([a-zA-Z]{3})");
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
