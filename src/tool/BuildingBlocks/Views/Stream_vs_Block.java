package tool.BuildingBlocks.Views;


import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import tool.BuildingBlocks.Controllers.PrimeNumbersController;
import tool.BuildingBlocks.Controllers.Stream_vs_Block_Controller;
import tool.Graphics.Block_Cipher;
import tool.Graphics.Stream_Cipher;
import tool.Graphics.Robot;
import tool.Models.Paragraph;


/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 12/11/2015.
 */
public class Stream_vs_Block {

    public static void start(BorderPane bp) {
        setUpLeft(bp);
        setUpRight(bp);
    }

    private static void setUpLeft(BorderPane bp){
        Pane left = new Pane();

        Stream_Cipher stream_cipher = new Stream_Cipher(75);
        stream_cipher.getStream().setLayoutX(250);
        stream_cipher.getStream().setLayoutY(300);

        Button playStream = new Button("Play Stream animation");
        playStream.setOnAction(event-> stream_cipher.getStreamAnimation().playFromStart());
        playStream.setLayoutX(300);playStream.setLayoutY(575);

        Robot encrypt= PrimeNumbersController.getEncrypt();
        StackPane sp = robotSetup(encrypt,Stream_vs_Block_Controller.getStreamPara());


        left.getChildren().addAll(sp, stream_cipher.getStream(),playStream);

        bp.setLeft(left);
    }

    private static void setUpRight(BorderPane bp){
        Pane right = new Pane();

        Block_Cipher block_cipher = new Block_Cipher(60);
        block_cipher.getBlock().setLayoutX(250);
        block_cipher.getBlock().setLayoutY(275);

        Button playBlock = new Button("Play Block animation");
        playBlock.setOnAction(event-> block_cipher.getAnimation().playFromStart());
        playBlock.setLayoutX(300);playBlock.setLayoutY(575);


        Robot decrypt= PrimeNumbersController.getDecrypt();
        StackPane sp=robotSetup(decrypt, Stream_vs_Block_Controller.getBlockPara());

        right.getChildren().addAll(sp, block_cipher.getBlock(),playBlock);

        bp.setRight(right);

    }

    private static StackPane robotSetup(Robot r,Paragraph p){
        VBox vb = PrimeNumbersController.organiseText(r.getTitle(), p, Stream_vs_Block_Controller.getTextWidth());
        vb.getChildren().add(r.getView());

        vb.getStyleClass().add("vbox");


        StackPane sp = new StackPane();
        drawBackground(Stream_vs_Block_Controller.getRectWidth(),Stream_vs_Block_Controller.getRectHeight(),sp,r.getStyle());
        sp.setPadding(new Insets(20));

        sp.getChildren().add(vb);
        return sp;
    }
    public static void drawBackground(int x, int y,StackPane sp, String style){
        Rectangle r=new Rectangle();
        r.setWidth(x);
        r.setHeight(y);
        r.getStyleClass().add(style);
        sp.getChildren().add(r);

    }

}
