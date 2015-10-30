package tool.CryptoMethods.Views;

import javafx.scene.layout.BorderPane;
import tool.CryptoMethods.Controllers.RSA_Controller;
import tool.Graphics.Robot;


/**
 * Created by Phillipa on 21/10/2015.
 */
public class RSA {


    public static void start(BorderPane bp){
        Robot encrypt = RSA_Controller.getEncrypt();
        Robot decrypt = RSA_Controller.getDecrypt();


    }
}
