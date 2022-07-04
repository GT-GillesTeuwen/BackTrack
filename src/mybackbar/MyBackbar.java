/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybackbar;

import LoginToDatabase.LoginToDatabaseCont;
import Security.Encryption;
import Security.Hasher;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Settings.Settings;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

/*
 *
 * @author User
 */
public class MyBackbar extends Application {

    @Override
    public void start(Stage stage) throws SQLException, IOException {
        FXMLLoader loadHomeHelp = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
                    Parent root = (Parent) loadHomeHelp.load();
                    FXMLDocumentController help = loadHomeHelp.getController();
                    
                    Stage newStage = new Stage();
                    Scene scene = new Scene(root);
                    //scene.getStylesheets().add("logintodatabase.css");
                    
                    //Image icon = new Image(getClass().getResourceAsStream("/icons/LogoForToolbar.png"));
                    //newStage.getIcons().add(icon);
                    
                    newStage.setScene(scene);
                    newStage.show();
         /*try {
            Settings.Implement.initSettings();
            Encryption.Implement.init();
        } catch (IOException ex) {
            Logger.getLogger(MyBackbar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(MyBackbar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MyBackbar.class.getName()).log(Level.SEVERE, null, ex);
        }

        String path = System.getenv("APPDATA") + File.separator + "MyBackbar";
        File tmpDir = new File(path + "/bin/License.txt");
        boolean exists = tmpDir.exists();
        if (exists) {

            try {
                String key = "";
                String attempt = "";
                Scanner file = new Scanner(new FileReader("./bin/Config.txt"));
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
                    
                    FXMLLoader loadHomeHelp = new FXMLLoader(getClass().getResource("/LoginToDatabase/LoginToDatabase.fxml"));
                    Parent root = (Parent) loadHomeHelp.load();
                    LoginToDatabaseCont help = loadHomeHelp.getController();
                    
                    Stage newStage = new Stage();
                    Scene scene = new Scene(root);
                    //scene.getStylesheets().add("logintodatabase.css");
                    
                    //Image icon = new Image(getClass().getResourceAsStream("/icons/LogoForToolbar.png"));
                    //newStage.getIcons().add(icon);
                    
                    newStage.setScene(scene);
                    newStage.show();
                } else {
                    Parent root = FXMLLoader.load(getClass().getResource("/License/LicensePage.fxml"));
                    Scene scene = new Scene(root);
                    if (Settings.Implement.getTheme().equals("Classic")) {
                        // scene.getStylesheets().add(getClass().getResource("newaccount.css").toExternalForm());
                    } else {
                        //scene.getStylesheets().add(getClass().getResource(Settings.Implement.getTheme() + "theme.css").toExternalForm());
                    }
                    //Image icon = new Image(getClass().getResourceAsStream("/icons/LogoForToolbar.png"));
                    // stage.getIcons().add(icon);
                    
                    stage.setScene(scene);
                    stage.show();
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MyBackbar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MyBackbar.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            MySqlDBManager.Interface.makeKey();

            Parent root = FXMLLoader.load(getClass().getResource("/License/LicensePage.fxml"));

            Scene scene = new Scene(root);
            if (Settings.Implement.getTheme().equals("Classic")) {
//                scene.getStylesheets().add(getClass().getResource("newaccount.css").toExternalForm());
            } else {
//                scene.getStylesheets().add(getClass().getResource(Settings.Implement.getTheme() + "theme.css").toExternalForm());
            }
//            Image icon = new Image(getClass().getResourceAsStream("/icons/LogoForToolbar.png"));
//            stage.getIcons().add(icon);

            stage.setScene(scene);
            stage.show();
        }*/
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
