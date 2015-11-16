package tool.CryptoMethods.Views.Diffie_HellmanScenes;

import javafx.animation.SequentialTransition;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import tool.CryptoMethods.Controllers.DH_Controller;
import tool.CryptoMethods.Controllers.RSA_Controller;
import tool.CryptoMethods.Views.AnimationMethods;
import tool.Graphics.Robot;
import tool.Graphics.Speechbubble;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 09/11/2015.
 */
public class DH_Step_1 {

    public static void createPane(Pane root){
        Robot encrypt = RSA_Controller.getEncrypt();
        Robot decrypt = RSA_Controller.getDecrypt();

        root.setPrefSize(1100,600);
        background(root);

        placeRobotsFirst(encrypt,decrypt,root);

        root.setPadding(new Insets(0,100,0,100));

    }

    public static void placeRobotsFirst(Robot e, Robot d, Pane p){
        AnimationMethods.placeRobots(d, p, 950, 175);
        AnimationMethods.placeRobots(e, p, 50, 175);
    }

    public static void background(Pane p){
        Rectangle left = new Rectangle(30,50,550,550);
        left.getStyleClass().add("rectangle-encrypt");

        Rectangle right = new Rectangle(600,50,550,550);
        right.getStyleClass().add("rectangle-decrypt");

        p.getChildren().addAll(left,right);

    }

    public static SequentialTransition createTimeLine(Pane root) {
        SequentialTransition st = new SequentialTransition();

        Speechbubble bubble = new Speechbubble("bl",DH_Controller.getStep1welcome(),250,125,20);
        root.getChildren().add(bubble.getSp());




        return st;
    }

}
