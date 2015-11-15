package tool.CryptoMethods.Views;

import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import tool.CryptoMethods.Controllers.RSA_Controller;
import tool.CryptoMethods.Views.RSAScenes.RSA_Step_1;
import tool.CryptoMethods.Views.RSAScenes.RSA_Step_2;
import tool.CryptoMethods.Views.RSAScenes.RSA_Step_3;
import tool.CryptoMethods.Views.RSAScenes.RSA_Step_4;

import java.util.ArrayList;
import java.util.Arrays;


/** Author : Phillipa Russell
 *  Created: 21/10/2015
 */
public class RSA {
    static Duration paused=Duration.seconds(0);
    static Pane p = new Pane();
    static SequentialTransition st =new SequentialTransition();
    static String[] used =new String[]{};

    public static void start(BorderPane bp){

        bottomControls(bp);



    }



    public static void bottomControls(BorderPane bp){
        MenuButton bbUsed = new MenuButton("Building Blocks Used");
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
            AnimationMethods.buildingBlockButton(RSA_Controller.getStep1Used(),bbUsed);
        });

        Button second = new Button("Step 2");
        second.setOnAction((javafx.event.ActionEvent event) -> {
            p.getChildren().clear();
            st.getChildren().clear();
            RSA_Step_2.createPane(p);
            st =RSA_Step_2.createTimeLine(p);
            paused=Duration.seconds(0);
            AnimationMethods.buildingBlockButton(RSA_Controller.getStep2Used(),bbUsed);
        });
        Button third = new Button("Step 3");
        third.setOnAction((javafx.event.ActionEvent event) -> {
            p.getChildren().clear();
            st.getChildren().clear();
            RSA_Step_3.createPane(p);
            st =RSA_Step_3.createTimeLine(p);
            paused=Duration.seconds(0);
            AnimationMethods.buildingBlockButton(RSA_Controller.getStep3Used(),bbUsed);
        });

        Button fourth = new Button("Step 4");
        fourth.setOnAction((javafx.event.ActionEvent event) -> {
            p.getChildren().clear();
            st.getChildren().clear();
            RSA_Step_4.createPane(p);
            st =RSA_Step_4.createTimeLine(p);
            paused=Duration.seconds(0);
            AnimationMethods.buildingBlockButton(RSA_Controller.getStep4Used(),bbUsed);
        });


        HBox hb = new HBox();
        hb.getChildren().addAll(first,second,third,fourth,play,pause);

        AnimationMethods.speedButtons(hb, st);
        hb.getChildren().add(bbUsed);

        bp.setBottom(hb);
        bp.setCenter(p);
    }






}
