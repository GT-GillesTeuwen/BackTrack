package Settings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
public class Settings {

    public static class Implement {

        private static String[] allSettings = new String[2];
        
        public static String getAddress(){
            return allSettings[0];
        }
        
        public static String getTheme(){
            return allSettings[1];
        }

        public static void initSettings() throws IOException {
            String path = System.getenv("APPDATA") + File.separator + "MyBackbar";
            File tmpDir = new File(path + "/bin/Settings.txt");
            if (tmpDir.exists()) {
                int count = 0;
                try {
                    Scanner file = new Scanner(new FileReader(path + "/bin/Settings.txt"));
                    while (file.hasNext()) {
                        allSettings[count] = file.nextLine();
                        count++;
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                FileWriter fileWriter =null;
                try {

                    File dir = new File(String.valueOf(path));
                    dir.mkdirs();
                    File dir2 = new File(String.valueOf(path + "/bin"));
                    dir2.mkdirs();
                    File sett = new File(String.valueOf(path + "\\bin\\Settings.txt"));
                    sett.createNewFile();
                    fileWriter = new FileWriter(path + "/bin/Settings.txt");
                    allSettings[0] = "192.168.8.102:3306";
                    allSettings[1] = "Classic";
                    for (int i = 0; i < allSettings.length; i++) {
                        fileWriter.write(allSettings[i]);
                        if (i != allSettings.length - 1) {
                            fileWriter.write("\n");
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                    fileWriter.close();
                }

            }

        }
    }
}
