package tool.CryptoMethods.Controllers;

import tool.Graphics.Robot;
import tool.Models.Header;

/**
 * Created by Phillipa on 21/10/2015.
 */
public class CryptoMethodsController {

    final static Robot encrypt = new Robot("tool/Files/Images/encrypt.png", "rectangle-encrypt", new String[]{"Hello! I'm Encrypt.", "", ""}, new Header("Encrypt Says:"), 0, 0);

    final static Robot decrypt = new Robot("tool/Files/Images/decrypt.png", "rectangle-decrypt", new String[]{"Hello! I'm Decrypt.", "", ""}, new Header("Decrypt Says:"), 0, 0);

    public static Robot getEncrypt() {
        return encrypt;
    }

    public static Robot getDecrypt() {
        return decrypt;
    }

}
