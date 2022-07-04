/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataObjects;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author User
 */
public class Appointment {
    private int appointmentID;
    private int stylistID;
    private int guestID;
    private Date appointmentDate;
    private Time startTime;
    private double duration;

    public Appointment(int appointmentID, int stylistID, int guestID, Date appointmentDate, Time startTime, double duration) {
        this.appointmentID = appointmentID;
        this.stylistID = stylistID;
        this.guestID = guestID;
        this.appointmentDate = appointmentDate;
        this.startTime = startTime;
        this.duration = duration;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public int getStylistID() {
        return stylistID;
    }

    public int getGuestID() {
        return guestID;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public double getDuration() {
        return duration;
    }
    
    
    
}
