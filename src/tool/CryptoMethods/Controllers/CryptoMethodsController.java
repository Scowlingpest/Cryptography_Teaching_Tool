package tool.CryptoMethods.Controllers;

import javafx.collections.ObservableList;
import tool.Graphics.Robot;
import tool.Models.DataRow;
import tool.Models.Header;

/** Author : Phillipa Russell
 *  Created: 21/10/2015
 */
//Cryptography method master controller class
public class CryptoMethodsController {

    //robots used
    private final static Robot encrypt = new Robot("encrypt", "rectangle-encrypt", new String[]{"Hello! I'm Encrypt.", "", ""}, new Header("Encrypt Says:"));

    private final static Robot encrypt2 = new Robot("encrypt", "rectangle-encrypt", new String[]{"Hello! I'm another Encrypt.", "", ""}, new Header("Encrypt Says:"));

    private final static Robot decrypt = new Robot("decrypt", "rectangle-decrypt", new String[]{"Hello! I'm Decrypt.", "", ""}, new Header("Decrypt Says:"));

    public static Robot getEncrypt() {
        return encrypt;
    }

    public static Robot getEncrypt2(){return encrypt2;}

    public static Robot getDecrypt() {
        return decrypt;
    }

    /*addToTable, adds a new row to a table
    parameters: n-string to add, v- number to add, data-table to add to
    returns: null
     */
    public static void addToTable(String n,double v,ObservableList<DataRow> data){
        data.add(new DataRow(n,String.valueOf(v)));

    }

}
