package tool.BuildingBlocks.Views;

import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import tool.BuildingBlocks.Controllers.PrimeNumbersController;
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
        drawBackground(600,650,sp,"rectangle-neither");

        Text title =new Text("Prime Numbers");
        title.getStyleClass().add("text-title");




        ColumnConstraints cc = new ColumnConstraints();
        cc.setPercentWidth(20);
        gridPane.getColumnConstraints().add(cc);
        gridPane.setVgap(15);
        gridPane.setHgap(15);

        int k = 0;
        int x = 30;
        int y = 30;
        for (int i = 1; i < 6; i++) {
            for (int j = 0; j < 5; j++) {

                Box temp = new Box(PrimeNumbersController.getPrime(k));
                temp.drawBox(0, 0,
                        PrimeNumbersController.getTooltip());
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
        Robot encrypt=PrimeNumbersController.getEncrypt();

        VBox vb = PrimeNumbersController.organiseText(encrypt.getTitle(), PrimeNumbersController.getLeftPara(), PrimeNumbersController.getTextWidth());
        vb.getChildren().add(encrypt.getView());


        StackPane sp = new StackPane();
        drawBackground(275,650,sp,encrypt.getStyle());

        sp.getChildren().add(vb);
        bp.setLeft(sp);

    }

    public static void setUpRight(BorderPane bp){

        Robot decrypt= PrimeNumbersController.getDecrypt();

        VBox vb = PrimeNumbersController.organiseText(decrypt.getTitle(), PrimeNumbersController.getRightPara(), PrimeNumbersController.getTextWidth());
        vb.getChildren().add(decrypt.getView());

        StackPane sp = new StackPane();
        drawBackground(275,650,sp,decrypt.getStyle());
        sp.getChildren().add(vb);

        bp.setRight(sp);

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


