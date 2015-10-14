package tool.BuildingBlocks.Views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import tool.BuildingBlocks.Controllers.BuildingBlockController;
import tool.BuildingBlocks.Controllers.Encrypt_Decrypt_Controller;
import tool.Graphics.Arrow;
import tool.Graphics.Robot;
import tool.Graphics.Titled_Information;
import tool.Models.Header;
import tool.Models.Paragraph;


/**
 * Created by Phillipa on 10/10/2015.
 */
public class Encrypt_Decrypt {
    final static int TEXTWIDTH=300;



    public static void start(BorderPane bp) {

        setUpLeft(bp);
        setUpRight(bp);
        setUpBottom(bp);
    }


    public static void setUpLeft(BorderPane bp){

        bp.setLeft(encryptInfo());


    }
    public static VBox organiseText(Header h,Paragraph p, int width){
        return new Titled_Information(h,p,width).getVb();



    }

    public static void setUpRight(BorderPane bp) {
        bp.setRight(decryptInfo());
    }


    public static StackPane encryptInfo(){
        Robot encrypt = Encrypt_Decrypt_Controller.getEncrypt();
        StackPane sp = new StackPane();
        Paragraph encryptPara = Encrypt_Decrypt_Controller.getEncryptPara();
        return info(sp,encrypt,encryptPara);
    }

    public static StackPane decryptInfo() {
        Robot decrypt =Encrypt_Decrypt_Controller.getDecrypt();
        StackPane sp = new StackPane();
        Paragraph decryptPara = Encrypt_Decrypt_Controller.getDecryptPara();

        return info(sp,decrypt,decryptPara);
    }

    public static StackPane info(StackPane sp, Robot robot, Paragraph information){
        backgroundSetup(550,400,sp,robot.getStyle());

        HBox hb = new HBox();
        hb.getStyleClass().add("hbox");


        hb.getChildren().add(robot.getView());
        VBox vb = organiseText(robot.getTitle(), information,Encrypt_Decrypt_Controller.getTEXTWIDTH());

        hb.getChildren().add(vb);
        sp.getChildren().add(hb);
        return sp;
    }

    public static void backgroundSetup(int width, int height, StackPane sp,String style){
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(height);
        rectangle.setWidth(width);
        rectangle.getStyleClass().add(style);

        sp.getChildren().add(rectangle);
    }

    public static void setUpBottom(BorderPane bp){
        StackPane sp = new StackPane();
        backgroundSetup(1100,250,sp,"rectangle-neither");


        GridPane textFields= new GridPane();

        encryptToDecrypt(textFields);
        sp.getChildren().add(textFields);
        bp.setBottom(sp);

    }

    public static void encryptToDecrypt(GridPane gp){
        TextField name = new TextField();
        name.setText("Enter your name here!");


        Arrow arrow =new Arrow(0,15,100,15);
        gp.add(name,0,1);
        gp.add(arrow.getC(),1,1);

        TextField encrypted = new TextField();
        encrypted.setDisable(true);
        Arrow next = new Arrow(10,15,100,15);
        gp.add(encrypted,2,1);
        gp.add(next.getC(),3,1);



    }
}
