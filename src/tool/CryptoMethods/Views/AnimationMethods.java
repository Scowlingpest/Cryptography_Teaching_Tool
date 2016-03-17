package tool.CryptoMethods.Views;

import javafx.animation.*;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import tool.Graphics.Robot;
import tool.Graphics.Speechbubble;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 02/11/2015.
 */
//AnimationMethods class, essentially a library of methods I use a lot e.g fade in
public class AnimationMethods {

    /*changeSpeedbutton, changes the text on the change speed button
    parameters: b- button to change, speed- value to change button to
    returns: null
     */
    public static void changeSpeedButton(Button b, double speed){
        b.setText("Current speed:"+(String.format("%.0f",speed)));
    }

    /*speedChanged, changes the speed of the animation and the notice
    parameters: st - animation, i- speed to change to, b- speed button notice to change
    returns: null
     */
    public static void speedChanged(SequentialTransition st, int i,Button b){
        st.setRate(i);
        changeSpeedButton(b,st.getRate());
    }

    /*placeRobots, places robots on screen
    parameters: r - robot to add to screen, p- pane to add to, x- x co-ordinate, y- y co-ordinate
    returns: null
     */
    public static void placeRobots(Robot r, Pane p,int x, int y){
        r.getView().setLayoutX(x);
        r.getView().setLayoutY(y);
        r.setImageWidth(135);


        p.getChildren().add(r.getView());
    }

    /*buildingBlockButton, setups the the list of building blocks used
    parameters: used - list of bb used, mb - button to add list to
    returns: null
     */
    public static void buildingBlockButton(String[] used, MenuButton mb){
        mb.getItems().clear();
        mb.setPopupSide(Side.TOP);
        for(String s:used){

            mb.getItems().add(new MenuItem(s));
        }

    }

    /*invisSpeechbubble, makes a new speechbubble which is invisible
    parameters: bubble- text to put in bubble, x- x coordinate, y- y coordinate,
                type- type of bubble, size - size of bubble
    returns: the invisible speech bubble
     */
    public static Speechbubble invisSpeechbubble(String bubble, int x, int y, String type,int size){
        Speechbubble speechB = new Speechbubble(type,bubble,size,x,y);
        speechB.getSp().setOpacity(0);
        return speechB;
    }

    /*textSetup, setups a title text object
    parameters: text- text to display, x - x coordinate, y- y coordinate,
                tool - text for the tootltip
    returns: the text object
     */
    public static Text textSetup(String text, int x, int y, String tool){
        return textSmallSetup(text,x,y,tool,"text-title");
    }

    /*equationSetup, creates an equation text object
    parameters: text - text to display
    returns: the created text object
     */
    public static Text equationSetup(String text){
        return textSmallSetup(text,0,0,null,"text-animate");
    }

    /*textSmallSetup, general method for creating any text object, used by other methods
    parameters: text - text to display, x - x coordinate, y- y coordinate,
                        tool - tooltip text, style - style of text to apply
    returns: the created text object
     */
    private static Text textSmallSetup(String text, int x, int y, String tool, String style){
        Text t = new Text(text);
        t.getStyleClass().add(style);
        t.setLayoutX(x);
        t.setLayoutY(y);
        t.setOpacity(0);
        Tooltip.install(t, new Tooltip(tool));
        return t;
    }

    /* fadeInto, creates a fade transition that makes an object appear
    parameters: a - object to appear, seconds- length of fade
    returns: a fade transition that makes the object appear
     */
    public static FadeTransition fadeInto(Node a, int seconds){
        FadeTransition ft = new FadeTransition(Duration.seconds(seconds),a);
        ft.setToValue(1);
        ft.setFromValue(0);
        return ft;
    }

    /*fadeInto, same as above but with standard length of time
    parameters: a - node to make appear
    returns: fade transition that makes the object appear in 3 seconds
     */
    public static FadeTransition fadeInto(Node a){
        return fadeInto(a,3);
    }

    /*fadeAway, makes a fade transition to make a disappear
    parameters: a - object to fade away, seconds - length of fade
    returns: fade transition that makes the object disappear
     */
    public static FadeTransition fadeAway(Node a, int seconds){
        FadeTransition ft = new FadeTransition(Duration.seconds(seconds),a);
        ft.setToValue(0);
        ft.setFromValue(1);

        return ft;
    }

    /*fadeAway, same as above but with a standard length of time
    parameters: a - object to fade away
    returns: fade transition that makes object disappear in 3 seconds
     */
    public static FadeTransition fadeAway(Node a){
       return fadeAway(a,3);
    }

    /* moveNode, makes an object move from original point to given point, is always based on original point
                so to move from 700,700 to 600,600 you would pass -100,-100
    parameters: a -object to move, x- x value, y -y value, seconds - length of time for move
    returns: a translate transition which moves the node to the point provided
     */
    public static TranslateTransition moveNode(Node a,int x,int y, int seconds){
        TranslateTransition tt = new TranslateTransition(Duration.seconds(seconds),a);
        tt.setToX(x);
        tt.setToY(y);
        return tt;


    }

    /*createParallel, wraps the given transitions into a parallel transition
    parameters: t- array of transitions to put into a single parallel transition
    returns: the parallel transition with all the provided transitions in it
     */
    public static ParallelTransition createParallel(Transition[] t){
        ParallelTransition pt = new ParallelTransition();
        for (Transition temp:t){
            pt.getChildren().add(temp);
        }
        return pt;
    }

    /* createSequential, wraps given transitions into a sequential in order of list
    parameters: t - array of transitions in order you want in transition
    returns: sequential transition of all provided transitions
     */
    public static SequentialTransition createSequential(Transition[] t){
        SequentialTransition st = new SequentialTransition();
        for (Transition temp:t){
            st.getChildren().add(temp);
        }
        return st;
    }

    /*pauseSeconds, returns a pause transition for the period of time given
    parameters: seconds - length of pause wanted
    returns: pause transition of length -seconds
     */
    public static PauseTransition pauseSeconds(int seconds){
        return new PauseTransition(Duration.seconds(seconds));
    }


    /*changeSize, makes an animation to change the size of an object
    parameters: a - object to change size of, x- new size, seconds - length of animation
    returns: scale transition of object changing
     */
    public static ScaleTransition changeSize(Node a, double x, int seconds){
        ScaleTransition st = new ScaleTransition(Duration.seconds(seconds),a);
        st.setToX(x);
        st.setToY(x);

        return st;
    }

    /*changeBubble, changes the text of a speechbubble with fade away and fade in
    parameters: st - sequential transition to add to, bubble- bubble to change, s- new text for bubble
    returns: null
    */
    public static void changeBubble(SequentialTransition st, Speechbubble bubble, String s){
        FadeTransition bubbleChange =AnimationMethods.fadeAway(bubble.getSp(),3);
        bubbleChange.setOnFinished(event-> bubble.setSpeech(s));
        FadeTransition bubbleAppear =AnimationMethods.fadeInto(bubble.getSp(),3);
        st.getChildren().addAll(bubbleChange,bubbleAppear);
    }

}
