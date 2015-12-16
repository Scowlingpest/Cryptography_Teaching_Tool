package tool.BuildingBlocks.Views;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import tool.BuildingBlocks.Controllers.Generator_controller;
import tool.Graphics.Robot;
import tool.Models.Paragraph;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 02/12/2015.
 */
public class Generator {
    static StackPane decryptStack = new StackPane();

    public static void start(BorderPane bp) {
        setUpRight(bp);
        setUpLeft(bp);
    }

    private static void setUpLeft(BorderPane bp) {
        StackPane sp = new StackPane();

        drawBackground(650,625,sp,"rectangle-neither");
        sp.setPadding(new Insets(10));

        VBox vb  = new VBox();
        vb.setSpacing(5);
        HBox hb = new HBox();
        ComboBox<Integer> choose = new ComboBox<>();
        choose.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16);
        choose.setValue(3);

        Button play = new Button("Click to see if it's modulus");
        hb.getChildren().addAll(choose,play);

        play.setOnAction(event->{
            int j=choose.getValue();
            Generator_controller.playTransition(j,vb,hb);
            decryptSays(j);
        });


        vb.getChildren().add(hb);

        sp.getChildren().add(vb);
        vb.setPadding(new Insets(40,0,0,40));

        bp.setLeft(sp);
    }



    private static void setUpRight(BorderPane bp) {

        Robot decrypt = Generator_controller.getDecrypt();
        Robot encrypt= Generator_controller.getEncrypt();
        VBox vb = new VBox();
        vb.getStyleClass().add("vbox");
        vb.getChildren().add(drawRobot(encrypt,Generator_controller.getBackground()));
        decryptStack=drawRobot(decrypt,"Play a number and i'll tell you if it is a generator or not");
        vb.getChildren().add(decryptStack);


        bp.setRight(vb);

    }

    private static StackPane drawRobot(Robot r,String s){
        StackPane sp = new StackPane();
        drawBackground(500,300,sp,r.getStyle());
        VBox vb = Generator_controller.organiseText(r.getTitle(), new Paragraph(s),290);
        HBox hb = new HBox();
        hb.getChildren().addAll(vb, r.getView());
        sp.getChildren().add(hb);
        return sp;
    }

    private static void drawBackground(int x, int y,StackPane sp, String style){
        Rectangle r=new Rectangle();
        r.setY(10);
        r.setWidth(x);
        r.setHeight(y);
        r.getStyleClass().add(style);
        sp.getChildren().add(r);

    }

    private static void decryptSays(int i) {
        String s = "";
        switch (i){
            case 2:case 13:
                s=Generator_controller.getNotGenerator();
                break;
            case 1:case 4:case 8:case 9:case 15:
                s=Generator_controller.getNotPrimeGenerator();
                break;
            case 3:case 5:case 7:case 11:
                s=Generator_controller.getGenerator();
                break;
            case 6:case 10:case 12:case 14:
                s=Generator_controller.getModulus();
                break;

        };
        changeText(s,decryptStack);
    }

    private static void changeText(String text, StackPane sp){
        HBox hb =(HBox)sp.getChildren().get(1);
        VBox vb = (VBox)hb.getChildren().get(0);
        Text p = (Text)vb.getChildren().get(1);
        p.setText(text);


    }
}
