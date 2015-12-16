package tool.BuildingBlocks.Views;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import tool.BuildingBlocks.Controllers.Inv_Mod_Controller;
import tool.Graphics.Robot;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 13/12/2015.
 */
public class Inv_Mod {


    public static void start(BorderPane bp) {

        VBox vb=new VBox();
        vb.getStyleClass().add("vbox");


        setUpLeft(bp);
        setUpRight(bp);

        StackPane sp = new StackPane();
        drawBackground(500,650,sp,"rectangle-neither");

        Text title =new Text("Inverse Modulus of Number 17");
        title.getStyleClass().add("text-title");

        vb.setSpacing(1);
        HBox hb = new HBox();
        ComboBox<Integer> choose = new ComboBox<>();
        choose.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16);
        choose.setValue(3);

        Button play = new Button("Click to see if it is an inverted modulus of 17");
        hb.getChildren().addAll(choose,play);

        play.setOnAction(event->{
            int j=choose.getValue();
            Inv_Mod_Controller.playTransition(j, vb, hb);

        });


        vb.getChildren().addAll(title, hb);

        sp.getChildren().add(vb);
        bp.setCenter(sp);

    }
    public static void setUpRight(BorderPane bp){
        Robot encrypt= Inv_Mod_Controller.getEncrypt();

        VBox vb = Inv_Mod_Controller.organiseText(encrypt.getTitle(), Inv_Mod_Controller.getRightPara(), Inv_Mod_Controller.getTextWidth());
        vb.getChildren().add(encrypt.getView());


        StackPane sp = new StackPane();
        drawBackground(325,650,sp,encrypt.getStyle());

        sp.getChildren().add(vb);
        bp.setRight(sp);

    }

    public static void setUpLeft(BorderPane bp){

        Robot decrypt= Inv_Mod_Controller.getDecrypt();

        VBox vb = Inv_Mod_Controller.organiseText(decrypt.getTitle(), Inv_Mod_Controller.getLeftPara(), Inv_Mod_Controller.getTextWidth());
        vb.getChildren().add(decrypt.getView());

        StackPane sp = new StackPane();
        drawBackground(325,650,sp,decrypt.getStyle());
        sp.getChildren().add(vb);

        bp.setLeft(sp);

    }


    public static void drawBackground(int x, int y,StackPane sp, String style){
        Rectangle r=new Rectangle();
        r.setY(10);
        r.setWidth(x);
        r.setHeight(y);
        r.getStyleClass().add(style);
        sp.getChildren().add(r);

    }
}
