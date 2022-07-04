/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Security;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author gteuw
 */
public class Encryption {

    public static class Implement {

        private static String secretKey;
        private static String salt;
        private static IvParameterSpec ivspec;
        private static SecretKeyFactory factory;
        private static KeySpec spec;
        private static SecretKey tmp;
        private static SecretKeySpec secretKeySpec;

        public static void init() throws FileNotFoundException, InvalidKeySpecException, NoSuchAlgorithmException {
            Implement.secretKey = "YcIwrl4RGLxdlyaJWCun";
            Implement.salt = readSalt();

            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            ivspec = new IvParameterSpec(iv);
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 128);
            tmp = factory.generateSecret(spec);
            secretKeySpec = new SecretKeySpec(tmp.getEncoded(), "AES");

        }

        public static String encrypt(String strToEncrypt) {
            if (strToEncrypt != null) {

                try {

                    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                    cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivspec);
                    return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
                } catch (Exception e) {
                    System.out.println("Error while encrypting: " + e.toString());
                }
            }

            return null;
        }

        public static String decrypt(String strToDecrypt) {
            if (strToDecrypt != null) {
                try {

                    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
                    cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivspec);
                    return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
                } catch (Exception e) {
                    System.out.println("Error while decrypting: " + e.toString());
                }
            }
            return null;
        }

        public static String readSalt() throws FileNotFoundException {
            String path = System.getenv("APPDATA") + File.separator + "MyBackbar";
            String out = "";
            try (Scanner file = new Scanner(new FileReader(path + "/bin/License.txt"))) {
                while (file.hasNext()) {
                    out = file.nextLine();
                }
            } catch (FileNotFoundException e) {

            }
            System.out.println(out);
            return out;
        }

    }
}
