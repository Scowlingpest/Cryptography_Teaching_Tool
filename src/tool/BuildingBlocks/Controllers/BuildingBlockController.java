package tool.BuildingBlocks.Controllers;

import javafx.scene.layout.VBox;
import tool.Graphics.Robot;
import tool.Graphics.Titled_Information;
import tool.Models.Header;
import tool.Models.Paragraph;

/**
 * Created by Phillipa on 13/10/2015.
 */
public abstract class BuildingBlockController {

    final static Robot encrypt = new Robot("tool/Files/encrypt.png", "rectangle-encrypt", new String[]{"Hello! I'm Encrypt.", "", ""}, new Header("Encrypt Says:"), 0, 0);

    final static Robot decrypt = new Robot("tool/Files/decrypt.png", "rectangle-decrypt", new String[]{"Hello! I'm Decrypt.", "", ""}, new Header("Decrypt Says:"), 0, 0);

    public static Robot getEncrypt() {
        return encrypt;
    }

    public static Robot getDecrypt() {
        return decrypt;
    }

}
