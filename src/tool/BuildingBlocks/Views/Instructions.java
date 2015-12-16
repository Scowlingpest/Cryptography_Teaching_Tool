package tool.BuildingBlocks.Views;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import tool.BuildingBlocks.Controllers.Instructions_Controller;
import tool.CryptoMethods.Views.AnimationMethods;
import tool.Graphics.Robot;
import tool.Graphics.Speechbubble;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 13/12/2015.
 */
public class Instructions {

    public static void start(BorderPane bp,Boolean button){
        Pane p = new Pane();
        p.setPrefSize(1150,650);

        Robot encrypt = Instructions_Controller.getEncrypt();
        Robot decrypt = Instructions_Controller.getDecrypt();

        AnimationMethods.placeRobots(decrypt, p, 1000, 175);
        AnimationMethods.placeRobots(encrypt, p, 50, 175);

        encrypt.setImageWidth(175);decrypt.setImageWidth(175);

        setupBubbles(p);
        setupText(button,p);
        bp.setCenter(p);

    }

    private static void setupBubbles(Pane p){
        Speechbubble helloEncrypt=new Speechbubble("bl",
                Instructions_Controller.getHelloEncrypt(), Instructions_Controller.getBubbleSize(),
                Instructions_Controller.getX1(),Instructions_Controller.getY1());
        Speechbubble topControls=new Speechbubble("tl",
                Instructions_Controller.getTopControls(), Instructions_Controller.getBubbleSize(),
                Instructions_Controller.getX1()+100,Instructions_Controller.getY2());
        Speechbubble bottomControls=new Speechbubble("tc",
                Instructions_Controller.getBottomControls(), Instructions_Controller.getBubbleSize()+20,
                0,Instructions_Controller.getY3());

        Speechbubble helloDecrypt=new Speechbubble("br",
                Instructions_Controller.getHelloDecrypt(), Instructions_Controller.getBubbleSize(),
                Instructions_Controller.getX2(),Instructions_Controller.getY1());
        Speechbubble buildingBlocks=new Speechbubble("tr",
                Instructions_Controller.getBuildingBlocks(), Instructions_Controller.getBubbleSize(),
                Instructions_Controller.getX2()-100,Instructions_Controller.getY2());
        Speechbubble interactive=new Speechbubble("tc",
                Instructions_Controller.getInteractive(), Instructions_Controller.getBubbleSize(),
                900,Instructions_Controller.getY3());

        p.getChildren().addAll(helloEncrypt.getSp(),topControls.getSp(),bottomControls.getSp(),
                helloDecrypt.getSp(),buildingBlocks.getSp(),interactive.getSp());

    }

    private static void setupText(Boolean button, Pane p){
        Rectangle rect = new Rectangle(400,250,450,100);
        rect.getStyleClass().add("rectangle-neither");

        Text intro = new Text("Cryptography Teaching \n Tool");
        intro.setLayoutX(400);intro.setLayoutY(300);
        intro.getStyleClass().add("text-welcome");

        if (button){
            intro.setText("Instructions");
            intro.getStyleClass().add("text-instructions");
            intro.setLayoutX(425);intro.setLayoutY(325);
        }

        p.getChildren().addAll(rect,intro);
    }
}
