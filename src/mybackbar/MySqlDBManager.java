/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybackbar;

import Security.Encryption;
import com.mysql.cj.jdbc.Driver;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class MySqlDBManager {

    public static class Interface {

        private static Connection con;
        private static boolean hasData = false;
        private static String db = "MyBackbar";
        private static String username;
        private static String password;
        private static String bindAddress;

        public static boolean testConnection(String bindAddress, String username, String password) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                DriverManager.getConnection("jdbc:mysql://" + bindAddress, username, password);
                System.out.println("Test Success");
                Interface.username = username;
                Interface.password = password;
                Interface.bindAddress = bindAddress;
                return true;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MySqlDBManager.class.getName()).log(Level.SEVERE, null, ex);
                con = null;
                System.out.println("Test Failed");
                return false;
            } catch (SQLException ex) {
                Logger.getLogger(MySqlDBManager.class.getName()).log(Level.SEVERE, null, ex);
                con = null;
                System.out.println("Test Failed");
                return false;
            }

        }

        public static void getConnection() {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://" + bindAddress, username, password);
                System.out.println("Connection Established");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MySqlDBManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(MySqlDBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            Initialise();
        }

        public static void Initialise() {
            if (!hasData) {
                try {
                    hasData = true;
                    Statement statement = con.createStatement();
                    ResultSet res = statement.executeQuery("Select SCHEMA_NAME\n"
                            + "from INFORMATION_SCHEMA.SCHEMATA\n"
                            + "WHERE SCHEMA_NAME='" + db + "'");

                    if (!res.next()) {
                        System.out.println("No database yet");
                        //Create Database here
                    } else {
                        con = DriverManager.getConnection("jdbc:mysql://" + bindAddress + "/" + db, username, password);
                        System.out.println("Database found");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MySqlDBManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        public static void makeKey() throws SQLException, IOException {
            //String path = System.getenv("APPDATA") + File.separator + "MyBackbar";
            File tmpDir = new File("./bin/Config.txt");
            boolean exists = tmpDir.exists();
            if (!exists) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                System.out.println(dtf.format(now));
                String key = dtf.format(now).replace("/", "");
                key = key.replace(":", "");
                key = key.replace(" ", "");
                if (!(new File("./bin").exists())) {
                    File conf2 = new File(String.valueOf("./bin"));
                    conf2.mkdir();
                }
                File conf = new File(String.valueOf("./bin/Config.txt"));
                conf.createNewFile();
                FileWriter fileWriter = new FileWriter("./bin/Config.txt");
                fileWriter.write(key);
                fileWriter.close();
            }
        }
        public static void A_addUser(String username, String password, int accessLevel) {
            try {
                if (con == null) {
                    getConnection();
                }

                PreparedStatement prep = con.prepareStatement("INSERT INTO users\n"
                        + "(Username,Password,AccessLevel)\n"
                        + "values\n"
                        + "(?,?,?)");

                prep.setString(1, Encryption.Implement.encrypt(username));
                prep.setString(2, Encryption.Implement.encrypt(password));
                prep.setInt(3, accessLevel);

                prep.execute();

            } catch (SQLException ex) {
                Logger.getLogger(MySqlDBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public static void I_insertStylist(String name, String surname, String email, String contactNumber) {
            try {
                if (con == null) {
                    getConnection();
                }

                PreparedStatement prep = con.prepareStatement("INSERT INTO stylists\n"
                        + "(StylistName,StylistSurname,StylistEmail,StylistContactNumber)\n"
                        + "values\n"
                        + "(?,?,?,?)");

                prep.setString(1, Encryption.Implement.encrypt(name));
                prep.setString(2, Encryption.Implement.encrypt(surname));
                prep.setString(3, Encryption.Implement.encrypt(email));
                prep.setString(4, Encryption.Implement.encrypt(contactNumber));

                prep.execute();

            } catch (SQLException ex) {
                Logger.getLogger(MySqlDBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public static void I_insertGuest(String name, String surname, String email, String contactNumber, Date dob) {
            try {
                if (con == null) {
                    getConnection();
                }

                PreparedStatement prep = con.prepareStatement("INSERT INTO guests\n"
                        + "(GuestName,GuestSurname,Email,ContactNumber,DateOfBirth)\n"
                        + "values\n"
                        + "(?,?,?,?,?)");

                prep.setString(1, Encryption.Implement.encrypt(name));
                prep.setString(2, Encryption.Implement.encrypt(surname));
                prep.setString(3, Encryption.Implement.encrypt(email));
                prep.setString(4, Encryption.Implement.encrypt(contactNumber));
                prep.setDate(5, dob);

                prep.execute();

            } catch (SQLException ex) {
                Logger.getLogger(MySqlDBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public static void I_insertSupplier(String name, String contactPerson, String email, String contactNumber) {
            try {
                if (con == null) {
                    getConnection();
                }

                PreparedStatement prep = con.prepareStatement("INSERT INTO suppliers\n"
                        + "(SupplierName,ContactPerson,ContactEmail,ContactNumber)\n"
                        + "values\n"
                        + "(?,?,?,?)");

                prep.setString(1, Encryption.Implement.encrypt(name));
                prep.setString(2, Encryption.Implement.encrypt(contactNumber));
                prep.setString(3, Encryption.Implement.encrypt(email));
                prep.setString(4, Encryption.Implement.encrypt(contactNumber));

                prep.execute();

            } catch (SQLException ex) {
                Logger.getLogger(MySqlDBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public static void I_createAppointment(int stylistID, int guestID, Date apptDate, Time startTime, double duration) {
            try {
                if (con == null) {
                    getConnection();
                }

                PreparedStatement prep = con.prepareStatement("INSERT INTO appointments\n"
                        + "(StylistID,GuestID,AppointmentDate,StartTime,Duration)\n"
                        + "values\n"
                        + "(?,?,?,?,?)");

                prep.setInt(1, stylistID);
                prep.setInt(2, guestID);
                prep.setDate(3, apptDate);
                prep.setTime(4, startTime);
                prep.setDouble(5, duration);

                prep.execute();

            } catch (SQLException ex) {
                Logger.getLogger(MySqlDBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public static void I_insertService(String description, double price) {
            try {
                if (con == null) {
                    getConnection();
                }

                PreparedStatement prep = con.prepareStatement("INSERT INTO services\n"
                        + "(ServiceDescription,ServicePrice)\n"
                        + "values\n"
                        + "(?,?)");

                prep.setString(1, Encryption.Implement.encrypt(description));
                prep.setDouble(2, price);

                prep.execute();

            } catch (SQLException ex) {
                Logger.getLogger(MySqlDBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public static void I_insertStockItem(int supplierID, String itemName, String itemDescription, double price, double weightPerUnit, int isColour) {
            try {
                if (con == null) {
                    getConnection();
                }

                PreparedStatement prep = con.prepareStatement("INSERT INTO stock_items\n"
                        + "(SupplierID,ItemName,ItemDescription,Price,WeightPerUnit,isColour,WeightInStock)\n"
                        + "values\n"
                        + "(?,?,?,?,?,?,?)");

                prep.setInt(1, supplierID);
                prep.setString(2, Encryption.Implement.encrypt(itemName));
                prep.setString(3, Encryption.Implement.encrypt(itemDescription));
                prep.setDouble(4, price);
                prep.setDouble(5, weightPerUnit);
                prep.setInt(6, isColour);
                prep.setDouble(7, 0.0);
                prep.execute();

            } catch (SQLException ex) {
                Logger.getLogger(MySqlDBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public static void I_addItemToService(int serviceID, int itemID) {
            try {
                if (con == null) {
                    getConnection();
                }

                PreparedStatement prep = con.prepareStatement("INSERT INTO service_item\n"
                        + "(ServiceID,ItemID)\n"
                        + "values\n"
                        + "(?,?)");

                prep.setInt(1, serviceID);
                prep.setInt(2, itemID);
                prep.execute();

            } catch (SQLException ex) {
                Logger.getLogger(MySqlDBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public static void I_addServiceToAppointment(int appointmentID, int serviceID) {
            try {
                if (con == null) {
                    getConnection();
                }

                PreparedStatement prep = con.prepareStatement("INSERT INTO appointment_service\n"
                        + "(AppointmentID,ServiceID)\n"
                        + "values\n"
                        + "(?,?)");

                prep.setInt(1, appointmentID);
                prep.setInt(2, serviceID);
                prep.execute();

            } catch (SQLException ex) {
                Logger.getLogger(MySqlDBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public static void I_addColourToAppointment(int appointmentID, int colourItemID, double weightUsed) {
            try {
                if (con == null) {
                    getConnection();
                }

                PreparedStatement prep = con.prepareStatement("INSERT INTO appointment_colours\n"
                        + "(AppointmentID,ColourID,WeightUsed)\n"
                        + "values\n"
                        + "(?,?,?)");

                prep.setInt(1, appointmentID);
                prep.setInt(2, colourItemID);
                prep.setDouble(3, weightUsed);
                prep.execute();

            } catch (SQLException ex) {
                Logger.getLogger(MySqlDBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public static void D_removeColourFromAppointment(int appointmentID, int colourItemID) {
            try {
                if (con == null) {
                    getConnection();
                }

                PreparedStatement prep = con.prepareStatement("DELETE FROM appointment_colours WHERE AppointmentID=? AND ColourID=?");

                prep.setInt(1, appointmentID);
                prep.setInt(2, colourItemID);
                prep.execute();

            } catch (SQLException ex) {
                Logger.getLogger(MySqlDBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public static void D_removeServiceFromAppointment(int appointmentID, int serviceID) {
            try {
                if (con == null) {
                    getConnection();
                }

                PreparedStatement prep = con.prepareStatement("DELETE FROM appointment_service WHERE AppointmentID=? AND ServiceID=?");

                prep.setInt(1, appointmentID);
                prep.setInt(2, serviceID);
                prep.execute();

            } catch (SQLException ex) {
                Logger.getLogger(MySqlDBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public static void D_deleteAppointment(int appointmentID) {
            try {
                if (con == null) {
                    getConnection();
                }

                PreparedStatement prep = con.prepareStatement("DELETE FROM appointment_service WHERE AppointmentID=?");
                prep.setInt(1, appointmentID);
                prep.execute();

                prep = con.prepareStatement("DELETE FROM appointment_colours WHERE AppointmentID=?");
                prep.setInt(1, appointmentID);
                prep.execute();

                prep = con.prepareStatement("DELETE FROM appointments WHERE AppointmentID=?");
                prep.setInt(1, appointmentID);
                prep.execute();

            } catch (SQLException ex) {
                Logger.getLogger(MySqlDBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public static void D_removeItemFromService(int serviceID, int itemID) {
            try {
                if (con == null) {
                    getConnection();
                }

                PreparedStatement prep = con.prepareStatement("DELETE FROM service_items WHERE ServiceID=? AND ItemID=?");

                prep.setInt(1, serviceID);
                prep.setInt(2, itemID);
                prep.execute();

            } catch (SQLException ex) {
                Logger.getLogger(MySqlDBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public static void D_deleteService(int serviceID) {
            try {
                if (con == null) {
                    getConnection();
                }

                PreparedStatement prep = con.prepareStatement("DELETE FROM service_items WHERE ServiceID=?");
                prep.setInt(1, serviceID);
                prep.execute();

                prep = con.prepareStatement("DELETE FROM appointment_service WHERE ServiceID=?");
                prep.setInt(1, serviceID);
                prep.execute();

                prep = con.prepareStatement("DELETE FROM services WHERE ServiceID=?");
                prep.setInt(1, serviceID);
                prep.execute();

            } catch (SQLException ex) {
                Logger.getLogger(MySqlDBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public static void D_deleteItem(int itemID) {
            try {
                if (con == null) {
                    getConnection();
                }

                PreparedStatement prep = con.prepareStatement("DELETE FROM service_items WHERE ItemID=?");
                prep.setInt(1, itemID);
                prep.execute();

                prep = con.prepareStatement("DELETE FROM appointment_colours WHERE ItemID=?");
                prep.setInt(1, itemID);
                prep.execute();

                prep = con.prepareStatement("DELETE FROM stock_items WHERE ItemID=?");
                prep.setInt(1, itemID);
                prep.execute();

            } catch (SQLException ex) {
                Logger.getLogger(MySqlDBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public static void D_deleteSupplier(int supplierID) {
            try {
                if (con == null) {
                    getConnection();
                }

                PreparedStatement prep = con.prepareStatement("DELETE FROM stock_items WHERE supplierID=?");
                prep.setInt(1, supplierID);
                prep.execute();

                prep = con.prepareStatement("DELETE FROM suppliers WHERE supplierID=?");
                prep.setInt(1, supplierID);
                prep.execute();

            } catch (SQLException ex) {
                Logger.getLogger(MySqlDBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public static void D_deleteStylist(int stylistID) {
            try {
                if (con == null) {
                    getConnection();
                }

                PreparedStatement find = con.prepareStatement("Select AppointmentID\n"
                        + "from appointments\n"
                        + "WHERE StylistID=?");
                find.setInt(1, stylistID);
                ResultSet stylistAppts = find.executeQuery();
                while (stylistAppts.next()) {
                    D_deleteAppointment(stylistAppts.getInt("AppointmentID"));
                }

                PreparedStatement prep = con.prepareStatement("DELETE FROM stylists WHERE StylistID=?");
                prep.setInt(1, stylistID);
                prep.execute();

            } catch (SQLException ex) {
                Logger.getLogger(MySqlDBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public static void D_deleteGuest(int guestID) {
            try {
                if (con == null) {
                    getConnection();
                }

                PreparedStatement find = con.prepareStatement("Select AppointmentID\n"
                        + "from appointments\n"
                        + "WHERE GuestID=?");
                find.setInt(1, guestID);
                ResultSet stylistAppts = find.executeQuery();
                while (stylistAppts.next()) {
                    D_deleteAppointment(stylistAppts.getInt("AppointmentID"));
                }

                PreparedStatement prep = con.prepareStatement("DELETE FROM guests WHERE GuestID=?");
                prep.setInt(1, guestID);
                prep.execute();

            } catch (SQLException ex) {
                Logger.getLogger(MySqlDBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public static void U_updateAppointment(int appointmentID, int stylistID, int guestID, Date apptDate, Time startTime, double duration) {
            try {
                if (con == null) {
                    getConnection();
                }

                PreparedStatement prep = con.prepareStatement("Update appointments\n"
                        + "SET StylistID=?,GuestID=?,AppointmentDate=?,StartTime=?,Duration=?\n"
                        + "WHERE AppointmentID=?");

                prep.setInt(1, stylistID);
                prep.setInt(2, guestID);
                prep.setDate(3, apptDate);
                prep.setTime(4, startTime);
                prep.setDouble(5, duration);
                prep.setInt(6, appointmentID);

                prep.execute();

            } catch (SQLException ex) {
                Logger.getLogger(MySqlDBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public static void U_updateGuest(int guestID, String name, String surname, String email, String contactNumber, Date dob) {
            try {
                if (con == null) {
                    getConnection();
                }

                PreparedStatement prep = con.prepareStatement("UPDATE guests\n"
                        + "SET GuestName=?,GuestSurname=?,Email=?,ContactNumber=?,DateOfBirth=?\n"
                        + "WHERE GuestID=?\n");

                prep.setString(1, name);
                prep.setString(2, surname);
                prep.setString(3, email);
                prep.setString(4, contactNumber);
                prep.setDate(5, dob);
                prep.setInt(6, guestID);

                prep.execute();

            } catch (SQLException ex) {
                Logger.getLogger(MySqlDBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public static void U_updateService(int serviceID, String description, double price) {
            try {
                if (con == null) {
                    getConnection();
                }

                PreparedStatement prep = con.prepareStatement("UPDATE services\n"
                        + "SET ServiceDescription=?,ServicePrice=?\n"
                        + "WHERE ServiceID=?\n");

                prep.setString(1, description);
                prep.setDouble(2, price);
                prep.setInt(3, serviceID);

                prep.execute();

            } catch (SQLException ex) {
                Logger.getLogger(MySqlDBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public static void U_updateStockItem(int itemID, int supplierID, String itemName, String itemDescription, double price, double weightPerUnit, int isColour) {
            try {
                if (con == null) {
                    getConnection();
                }

                PreparedStatement prep = con.prepareStatement("UPDATE stock_items\n"
                        + "SET SupplierID=?,ItemName=?,ItemDescription=?,Price=?,WeightPerUnit=?,isColour=?\n"
                        + "WHERE ItemID=?\n");

                prep.setInt(1, supplierID);
                prep.setString(2, itemName);
                prep.setString(3, itemDescription);
                prep.setDouble(4, price);
                prep.setDouble(5, weightPerUnit);
                prep.setInt(6, isColour);
                prep.setInt(7, itemID);
                prep.execute();

            } catch (SQLException ex) {
                Logger.getLogger(MySqlDBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public static void U_updateStylist(int stylistID, String name, String surname, String email, String contactNumber) {
            try {
                if (con == null) {
                    getConnection();
                }

                PreparedStatement prep = con.prepareStatement("UPDATE stylists\n"
                        + "SET StylistName=?,StylistSurname=?,StylistEmail=?,StylistContactNumber=?\n"
                        + "WHERE StylistID=?\n");

                prep.setString(1, name);
                prep.setString(2, surname);
                prep.setString(3, email);
                prep.setString(4, contactNumber);
                prep.setInt(5, stylistID);

                prep.execute();

            } catch (SQLException ex) {
                Logger.getLogger(MySqlDBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public static void U_updateSupplier(int supplierID, String name, String contactPerson, String email, String contactNumber) {
            try {
                if (con == null) {
                    getConnection();
                }

                PreparedStatement prep = con.prepareStatement("UPDATE suppliers\n"
                        + "SET SupplierName=?,ContactPerson=?,ContactEmail=?,ContactNumber=?\n"
                        + "WHERE supplierID=?\n");

                prep.setString(1, name);
                prep.setString(2, contactNumber);
                prep.setString(3, email);
                prep.setString(4, contactNumber);
                prep.setInt(5, supplierID);

                prep.execute();

            } catch (SQLException ex) {
                Logger.getLogger(MySqlDBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        

    }
}
