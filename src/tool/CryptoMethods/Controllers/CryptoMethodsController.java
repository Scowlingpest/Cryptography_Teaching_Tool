package tool.CryptoMethods.Controllers;

import tool.Graphics.Robot;
import tool.Models.Header;

/** Author : Phillipa Russell
 *  Created: 21/10/2015
 */
public class CryptoMethodsController {

    final static Robot encrypt = new Robot("tool/Files/Images/Robots/encrypt", "rectangle-encrypt", new String[]{"Hello! I'm Encrypt.", "", ""}, new Header("Encrypt Says:"), 0, 0);

    final static Robot encrypt2 = new Robot("tool/Files/Images/Robots/encrypt", "rectangle-encrypt", new String[]{"Hello! I'm another Encrypt.", "", ""}, new Header("Encrypt Says:"), 0, 0);

    final static Robot decrypt = new Robot("tool/Files/Images/Robots/decrypt", "rectangle-decrypt", new String[]{"Hello! I'm Decrypt.", "", ""}, new Header("Decrypt Says:"), 0, 0);

    public static Robot getEncrypt() {
        return encrypt;
    }

    public static Robot getEncrypt2(){return encrypt2;}

    public static Robot getDecrypt() {
        return decrypt;
    }

}
