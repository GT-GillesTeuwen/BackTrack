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
public class Service {
    private int serviceID;
    private String serviceDescription;
    private double servicePrice;

    public Service(int serviceID, String serviceDescription, double servicePrice) {
        this.serviceID = serviceID;
        this.serviceDescription = serviceDescription;
        this.servicePrice = servicePrice;
    }

    public int getServiceID() {
        return serviceID;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public double getServicePrice() {
        return servicePrice;
    }
    
    
}
