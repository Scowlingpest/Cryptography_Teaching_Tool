package tool.CryptoMethods.Views;

import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import tool.CryptoMethods.Views.RSAScenes.RSA_Step_1;
import java.util.ArrayList;
import java.util.Arrays;


/** Author : Phillipa Russell
 *  Created: 21/10/2015
 */
public class RSA {
    static ArrayList<Pane> panes = new ArrayList<Pane>(Arrays.asList(new Pane(),new Pane(),new Pane(),new Pane()));
    static ArrayList<Timeline> animations = new ArrayList<Timeline>();
    static Duration paused=Duration.seconds(0);
    static Pane p = new Pane();
    static Timeline tl =new Timeline();

    public static void start(BorderPane bp){

        RSA_Step_1.createPane(panes.get(0));
        animations.add(0,RSA_Step_1.createTimeLine(panes.get(0)));
        bottomControls(bp);



    }



    public static void bottomControls(BorderPane bp){


        Button first = new Button("Step 1");
        first.setOnAction((javafx.event.ActionEvent event) -> {
            p.getChildren().clear();
            tl.getKeyFrames().clear();
            RSA_Step_1.createPane(p);
            tl=RSA_Step_1.createTimeLine(p);
            paused=Duration.seconds(0);
        });

        Button second = new Button("Step 2");
        Button third = new Button("Step 3");

        Button fourth = new Button("Step 4");

        Button play = new Button("Play");
        play.setOnAction((javafx.event.ActionEvent event) -> {
            tl.playFrom(paused);
        });

        Button pause = new Button("Pause");
        pause.setOnAction((javafx.event.ActionEvent event) -> {
            tl.pause();
            paused=tl.getCurrentTime();
        });

        HBox hb = new HBox();
        hb.getChildren().addAll(first,second,play,pause,third,fourth);
        bp.setBottom(hb);
        bp.setCenter(p);
    }



}
