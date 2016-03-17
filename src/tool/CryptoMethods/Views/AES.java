package tool.CryptoMethods.Views;

import javafx.animation.SequentialTransition;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import tool.CryptoMethods.Controllers.AES_Controller;
import tool.CryptoMethods.Views.AES_Scenes.AES_Step_1;
import tool.CryptoMethods.Views.AES_Scenes.AES_Step_2;
import tool.CryptoMethods.Views.AES_Scenes.AES_Step_3;
import tool.CryptoMethods.Views.AES_Scenes.AES_Step_4;
import tool.Models.MonitoringMap;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 16/12/2015.
 */
//aes start class, launches steps
public class AES {

    //variables needed, are reset upon load
    private static Duration paused=Duration.seconds(0);
    private static Pane p = new Pane();
    private static SequentialTransition st =new SequentialTransition();
    private static int step=0;

    /*start, wipes screen,resets values and adds controls for AES
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

        Button currentSpeed = new Button("Current speed:1 ");

        Button play = new Button("Play");
        play.setOnAction((javafx.event.ActionEvent event) -> {
            if(paused==Duration.seconds(0)){
                setupPane(step,bp);
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


        //step buttons, repetitive but essential
        Button first = new Button("Step 1");
        first.setOnAction((javafx.event.ActionEvent event) -> {
            //clear screen of old animation
           setupPane(1,bp);

            //set time to 0 and get used building blocks for the step
            paused=Duration.seconds(0);
            AnimationMethods.buildingBlockButton(AES_Controller.getStep1Used(),bbUsed);
            step=1;
            monitor.incrementValue("AES1");
        });


        Button second = new Button("Step 2");
        second.setOnAction((javafx.event.ActionEvent event) -> {
            setupPane(2,bp);

            paused=Duration.seconds(0);
            AnimationMethods.buildingBlockButton(AES_Controller.getStep2Used(),bbUsed);
            step=2;
            monitor.incrementValue("AES2");
        });

        Button third = new Button("Step 3");
        third.setOnAction((javafx.event.ActionEvent event) -> {
            setupPane(3,bp);

            paused=Duration.seconds(0);
            AnimationMethods.buildingBlockButton(AES_Controller.getStep3Used(),bbUsed);
            step=3;
            monitor.incrementValue("AES3");
        });

        Button fourth = new Button("Step 4");
        fourth.setOnAction((javafx.event.ActionEvent event) -> {
            setupPane(4,bp);
            step=4;
            paused=Duration.seconds(0);
            AnimationMethods.buildingBlockButton(AES_Controller.getStep4Used(),bbUsed);
            monitor.incrementValue("AES4");
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


        //adds buttons to screen
        HBox buttonBar = new HBox();
        buttonBar.getChildren().addAll(first, second, third, fourth, play, pause);
        buttonBar.getChildren().addAll(speed1, speed2, speed3, speed6, speed10);

        buttonBar.getChildren().addAll(bbUsed, currentSpeed);


        bp.setBottom(buttonBar);

    }

    /*setupPane,setups the pane and creates the animation for each step
    parameters: i -step to create, bp - borderpane to add stuff to
    returns: null
     */
    private static void setupPane(int i,BorderPane bp){
        st.stop();
        p.getChildren().clear();
        st.getChildren().clear();
        switch (i){
            case 1:
                AES_Step_1.createPane(p);
                st=AES_Step_1.createTransition(p);
                break;
            case 2:
                AES_Step_2.createPane(p);
                st=AES_Step_2.createTransition(p);
                break;
            case 3:
                AES_Step_3.createPane(p);
                st=AES_Step_3.createTransition(p);
                break;
            case 4:
                AES_Step_4.createPane(p);
                st=AES_Step_4.createTransition(p);
        }
        bp.setCenter(p);

    }








}
