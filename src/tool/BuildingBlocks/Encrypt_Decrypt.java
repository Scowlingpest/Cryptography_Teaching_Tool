package tool.BuildingBlocks;


import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import tool.Graphics.Arrow;
import tool.Graphics.Robot;


/**
 * Created by Phillipa on 10/10/2015.
 */
public class Encrypt_Decrypt {



    public static void start(BorderPane bp) {
        setUpLeft(bp);
        setUpRight(bp);
        setUpBottom(bp);
    }


    public static void setUpLeft(BorderPane bp){

        bp.setLeft(encryptInfo());


    }


    public static void setUpRight(BorderPane bp) {
        bp.setRight(decryptInfo());
    }


    public static void textSettings(Text text){
        text.setStyle("-fx-font-size:16");
        text.setWrappingWidth(300);
        text.setTextAlignment(TextAlignment.valueOf("LEFT"));
        text.setFont(Font.font("Arial"));

    }

    public static StackPane encryptInfo(){
        Robot encrypt =new Robot("tool/Files/encrypt.png",Color.web("#cededa"),0,0,"Hello, I'm Encrypt!","Encrypt Says:");
        StackPane sp = new StackPane();
        Text speech = new Text("Encryption is my speciality! Encryption is the art of concealing the contents of a message. "+
        "This is achieved by changing each letter in the message to something else using a key. This key tends to be a number"+
        " which is used in a formula which is decided by the encryption method. You can check out some of the other building "+
        "blocks for more information. Once I've encrypted something I pass it to my little brother Decrypt.");
        return info(sp,encrypt,speech);
    }

    public static StackPane decryptInfo() {
        Robot decrypt = new Robot("tool/Files/decrypt.png", Color.web("#8aa7cc"), 0, 0, "Hello, I'm Decrypt!", "Decrypt Says:");
        StackPane sp = new StackPane();
        Text speech = new Text("I know everything about Decryption! Decryption happens when a message has been encrypted. My "+
        "sister transforms the message into gibberish, and it's my job to transform it back into the original message. I "+
        "need to use my key and a special formula on each letter to transform it back. My key doesn't need to be the same "+
        "key Encrypt used, it can be totally different! Try out Encryption and Decryption below.");
        return info(sp,decrypt,speech);
    }

    public static StackPane info(StackPane sp, Robot robot, Text information){
        backgroundSetup(550,400,sp,robot.getColor());

        robot.setImageWidth(175);
        HBox hb = new HBox();
        VBox vb = new VBox();
        vb.setSpacing(20);
        vb.setPadding(new Insets(20,10,10,10));
        hb.setPadding(new Insets(20,10,10,10));


        hb.getChildren().add(robot.getView());

        Text title=robot.getTitle();
        textSettings(title);
        textSettings(information);

        vb.getChildren().addAll(title,information);
        hb.getChildren().add(vb);
        sp.getChildren().add(hb);
        return sp;
    }

    public static void backgroundSetup(int width, int height, StackPane sp,Color c){
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(height);
        rectangle.setWidth(width);
        rectangle.setFill(c);
        rectangle.setArcHeight(height/3);
        rectangle.setArcWidth(width/3);

        sp.getChildren().add(rectangle);
    }

    public static void setUpBottom(BorderPane bp){
        StackPane sp = new StackPane();
        backgroundSetup(1100,250,sp,Color.web("#c7afc7"));

        HBox textFields= new HBox();
        encryptToDecrypt(textFields);
        sp.getChildren().add(textFields);
        bp.setBottom(sp);

    }

    public static void encryptToDecrypt(HBox hb){
        TextField encrypt = new TextField();
        encrypt.setText("Enter your name here!");
        Arrow arrow =new Arrow(0,10,20,10);
        hb.getChildren().addAll(encrypt, arrow.getC());



    }
}
