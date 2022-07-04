/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package License;

import LoginToDatabase.LoginToDatabaseCont;
import Security.Hasher;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Client
 */
public class LicensePageCont implements Initializable {

    @FXML
    private TextField keyFLD;
    @FXML
    private Button validateBTN;
    @FXML
    private Label errLbl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private void checkKey() {
        String path = System.getenv("APPDATA") + File.separator + "MyBackbar";
        File tmpDir = new File(path + "/bin/License.txt");
        boolean exists = tmpDir.exists();

        if (exists) {
            try {
                String key = "";
                String attempt = "";
                Scanner file = new Scanner(new FileReader(path + "/bin/Config.txt"));
                while (file.hasNext()) {
                    key = file.next();
                }
                Hasher hash = new Hasher(key);
                key = hash.getOutput();
                file = new Scanner(new FileReader(path + "/bin/License.txt"));
                while (file.hasNext()) {
                    attempt = file.next();
                }

                if (key.equals(attempt)) {
                    Stage homeStage = (Stage) keyFLD.getScene().getWindow();
                    homeStage.close();
                    FXMLLoader loadHomeHelp = new FXMLLoader(getClass().getResource("LoginToDatabase.fxml"));
                    Parent root = (Parent) loadHomeHelp.load();
                    LoginToDatabaseCont help = loadHomeHelp.getController();

                    Stage newStage = new Stage();
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add("logintodatabase.css");

                    Image icon = new Image(getClass().getResourceAsStream("/icons/LogoForToolbar.png"));
                    newStage.getIcons().add(icon);

                    newStage.setScene(scene);
                    newStage.show();
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(LicensePageCont.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(LicensePageCont.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void ValidateKey(ActionEvent event) throws IOException {
        String path = System.getenv("APPDATA") + File.separator + "MyBackbar";
        String attempt = keyFLD.getText();
        String key = "";
        try {
            Scanner file = new Scanner(new FileReader("./bin/Config.txt"));
            while (file.hasNext()) {
                key = file.next();

            }
            Hasher hash = new Hasher(key);
            key = hash.getOutput();
            if (attempt.equals(key)) {
                File lic = new File(String.valueOf(path + "/bin/License.txt"));
                lic.createNewFile();
                FileWriter fileWriter = new FileWriter(path + "/bin/License.txt");
                fileWriter.write(attempt);
                fileWriter.close();
                Stage homeStage = (Stage) keyFLD.getScene().getWindow();
                homeStage.close();
                FXMLLoader loadHomeHelp = new FXMLLoader(getClass().getResource("/LoginToDatabase/LoginToDatabase.fxml"));
                Parent root = (Parent) loadHomeHelp.load();
                LoginToDatabaseCont help = loadHomeHelp.getController();

                Stage newStage = new Stage();
                Scene scene = new Scene(root);
                //scene.getStylesheets().add("logintodatabase.css");

               // Image icon = new Image(getClass().getResourceAsStream("/icons/LogoForToolbar.png"));
                //newStage.getIcons().add(icon);

                newStage.setScene(scene);
                newStage.show();
            } else {
                errLbl.setText("Invalid License Key");
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(LicensePageCont.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
