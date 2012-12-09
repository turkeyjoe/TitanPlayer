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
        if (username.isEmpty()) {
            throw new IllegalArgumentException("Enter A Username");
        }
        if (!validatePassword(password)) {
            throw new IllegalArgumentException("Password must contain at least 1 digit, 1 alpha, and 1 special character!");
        }
        if (!validateEmail(email)) {
            throw new IllegalArgumentException("Invalid Email Address");
        }
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    
    
    private boolean validatePassword(String pass) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(pass);
        if (m.find()) {
            p = Pattern.compile("[a-zA-Z]+");
            m = p.matcher(pass);
            if (m.find()) {
                p = Pattern.compile("\\W+");
                m = p.matcher(pass);
                if (m.find()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean validateEmail(String email) {
        Pattern p = Pattern.compile("(\\w+)\\@(\\w+)\\.([a-zA-Z]{3})");
        Matcher m = p.matcher(email);
        if (m.matches()) {
            return true;
        }
        return false;
    }
    
    public boolean validateLogin(String pass, String email) 
        throws Exception {
        if (!this.password.equals(pass)){
            throw new Exception("Invalid Password");
        }
        if (!this.email.equals(email)){
            throw new Exception("Invalid Email");
        }
        return true;
    }

    @Override
    public String toString() {
        return this.username;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (this.username != null ? this.username.hashCode() : 0);
        hash = 59 * hash + (this.password != null ? this.password.hashCode() : 0);
        hash = 59 * hash + (this.email != null ? this.email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserAccount other = (UserAccount) obj;
        if ((this.username == null) ? (other.username != null) : !this.username.equals(other.username)) {
            return false;
        }
        return true;
    }


}
