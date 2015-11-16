package tool.CryptoMethods.Views;

import javafx.animation.SequentialTransition;
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


        //step buttons, repetitive but essential
        Button first = new Button("Step 1");
        first.setOnAction((javafx.event.ActionEvent event) -> {
            //clear screen of old animation
            p.getChildren().clear();
            st.getChildren().clear();

            //draw new stuff
            RSA_Step_1.createPane(p);
            st =RSA_Step_1.createTimeLine(p);

            //set time to 0 and get used building blocks for the step
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

        Button speed1 = new Button("Play speed 1x");
        speed1.setOnAction(event -> st.setRate(1));

        Button speed2 = new Button("Play speed 2x");
        speed2.setOnAction(event-> st.setRate(2));

        Button speed3 = new Button("Play speed 3x");
        speed3.setOnAction(event -> st.setRate(3));

        Button speed6 = new Button("Play Speed 6x");
        speed6.setOnAction(event -> st.setRate(6));

        Button speed10=new Button("Play speed 10x");
        speed10.setOnAction(event -> st.setRate(10));




        HBox hb = new HBox();
        hb.getChildren().addAll(first,second,third,fourth,play,pause);
        hb.getChildren().addAll(speed1, speed2, speed3,speed6,speed10);

        hb.getChildren().add(bbUsed);

        bp.setBottom(hb);
        bp.setCenter(p);
    }







}
