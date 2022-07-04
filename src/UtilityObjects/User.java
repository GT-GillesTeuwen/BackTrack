/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UtilityObjects;

/**
 *
 * @author User
 */
public class User {
    private String username;
    private int accessLevel;

    public User(String username, int accessLevel) {
        this.username = username;
        this.accessLevel = accessLevel;
    }

    public String getUsername() {
        return username;
    }

    public int getAccessLevel() {
        return accessLevel;
    }
    
    
}
