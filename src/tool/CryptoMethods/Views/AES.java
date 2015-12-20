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

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 16/12/2015.
 */
public class AES {

    static Duration paused=Duration.seconds(0);
    static Pane p = new Pane();
    static SequentialTransition st =new SequentialTransition();
    static int step=0;

    public static void start(BorderPane bp){

        bottomControls(bp);
    }



    public static void bottomControls(BorderPane bp){
        MenuButton bbUsed = new MenuButton("Building Blocks Used");
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
        });


        Button second = new Button("Step 2");
        second.setOnAction((javafx.event.ActionEvent event) -> {
            setupPane(2,bp);

            paused=Duration.seconds(0);
            AnimationMethods.buildingBlockButton(AES_Controller.getStep2Used(),bbUsed);
            step=2;
        });

        Button third = new Button("Step 3");
        third.setOnAction((javafx.event.ActionEvent event) -> {
            setupPane(3,bp);

            paused=Duration.seconds(0);
            AnimationMethods.buildingBlockButton(AES_Controller.getStep3Used(),bbUsed);
            step=3;
        });

        Button fourth = new Button("Step 4");
        fourth.setOnAction((javafx.event.ActionEvent event) -> {
            setupPane(4,bp);
            step=4;
            paused=Duration.seconds(0);
            AnimationMethods.buildingBlockButton(AES_Controller.getStep4Used(),bbUsed);
        });

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




        HBox buttonBar = new HBox();
        buttonBar.setSpacing(3);
        buttonBar.getChildren().addAll(first, second, third, fourth, play, pause);
        buttonBar.getChildren().addAll(speed1, speed2, speed3, speed6, speed10);

        buttonBar.getChildren().addAll(bbUsed, currentSpeed);


        bp.setBottom(buttonBar);

    }

    private static void setupPane(int i,BorderPane bp){
        st.stop();
        p.getChildren().clear();
        st.getChildren().clear();
        switch (i){
            case 1:
                AES_Step_1.createPane(p);
                st=AES_Step_1.createTimeLine(p);
                break;
            case 2:
                AES_Step_2.createPane(p);
                st=AES_Step_2.createTimeLine(p);
                break;
            case 3:
                AES_Step_3.createPane(p);
                st=AES_Step_3.createTimeLine(p);
                break;
            case 4:
                AES_Step_4.createPane(p);
                st=AES_Step_4.createTimeLine(p);
        }
        bp.setCenter(p);

    }








}
