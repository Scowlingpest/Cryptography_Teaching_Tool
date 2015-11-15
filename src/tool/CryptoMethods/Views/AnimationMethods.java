package tool.CryptoMethods.Views;

import javafx.animation.*;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.util.Duration;
import tool.CryptoMethods.Controllers.RSA_Controller;
import tool.Graphics.Robot;
import tool.Graphics.Speechbubble;

import java.lang.reflect.Array;

/**
 * Created by Phillipa on 02/11/2015.
 */
public class AnimationMethods {

    public static void placeRobots(Robot r, Pane p,int x, int y){
        r.getView().setLayoutX(x);
        r.getView().setLayoutY(y);
        r.setImageWidth(135);

        p.getChildren().add(r.getView());
    }
    public static void speedButtons(HBox hb, SequentialTransition st){

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

    public static void buildingBlockButton(String[] used, MenuButton mb){
        mb.getItems().clear();
        mb.setPopupSide(Side.TOP);
        for(String s:used){

            mb.getItems().add(new MenuItem(s));
        }

    }
    public static Speechbubble invisSpeechbubble(String bubble, int x, int y, String type,int size){
        Speechbubble speechB = new Speechbubble(type,bubble,size,x,y);
        speechB.getSp().setOpacity(0);
        return speechB;
    }

    public static Text textSetup(String text, int x, int y, String tool){
        Text t = new Text(text);
        t.getStyleClass().add("text-animate");
        t.setLayoutX(x);
        t.setLayoutY(y);
        t.setOpacity(0);
        Tooltip.install(t, new Tooltip(tool));
        return t;
    }

    public static FadeTransition fadeInto(Node a, int seconds){
        FadeTransition ft = new FadeTransition(Duration.seconds(seconds),a);
        ft.setToValue(1);
        ft.setFromValue(0);
        return ft;
    }
    public static FadeTransition fadeInto(Node a){
        return fadeInto(a,3);
    }

    public static FadeTransition fadeAway(Node a, int seconds){
        FadeTransition ft = new FadeTransition(Duration.seconds(seconds),a);
        ft.setToValue(0);
        ft.setFromValue(1);

        return ft;
    }

    public static FadeTransition fadeAway(Node a){
       return fadeAway(a,3);
    }

    public static TranslateTransition moveNode(Node a,int x,int y, int seconds){
        TranslateTransition tt = new TranslateTransition(Duration.seconds(seconds),a);
        tt.setToX(x);
        tt.setToY(y);
        return tt;


    }

    public static ParallelTransition createParallel(Transition[] t){
        ParallelTransition pt = new ParallelTransition();
        for (Transition temp:t){
            pt.getChildren().add(temp);
        }
        return pt;
    }

    public static SequentialTransition createSequential(Transition[] t){
        SequentialTransition st = new SequentialTransition();
        for (Transition temp:t){
            st.getChildren().add(temp);
        }
        return st;
    }

    public static PauseTransition pauseSeconds(int seconds){
        PauseTransition pt = new PauseTransition(Duration.seconds(seconds));
        return pt;
    }

    public static ScaleTransition changeSize(Node a, double x, int seconds){
        ScaleTransition st = new ScaleTransition(Duration.seconds(seconds),a);
        st.setToX(x);
        st.setToY(x);

        return st;
    }


    public static void changeBubble(SequentialTransition st, Speechbubble bubble, String s){
        FadeTransition bubbleChange =AnimationMethods.fadeAway(bubble.getSp(),3);
        bubbleChange.setOnFinished(event->{
            bubble.setSpeech(s);
        });
        FadeTransition bubbleAppear =AnimationMethods.fadeInto(bubble.getSp(),3);
        st.getChildren().addAll(bubbleChange,bubbleAppear);
    }


}
