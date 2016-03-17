package tool.CryptoMethods.Views.El_Gamal_Scenes;

import javafx.animation.SequentialTransition;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import tool.CryptoMethods.Controllers.EG_Controller;
import tool.CryptoMethods.Controllers.RSA_Controller;
import tool.CryptoMethods.Views.AnimationMethods;
import tool.Graphics.Monitor;
import tool.Graphics.Robot;
import tool.Graphics.Speechbubble;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 07/12/2015.
 */
//El Gamal step class, used to sort out which step to load
public class EG_Step {
    //combo box needed for step 2
    private static HBox combos = new HBox();
    private static ComboBox<Integer> pSelect = new ComboBox<>();
    private static ComboBox<Integer> qSelect = new ComboBox<>();
    private static ComboBox<Integer> aSelect = new ComboBox<>();
    private static ComboBox<Integer> bSelect = new ComboBox<>();

    /*createPane,creates the pane needed for the animation
    parameters: root - pane to add items to
    returns: null
    */
    public static void createPane(Pane root){
        Robot encrypt = RSA_Controller.getEncrypt();
        Robot decrypt = RSA_Controller.getDecrypt();
        Monitor evil = new Monitor(200,500,400);

        root.setPrefSize(1100,600);
        background(root);

        placeRobotsFirst(encrypt,decrypt,root);

        root.setPadding(new Insets(0,100,0,100));
        root.getChildren().add(evil.getImage());

    }

    /*placeRobotsFirst, places the robots in their first location
    parameters: e,d - robots to place, p - pane to add robots to
    returns: null
    */
    private static void placeRobotsFirst(Robot e, Robot d, Pane p){
        AnimationMethods.placeRobots(d, p, 50, 175);
        AnimationMethods.placeRobots(e, p, 1030, 175);
    }

    /*background, setups the background of the animation
    parameters: p -pane to add backgrounds too
    returns: null
    */
    private static void background(Pane p){
        int start =20;
        Rectangle left = new Rectangle(start,50,400,550);
        left.getStyleClass().add("rectangle-decrypt");

        Rectangle middle = new Rectangle(start=+430,50,350,550);
        middle.getStyleClass().add("rectangle-neither");

        Rectangle right = new Rectangle(start+360,50,400,550);
        right.getStyleClass().add("rectangle-encrypt");



        p.getChildren().addAll(left,right,middle);

    }

    /*createTransition, creates the animation, call DH_Animation to create the animation
    parameters: root- pane to add elements to
    returns: sequential transition with all the animations
    */
    public static SequentialTransition createTransition(Pane root, int step) {
        SequentialTransition st = new SequentialTransition();
        Speechbubble bubble;
        if(step==2) {
            bubble = new Speechbubble("bl", EG_Controller.getStep2welcome(), 250, 125, 20);
            comboBoxSetup(root);
        }
        else{
            bubble = new Speechbubble("bl", EG_Controller.getStep1welcome(), 250, 125, 20);
        }

        root.getChildren().add(bubble.getSp());
        EG_Animation.animationCreate(root, st, step, bubble);
        return st;
    }

    /*comboBoxSetup, setups the values for the combo boxes for step 2
    parameters: root - pane to add the comboboxes to
    returns: null
     */
    private static void comboBoxSetup(Pane root){
        pSelect = new ComboBox<>();
        pSelect.getItems().addAll(EG_Controller.getGenerators().keySet());
        pSelect.setValue(7);


        qSelect = new ComboBox<>();
        qSelect.getItems().addAll(EG_Controller.getGenerators().get(7));
        qSelect.setValue(3);

        pSelect.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            qSelect.getItems().clear();
            qSelect.getItems().addAll(EG_Controller.getGenerators().get(newValue));
            qSelect.setValue(EG_Controller.getGenerators().get(newValue)[0]);
        });

        aSelect = new ComboBox<>();
        bSelect = new ComboBox<>();
        for (int i=1;i<=15;i++){
            aSelect.getItems().add(i);
            bSelect.getItems().add(i);
        }
        aSelect.setValue(10);
        bSelect.setValue(14);
        combos = new HBox();

        VBox line1 = new VBox();VBox line2 = new VBox();
        VBox line3 = new VBox();VBox line4 = new VBox();

        line1.getChildren().addAll(new Label("p"),pSelect);line2.getChildren().addAll(new Label("q"),qSelect);
        line3.getChildren().addAll(new Label("a"),aSelect); line4.getChildren().addAll( new Label("b"),bSelect);

        combos.setLayoutX(465);
        combos.setLayoutY(200);

        combos.getChildren().addAll(line1,line2,line3,line4);
        root.getChildren().add(combos);
    }

    /*clearCombos, removes the combo boxes from the screen
    parameters: root- pane to remove combo boxes from
    returns: null
     */
    public static void clearCombos(Pane root){
        root.getChildren().remove(combos);

    }

    /*getValues, gets the combobox values and setups the animation with the new values
    parameters: null
    returns: null
     */
    public static void getValues(){
        int[] temp =comboBoxValues();
        EG_Controller.setUpAnimation(temp[0],temp[1],temp[2],temp[3]);
    }

    /*comboBoxValues, gets the values for all comboboxes
    parameters: null
    returns: array of ints from comboboxes
     */
    private static int[] comboBoxValues(){
        return new int[]{pSelect.getValue(),qSelect.getValue(),
                aSelect.getValue(),bSelect.getValue()};
    }

}

