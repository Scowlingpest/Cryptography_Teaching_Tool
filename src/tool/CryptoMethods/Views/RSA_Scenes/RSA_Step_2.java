package tool.CryptoMethods.Views.RSA_Scenes;

import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import tool.CryptoMethods.Controllers.RSA_Controller;
import tool.CryptoMethods.Views.AnimationMethods;
import tool.Graphics.Monitor;
import tool.Graphics.Paper;
import tool.Graphics.Robot;
import tool.Graphics.Speechbubble;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 01/11/2015.
 */
//RSA step 2 class, sending the key and encryption
public class RSA_Step_2 {
    private static Monitor m1;
    private static Monitor m2;
    private static Monitor m3;

    /*createPane,creates the pane needed for the animation
    parameters: root - pane to add items to
    returns: null
    */
    public static void createPane(Pane root){
        //gets the three robots needed
        Robot encrypt = RSA_Controller.getEncrypt();
        Robot encrypt2 = RSA_Controller.getEncrypt2();
        Robot decrypt = RSA_Controller.getDecrypt();

        root.setPrefSize(1000,600);
        background(root);

        //places robots
        placeRobotsFirst(encrypt,encrypt2,decrypt,root);

        //places monitors
        m1 = new Monitor(100,850,200);
        m2 = new Monitor(100,175,100);
        m3 = new Monitor(100,175,425);

        root.getChildren().addAll(m1.getImage(),m2.getImage(),m3.getImage());

        root.setPadding(new Insets(0,100,0,100));

    }

    /*background, setups the background of the animation
    parameters: p -pane to add backgrounds too
    returns: null
    */
    private static void background(Pane p){
        Rectangle left = new Rectangle(30,50,600,550);
        left.getStyleClass().add("rectangle-encrypt");

        Rectangle right = new Rectangle(650,50,500,550);
        right.getStyleClass().add("rectangle-decrypt");

        p.getChildren().addAll(left,right);

    }


    /*placeRobotsFirst, places the robots in their first location
    parameters: e,e2,d - robots to place, p - pane to add robots to
    returns: null
    */
    private static void placeRobotsFirst(Robot e, Robot e2, Robot d, Pane p){
        AnimationMethods.placeRobots(d, p, 950, 175);
        AnimationMethods.placeRobots(e, p, 35, 50);
        AnimationMethods.placeRobots(e2, p, 35, 350);
    }

    /*createTransition, creates the animation, calls other methods to create each bit
    parameters: root- pane to add elements to
    returns: sequential transition with all the animations
    */
    public static SequentialTransition createTransition(Pane p){
        SequentialTransition st = new SequentialTransition();

        //first bits of text needed in animations
        Text publicKey = AnimationMethods.textSetup(RSA_Controller.getPublicKey(),875,250,RSA_Controller.getTooltipPK());
        Text publicKey2 = AnimationMethods.textSetup(RSA_Controller.getPublicKey(),875,250,RSA_Controller.getTooltipPK());

        Text equation1 = AnimationMethods.textSetup(RSA_Controller.getEncryptEq(),335,175,RSA_Controller.getTooltipEq());
        Text equation2 = AnimationMethods.textSetup(RSA_Controller.getEncryptEq(),335,400,RSA_Controller.getTooltipEq());

        //speechbubble needed
        Speechbubble sb = new Speechbubble("tc",RSA_Controller.getHeyStep2(),250,900,275);

        p.getChildren().addAll(equation1,equation2,sb.getSp());

        //animation methods, each bit
        movePublicKeys(publicKey, publicKey2, st, p,sb);
        encryptsSay(st,p);
        moveMonitors(st);
        equationAppear(st,equation1,equation2,sb);
        changeKeys(st,publicKey,publicKey2,sb);
        encryptEquation(st,equation1,equation2,publicKey,publicKey2,p);
        encryptPapers(st,p);
        finalBubble(st,p);


        return st;
    }

    /*movePublicKeys,method that deals with the animation for moving the keys across the screen with speechbubble
    parameters: a,b - public key text, st- sequential transition to add to, p - pane to add objects to
                sb - speechbubble to change
    returns: null
     */
    private static void movePublicKeys(Text a, Text b, SequentialTransition st, Pane p, Speechbubble sb){
        AnimationMethods.changeBubble(st,sb,RSA_Controller.getStep2Welcome());

        FadeTransition opaqueA = AnimationMethods.fadeInto(a);
        FadeTransition opaqueB = AnimationMethods.fadeInto(b);

        TranslateTransition kf1 = AnimationMethods.moveNode(a,-675,-100,10);
        TranslateTransition kf2 = AnimationMethods.moveNode(b,-675,225,10);

        FadeTransition bubbleDisappear =AnimationMethods.fadeAway(sb.getSp());


        p.getChildren().addAll(a,b);
        ParallelTransition pt=new ParallelTransition();
        pt.getChildren().addAll(opaqueA,opaqueB);

        st.getChildren().addAll(pt,kf1,kf2,bubbleDisappear);



    }

    /*encryptsSay, animation method for the two speechbubbles for the encrypts
    parameters: st - sequential transition for animations, p -pane for objects
    returns: null
     */
    private static void encryptsSay(SequentialTransition st, Pane p){
        Speechbubble e1 = AnimationMethods.invisSpeechbubble(RSA_Controller.getStep2KeyReceived(),125,125,"tl",215);
        Speechbubble e2 = AnimationMethods.invisSpeechbubble(RSA_Controller.getStep2Equation(),125,400,"tl",215);
        p.getChildren().addAll(e1.getSp(),e2.getSp());

        //makes bubbles appear and disappear
        FadeTransition e1Appear =AnimationMethods.fadeInto(e1.getSp());
        FadeTransition e2Appear = AnimationMethods.fadeInto(e2.getSp());

        FadeTransition e1Disappear=AnimationMethods.fadeAway(e1.getSp());
        FadeTransition e2Disappear=AnimationMethods.fadeAway(e2.getSp());

        //add transitions with 5 seconds pause
        SequentialTransition pt1 = AnimationMethods.createSequential(new Transition[]{e1Appear,
                AnimationMethods.pauseSeconds(5),
                e1Disappear});

        SequentialTransition pt2 = AnimationMethods.createSequential(new Transition[]{e2Appear,
                AnimationMethods.pauseSeconds(5),
                e2Disappear});

        st.getChildren().addAll(pt1, pt2);
    }

    /*moveMonitors, animation method for moving and resizing the two monitors for encrypt
    parameters: st -sequential transition for animations
    returns: null
     */
    private static void moveMonitors(SequentialTransition st){
        ScaleTransition m2Resize = AnimationMethods.changeSize(m2.getImage(),2.25,7);
        ScaleTransition m3Resize = AnimationMethods.changeSize(m3.getImage(),2.25,7);

        TranslateTransition m2Move = AnimationMethods.moveNode(m2.getImage(),200,50,7);
        TranslateTransition m3Move = AnimationMethods.moveNode(m3.getImage(),200,-50,7);

        ParallelTransition pt1 = AnimationMethods.createParallel(new Transition[]{m2Resize,m3Resize,m2Move,m3Move});

        st.getChildren().addAll(pt1);

    }

    /*equationAppear, animation method for making the equation appear on encrypts side
    parameters: st -sequential transition for animations, a,b - text objects that hold the equations,
                sb - speechbubble for changing
    returns: null
     */
    private static void equationAppear(SequentialTransition st, Text a, Text b, Speechbubble sb){
        FadeTransition aAppear = AnimationMethods.fadeInto(a);
        FadeTransition bAppear = AnimationMethods.fadeInto(b);

        bAppear.setOnFinished(event-> sb.setSpeech(RSA_Controller.getReminder()));

        ParallelTransition pt1 = AnimationMethods.createParallel(new Transition[]{aAppear,bAppear});
        st.getChildren().addAll(pt1);

    }

    /*changeKeys, animation method for changing the keys to numbers and moving them into the monitors
    parameters: st -sequential transition for animations, a,b - text objects that hold the keys,
                sb - speechbubble for changing
    returns: null
     */
    private static void changeKeys(SequentialTransition st, Text a, Text b, Speechbubble sb){
        FadeTransition bubbleAppear = AnimationMethods.fadeInto(sb.getSp());
        FadeTransition aDisappear = AnimationMethods.fadeAway(a);
        FadeTransition bDisappear = AnimationMethods.fadeAway(b);


        ParallelTransition pt1 = AnimationMethods.createParallel(new Transition[]{aDisappear,bDisappear,bubbleAppear});

        //once keys disappear replace with key value
        aDisappear.setOnFinished(event->a.setText(RSA_Controller.getNoPublicKey()));
        bDisappear.setOnFinished(event->b.setText(RSA_Controller.getNoPublicKey()));

        //reappear keys
        FadeTransition aAppear = AnimationMethods.fadeInto(a);
        FadeTransition bAppear = AnimationMethods.fadeInto(b);

        ParallelTransition pt2 = AnimationMethods.createParallel(new Transition[]{aAppear,bAppear});

        //move keys into monitors
        TranslateTransition moveA = AnimationMethods.moveNode(a,-500,-50,5);
        TranslateTransition moveB = AnimationMethods.moveNode(b,-500,175,5);
        FadeTransition sbDisappear = AnimationMethods.fadeAway(sb.getSp(),5);

        ParallelTransition pt3 = AnimationMethods.createParallel(new Transition[]{moveA,moveB,sbDisappear});

        st.getChildren().addAll(pt1,pt2,
                AnimationMethods.pauseSeconds(5),pt3);
    }

    /*encryptEquation, animation method for making the equation change to have numbers
    parameters: st -sequential transition for animations, eq1,eq2- equations to change,
                pk1,pk2 - public keys to
    returns: null
     */
    private static void encryptEquation(SequentialTransition st, Text eq1, Text eq2, Text pk1, Text pk2, Pane p){
        Speechbubble sb = AnimationMethods.invisSpeechbubble(RSA_Controller.getHereEquation(),900,275,"tc",250);
        p.getChildren().add(sb.getSp());

        //make public keys disappear and bubble appear
        FadeTransition pk1Disappear = AnimationMethods.fadeAway(pk1);
        FadeTransition pk2Disappear = AnimationMethods.fadeAway(pk2);
        FadeTransition bubbleAppear = AnimationMethods.fadeInto(sb.getSp());

        ParallelTransition pt1 = AnimationMethods.createParallel(new Transition[]{pk1Disappear,pk2Disappear,bubbleAppear});

        //make equations fade and change to include values
        FadeTransition eq1Disappear = AnimationMethods.fadeAway(eq1);
        FadeTransition eq2Disappear = AnimationMethods.fadeAway(eq2);
        eq1Disappear.setOnFinished(event->eq1.setText(RSA_Controller.getNoEncryptEq()));
        eq2Disappear.setOnFinished(event->eq2.setText(RSA_Controller.getNoEncryptEq()));

        ParallelTransition pt2 = AnimationMethods.createParallel(new Transition[]{eq1Disappear,eq2Disappear});

        FadeTransition eq1Appear = AnimationMethods.fadeInto(eq1);
        FadeTransition eq2Appear = AnimationMethods.fadeInto(eq2);
        FadeTransition bubbleDisappear = AnimationMethods.fadeAway(sb.getSp());
        bubbleDisappear.setOnFinished(event->p.getChildren().remove(sb));

        ParallelTransition pt3 = AnimationMethods.createParallel(new Transition[]{eq1Appear,eq2Appear,bubbleDisappear});
        st.getChildren().addAll(pt1,pt2,
                AnimationMethods.pauseSeconds(5),pt3);
    }

    /*encryptPapers,animation method for making the papers appear, move and change to encrypted papers
    parameters: st - sequential transition for animations, p -pane for objects
    returns: null
     */
    private static void encryptPapers(SequentialTransition st, Pane p){
        Paper p1 = new Paper("n",125,175,125);
        Paper p2 = new Paper("n",125,175,350);
        Speechbubble sb = AnimationMethods.invisSpeechbubble(RSA_Controller.getEncryption(),900,275,"tc",250);

        p.getChildren().addAll(p1.getView(),p2.getView(),sb.getSp());
        //make paper and bubble appear
        FadeTransition p1Appear = AnimationMethods.fadeInto(p1.getView());
        FadeTransition p2Appear = AnimationMethods.fadeInto(p2.getView());
        FadeTransition sbAppear = AnimationMethods.fadeInto(sb.getSp());

        ParallelTransition pt1 = AnimationMethods.createParallel(new Transition[]{p1Appear, p2Appear, sbAppear});

        //move papers across
        TranslateTransition p1Move = AnimationMethods.moveNode(p1.getView(), 180, 0, 5);
        TranslateTransition p2Move = AnimationMethods.moveNode(p2.getView(),180,0,5);

        ParallelTransition pt2 = AnimationMethods.createParallel(new Transition[]{p1Move,p2Move});

        //makes papers disappear and become encrypted
        FadeTransition p1Disappear = AnimationMethods.fadeAway(p1.getView());
        FadeTransition p2Disappear =AnimationMethods.fadeAway(p2.getView());
        p1Disappear.setOnFinished(event -> p1.changeToEncrypt());
        p2Disappear.setOnFinished(event -> p2.changeToEncrypt());

        ParallelTransition pt3 = AnimationMethods.createParallel(new Transition[]{p1Disappear,p2Disappear});

        //make papers reappear
        FadeTransition p1Changed = AnimationMethods.fadeInto(p1.getView());
        FadeTransition p2Changed = AnimationMethods.fadeInto(p2.getView());

        ParallelTransition pt4 = AnimationMethods.createParallel(new Transition[]{p1Changed,p2Changed});

        //make bubble disappear
        FadeTransition sbDisappear = AnimationMethods.fadeAway(sb.getSp());
        sbDisappear.setOnFinished(event -> p.getChildren().remove(sb));

        SequentialTransition temp = new SequentialTransition();
        temp.getChildren().addAll(pt1,pt2,pt3,pt4,sbDisappear);

        st.getChildren().addAll(temp);


    }

    /*finalBubble,animation method that makes the final speech bubble appear
    parameters: st - sequential transition for animations, p -pane for objects
    returns: null
     */
    private static void finalBubble(SequentialTransition st, Pane p){
        Speechbubble sb = AnimationMethods.invisSpeechbubble(RSA_Controller.getNextStep2(),900,275,"tc",250);
        p.getChildren().add(sb.getSp());

        FadeTransition sbAppear =AnimationMethods.fadeInto(sb.getSp());

        st.getChildren().addAll(sbAppear, AnimationMethods.pauseSeconds(5));

    }




}
