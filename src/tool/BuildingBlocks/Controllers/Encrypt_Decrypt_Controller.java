package tool.BuildingBlocks.Controllers;

import tool.Models.Paragraph;

/**
 * Created by Phillipa on 14/10/2015.
 */
public class Encrypt_Decrypt_Controller extends BuildingBlockController{
    final static Paragraph encryptPara = new Paragraph("Encryption is my speciality! Encryption is the art of concealing the contents of a message. "+
            " This is achieved by changing each letter in the message to something else using a key. This key tends to be a number "+
            " which is used in a formula which is decided by the encryption method. You can check out some of the other building  "+
            " blocks for more information. Once I've encrypted something I pass it to my little brother Decrypt.");

    final static Paragraph decryptPara = new Paragraph("I know everything about Decryption! Decryption happens when a message has been encrypted. My "+
            "sister transforms the message into gibberish, and it's my job to transform it back into the original message. I "+
            "need to use my key and a special formula on each letter to transform it back. My key doesn't need to be the same "+
            "key Encrypt used, it can be totally different! Try out Encryption and Decryption below.");

    final static int TEXTWIDTH=300;

    public static int getTEXTWIDTH() {
        return TEXTWIDTH;
    }

    public static Paragraph getEncryptPara() {
        return encryptPara;
    }

    public static Paragraph getDecryptPara() {
        return decryptPara;
    }

    public Encrypt_Decrypt_Controller() {
    }
}
