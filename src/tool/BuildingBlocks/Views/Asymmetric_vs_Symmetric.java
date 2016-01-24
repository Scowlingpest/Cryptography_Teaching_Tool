package tool.BuildingBlocks.Views;


import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import tool.BuildingBlocks.Controllers.Asymmetric_vs_Symmetric_Controller;
import tool.Graphics.Robot;
import tool.Models.Header;
import tool.Models.Paragraph;

/** Author : Phillipa Russell
 *  Created: 10/10/2015
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

        vb.getChildren().add(setUpInformation(decrypt, Asymmetric_vs_Symmetric_Controller.getDecryptPara()));

        Robot encrypt =Asymmetric_vs_Symmetric_Controller.getEncrypt();


        vb.getChildren().add(setUpInformation(encrypt, Asymmetric_vs_Symmetric_Controller.getEncryptPara()));


        bp.setLeft(vb);


    }


    public static StackPane setUpInformation(Robot robot, Paragraph text){
        StackPane sp =new StackPane();
        Asymmetric_vs_Symmetric_Controller.backgroundSetup(600,300,sp,robot.getStyle());

        HBox hb = new HBox();
        hb.getChildren().add(robot.getView());

        VBox vb = Asymmetric_vs_Symmetric_Controller.organiseText(robot.getTitle(),
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
        Asymmetric_vs_Symmetric_Controller.backgroundSetup(500, 600, sp, "rectangle-neither");

        VBox vbEncrypt = new VBox();
        final ToggleGroup first=setUpVBox(Asymmetric_vs_Symmetric_Controller.getEncryptKey(),vbEncrypt);
        VBox vbDecrypt = new VBox();
        final ToggleGroup second =setUpVBox(Asymmetric_vs_Symmetric_Controller.getDecryptKey(),vbDecrypt);


        HBox hb = new HBox();
        hb.getChildren().addAll(vbEncrypt,vbDecrypt);


        Button button = new Button(Asymmetric_vs_Symmetric_Controller.getButtonText());


        VBox vb = Asymmetric_vs_Symmetric_Controller.organiseText(
                Asymmetric_vs_Symmetric_Controller.getTryItOut().getHeader(),
                Asymmetric_vs_Symmetric_Controller.getTryItOut().getParagraph(),
                Asymmetric_vs_Symmetric_Controller.getTEXTWIDTH());

        finished.getChildren().addAll(hb,button, vb);

        button.setOnAction((ActionEvent event) -> changeVB(first,second,finished));


        sp.getChildren().add(finished);
        sp.setPadding(new Insets(10,10,10,10));

        bp.setRight(sp);




    }

    public static void changeVB(ToggleGroup first, ToggleGroup second, VBox vb){
        String expected1 =(String)first.getSelectedToggle().getUserData();
        String expected2 =(String)second.getSelectedToggle().getUserData();
        vb.getChildren().remove(2);
        vb.getChildren().add(changeBottom(expected1.equals(expected2)));

    }

    public static ToggleGroup setUpVBox(Header title, VBox vb){

        vb.getStyleClass().add("vbox");
        return radioButtonSetup(vb,title.getTitle());
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

    public static VBox changeBottom(Boolean b) {
        if (b) {

            return(Asymmetric_vs_Symmetric_Controller.organiseText(
                    Asymmetric_vs_Symmetric_Controller.getSymmetric().getHeader(),
                    Asymmetric_vs_Symmetric_Controller.getSymmetric().getParagraph(),
                    Asymmetric_vs_Symmetric_Controller.getTEXTWIDTH()));
        }
            return Asymmetric_vs_Symmetric_Controller.organiseText(
                    Asymmetric_vs_Symmetric_Controller.getAsymmetric().getHeader(),
                    Asymmetric_vs_Symmetric_Controller.getAsymmetric().getParagraph(),
                    Asymmetric_vs_Symmetric_Controller.getTEXTWIDTH());

    }

    }



