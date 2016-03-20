package tool.CryptoMethods.Views;

import javafx.animation.SequentialTransition;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import tool.CryptoMethods.Controllers.RSA_Controller;
import tool.CryptoMethods.Views.RSA_Scenes.RSA_Step_1;
import tool.CryptoMethods.Views.RSA_Scenes.RSA_Step_2;
import tool.CryptoMethods.Views.RSA_Scenes.RSA_Step_3;
import tool.CryptoMethods.Views.RSA_Scenes.RSA_Step_4;
import tool.Models.MonitoringMap;


/** Author : Phillipa Russell
 *  Created: 21/10/2015
 */
//rsa start class, launches steps
public class RSA {

    //variables needed, are reset upon load
    private static Duration paused=Duration.seconds(0);
    private static Pane p = new Pane();
    private static SequentialTransition st =new SequentialTransition();
    private static int step=0;

    /*start, wipes screen,resets values and adds controls for RSA
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
            setupPane(1,bp);
            step=1;
            //set time to 0 and get used building blocks for the step
            paused=Duration.seconds(0);
            AnimationMethods.buildingBlockButton(RSA_Controller.getStep1Used(),bbUsed);
            monitor.incrementValue("RSA1");
        });


        Button second = new Button("Step 2");
        second.setOnAction((javafx.event.ActionEvent event) -> {
            setupPane(2, bp);
            step=2;
            paused=Duration.seconds(0);
            AnimationMethods.buildingBlockButton(RSA_Controller.getStep2Used(),bbUsed);
            monitor.incrementValue("RSA2");
        });

        Button third = new Button("Step 3");
        third.setOnAction((javafx.event.ActionEvent event) -> {
            setupPane(3, bp);
            step=3;
            paused=Duration.seconds(0);
            AnimationMethods.buildingBlockButton(RSA_Controller.getStep3Used(),bbUsed);
            monitor.incrementValue("RSA3");
        });

        Button fourth = new Button("Step 4");
        fourth.setOnAction((javafx.event.ActionEvent event) -> {
            setupPane(4, bp);
            step=4;
            paused=Duration.seconds(0);
            AnimationMethods.buildingBlockButton(RSA_Controller.getStep4Used(),bbUsed);
            monitor.incrementValue("RSA4");
        });

        //speed buttons, tried putting these in a method in the controller - didn't work
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
        bp.setCenter(p);
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
                RSA_Step_1.createPane(p);
                st=RSA_Step_1.createTransition(p);
                break;
            case 2:
                RSA_Step_2.createPane(p);
                st=RSA_Step_2.createTransition(p);
                break;
            case 3:
                RSA_Step_3.createPane(p);
                st=RSA_Step_3.createTransition(p);
                break;
            case 4:
                RSA_Step_4.createPane(p);
                st=RSA_Step_4.createTransition(p);
                break;
        }
        bp.setCenter(p);

    }







}
