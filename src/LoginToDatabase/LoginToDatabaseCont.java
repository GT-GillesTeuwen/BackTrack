/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginToDatabase;

import Security.Encryption;
import Settings.Settings;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import mybackbar.MySqlDBManager;

/**
 * FXML Controller class
 *
 * @author Client
 */
public class LoginToDatabaseCont implements Initializable {

    @FXML
    private Label HeaderName1;
    @FXML
    private TextField bindAddressFld;
    @FXML
    private TextField usernameFld;
    @FXML
    private PasswordField passwordFld;
    @FXML
    private Button saveBTN;
    @FXML
    private Label errLbl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bindAddressFld.setText(Settings.Implement.getAddress());
    }

    @FXML
    private void loginToDatabase(ActionEvent event) throws IOException {
        if (MySqlDBManager.Interface.testConnection(bindAddressFld.getText(), usernameFld.getText(), passwordFld.getText())) {
            MySqlDBManager.Interface.A_addUser("wpuser", "May272002", 0);
            Stage homeStage = (Stage) usernameFld.getScene().getWindow();
            homeStage.close();
            /*
            FXMLLoader loadHomeHelp = new FXMLLoader(getClass().getResource("HomeScreen.fxml"));
            Parent root = (Parent) loadHomeHelp.load();
            HomeScreenCont help = loadHomeHelp.getController();

            Stage newStage = new Stage();
            Scene scene = new Scene(root);

            if (Settings.Implement.getTheme().equals("Classic")) {
                scene.getStylesheets().add(getClass().getResource("newaccount.css").toExternalForm());
            } else {
                scene.getStylesheets().add(getClass().getResource(Settings.Implement.getTheme() + "theme.css").toExternalForm());
            }
            Image icon = new Image(getClass().getResourceAsStream("/icons/LogoForToolbar.png"));
            newStage.getIcons().add(icon);

            newStage.setScene(scene); 
            newStage.show();
             */
        } else {
            errLbl.setTextFill(Paint.valueOf("RED"));
            errLbl.setText("Invalid address or credentials");
            System.out.println("Error");
        }

    }

}
