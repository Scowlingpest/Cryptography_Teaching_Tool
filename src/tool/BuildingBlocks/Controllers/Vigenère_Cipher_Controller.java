package tool.BuildingBlocks.Controllers;

import tool.Models.Header;
import tool.Models.Paragraph;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 15/11/2015.
 */
//controller class for the vigenere cipher building block
public class Vigenère_Cipher_Controller extends BuildingBlockController {

    //variables needed
    private static String key = "cookie";
    private static String message ="Cryptography is Fun";
    private static String noSpaces =message.replaceAll("\\s", "");
    private static String keyword = generateKeyWord(key, noSpaces.length());
    private static String encrypted =encrypt(noSpaces,key);
    private static int TEXTWIDTH=410;

    /*generateKeyWord
    parameters: key-key for cipher, limit - length of word to repeat to
    returns - key that matches the length of the word that beings encrypted
    takes in a word and repeats it until it matches the limit
     */
    private static String generateKeyWord(String key, int limit){
        String temp = "";
        for(int i=0;i<limit;i++){
            temp+= key.charAt(i%6);
        }
        return temp;
    }

    //more variables needed
    private static Paragraph information = new Paragraph("The Vigenère Cipher is another way of encryption and decryption. "+
            "This method uses a graph like the one on screen now. The X and Y axis are both the alphabet. Looking at the X axis you can see that each line is the alphabet starting at that letter. "+
    " So for the third line, C, the alphabet has been shifted twice and now starts at C, with A & B being moved to the end. "+
    "The way this cipher method works is that there is a keyword(a word used as a key) and a message. ");

    private static Paragraph howItWorks = new Paragraph("In this example we will use the word 'cookie' as the keyword."+
            "You can see our message at the bottom of the screen, 'Cryptography is Fun'. First we repeat the keyword so that we have the same number " + "" +
            "of letters as the message (we remove the spaces),so 'CryptographyisFun' has 17 letters, meaning 'cookie' becomes 'cookiecookiecooki'."+
    "To encrypt we compare the letters to the graph. The letter of the keyword is the X axis, the letter of the message is the Y axis, so "+
    "for the first letter we look at square (c,c), meaning we replace it with E (the letter at square (c,c)). We do this for the entire message to get the encrypted message. "+
    "For decrypting we look at the X axis for the letter (c in this case) and move along until we reach the letter from the encrypted message, then we get the y axis value to get the original letter.");

    private static Paragraph notice = new Paragraph("Math whizzes will notice something here. If A=0, then C+C =2+2=4(mod 26) = E for encryption, and E-C=4-2=2(mod 26)=C for decryption. Try this out for the above example.");

    private static Header graph =new Header("Vigenère Graph");

    //getters
    public static String getKey() {
        return key;
    }

    public static String getMessage() {
        return message;
    }

    public static String getNoSpaces() {
        return noSpaces;
    }

    public static String getKeyword() {
        return keyword;
    }

    public static Paragraph getInformation() {
        return information;
    }

    public static Paragraph getHowItWorks() {
        return howItWorks;
    }

    public static int getTEXTWIDTH() {
        return TEXTWIDTH;
    }

    public static Header getGraph() {
        return graph;
    }

    public static String getEncrypted() {
        return encrypted;
    }

    public static Paragraph getNotice() {
        return notice;
    }


    /*encrypt
    parameters: text- text to encrypt, key- keyword to use for encryption
    returns: encrypted word
    Goes through the word to encrypt and encrypts it using the keyword
    Uses the maths specified in variable 'notice'
    */
    private static String encrypt(String text, String key)
    {
        String res = "";
        text = text.toUpperCase();
        key=key.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++)
        {
            char c = text.charAt(i);
            res += (char) (((c-'A') + (key.charAt(j)-'A')) % 26 + 'A');
            j = ++j % key.length();
        }
        return res;
    }

}
