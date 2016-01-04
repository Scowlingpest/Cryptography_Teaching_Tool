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

public class Prime_Numbers {


    public static void start(BorderPane bp) {

        GridPane gridPane = new GridPane();
        VBox vb=new VBox();
        vb.getStyleClass().add("vbox");


        setUpLeft(bp);
        setUpRight(bp);

        StackPane sp = new StackPane();
        Prime_Numbers_Controller.backgroundSetup(600, 650, sp, "rectangle-neither");

        Text title =new Text("Prime Numbers");
        title.getStyleClass().add("text-title");




        ColumnConstraints cc = new ColumnConstraints();
        cc.setPercentWidth(20);
        gridPane.getColumnConstraints().add(cc);
        gridPane.setVgap(15);
        gridPane.setHgap(15);

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
        gridPane.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(title,gridPane);
        sp.getChildren().add(vb);
        bp.setCenter(sp);

    }


    public static void setUpLeft(BorderPane bp){
        Robot encrypt= Prime_Numbers_Controller.getEncrypt();

        VBox vb = Prime_Numbers_Controller.organiseText(encrypt.getTitle(), Prime_Numbers_Controller.getLeftPara(), Prime_Numbers_Controller.getTextWidth());
        vb.getChildren().add(encrypt.getView());


        StackPane sp = new StackPane();
        Prime_Numbers_Controller.backgroundSetup(275, 650, sp, encrypt.getStyle());

        sp.getChildren().add(vb);
        bp.setLeft(sp);

    }

    public static void setUpRight(BorderPane bp){

        Robot decrypt= Prime_Numbers_Controller.getDecrypt();

        VBox vb = Prime_Numbers_Controller.organiseText(decrypt.getTitle(), Prime_Numbers_Controller.getRightPara(), Prime_Numbers_Controller.getTextWidth());
        vb.getChildren().add(decrypt.getView());

        StackPane sp = new StackPane();
        Prime_Numbers_Controller.backgroundSetup(275, 650, sp, decrypt.getStyle());
        sp.getChildren().add(vb);

        bp.setRight(sp);

    }



}


