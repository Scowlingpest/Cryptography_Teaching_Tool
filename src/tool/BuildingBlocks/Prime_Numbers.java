package tool.BuildingBlocks;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import tool.Graphics.Box;
import tool.Graphics.Robot;

/**
 * Created by Phillipa on 08/10/2015.
 */
public class Prime_Numbers {


    public static void start(BorderPane bp) {
        GridPane gridPane = new GridPane();


        setUpLeft(bp);
        setUpRight(bp);

        Text title =new Text("Prime Numbers");
        title.setStyle("-fx-font-size:20");
        gridPane.add(title,2,0);


        ColumnConstraints cc = new ColumnConstraints();
        cc.setPercentWidth(20);
        gridPane.getColumnConstraints().add(cc);
        gridPane.setVgap(15);
        gridPane.setHgap(15);

        int k = 1;
        int x = 30;
        int y = 30;
        for (int i = 1; i < 6; i++) {
            for (int j = 0; j < 5; j++) {

                Box temp = new Box(Integer.toString(k), isPrime(k));
                temp.drawBox(0,0,
                        "Think this is a prime number? Click and find out",
                        "Well done! This is a prime as \n it can only be divided by itself \n and 1!",
                        "Nope! Sorry, this number can be divided\n by something other than itself and 1.\n Try again");
                temp.setUpForPrime();
                gridPane.add(temp.getSp(),j,i);

                k++;

            }
        }
        gridPane.setAlignment(Pos.CENTER);
        bp.setCenter(gridPane);

    }

    public static boolean isPrime(int n){
        for (int i =2;i<n;i++){
            if(n%i==0){
                return false;
            }
        }
        return true;
    }



    public static void setUpLeft(BorderPane bp){
        Text left = new Text("A prime number is a number than can only be divided by itself and 1." +
                "They are often used in maths and are used in a lot of cryptography methods\n"+
                "If a number can be divided by anything other than itself and 1, then it is \n" +
                "not a prime number. For example 6 is not a prime number because it can be"+
                "divided by 1,2,3,6. 7 is a prime number because it can only be divided by"+
                "itself (7) and 1.");


        VBox vb = new VBox();

        Robot encrypt =new Robot("tool/Files/encrypt.png",Color.web("#cededa"),0,0,"Hello, I'm Encrypt!","Encrypt Says:");
        encrypt.setImageWidth(175);
        vb.getChildren().add(0, encrypt.getView());

        organiseText(left,encrypt.getTitle(),Color.GREEN,vb);
        StackPane sp = new StackPane();
        drawBackground(sp,encrypt.getColor());
        sp.getChildren().add(vb);
        bp.setLeft(sp);
    }

    public static void setUpRight(BorderPane bp){

        Text right =new Text("Click the numbered boxes and see if you can guess which are prime "+
                            " If the box turns red then you've clicked a number which is not prime"+
                            "If the box turns green then you've clicked a prime number. Hover your mouse"+
                            "over the boxes to get a small explanation of why they are/aren't prime.");

        Robot decrypt =new Robot("tool/Files/decrypt.png",Color.web("#dbccd0"),0,0,"Hello, I'm Decrypt!","Decrypt Says:");
        decrypt.setImageWidth(175);


        VBox vb =new VBox();
        organiseText(right, decrypt.getTitle(), Color.RED,vb);


        vb.getChildren().add(2, decrypt.getView());

        StackPane sp = new StackPane();
        drawBackground(sp,decrypt.getColor());
        sp.getChildren().add(vb);

        bp.setRight(sp);
    }

    public static void textSettings(Text text){
        text.setStyle("-fx-font-size:18");
        text.setWrappingWidth(250);
        text.setTextAlignment(TextAlignment.valueOf("CENTER"));
        text.setFont(Font.font("Arial"));

    }

    public static void organiseText(Text s,Text title, Color c, VBox vb){

        vb.setPadding(new Insets(10,10,10,10));

        textSettings(s);

        textSettings(title);
        title.setStyle("-fx-font-size:22");
        title.setFill(c);
        vb.getChildren().add(title);
        vb.getChildren().add(s);

        vb.setSpacing(30);



    }

    public static void drawBackground(StackPane sp, Color c){
        Rectangle r=new Rectangle();
        r.setY(10);
        r.setWidth(250);
        r.setHeight(650);
        r.setArcWidth(40);
        r.setArcHeight(40);
        r.setFill(c);
        sp.getChildren().add(r);

    }


}


