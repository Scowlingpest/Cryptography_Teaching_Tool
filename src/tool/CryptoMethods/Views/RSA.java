package tool.CryptoMethods.Views;

import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import tool.CryptoMethods.Views.RSAScenes.RSA_Step_1;
import tool.CryptoMethods.Views.RSAScenes.RSA_Step_2;
import tool.CryptoMethods.Views.RSAScenes.RSA_Step_3;

import java.util.ArrayList;
import java.util.Arrays;


/** Author : Phillipa Russell
 *  Created: 21/10/2015
 */
public class RSA {
    static Duration paused=Duration.seconds(0);
    static Pane p = new Pane();
    static SequentialTransition st =new SequentialTransition();

    public static void start(BorderPane bp){

        bottomControls(bp);



    }



    public static void bottomControls(BorderPane bp){
        Button play = new Button("Play");
        play.setOnAction((javafx.event.ActionEvent event) -> {
            st.playFrom(paused);
            st.setRate(1);
        });

        Button pause = new Button("Pause");
        pause.setOnAction((javafx.event.ActionEvent event) -> {
            st.pause();
            paused= st.getCurrentTime();
        });


        Button first = new Button("Step 1");
        first.setOnAction((javafx.event.ActionEvent event) -> {
            p.getChildren().clear();
            st.getChildren().clear();
            RSA_Step_1.createPane(p);
            st =RSA_Step_1.createTimeLine(p);
            paused=Duration.seconds(0);
        });

        Button second = new Button("Step 2");
        second.setOnAction((javafx.event.ActionEvent event) -> {
            p.getChildren().clear();
            st.getChildren().clear();
            RSA_Step_2.createPane(p);
            st =RSA_Step_2.createTimeLine(p);
            paused=Duration.seconds(0);
        });
        Button third = new Button("Step 3");
        third.setOnAction((javafx.event.ActionEvent event) -> {
            p.getChildren().clear();
            st.getChildren().clear();
            RSA_Step_3.createPane(p);
            st =RSA_Step_3.createTimeLine(p);
            paused=Duration.seconds(0);
        });

        Button fourth = new Button("Step 4");



        HBox hb = new HBox();
        hb.getChildren().addAll(first,second,third,fourth,play,pause);

        speedButtons(hb);
        bp.setBottom(hb);
        bp.setCenter(p);
    }

    public static void speedButtons(HBox hb){

        Button speed1 = new Button("Play speed 1x");
        speed1.setOnAction((javafx.event.ActionEvent event)->{
            st.setRate(1);
        });
        Button speed2 = new Button("Play speed 2x");
        speed2.setOnAction((javafx.event.ActionEvent event)->{
            st.setRate(2);
        });
        Button speed3 = new Button("Play speed 3x");
        speed3.setOnAction((javafx.event.ActionEvent event)->{
            st.setRate(3);
        });
        Button speed6 = new Button("Play Speed 6x");
        speed6.setOnAction((javafx.event.ActionEvent event)->{
            st.setRate(6);
        });
        Button speed10=new Button("Play speed 10x");
        speed10.setOnAction((javafx.event.ActionEvent event) -> {
            st.setRate(10);
        });

        hb.getChildren().addAll(speed1,speed2,speed3,speed6,speed10);
    }




}
