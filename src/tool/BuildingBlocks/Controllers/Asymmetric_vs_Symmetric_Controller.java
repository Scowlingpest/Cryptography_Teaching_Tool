package tool.BuildingBlocks.Controllers;

import tool.Models.Header;
import tool.Models.Header_Paragraph;
import tool.Models.Paragraph;

/** Author : Phillipa Russell
 *  Created: 14/10/2015
 */
public class Asymmetric_vs_Symmetric_Controller extends BuildingBlockController {

    /*This is the controller class for the Asymmetric vs Symmetric building block.
    It just contains texts and the getters for the text.
     */


    //final variables needed for building block
    private final static int TEXTWIDTH=350;

    private final static Paragraph decryptPara = new Paragraph("Asymmetric methods are cryptography methods in which the key for "+
            "encryption and decryption are different. This is normally achieved by having a public key and "+
            "a secret key. One will be used to encrypt the information and the other will be used to decrypt "+
            "it. ");

    private final static Paragraph encryptPara = new Paragraph("Symmetric methods are cryptography methods in which the key for encryption "+
            "and decryption are the same. This is achieved by using the public key and/or the secret key for both "+
            "encryption and decryption. Normally it is a secret key that is used which is shared amongst all users. ");

    private final static String buttonText = "Click Here!";

    private final static Header_Paragraph tryItOut = new Header_Paragraph("Try it Out!","There are 2 keys above, Key A and Key B. Give Encrypt and Decrypt different combinations of keys, click the button and find out what type of cryptography method it is.");

    private final static Header encryptKey =new Header("Encrypt's Key");

    private final static Header decryptKey = new Header("Decrypt's Key");

    private final static Header_Paragraph symmetric= new Header_Paragraph("Symmetric",("When both users have the same key, whether it is secret or not, then the method type is symmetric. "+
                             "This means that the same key is being used for encrypting the message and decrypting. If the key is public then "+
                             "ideally anyone can encrypt or decrypt the information. Normally a public key is used symmetrically only as an "+
                             "extra layer of encryption. Secret key pairs are used most commonly, either singularly or with extra layers of public "+
                             "keys or secret keys."));

    private final static Header_Paragraph asymmetric = new Header_Paragraph("Asymmetric",("When the users have different keys for decryption and encryption then the method is asymmetric. "+
            "This means that different keys are being used for encrypting the message and decrypting it. Normally a public "+
            "key is used for encrypting and a secret key is used for decrypting. This is achieved in most methods by taking "+
            "advantage of mathematic laws."));

    public Asymmetric_vs_Symmetric_Controller() {
    }

    //getters
    public static Paragraph getDecryptPara() {
        return decryptPara;
    }

    public static Paragraph getEncryptPara() {
        return encryptPara;
    }

    public static int getTEXTWIDTH() {
        return TEXTWIDTH;
    }

    public static String getButtonText() {
        return buttonText;
    }

    public static Header_Paragraph getTryItOut() {
        return tryItOut;
    }

    public static Header getEncryptKey() {
        return encryptKey;
    }

    public static Header getDecryptKey() {
        return decryptKey;
    }

    public static Header_Paragraph getSymmetric() {
        return symmetric;
    }

    public static Header_Paragraph getAsymmetric() {
        return asymmetric;
    }
}
