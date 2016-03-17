package tool.BuildingBlocks.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tool.Models.Header;
import tool.Models.Paragraph;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 14/10/2015.
 */
//Controller class for the encrypt decrypt building block
public class Encrypt_Decrypt_Controller extends BuildingBlockController{

    //variables needed by the building block
    private final static Paragraph encryptPara = new Paragraph("Encryption is my speciality! Encryption is the art of concealing the contents of a message. "+
            " This is achieved by changing each letter in the message to something else using a key. This key tends to be a number "+
            " which is used in a formula which is decided by the encryption method. You can check out some of the other building  "+
            " blocks for more information. Once I've encrypted something I pass it to my little brother Decrypt.");

    private final static Paragraph decryptPara = new Paragraph("I know everything about Decryption! Decryption happens when a message has been encrypted. My "+
            "sister transforms the message into gibberish, and it's my job to transform it back into the original message. I "+
            "need to use my key and a special formula on each letter to transform it back. My key doesn't need to be the same "+
            "key Encrypt used, it can be totally different! Try out Encryption and Decryption below.");

    private final static  Paragraph caesar = new Paragraph("This demonstrates the Caesar Cipher, one of the most well known encryption"+
    " methods. It is achieved by taking a message to encrypt and a number as a key. Each letter in the message is increased by key. "+
    "So if the key is 2, then 'a' is shifted to 'c' as it is increased from 1(a) to c(3). When decrypting the letters are shifted back "+
            "by the same amount, making this method a symmetric method of cryptography.");

    private final static Paragraph instructions = new Paragraph("Type your name into the first box, select a number from the dropdown and click the button to see your name encrypted!");
    private final static int TEXTWIDTH=300;

    private final static String enterName = "Enter your name here!";

    private final static String clicky = "Click to encrypt!";

    private final static Header encryption = new Header("Encryption stage");

    private final static Header decryption = new Header("Decryption stage");

    private final static String lower = "abcdefghijklmnopqrstuvwxyz";
    private final static String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final static int titleRow=1;
    private final static int textFieldsRow=3;
    private final static int finalRow=4;
    private final static int instructionRow=5;


    //getters for the information
    public static int getTitleRow() {
        return titleRow;
    }

    public static int getTextFieldsRow() {
        return textFieldsRow;
    }

    public static int getFinalRow() {
        return finalRow;
    }

    public static int getInstructionRow() {return instructionRow;}

    private final static ObservableList<Integer> options = setUpCombo();

    //setups the combo box to have values 1-25
    private static ObservableList<Integer> setUpCombo() {

        ObservableList<Integer> temp =FXCollections.observableArrayList();
        for (int i=1;i<26;i++){
            temp.add(i);
        }

        return temp;
    }

    public static ObservableList<Integer> getOptions() {
        return options;
    }

    public static String getClicky() {
        return clicky;
    }

    public static Header getDecryption() {
        return decryption;
    }

    public static Header getEncryption() {
        return encryption;
    }

    public static String getEnterName() {
        return enterName;

    }

    public static int getTEXTWIDTH() {
        return TEXTWIDTH;
    }

    public static Paragraph getEncryptPara() {
        return encryptPara;
    }

    public static Paragraph getDecryptPara() {
        return decryptPara;
    }

    public static Paragraph getInstructions() {
        return instructions;
    }

    public static Paragraph getCaesar() {
        return caesar;
    }


    /*encryptText method
    parameters: input- word to encrypt, n - key for encryption
    returns: - encrypted text
    Goes through word, adds key to value of char and mods by 26, adds new char to encrypted word
     */
    public static String encryptText(String input, int n){
        int m;
        String encrypted="";
        for(char c:input.toCharArray()){
            if(Character.isLowerCase(c)){
                m =lower.indexOf(c);
                encrypted+=lower.charAt(Math.floorMod((m+n),26));

            }
            else if (Character.isUpperCase(c)){
                m=upper.indexOf(c);
                encrypted+=upper.charAt(Math.floorMod((m+n),26));
            }
            else{
                encrypted+=c;
            }

        }
        return encrypted;
    }

    /*encryptText method
    parameters: input- word to encrypt, n - key for decryption
    returns: - decrypted text
    Goes through word, removes key from value of char and mods by 26, adds new char to decrypted word
    */
    public static String decryptText(String input, int n){
        int m;
        String decrypted ="";
        for(char c:input.toCharArray()){
            if(Character.isLowerCase(c)){
                m =lower.indexOf(c);
                decrypted+=lower.charAt(Math.floorMod((m-n),26));

            }
            else if (Character.isUpperCase(c)){
                m=upper.indexOf(c);
                decrypted+=upper.charAt(Math.floorMod((m-n),26));
            }
            else{
                decrypted+=c;
            }

        }
        return decrypted;
    }

}
