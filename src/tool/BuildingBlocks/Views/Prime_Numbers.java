package tool.BuildingBlocks.Views;

import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import tool.BuildingBlocks.Controllers.Prime_Numbers_Controller;
import tool.Graphics.Box;
import tool.Graphics.Robot;


/** Author : Phillipa Russell
 *  Created: 08/10/2015
 */
//prime numbers building block view
public class Prime_Numbers {


    /*start method, setups the borderpane
    parameters : bp- borderpane to setup
    returns: null
     */
    public static void start(BorderPane bp) {
        //sets up the left and right of the borderpane
        setUpLeft(bp);
        setUpRight(bp);
        setUpCenter(bp);

    }

    /*setUpCenter, setups the prime number box in the center of the screen
    parameters: bp- borderpane to add to
    returns: null
     */
    private static void setUpCenter(BorderPane bp){
        GridPane gridPane = new GridPane();
        VBox vb=new VBox();
        vb.getStyleClass().add("vbox");


        //setups the center sections background
        StackPane sp = new StackPane();
        Prime_Numbers_Controller.backgroundSetup(600, 650, sp, "rectangle-neither");

        Text title =new Text("Prime Numbers");
        title.getStyleClass().add("text-title");

        //sets up the prime number grid layout
        ColumnConstraints cc = new ColumnConstraints();
        cc.setPercentWidth(20);
        gridPane.getColumnConstraints().add(cc);
        gridPane.setVgap(15);
        gridPane.setHgap(15);

        //puts all the boxes in the grid
        int k = 0;
        for (int i = 1; i < 6; i++) {
            for (int j = 0; j < 5; j++) {

                Box temp = new Box(Prime_Numbers_Controller.getPrime(k));
                temp.drawBox(Prime_Numbers_Controller.getTooltip());
                temp.setUpForPrime();
                gridPane.add(temp.getSp(),j,i);

                k++;

            }
        }
        //adds all elements to a Vbox, adds the VBox to the stackpane and sets as the center of the borderpane
        gridPane.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(title,gridPane);
        sp.getChildren().add(vb);
        bp.setCenter(sp);

    }

    /*setUpLeft, creates the left hand side of the screen with the Encrypt
    parameters: bp- borderpane to add to
    returns: null
     */
    private static void setUpLeft(BorderPane bp){
        Robot encrypt= Prime_Numbers_Controller.getEncrypt();

        //gets the left text from the controller and adds to a VBox with the robot
        VBox vb = Prime_Numbers_Controller.organiseText(encrypt.getTitle(), Prime_Numbers_Controller.getLeftPara(), Prime_Numbers_Controller.getTextWidth());
        vb.getChildren().add(encrypt.getView());

        //sets up the background for the left
        StackPane sp = new StackPane();
        Prime_Numbers_Controller.backgroundSetup(275, 650, sp, encrypt.getStyle());

        //combines text & robot with background, sets to left of borderpane
        sp.getChildren().add(vb);
        bp.setLeft(sp);

    }

    /*setUpRight,sets up the right side of the screen where Decrypt is
    parameters: np - borderpane to add things too
    returns :null
     */
    private static void setUpRight(BorderPane bp){
        Robot decrypt= Prime_Numbers_Controller.getDecrypt();

        //gets the text for th right hand side form the controller, adds to VBox with robot
        VBox vb = Prime_Numbers_Controller.organiseText(decrypt.getTitle(), Prime_Numbers_Controller.getRightPara(), Prime_Numbers_Controller.getTextWidth());
        vb.getChildren().add(decrypt.getView());

        //sets up the background for the right
        StackPane sp = new StackPane();
        Prime_Numbers_Controller.backgroundSetup(275, 650, sp, decrypt.getStyle());

        //combines text & robot with background, sets to right of borderpane
        sp.getChildren().add(vb);
        bp.setRight(sp);

    }



}


