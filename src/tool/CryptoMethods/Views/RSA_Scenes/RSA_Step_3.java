package tool.CryptoMethods.Views.RSA_Scenes;

import javafx.animation.*;
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
 * Creation: 04/11/2015.
 */
//RSA step 3 class, sending the message and decryption
public class RSA_Step_3 {
    //monitor objects needed
    private static Monitor m1 = new Monitor(100,850,200);
    private static Monitor m2 = new Monitor(100,175,100);
    private static Monitor m3 = new Monitor(100,175,425);
    private static Monitor m4 = new Monitor(200,500,300);

    /*createPane,creates the pane needed for the animation
    parameters: root - pane to add items to
    returns: null
    */
    public static void createPane(Pane root){
        Robot encrypt = RSA_Controller.getEncrypt();
        Robot encrypt2 = RSA_Controller.getEncrypt2();
        Robot decrypt = RSA_Controller.getDecrypt();

        m4.getImage().setOpacity(0);

        root.setPrefSize(1100,600);
        background(root);


        placeRobotsFirst(encrypt,encrypt2,decrypt,root);

    }

    /*background, setups the background of the animation
   parameters: p -pane to add backgrounds too
   returns: null
   */
    private static void background(Pane p){
        Rectangle right = new Rectangle(450,50,700,550);
        right.getStyleClass().add("rectangle-decrypt");

        Rectangle left = new Rectangle(30,50,400,550);
        left.getStyleClass().add("rectangle-encrypt");

        p.getChildren().addAll(left, right);

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

        Speechbubble welcome = new Speechbubble("bl",RSA_Controller.getWelcomeStep3(),215,125,175);

        Paper p1 = new Paper("encrypt",50,200,110);
        Paper p2 = new Paper("encrypt",50,200,435);

        p.getChildren().addAll(m1.getImage(),m2.getImage(),m3.getImage(),m4.getImage(),welcome.getSp());

        movePaper(p,st,welcome,p1,p2);
        decryption(p,welcome,st,p1,p2);

        return st;
    }

    /*movePaper, moves the encrypted paper, sends 1 successfully, sends 1 unsuccessfully
    parameters: p - pane to add objects to, st - sequential transition to add animations to,
                sb- speech bubble so robot can speak, p1,p2 - paper objects
    returns: null
     */
    private static void movePaper(Pane p, SequentialTransition st, Speechbubble sb,Paper p1, Paper p2){
        createPaper(p, sb, st, p1, p2);
        successfulSend(st,sb,p1);
        unsuccessfulSend(st,sb,p2);

    }

    /*createPaper, creates the paper objects and makes bubble appear to explain
    parameters:p - pane to add objects to, sb- speech bubble so robot can speak,
               st - sequential transition to add animations to, p1,p2 - paper objects
    returns: null
     */
    private static void createPaper(Pane p, Speechbubble sb, SequentialTransition st, Paper p1, Paper p2){
        p1.getView().setOpacity(0);
        p2.getView().setOpacity(0);

        p.getChildren().addAll(p1.getView(),p2.getView());

        FadeTransition bubbleDisappear = AnimationMethods.fadeAway(sb.getSp());
        bubbleDisappear.setOnFinished(event->
        sb.setSpeech(RSA_Controller.getFirstStep3()));

        FadeTransition paper1Appear = AnimationMethods.fadeInto(p1.getView());
        FadeTransition paper2Appear = AnimationMethods.fadeInto(p2.getView());

        ParallelTransition pt1 = AnimationMethods.createParallel(new Transition[]{bubbleDisappear,paper1Appear,paper2Appear});

        FadeTransition bubbleAppear = AnimationMethods.fadeInto(sb.getSp());
        st.getChildren().addAll(pt1, AnimationMethods.pauseSeconds(2),bubbleAppear);
    }

    /*successfulSend, makes the first paper object 'successfully' go to Decrypt
    parameters: st - sequential transition to add animations to, sb- speech bubble so robot can speak,
                p1 - paper object
    returns: null
     */
    private static void successfulSend(SequentialTransition st, Speechbubble sb, Paper p1){
        FadeTransition bubbleDisappear= AnimationMethods.fadeAway(sb.getSp());
        bubbleDisappear.setOnFinished(event-> sb.setSpeech(RSA_Controller.getTransferStep3()));

        FadeTransition bubbleAppear =AnimationMethods.fadeInto(sb.getSp());

        TranslateTransition p1Move=AnimationMethods.moveNode(p1.getView(),575,90,6);
        ScaleTransition p1Resize = AnimationMethods.changeSize(p1.getView(),2,6);

        ScaleTransition m1Resize = AnimationMethods.changeSize(m1.getImage(),2,6);
        TranslateTransition m1Move = AnimationMethods.moveNode(m1.getImage(),-100,0,6);

        ParallelTransition pt1 = AnimationMethods.createParallel(new Transition[]{p1Move,p1Resize,m1Resize,m1Move});

        st.getChildren().addAll(AnimationMethods.pauseSeconds(5),bubbleDisappear, bubbleAppear, pt1);
    }

    /*unsuccessfulSend, makes the second paper object 'unsuccessfully' go to Decrypt i.e man in middle
    parameters:st - sequential transition to add animations to, sb- speech bubble so robot can speak,
                p2 - paper object
    returns: null
     */
    private static void unsuccessfulSend(SequentialTransition st, Speechbubble sb,Paper p2){
        FadeTransition m4Appear = AnimationMethods.fadeInto(m4.getImage());

        TranslateTransition p2Move=AnimationMethods.moveNode(p2.getView(),375,-90,6);
        ScaleTransition p2Resize = AnimationMethods.changeSize(p2.getView(),2,6);

        FadeTransition bubbleDisappear = AnimationMethods.fadeAway(sb.getSp());
        bubbleDisappear.setOnFinished(event-> sb.setSpeech(RSA_Controller.getInterceptionStep3()));
        FadeTransition bubbleAppear = AnimationMethods.fadeInto(sb.getSp());

        ParallelTransition pt1 = AnimationMethods.createParallel(new Transition[]{m4Appear,p2Move,p2Resize});

        st.getChildren().addAll(bubbleDisappear,pt1,bubbleAppear,AnimationMethods.pauseSeconds(5));


    }

    /*decryption, shows how the key is used to decrypt the message
    parameters:p - pane to add objects to, sb- speech bubble so robot can speak,
               st - sequential transition to add animations to, p1,p2 - paper objects
    returns: null
     */
    private static void decryption(Pane p, Speechbubble sb, SequentialTransition st, Paper p1, Paper p2){
        Text equation =AnimationMethods.textSetup(RSA_Controller.getEquationStep3(),500,200,RSA_Controller.getTooltipStep3Eq());
        Text secretKey = AnimationMethods.textSetup(RSA_Controller.getSecretKey(),975,125,RSA_Controller.getTooltipStep3SK());
        p.getChildren().addAll(secretKey, equation);

        secretKeyAdded(sb,st,secretKey);
        equationAdd(sb, st, equation);
        moveKey(st,secretKey,equation);
        applyKey(st,equation,p1);
        emptyKey(sb,st,equation,p2);
        finishStep3(p,st,sb);


    }

    /*secretKeyAdded, makes the secret key appear, and show value
    parameters:sb- speech bubble so robot can speak,st - sequential transition to add animations to,
                sk - text object that represents secret key
    returns: null
     */
    private static void secretKeyAdded(Speechbubble sb,SequentialTransition st,Text sk){
        AnimationMethods.changeBubble(st,sb,RSA_Controller.getSecretKeyStep3());
        FadeTransition keyAppear = AnimationMethods.fadeInto(sk);
        FadeTransition keyReplace = AnimationMethods.fadeAway(sk);
        keyReplace.setOnFinished(event-> sk.setText(RSA_Controller.getSecretKeyNo()));
        FadeTransition keyReappear = AnimationMethods.fadeInto(sk);

        SequentialTransition key = AnimationMethods.createSequential(new Transition[]{keyAppear,
        AnimationMethods.pauseSeconds(5),keyReplace,keyReappear});

        st.getChildren().add(key);
    }

    /*equationAdd, makes the decryption equation appear
    parameters:sb- speech bubble so robot can speak,st - sequential transition to add animations to,
               eq - equation text object
    returns: null
     */
    private static void equationAdd( Speechbubble sb, SequentialTransition st, Text eq){
        FadeTransition bubbleDisappear= AnimationMethods.fadeAway(sb.getSp());
        bubbleDisappear.setOnFinished(event-> sb.setSpeech(RSA_Controller.getDecryptStep3()));

        FadeTransition bubbleAppear =AnimationMethods.fadeInto(sb.getSp());

        FadeTransition eqAppear = AnimationMethods.fadeInto(eq);

        st.getChildren().addAll(bubbleDisappear,bubbleAppear,
                AnimationMethods.pauseSeconds(5),eqAppear,
                AnimationMethods.pauseSeconds(5));

    }

    /*moveKey, moves secret key into equation and changes equation to compensate
    parameters:st - sequential transition to add animations to, key - text object for key,
                eq - text object for equation
    returns: null
     */
    private static void moveKey( SequentialTransition st,Text key,Text eq){
        TranslateTransition keyMove = AnimationMethods.moveNode(key,-450,75,5);
        FadeTransition keyDisappear = AnimationMethods.fadeAway(key);

        FadeTransition eqDisappear = AnimationMethods.fadeAway(eq);
        eqDisappear.setOnFinished(event-> eq.setText(RSA_Controller.getEquationStep3No()));
        FadeTransition eqAppear = AnimationMethods.fadeInto(eq);

        ParallelTransition pt1 = AnimationMethods.createParallel(new Transition[]{keyDisappear});

        st.getChildren().addAll(keyMove,pt1,eqDisappear,eqAppear);
    }

    /*applyKey, moves the equation onto the paper and decrypts it
    parameters: st - sequential transition to add animations to, eq - text object for equation,
                p1 - paper object
    returns: null
     */
    private static void applyKey(SequentialTransition st,Text eq, Paper p1){
        TranslateTransition moveEq = AnimationMethods.moveNode(eq,225,50,4);
        FadeTransition paperFade =AnimationMethods.fadeAway(p1.getView());
        paperFade.setOnFinished(event-> p1.changeToDecrypt());
        FadeTransition p1Appear = AnimationMethods.fadeInto(p1.getView());

        st.getChildren().addAll(moveEq,paperFade,p1Appear);
    }

    /*emptyKey, shows the 'listener' can't decrypt the page
    parameters: sb- speech bubble so robot can speak,st - sequential transition to add animations to,
                eq - text object for the equation, p2 - paper object
    returns: null
     */
    private static void emptyKey(Speechbubble sb,SequentialTransition st,Text eq,Paper p2){
        FadeTransition equationChange = AnimationMethods.fadeAway(eq);
        FadeTransition bubbleFade =AnimationMethods.fadeAway(sb.getSp());

        bubbleFade.setOnFinished(event-> sb.setSpeech(RSA_Controller.getUnknownStep3()));
        equationChange.setOnFinished(event-> eq.setText(RSA_Controller.getUnknownEqStep3()));

        ParallelTransition pt1 = AnimationMethods.createParallel(new Transition[]{bubbleFade,equationChange});

        FadeTransition bubbleAppear = AnimationMethods.fadeInto(sb.getSp());
        TranslateTransition moveEq = AnimationMethods.moveNode(eq,25,175,3);

        ParallelTransition pt2 = AnimationMethods.createParallel(new Transition[]{bubbleAppear,moveEq});

        FadeTransition eqAppear = AnimationMethods.fadeInto(eq);
        FadeTransition p2Disappear = AnimationMethods.fadeAway(p2.getView());

        FadeTransition p2Appear = AnimationMethods.fadeInto(p2.getView());

        st.getChildren().addAll(pt1,pt2,eqAppear,p2Disappear,AnimationMethods.pauseSeconds(4),p2Appear,equationChange);

    }

    /*finishStep3, finish off the step
    parameters:p - pane to add objects to,st - sequential transition to add animations to,
               sb- speech bubble so robot can speak
    returns: null
     */
    private static void finishStep3(Pane p, SequentialTransition st, Speechbubble sb){
        FadeTransition bubbleChange = AnimationMethods.fadeAway(sb.getSp());
        bubbleChange.setOnFinished(event-> sb.setSpeech(RSA_Controller.getResendStep3()));
        FadeTransition bubbleAppear = AnimationMethods.fadeInto(sb.getSp());

        Speechbubble next = AnimationMethods.invisSpeechbubble(RSA_Controller.getNextStep3(),900,275,"tc",250);
        p.getChildren().add(next.getSp());

        FadeTransition nextAppear=AnimationMethods.fadeInto(next.getSp());
        ParallelTransition pt1 =AnimationMethods.createParallel(new Transition[]{bubbleAppear,nextAppear});

        st.getChildren().addAll(bubbleChange,pt1, AnimationMethods.pauseSeconds(4));
    }


}
