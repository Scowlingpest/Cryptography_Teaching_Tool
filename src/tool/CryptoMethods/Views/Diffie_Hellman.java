package tool.CryptoMethods.Views;

import javafx.animation.SequentialTransition;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import tool.CryptoMethods.Controllers.DH_Controller;
import tool.CryptoMethods.Views.Diffie_Hellman_Scenes.DH_Step;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 09/11/2015.
 */
public class Diffie_Hellman {

    static Duration paused=Duration.seconds(0);
    static Pane p = new Pane();
    static SequentialTransition st =new SequentialTransition();
    static int step =1;

    public static void start(BorderPane bp){

        bottomControls(bp);



    }

    public static void bottomControls(BorderPane bp){
        MenuButton bbUsed = new MenuButton("Building Blocks Used");
        AnimationMethods.buildingBlockButton(DH_Controller.getStepUsed(),bbUsed);
        Button currentSpeed = new Button("Current speed:1");

        Button play = new Button("Play");
        play.setOnAction((javafx.event.ActionEvent event) -> {
            if(step==2 && paused==Duration.seconds(0)) {
                DH_Step.getValues();
                buttonStep(2);
                DH_Step.clearCombos(p);
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
        Button first = new Button("Step 1");
        first.setOnAction((javafx.event.ActionEvent event) -> {
            buttonStep(1);

        });
        Button second = new Button("Step 2");
        second.setOnAction((javafx.event.ActionEvent event) -> {
            buttonStep(2);

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



        HBox hb = new HBox();
        hb.getChildren().addAll(first,second,play,pause);
        hb.getChildren().addAll(speed1, speed2, speed3,speed6,speed10);

        hb.getChildren().addAll(bbUsed, currentSpeed);

        bp.setBottom(hb);
        bp.setCenter(p);
    }

    private static void buttonStep(int i){
        step=i;
        p.getChildren().clear();
        st.getChildren().clear();

        DH_Step.createPane(p);


        st = DH_Step.createTimeLine(p,step);
        paused=Duration.seconds(0);
    }



}
