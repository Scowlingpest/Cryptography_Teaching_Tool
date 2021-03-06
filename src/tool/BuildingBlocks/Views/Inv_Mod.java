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
//Inverted modulus building block view
public class Inv_Mod {


    /*start method, setups the borderpane
    parameters : bp- borderpane to setup
    returns: null
     */
    public static void start(BorderPane bp) {

        setUpLeft(bp);
        setUpRight(bp);
        setUpCenter(bp);

    }

    /*setUpCenter, setups the animation in the center of the screen
    parameters: bp- borderpane to add to
    returns: null
     */
    private static void setUpCenter(BorderPane bp){
        VBox vb=new VBox();
        vb.getStyleClass().add("vbox");

        //setups animation
        StackPane sp = new StackPane();
        drawBackground(500, sp,"rectangle-neither");

        Text title =new Text("Inverse Modulus of Number 16");
        title.getStyleClass().add("text-title");

        vb.setSpacing(1);
        HBox hb = new HBox();
        ComboBox<Integer> choose = new ComboBox<>();
        choose.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);
        choose.setValue(3);

        Button play = new Button("Click to see if it is an inverted modulus of 16");
        hb.getChildren().addAll(choose,play);

        play.setOnAction(event->{
            int j=choose.getValue();
            Inv_Mod_Controller.playTransition(j, vb, hb);

        });


        vb.getChildren().addAll(title, hb);

        sp.getChildren().add(vb);
        bp.setCenter(sp);
    }


    /*setUpRight, creates the right hand side of the screen with encrypt
    parameters: bp- borderpane to add to
    returns: null
     */
    private static void setUpRight(BorderPane bp){
        Robot encrypt= Inv_Mod_Controller.getEncrypt();
        encrypt.setImageWidth(165);

        VBox vb = Inv_Mod_Controller.organiseText(encrypt.getTitle(), Inv_Mod_Controller.getRightPara(), Inv_Mod_Controller.getTextWidth());
        vb.getChildren().add(encrypt.getView());


        StackPane sp = new StackPane();
        drawBackground(325, sp,encrypt.getStyle());

        sp.getChildren().add(vb);
        bp.setRight(sp);

    }

    /*setUpLeft, creates the left hand side of the screen with the decrypt
    parameters: bp- borderpane to add to
    returns: null
     */
    private static void setUpLeft(BorderPane bp){

        Robot decrypt= Inv_Mod_Controller.getDecrypt();
        decrypt.setImageWidth(165);

        VBox vb = Inv_Mod_Controller.organiseText(decrypt.getTitle(), Inv_Mod_Controller.getLeftPara(), Inv_Mod_Controller.getTextWidth());
        vb.getChildren().add(decrypt.getView());

        StackPane sp = new StackPane();
        drawBackground(325, sp,decrypt.getStyle());
        sp.getChildren().add(vb);

        bp.setLeft(sp);

    }


    /*drawBackground,draws the rectangle backgrounds
    parameters: x- width, y- height, sp- stackpane to add to, style- style of rectangle
    returns: null
     */
    private static void drawBackground(int x, StackPane sp, String style){
        Rectangle r=new Rectangle();
        r.setY(10);
        r.setWidth(x);
        r.setHeight(650);
        r.getStyleClass().add(style);
        sp.getChildren().add(r);

    }
}
