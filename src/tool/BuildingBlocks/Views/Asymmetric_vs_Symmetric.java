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
//Asymmetric vs symmetric building block view class
public class Asymmetric_vs_Symmetric{


    /*start method, setups the borderpane
    parameters : bp- borderpane to setup
    returns: null
     */
    public static void start(BorderPane bp) {
        setUpLeft(bp);
        setUpRight(bp);
    }


    /*setUpLeft,setups the left side, which has both robots and their information
    parameters: bp - borderpane to add to
    returns: null
     */
    public static void setUpLeft(BorderPane bp) {
        VBox vb = new VBox();
        vb.getStyleClass().add("vbox");

        //setups decrypts box of info
        Robot decrypt = Asymmetric_vs_Symmetric_Controller.getDecrypt();
        vb.getChildren().add(setUpInformation(decrypt, Asymmetric_vs_Symmetric_Controller.getDecryptPara()));

        //setups encrypts box of info
        Robot encrypt =Asymmetric_vs_Symmetric_Controller.getEncrypt();
        vb.getChildren().add(setUpInformation(encrypt, Asymmetric_vs_Symmetric_Controller.getEncryptPara()));


        bp.setLeft(vb);
    }


    /*setUpInformation,setups the info box with a robot and text
      parameters: robot - robot to add to screen, text- text to display
      returns: stackpane with robot and information with background and header
     */
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

    /*setUpRight, setups the right hand side with the interactive element
    parameter: bp- bp to add to
    returns: null
     */
    public static void setUpRight(BorderPane bp) {
        StackPane sp = new StackPane();
        VBox finished = new VBox();
        finished.getStyleClass().add("vbox");
        Asymmetric_vs_Symmetric_Controller.backgroundSetup(500, 600, sp, "rectangle-neither");

        //two toggle groups for the keys
        VBox vbEncrypt = new VBox();
        final ToggleGroup first=setUpVBox(Asymmetric_vs_Symmetric_Controller.getEncryptKey(),vbEncrypt);
        VBox vbDecrypt = new VBox();
        final ToggleGroup second =setUpVBox(Asymmetric_vs_Symmetric_Controller.getDecryptKey(),vbDecrypt);


        HBox hb = new HBox();
        hb.getChildren().addAll(vbEncrypt,vbDecrypt);

        //button for activating the change
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

    /* changeVB,changes the text based on the selected radio buttons
    parameters: first-first set of radio buttons, second - second group of radio buttons
                vb - vbox to change information in
    returns: null
     */
    public static void changeVB(ToggleGroup first, ToggleGroup second, VBox vb){
        String expected1 =(String)first.getSelectedToggle().getUserData();
        String expected2 =(String)second.getSelectedToggle().getUserData();
        vb.getChildren().remove(2);
        vb.getChildren().add(changeBottom(expected1.equals(expected2)));

    }

    /*setUpVBox, setups the vbox containing the radio buttons
    parameters: title- title to put at top of vbox, vb- vbox to setup
    returns - radiobuttons
     */
    public static ToggleGroup setUpVBox(Header title, VBox vb){
        vb.getStyleClass().add("vbox");
        return radioButtonSetup(vb,title.getTitle());
    }

    /*radioButtonSetup,setups the radio buttons
    parameters: radio- vbox to add radio buttons too,title - title of radio buttons
    returns - the radio buttons
     */
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

    /*changeBottom,changes the bottom segement of text based on the radio buttons
    parameters: b- boolean for whether radio button=radio button, if true then display symmetric
    returns: vbox with displayed information
     */
    public static VBox changeBottom(Boolean b) {
        if (b) {
            //if same keys selected, show symmetric
            return(Asymmetric_vs_Symmetric_Controller.organiseText(
                    Asymmetric_vs_Symmetric_Controller.getSymmetric().getHeader(),
                    Asymmetric_vs_Symmetric_Controller.getSymmetric().getParagraph(),
                    Asymmetric_vs_Symmetric_Controller.getTEXTWIDTH()));
        }
            //it different keys, show asymmetric
            return Asymmetric_vs_Symmetric_Controller.organiseText(
                    Asymmetric_vs_Symmetric_Controller.getAsymmetric().getHeader(),
                    Asymmetric_vs_Symmetric_Controller.getAsymmetric().getParagraph(),
                    Asymmetric_vs_Symmetric_Controller.getTEXTWIDTH());

    }

    }



