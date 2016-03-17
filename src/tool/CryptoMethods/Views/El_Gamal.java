package tool.CryptoMethods.Views;

import javafx.animation.SequentialTransition;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import tool.CryptoMethods.Controllers.EG_Controller;
import tool.CryptoMethods.Views.El_Gamal_Scenes.EG_Step;
import tool.Models.MonitoringMap;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 06/12/2015.
 */
//el gamal start class, begins steps
public class El_Gamal {

    //variables needed, are reset upon load
    private static Duration paused=Duration.seconds(0);
    private static Pane p = new Pane();
    private static SequentialTransition st =new SequentialTransition();
    private static int step =1;

    /*start, wipes screen, resets values and adds controls for El Gamal
    parameters: bp - borderpane to add to, monitor -monitoring hashmap for counting
    returns: null
    */
    public static void start(BorderPane bp,MonitoringMap monitor){
        p=new Pane();
        st=new SequentialTransition();
        step=0;
        paused=Duration.seconds(0);
        bottomControls(bp,monitor);

    }

    /*bottomControls, creates the controls for the animations along the bottom of the screen
    parameters: bp - borderpane to add to, monitor -monitoring hashmap for counting
    returns: null
     */
    private static void bottomControls(BorderPane bp, MonitoringMap monitor){
        MenuButton bbUsed = new MenuButton("Building Blocks Used");
        bbUsed.getStyleClass().add("button");
        AnimationMethods.buildingBlockButton(EG_Controller.getStepUsed(),bbUsed);
        Button currentSpeed = new Button("Current speed:1");

        Button play = new Button("Play");
        play.setOnAction((javafx.event.ActionEvent event) -> {
            if(step==2 && paused==Duration.seconds(0)) {
                EG_Step.getValues();
                buttonStep(2);
                EG_Step.clearCombos(p);
            }
            else if (paused==Duration.seconds(0)){
                st.stop();
                buttonStep(step);
            }
            st.playFrom(paused);
            st.setRate(1);
            AnimationMethods.changeSpeedButton(currentSpeed, st.getRate());
        });

        Button pause = new Button("Pause");
        pause.setOnAction((javafx.event.ActionEvent event) -> {
            st.pause();
            paused= st.getCurrentTime();
        });

        //step buttons
        Button first = new Button("Step 1");
        first.setOnAction((javafx.event.ActionEvent event) -> {
            monitor.incrementValue("EG1");
            buttonStep(1);

        });
        Button second = new Button("Step 2");
        second.setOnAction((javafx.event.ActionEvent event) -> {
            monitor.incrementValue("EG2");
            buttonStep(2);

        });


        //speed buttons
        Button speed1 = new Button("Play speed 1x");
        speed1.setOnAction(event -> AnimationMethods.speedChanged(st,1,currentSpeed));

        Button speed2 = new Button("Play speed 2x");
        speed2.setOnAction(event-> AnimationMethods.speedChanged(st, 2, currentSpeed));

        Button speed3 = new Button("Play speed 3x");
        speed3.setOnAction(event ->AnimationMethods.speedChanged(st, 3, currentSpeed));

        Button speed6 = new Button("Play Speed 6x");
        speed6.setOnAction(event ->AnimationMethods.speedChanged(st, 6, currentSpeed));

        Button speed10=new Button("Play speed 10x");
        speed10.setOnAction(event -> AnimationMethods.speedChanged(st, 10, currentSpeed));


        //add buttons to bottom of screen
        HBox hb = new HBox();
        hb.getChildren().addAll(first,second,play,pause);
        hb.getChildren().addAll(speed1, speed2, speed3,speed6,speed10);

        hb.getChildren().addAll(bbUsed, currentSpeed);

        bp.setBottom(hb);
        bp.setCenter(p);
    }

    /*buttonStep, setups the step based on the input, ether 1 or 2
    parameters: i - number of step to load
    returns: null
     */
    private static void buttonStep(int i){
        step=i;
        p.getChildren().clear();
        st.getChildren().clear();

        EG_Step.createPane(p);

        st = EG_Step.createTransition(p, step);
        paused=Duration.seconds(0);
    }



}
