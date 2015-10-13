package tool.BuildingBlocks;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import tool.Graphics.Robot;

/**
 * Created by Phillipa on 10/10/2015.
 */
public class Asymmetric_vs_Symmetric{
    final static int TEXTWIDTH=350;



    public static void start(BorderPane bp) {
        setUpLeft(bp);
        setUpRight(bp);
    }


    public static void setUpLeft(BorderPane bp) {

        VBox vb = new VBox();
        Robot decrypt =new Robot("decrypt",0,0);
        Text information = new Text("Asymmetric methods are cryptography methods in which the key for "+
                "encryption and decryption are different. This is normally achieved by having a public key and "+
                "a secret key. One will be used to encrypt the information and the other will be used to decrypt "+
                "it. ");
        vb.getChildren().add(setUpSpeechBubble(decrypt, information));

        Robot encrypt =new Robot("encrypt",0,0);
        Text symmetric = new Text("Symmetric methods are cryptography methods in which the key for encryption "+
        "and decryption are the same. This is achieved by using the public key and/or the secret key for both "+
        "encryption and decryption. Normally it is a secret key that is used which is shared amongst all users. ");

        vb.getChildren().add(setUpSpeechBubble(encrypt,symmetric));
        vb.getStyleClass().add("vbox");

        bp.setLeft(vb);


    }


    public static StackPane setUpSpeechBubble(Robot robot,Text text){
        StackPane sp =new StackPane();
        backgroundSetup(600,275,sp,robot.getStyle());
        robot.setImageWidth(175);

        HBox hb = new HBox();
        hb.getChildren().add(robot.getView());
        robot.getTitle().getStyleClass().add("text-title");


        text.getStyleClass().add("text-main");
        text.setWrappingWidth(TEXTWIDTH);


        VBox vBox = new VBox();
        vBox.getChildren().addAll(robot.getTitle(),text);
        vBox.getStyleClass().add("vbox");
        hb.getStyleClass().add("hbox");

        hb.getChildren().add(vBox);
        sp.getChildren().add(hb);
        return sp;
    }

    public static void setUpRight(BorderPane bp) {
        StackPane sp = new StackPane();
        VBox finished = new VBox();
        finished.getStyleClass().add("vbox");
        backgroundSetup(500, 600, sp, "rectangle-neither");

        VBox vbEncrypt = new VBox();
        final ToggleGroup first=setUpVBox("Encrypt's key",vbEncrypt);
        VBox vbDecrypt = new VBox();
        final ToggleGroup second =setUpVBox("Decrypt's key",vbDecrypt);


        HBox hb = new HBox();
        hb.getChildren().addAll(vbEncrypt,vbDecrypt);


        Button button = new Button("Click here");


        final Text type =new Text("Symmetric");
        final Text description = new Text("When both users have the same key, whether it is secret or not, then the method type is symmetric ");
        description.setWrappingWidth(400);


        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String expected1 =(String)first.getSelectedToggle().getUserData();
                String expected2 =(String)second.getSelectedToggle().getUserData();
                changeBottom(expected1.equals(expected2),type,description);

            }
        });

        finished.getChildren().addAll(hb,button,type,description);
        sp.getChildren().add(finished);
        sp.setPadding(new Insets(10,10,10,10));

        bp.setRight(sp);




    }


    public static void backgroundSetup(int width, int height, StackPane sp,String style){
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(height);
        rectangle.setWidth(width);
        rectangle.getStyleClass().add(style);

        sp.getChildren().add(rectangle);
    }

    public static ToggleGroup setUpVBox(String title, VBox vb){

        vb.setPadding(new Insets(60,20,20,20));
        vb.setSpacing(20);
        ToggleGroup radioBtns=radioButtonSetup(vb,title);
        return radioBtns;
    }
    public static ToggleGroup radioButtonSetup(VBox radio, String title){
        ToggleGroup group = new ToggleGroup();

        RadioButton rb1 = new RadioButton("Public Key");
        rb1.setToggleGroup(group);
        rb1.setUserData("P");
        rb1.setSelected(true);

        RadioButton rb2 = new RadioButton("Secret Key");
        rb2.setToggleGroup(group);
        rb2.setUserData("S");



        radio.getChildren().addAll(new Text(title), rb1, rb2);
        return group;
    }

    public static void changeBottom(Boolean b, Text type, Text desc){
        if (b){

                type.setText("Symmetric");
                desc.setText("When both users have the same key, whether it is secret or not, then the method type is symmetric ");
            }
            else{
                type.setText("Asymmetric");
                desc.setText("When the users have different keys for decryption and encryption then the method is asymmetric ");
            }
        }

    }



