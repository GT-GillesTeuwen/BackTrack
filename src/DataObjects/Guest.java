/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataObjects;

import java.sql.Date;

/**
 *
 * @author User
 */
public class Guest {
    private int GuestID;
    private String GuestName;
    private String GuestSurname;
    private String Email;
    private String ContactNumber;
    private Date DateOfBirth;

    public Guest(int GuestID, String GuestName, String GuestSurname, String Email, String ContactNumber, Date DateOfBirth) {
        this.GuestID = GuestID;
        this.GuestName = GuestName;
        this.GuestSurname = GuestSurname;
        this.Email = Email;
        this.ContactNumber = ContactNumber;
        this.DateOfBirth = DateOfBirth;
    }

    public int getGuestID() {
        return GuestID;
    }

    public String getGuestName() {
        return GuestName;
    }

    public String getGuestSurname() {
        return GuestSurname;
    }

    public String getEmail() {
        return Email;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public Date getDateOfBirth() {
        return DateOfBirth;
    }
    
    
}
