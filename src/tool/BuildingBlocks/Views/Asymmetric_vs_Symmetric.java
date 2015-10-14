package tool.BuildingBlocks.Views;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import tool.BuildingBlocks.Controllers.Asymmetric_vs_Symmetric_Controller;
import tool.Graphics.Robot;
import tool.Graphics.Titled_Information;
import tool.Models.Header;
import tool.Models.Paragraph;

/**
 * Created by Phillipa on 10/10/2015.
 */
public class Asymmetric_vs_Symmetric{


    public static void start(BorderPane bp) {
        setUpLeft(bp);
        setUpRight(bp);
    }


    public static void setUpLeft(BorderPane bp) {

        VBox vb = new VBox();
        vb.getStyleClass().add("vbox");
        Robot decrypt = Asymmetric_vs_Symmetric_Controller.getDecrypt();

        vb.getChildren().add(setUpSpeechBubble(decrypt, Asymmetric_vs_Symmetric_Controller.getDecryptPara()));

        Robot encrypt =Asymmetric_vs_Symmetric_Controller.getEncrypt();


        vb.getChildren().add(setUpSpeechBubble(encrypt,Asymmetric_vs_Symmetric_Controller.getEncryptPara()));


        bp.setLeft(vb);


    }

    public static VBox organiseText(Header h,Paragraph p, int width){
        return new Titled_Information(h,p,width).getVb();



    }

    public static StackPane setUpSpeechBubble(Robot robot,Paragraph text){
        StackPane sp =new StackPane();
        backgroundSetup(600,275,sp,robot.getStyle());

        HBox hb = new HBox();
        hb.getChildren().add(robot.getView());

        VBox vb = organiseText(robot.getTitle(),
                text, Asymmetric_vs_Symmetric_Controller.getTEXTWIDTH());

        hb.getStyleClass().add("hbox");

        hb.getChildren().add(vb);
        sp.getChildren().add(hb);
        return sp;
    }

    public static void setUpRight(BorderPane bp) {
        StackPane sp = new StackPane();
        VBox finished = new VBox();
        finished.getStyleClass().add("vbox");
        backgroundSetup(500, 600, sp, "rectangle-neither");

        VBox vbEncrypt = new VBox();
        final ToggleGroup first=setUpVBox(Asymmetric_vs_Symmetric_Controller.getEncryptKey(),vbEncrypt);
        VBox vbDecrypt = new VBox();
        final ToggleGroup second =setUpVBox(Asymmetric_vs_Symmetric_Controller.getDecryptKey(),vbDecrypt);


        HBox hb = new HBox();
        hb.getChildren().addAll(vbEncrypt,vbDecrypt);


        Button button = new Button(Asymmetric_vs_Symmetric_Controller.getButtonText());


        final VBox vb = organiseText(
                Asymmetric_vs_Symmetric_Controller.getTryItOut().getHeader(),
                Asymmetric_vs_Symmetric_Controller.getTryItOut().getParagraph(),
                Asymmetric_vs_Symmetric_Controller.getTEXTWIDTH());


        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String expected1 =(String)first.getSelectedToggle().getUserData();
                String expected2 =(String)second.getSelectedToggle().getUserData();
                changeBottom(expected1.equals(expected2),vb);

            }
        });

        finished.getChildren().addAll(hb,button,vb);
        sp.getChildren().add(finished);
        sp.setPadding(new Insets(10,10,10,10));

        bp.setRight(sp);




    }


    public static void backgroundSetup(int width, int height, StackPane sp,String style){
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(height);
        rectangle.setWidth(width);
        rectangle.getStyleClass().add(style);

        sp.getChildren().add(rectangle);
    }

    public static ToggleGroup setUpVBox(Header title, VBox vb){

        vb.getStyleClass().add("vbox");
        ToggleGroup radioBtns=radioButtonSetup(vb,title.getTitle());
        return radioBtns;
    }
    public static ToggleGroup radioButtonSetup(VBox radio, String title){
        ToggleGroup group = new ToggleGroup();

        RadioButton rb1 = new RadioButton("Key A");
        rb1.setToggleGroup(group);
        rb1.setUserData("P");
        rb1.setSelected(true);

        RadioButton rb2 = new RadioButton("Key B");
        rb2.setToggleGroup(group);
        rb2.setUserData("S");

        Text t=new Text(title);
        t.getStyleClass().add("text-title");

        radio.getChildren().addAll(t, rb1, rb2);
        return group;
    }

    public static void changeBottom(Boolean b,VBox vb){
        if (b){

                vb =organiseText(Asymmetric_vs_Symmetric_Controller.getSymmetric().getHeader(),
                        Asymmetric_vs_Symmetric_Controller.getSymmetric().getParagraph(),
                        Asymmetric_vs_Symmetric_Controller.getTEXTWIDTH());
            }
            else{
            vb =organiseText(Asymmetric_vs_Symmetric_Controller.getAsymmetric().getHeader(),
                    Asymmetric_vs_Symmetric_Controller.getAsymmetric().getParagraph(),
                    Asymmetric_vs_Symmetric_Controller.getTEXTWIDTH());
            }
        }

    }



