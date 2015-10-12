package tool.BuildingBlocks;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Created by Phillipa on 10/10/2015.
 */
public class Encrypt_Decrypt {



    public static void start(BorderPane bp) {

    }


    public void setUpLeft(BorderPane bp){
        VBox vb= new VBox();
        vb.setSpacing(30);

        Text title = new Text("Encryption and Decryption");
        textSettings(title);

        Text desc = new Text("The heart of all cryptography methods is encryption and decryption. "+
                            "This is the ability to transform a message into a form that cannot be read "+
                            "until it is decrypted. Most methods use a key for this which is used on the "+
                            "message to encrypt and decrypt it. This key is normally kept secret.");
        textSettings(desc);

        vb.getChildren().addAll(title,desc);
        bp.setLeft(vb);


    }


    public void setUpRight(BorderPane bp) {

    }


    public void textSettings(Text text){
        text.setStyle("-fx-font-size:20");
        text.setWrappingWidth(250);
        text.setTextAlignment(TextAlignment.valueOf("CENTER"));
        text.setFont(Font.font("Arial"));

    }
}
