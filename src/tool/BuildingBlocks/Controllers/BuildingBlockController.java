package tool.BuildingBlocks.Controllers;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import tool.Graphics.Robot;
import tool.Graphics.Titled_Information;
import tool.Models.Header;
import tool.Models.Paragraph;

/** Author : Phillipa Russell
 *  Created: 13/10/2015
 */

//Master controller for the building blocks
public abstract class BuildingBlockController {

    //two robots and there getters
    private final static Robot encrypt = new Robot("encrypt", "rectangle-encrypt", new String[]{"Hello! I'm Encrypt.", "", ""}, new Header("Encrypt Says:"));

    private final static Robot decrypt = new Robot("decrypt", "rectangle-decrypt", new String[]{"Hello! I'm Decrypt.", "", ""}, new Header("Decrypt Says:"));

    public static Robot getEncrypt() {
        return encrypt;
    }

    public static Robot getDecrypt() {
        return decrypt;
    }

    //organises the header and the paragraph into a titled piece of information
    public static VBox organiseText(Header h,Paragraph p, int width){
        return new Titled_Information(h,p,width).getVb();
    }

    //setups rectangles for use in the background
    public static void backgroundSetup(int width, int height, StackPane sp,String style){
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(height);
        rectangle.setWidth(width);
        rectangle.getStyleClass().add(style);

        sp.getChildren().add(rectangle);
    }

}
