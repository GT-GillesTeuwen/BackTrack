/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Security;

import java.math.BigInteger;

/**
 *
 * @author Client
 */
public class Hasher {
    private String input;
    private String output;

    public Hasher() {
    }

    public Hasher(String input) {
        this.input=input;
        this.output=Encrypt(input);
    }
    

    private static String Encrypt(String input) {
        String out[] = new String[input.length() / 2];
        int out2[] = new int[input.length() / 2];
        for (int i = 0; i < input.length() / 2; i++) {
            out[i] = input.charAt((i * 2)) + "";
            out[i] += input.charAt((i * 2) + 1) + "";
        }
        for (int i = 0; i < out2.length; i++) {
            out2[i] = (Integer.parseInt(out[i]));
        }
        int n = 0;
        for (int i = 0; i < out2.length; i++) {
            if (out2[i] > n && out2[i] < 37) {
                n = out2[i];
            }
        }
        
        System.out.println(n);
        String out3 = "";
        for (int i = 0; i < out2.length; i++) {
            out3 += out2[i] + "";
        }

        return toBaseN(n, out3);
    }

    private static String toBaseN(int n, String input) {
        String output = "";
        BigInteger decimal = new BigInteger(input);
        while (decimal.compareTo(BigInteger.ZERO) > 0) {
            int rem = Integer.parseInt(decimal.mod(BigInteger.valueOf(n)) + "");
            switch (rem) {
                case 0:
                    output += "0";
                    break;
                case 1:
                    output += "1";
                    break;
                case 2:
                    output += "2";
                    break;
                case 3:
                    output += "3";
                    break;
                case 4:
                    output += "4";
                    break;
                case 5:
                    output += "5";
                    break;
                case 6:
                    output += "6";
                    break;
                case 7:
                    output += "7";
                    break;
                case 8:
                    output += "8";
                    break;
                case 9:
                    output += "9";
                    break;
                case 10:
                    output += "A";
                    break;
                case 11:
                    output += "B";
                    break;
                case 12:
                    output += "C";
                    break;
                case 13:
                    output += "D";
                    break;
                case 14:
                    output += "E";
                    break;
                case 15:
                    output += "F";
                    break;
                case 16:
                    output += "G";
                    break;
                case 17:
                    output += "H";
                    break;
                case 18:
                    output += "I";
                    break;
                case 19:
                    output += "J";
                    break;
                case 20:
                    output += "K";
                    break;
                case 21:
                    output += "L";
                    break;
                case 22:
                    output += "M";
                    break;
                case 23:
                    output += "N";
                    break;
                case 24:
                    output += "O";
                    break;
                case 25:
                    output += "P";
                    break;
                case 26:
                    output += "Q";
                    break;
                case 27:
                    output += "R";
                    break;
                case 28:
                    output += "S";
                    break;
                case 29:
                    output += "T";
                    break;
                case 30:
                    output += "U";
                    break;
                case 31:
                    output += "V";
                    break;
                case 32:
                    output += "W";
                    break;
                case 33:
                    output += "X";
                    break;
                case 34:
                    output += "Y";
                    break;
                case 35:
                    output += "Z";
                    break;
                default:
                    break;
            }
            decimal = decimal.divide(BigInteger.valueOf(n));
        }
        String reverseOut = "";
        for (int i = output.length() - 1; i > -1; i--) {
            reverseOut += output.charAt(i);
        }
        return reverseOut;
    }

    public String getOutput() {
        return output;
    }
    
}
