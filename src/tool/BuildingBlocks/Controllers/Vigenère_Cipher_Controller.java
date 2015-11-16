package tool.BuildingBlocks.Controllers;

import tool.Models.Header;
import tool.Models.Paragraph;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 15/11/2015.
 */
public class Vigenère_Cipher_Controller extends BuildingBlockController {

    static String key = "cookie";
    static String message ="Cryptography is Fun";
    static String noSpaces =message.replaceAll("\\s", "");
    static String keyword = generateKeyWord(key, noSpaces.length());
    static String encrypted =encrypt(noSpaces,key);
    static int TEXTWIDTH=400;

    private static String generateKeyWord(String key, int limit){
        String temp = "";
        for(int i=0;i<limit;i++){
            temp+= key.charAt(i%6);
        }
        return temp;
    }

    static Paragraph information = new Paragraph("The Vigenère Cipher is another way of encryption and decryption. "+
            "This method uses a graph like the one on screen now. The X and Y axis are both the alphabet. Looking at the X axis you can see that each line starts at the letter specified, "+
    "the alphabet has been shifted. So for the third line, C, the alphabet has been shifted twice and now starts at C, with A & B being moved to the end. "+
    "The way this cipher method works is that there is a keyword and a message. ");

    static Paragraph howItWorks = new Paragraph("The keyword is a word that will be used as the key. In this example we will use the word 'cookie'."+
            "You can see our message at the bottom of the screen, 'Cryptography is Fun'. First we repeat the keyword so that we have the same number " + "" +
            "of letters as the message (we remove the spaces),so 'CryptographyisFun' has 17 letters, meaning 'cookie' becomes 'cookiecookiecookiecoo'."+
    "To encrypt we compare the letters to the graph. The letter of the keyword is the X axis, the letter of the message is the Y axis, so "+
    "for the first letter we look at square (c,c), meaning we replace it with E (the letter at square (c,c)). We do this for the entire message to get the encrypted message. "+
    "For decrypting we look at the X axis for the letter (c in this case) and move along until we reach the letter from the encrypted message, then we get the y axis value to get the original letter.");

    static Header graph =new Header("Vigenère Graph");

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

    public static String encrypt(String text, String key)
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
