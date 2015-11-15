package tool.CryptoMethods.Views;

import javafx.animation.SequentialTransition;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import tool.CryptoMethods.Controllers.DH_Controller;
import tool.CryptoMethods.Controllers.RSA_Controller;
import tool.CryptoMethods.Views.Diffie_HellmanScenes.DH_Step_1;
import tool.CryptoMethods.Views.RSAScenes.RSA_Step_1;

/**
 * Created by Phillipa on 09/11/2015.
 */
public class Diffie_Hellman {

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
            DH_Step_1.createPane(p);
            st =DH_Step_1.createTimeLine(p);
            paused=Duration.seconds(0);
            //AnimationMethods.buildingBlockButton(DH_Controller.getStep1Used(),bbUsed);
        });
        Button second = new Button("Step 2");
        Button third = new Button("Step 3");
        Button fourth = new Button("Step 4");

        HBox hb = new HBox();
        hb.getChildren().addAll(first,second,third,fourth,play,pause);

        AnimationMethods.speedButtons(hb, st);
        hb.getChildren().add(bbUsed);

        bp.setBottom(hb);
        bp.setCenter(p);
    }
}
