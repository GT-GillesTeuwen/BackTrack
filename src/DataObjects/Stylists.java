/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataObjects;

/**
 *
 * @author User
 */
public class Stylists {
    private int stylistID;
    private String stylistName;
    private String stylistsurname;
    private String stylistEmail;
    private String stylistContactNumber;

    public Stylists(int stylistID, String stylistName, String stylistsurname, String stylistEmail, String stylistContactNumber) {
        this.stylistID = stylistID;
        this.stylistName = stylistName;
        this.stylistsurname = stylistsurname;
        this.stylistEmail = stylistEmail;
        this.stylistContactNumber = stylistContactNumber;
    }

    public int getStylistID() {
        return stylistID;
    }

    public String getStylistName() {
        return stylistName;
    }

    public String getStylistsurname() {
        return stylistsurname;
    }

    public String getStylistEmail() {
        return stylistEmail;
    }

    public String getStylistContactNumber() {
        return stylistContactNumber;
    }
    
    
}
