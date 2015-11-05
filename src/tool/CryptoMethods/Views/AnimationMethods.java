package tool.CryptoMethods.Views;

import javafx.animation.*;
import javafx.scene.Node;
import javafx.scene.control.Button;
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

    public static FadeTransition fadeAway(Node a, int seconds){
        FadeTransition ft = new FadeTransition(Duration.seconds(seconds),a);
        ft.setToValue(0);
        ft.setFromValue(1);

        return ft;
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

    public static void buildingBlocksUsed(SequentialTransition st, Pane p, String[] used){
        Rectangle rect =new Rectangle(800,400,400,200);
        rect.getStyleClass().add("rectangle-speech");
        rect.setOpacity(0);

        FadeTransition rectangle = AnimationMethods.fadeInto(rect,2);

        Text bb = AnimationMethods.textSetup(RSA_Controller.getBbUsed(),815,425,"List of Building blocks used in this step");
        FadeTransition bbUsed =AnimationMethods.fadeInto(bb,4);

        SequentialTransition temp = AnimationMethods.createSequential(new Transition[]{rectangle,bbUsed});
        st.getChildren().add(temp);
        p.getChildren().addAll(rect,bb);

        int x=425;
        for(String s:used){
            Text t =AnimationMethods.textSetup(s,815,(x+=30),s);
            FadeTransition ft=AnimationMethods.fadeInto(t,2);
            temp.getChildren().add(ft);
            p.getChildren().add(t);
        }


    }


}
