package tool.BuildingBlocks.Views;


import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import tool.BuildingBlocks.Controllers.Prime_Numbers_Controller;
import tool.BuildingBlocks.Controllers.Stream_vs_Block_Controller;
import tool.Graphics.Block_Cipher;
import tool.Graphics.Robot;
import tool.Graphics.Stream_Cipher;
import tool.Models.Paragraph;


/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 12/11/2015.
 */

//stream vs block building block view
public class Stream_vs_Block {

    /*start method, setups the borderpane
    parameters : bp- borderpane to setup
    returns: null
     */
    public static void start(BorderPane bp) {
        setUpLeft(bp);
        setUpRight(bp);
    }

    /*setUpLeft, setups the left hand side of the screen with the stream stuff
    parameters: bp- borderpane to add to
    returns: null
     */
    private static void setUpLeft(BorderPane bp){
        Pane left = new Pane();

        //setup stream animation
        Stream_Cipher stream_cipher = new Stream_Cipher();
        stream_cipher.getStream().setLayoutX(250);
        stream_cipher.getStream().setLayoutY(300);

        //setup play button
        Button playStream = new Button("Play Stream animation");
        playStream.setOnAction(event->{
            if (stream_cipher.getStreamAnimation().getCurrentRate()==0.0d){
                stream_cipher.getStreamAnimation().playFromStart();}
        });
        playStream.setLayoutX(300);playStream.setLayoutY(575);

        //adds robot and information
        Robot encrypt= Prime_Numbers_Controller.getEncrypt();
        StackPane sp = robotSetup(encrypt,Stream_vs_Block_Controller.getStreamPara());


        left.getChildren().addAll(sp, stream_cipher.getStream(),playStream);

        bp.setLeft(left);
    }

    /*setUpRight, setups the right hand side of the screen with the block stuff
    parameters: bp- borderpane to add to
    returns: null
     *///
    private static void setUpRight(BorderPane bp){
        Pane right = new Pane();

        //setups the block cipher animation
        Block_Cipher block_cipher = new Block_Cipher();
        block_cipher.getBlock().setLayoutX(250);
        block_cipher.getBlock().setLayoutY(275);

        //setups the play button
        Button playBlock = new Button("Play Block animation");
        playBlock.setOnAction(event->{
            if (block_cipher.getAnimation().getCurrentRate()==0.0d){
                block_cipher.getAnimation().playFromStart();
            }
        });
        playBlock.setLayoutX(300);playBlock.setLayoutY(575);

        //setups the robot and information
        Robot decrypt= Prime_Numbers_Controller.getDecrypt();
        StackPane sp=robotSetup(decrypt, Stream_vs_Block_Controller.getBlockPara());

        right.getChildren().addAll(sp, block_cipher.getBlock(),playBlock);

        bp.setRight(right);

    }

    /*robotSetup, setups the robot with their information
    parameters: r- robot to put on screen, p - text to display
    returns: stackpane with robot, information, title and background
     */
    private static StackPane robotSetup(Robot r,Paragraph p){
        VBox vb = Prime_Numbers_Controller.organiseText(r.getTitle(), p, Stream_vs_Block_Controller.getTextWidth());
        vb.getChildren().add(r.getView());

        vb.getStyleClass().add("vbox");


        StackPane sp = new StackPane();
        Stream_vs_Block_Controller.backgroundSetup(Stream_vs_Block_Controller.getRectWidth(), Stream_vs_Block_Controller.getRectHeight(), sp, r.getStyle());
        sp.setPadding(new Insets(20));

        sp.getChildren().add(vb);
        return sp;
    }


}
