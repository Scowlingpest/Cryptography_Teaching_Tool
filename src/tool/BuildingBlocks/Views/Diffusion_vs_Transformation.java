package tool.BuildingBlocks.Views;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import tool.BuildingBlocks.Controllers.Diffusion_vs_Transformation_Controller;
import tool.BuildingBlocks.Controllers.Prime_Numbers_Controller;
import tool.Graphics.Column_Cipher;
import tool.Graphics.Robot;
import tool.Graphics.Transformation;
import tool.Models.Paragraph;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 20/12/2015.
 */
//diffusion vs transformation building block view class
public class Diffusion_vs_Transformation {

    /*start method, setups the borderpane
    parameters : bp- borderpane to setup
    returns: null
    */
    public static void start(BorderPane bp) {
        setUpLeft(bp);
        setUpRight(bp);
    }

    /*setUpLeft,setups the left side, which has the diffusion animation and information
    parameters: bp - borderpane to add to
    returns: null
     */
    private static void setUpLeft(BorderPane bp){
        Pane left = new Pane();

        //create column cipher
        Column_Cipher column_cipher = new Column_Cipher(65);
        column_cipher.getP().setLayoutX(250);
        column_cipher.getP().setLayoutY(250);

        //setup play button
        Button playColumn = new Button("Play Diffusion animation");
        playColumn.setOnAction(event -> {
            if (column_cipher.getAnimation().getCurrentRate()==0.0d){
            column_cipher.getAnimation().playFromStart();

        }});
        playColumn.setLayoutX(290);playColumn.setLayoutY(590);

        //set up robot and information
        Robot decrypt= Prime_Numbers_Controller.getDecrypt();
        StackPane sp = robotSetup(decrypt, Diffusion_vs_Transformation_Controller.getDiffusion());

        //add everything to screen
        left.getChildren().addAll(sp, column_cipher.getP(),playColumn);

        bp.setLeft(left);
    }

    /*setUpRight,setups the right side, which has the transformation animation and information
    parameters: bp - borderpane to add to
    returns: null
     */
    private static void setUpRight(BorderPane bp){
        Pane right = new Pane();

        //create transformation animation
        Transformation transformation = new Transformation();
        transformation.getP().setLayoutX(200);
        transformation.getP().setLayoutY(225);

        //setup play button
        Button playBlock = new Button("Play Transformation animation");
        playBlock.setOnAction(event-> {
            if (transformation.getAnimation().getCurrentRate()==0.0d){
            transformation.getAnimation().playFromStart();
        }});
        playBlock.setLayoutX(300);playBlock.setLayoutY(575);


        //setups the robot and the information
        Robot encrypt= Prime_Numbers_Controller.getEncrypt();
        StackPane sp=robotSetup(encrypt, Diffusion_vs_Transformation_Controller.getTransformation());

        //add everything to the screen
        right.getChildren().addAll(sp, transformation.getP(),playBlock);

        bp.setRight(right);

    }

    /*robotSetup, setup class for the two robots
    parameters: r- robot to put on screen, p -information the robot is 'saying'
    returns: stackpane with the robot, information and header on a rectangle
     */
    private static StackPane robotSetup(Robot r,Paragraph p){
        VBox vb = Prime_Numbers_Controller.organiseText(r.getTitle(), p, Diffusion_vs_Transformation_Controller.getTextWidth());
        vb.getChildren().add(r.getView());

        vb.getStyleClass().add("vbox");


        StackPane sp = new StackPane();
        Diffusion_vs_Transformation_Controller.backgroundSetup(Diffusion_vs_Transformation_Controller.getRectWidth(),Diffusion_vs_Transformation_Controller.getRectHeight(),sp,r.getStyle());
        sp.setPadding(new Insets(20));

        sp.getChildren().add(vb);
        return sp;
    }


}