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
public class Supplier {
    private int supplierID;
    private String supplierName;
    private String contactPerson;
    private String contactEmail;
    private String contactNumber;

    public Supplier(int supplierID, String supplierName, String contactPerson, String contactEmail, String contactNumber) {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.contactPerson = contactPerson;
        this.contactEmail = contactEmail;
        this.contactNumber = contactNumber;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public String getContactNumber() {
        return contactNumber;
    }
    
    
}
