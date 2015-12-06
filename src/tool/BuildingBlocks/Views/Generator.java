package tool.BuildingBlocks.Views;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import tool.BuildingBlocks.Controllers.Generator_controller;
import tool.Graphics.Robot;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 02/12/2015.
 */
public class Generator {
    public static void start(BorderPane bp) {
        setUpRight(bp);
        setUpLeft(bp);
    }

    private static void setUpLeft(BorderPane bp) {
        StackPane sp = new StackPane();

        drawBackground(650,625,sp,"rectangle-neither");
        sp.setPadding(new Insets(10));

        VBox vb  = new VBox();
        HBox hb = new HBox();
        ComboBox<Integer> choose = new ComboBox<>();
        choose.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16);
        choose.setValue(3);

        Button play = new Button("Click to see if it's modulus");
        play.setOnAction(event->{
            Generator_controller.playTransition(choose.getValue(),vb);
        });

        hb.getChildren().addAll(choose,play);
        vb.getChildren().add(hb);

        sp.getChildren().add(vb);

        bp.setLeft(sp);
    }

    private static void setUpRight(BorderPane bp) {

        Robot decrypt = Generator_controller.getDecrypt();
        Robot encrypt= Generator_controller.getEncrypt();
        VBox vb = new VBox();
        vb.getStyleClass().add("vbox");
        vb.getChildren().add(drawRobot(encrypt));
        vb.getChildren().add(drawRobot(decrypt));


        bp.setRight(vb);

    }

    private static StackPane drawRobot(Robot r){
        StackPane sp = new StackPane();
        drawBackground(500,300,sp,r.getStyle());
        sp.getChildren().add(r.getView());
        return sp;
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
