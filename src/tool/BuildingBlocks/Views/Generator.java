package tool.BuildingBlocks.Views;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import tool.BuildingBlocks.Controllers.Generator_controller;
import tool.Graphics.Robot;
import tool.Models.Paragraph;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 02/12/2015.
 */
//Generator building block view
public class Generator {
    //variables needed later on
    static StackPane decryptStack = new StackPane();

    /*start method, setups the borderpane
    parameters : bp- borderpane to setup
    returns: null
     */
    public static void start(BorderPane bp) {
        setUpRight(bp);
        setUpLeft(bp);
    }

    /*setUpLeft,setups the left side with the equation animation
    actually covers from left to right due to bp rules
    parameters: bp - borderpane to add to
    returns: null
     */
    private static void setUpLeft(BorderPane bp) {
        StackPane sp = new StackPane();

       Generator_controller.backgroundSetup(650, 625, sp, "rectangle-neither");
        sp.setPadding(new Insets(10));

        //setups combobox
        VBox vb  = new VBox();
        vb.setSpacing(5);
        HBox hb = new HBox();
        ComboBox<Integer> choose = new ComboBox<>();
        choose.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16);
        choose.setValue(3);

        //adds play button
        Button play = new Button("Click to see if it's modulus");
        hb.getChildren().addAll(choose,play);

        //when play, create animation, play, change what decrypt is saying
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


    /*setUpRight,sets up the right hand side of the screen with the two robots
    actually covers from left to right due to bp rules
    parameters: bp - borderpane to add to
    returns: null
     */
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

    /*drawRobot,adds the robot to the screen with the data
    parameters: r- robot to add, s - string to display
    returns: stackpane with robot,information,header and background
     */
    private static StackPane drawRobot(Robot r,String s){
        StackPane sp = new StackPane();
        Generator_controller.backgroundSetup(500, 300, sp, r.getStyle());
        VBox vb = Generator_controller.organiseText(r.getTitle(), new Paragraph(s),290);
        HBox hb = new HBox();
        hb.getChildren().addAll(vb, r.getView());
        sp.getChildren().add(hb);
        return sp;
    }

    /*decryptSays, changes what decrypt says depending on inputted value
    parameters: i- value chosen by user to test
    returns: null
     */
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

        }
        changeText(s,decryptStack);
    }

    /*changeText,small method for changing the text decrypt uses
    parameters: text- text to use now, sp- stackpane to change
    returns: null
     */
    private static void changeText(String text, StackPane sp){
        HBox hb =(HBox)sp.getChildren().get(1);
        VBox vb = (VBox)hb.getChildren().get(0);
        Text p = (Text)vb.getChildren().get(1);
        p.setText(text);

    }
}
