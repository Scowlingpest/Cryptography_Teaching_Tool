package tool.CryptoMethods.Views.RSAScenes;

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
 * Created by Phillipa on 01/11/2015.
 */
public class RSA_Step_2 {
    static Interpolator ip = Interpolator.DISCRETE;
    static Monitor m1;
    static Monitor m2;
    static Monitor m3;

    public static void createPane(Pane root){
        Robot encrypt = RSA_Controller.getEncrypt();
        Robot encrypt2 = RSA_Controller.getEncrypt2();
        Robot decrypt = RSA_Controller.getDecrypt();

        root.setPrefSize(1000,600);
        background(root);


        placeRobotsFirst(encrypt,encrypt2,decrypt,root);

        m1 = new Monitor(100,850,200);
        m2 = new Monitor(100,175,100);
        m3 = new Monitor(100,175,425);

        root.getChildren().addAll(m1.getImage(),m2.getImage(),m3.getImage());

        root.setPadding(new Insets(0,100,0,100));

    }

    public static void background(Pane p){
        Rectangle left = new Rectangle(30,50,600,550);
        left.getStyleClass().add("rectangle-encrypt");

        Rectangle right = new Rectangle(650,50,500,550);
        right.getStyleClass().add("rectangle-decrypt");

        p.getChildren().addAll(left,right);

    }


    public static void placeRobotsFirst(Robot e,Robot e2, Robot d, Pane p){
        AnimationMethods.placeRobots(d, p, 950, 175);
        AnimationMethods.placeRobots(e, p, 35, 50);
        AnimationMethods.placeRobots(e2, p, 35, 350);
    }

    public static SequentialTransition createTimeLine(Pane p){
        SequentialTransition st = new SequentialTransition();

        Text publicKey = AnimationMethods.textSetup(RSA_Controller.getPublicKey(),875,250,RSA_Controller.getTooltipPK());
        Text publicKey2 = AnimationMethods.textSetup(RSA_Controller.getPublicKey(),875,250,RSA_Controller.getTooltipPK());

        Text equation1 = AnimationMethods.textSetup(RSA_Controller.getEncryptEq(),335,175,RSA_Controller.getTooltipEq());
        Text equation2 = AnimationMethods.textSetup(RSA_Controller.getEncryptEq(),335,400,RSA_Controller.getTooltipEq());

        Speechbubble sb = new Speechbubble("tc",RSA_Controller.getHeyStep2(),250,900,275);

        p.getChildren().addAll(equation1,equation2,sb.getSp());

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

    //method that deals with the animation for moving the keys across the screen with speechbubble
    public static void movePublicKeys(Text a, Text b, SequentialTransition st,Pane p,Speechbubble sb){
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

    //animation method for the two speechbubbles for the encrypts
    public static void encryptsSay(SequentialTransition st, Pane p){
        Speechbubble e1 = AnimationMethods.invisSpeechbubble(RSA_Controller.getStep2KeyReceived(),125,125,"tl",215);
        Speechbubble e2 = AnimationMethods.invisSpeechbubble(RSA_Controller.getStep2Equation(),125,400,"tl",215);
        p.getChildren().addAll(e1.getSp(),e2.getSp());

        FadeTransition e1Appear =AnimationMethods.fadeInto(e1.getSp());
        FadeTransition e2Appear = AnimationMethods.fadeInto(e2.getSp());

        FadeTransition e1Disappear=AnimationMethods.fadeAway(e1.getSp());
        FadeTransition e2Disappear=AnimationMethods.fadeAway(e2.getSp());


        SequentialTransition pt1 = AnimationMethods.createSequential(new Transition[]{e1Appear,
                AnimationMethods.pauseSeconds(5),
                e1Disappear});

        SequentialTransition pt2 = AnimationMethods.createSequential(new Transition[]{e2Appear,
                AnimationMethods.pauseSeconds(5),
                e2Disappear});

        st.getChildren().addAll(pt1, pt2);
    }

    //animation method for moving and resizing the two monitors
    public static void moveMonitors(SequentialTransition st){
        ScaleTransition m2Resize = AnimationMethods.changeSize(m2.getImage(),2.25,7);
        ScaleTransition m3Resize = AnimationMethods.changeSize(m3.getImage(),2.25,7);

        TranslateTransition m2Move = AnimationMethods.moveNode(m2.getImage(),200,50,7);
        TranslateTransition m3Move = AnimationMethods.moveNode(m3.getImage(),200,-50,7);

        ParallelTransition pt1 = AnimationMethods.createParallel(new Transition[]{m2Resize,m3Resize,m2Move,m3Move});

        st.getChildren().addAll(pt1);

    }

    //animation method for making the equation appear
    public static void equationAppear(SequentialTransition st,Text a, Text b,Speechbubble sb){
        FadeTransition aAppear = AnimationMethods.fadeInto(a);
        FadeTransition bAppear = AnimationMethods.fadeInto(b);

        bAppear.setOnFinished(event->{
            sb.setSpeech(RSA_Controller.getReminder());
        });

        ParallelTransition pt1 = AnimationMethods.createParallel(new Transition[]{aAppear,bAppear});
        st.getChildren().addAll(pt1);

    }

    //animation method for changing the keys to numbers and moving them into the monitors
    public static void changeKeys(SequentialTransition st, Text a, Text b, Speechbubble sb){
        FadeTransition bubbleAppear = AnimationMethods.fadeInto(sb.getSp());
        FadeTransition aDisappear = AnimationMethods.fadeAway(a);
        FadeTransition bDisappear = AnimationMethods.fadeAway(b);



        ParallelTransition pt1 = AnimationMethods.createParallel(new Transition[]{aDisappear,bDisappear,bubbleAppear});

        aDisappear.setOnFinished(event->a.setText(RSA_Controller.getNoPublicKey()));
        bDisappear.setOnFinished(event->b.setText(RSA_Controller.getNoPublicKey()));


        FadeTransition aAppear = AnimationMethods.fadeInto(a);
        FadeTransition bAppear = AnimationMethods.fadeInto(b);

        ParallelTransition pt2 = AnimationMethods.createParallel(new Transition[]{aAppear,bAppear});

        TranslateTransition moveA = AnimationMethods.moveNode(a,-500,-50,5);
        TranslateTransition moveB = AnimationMethods.moveNode(b,-500,175,5);
        FadeTransition sbDisappear = AnimationMethods.fadeAway(sb.getSp(),5);

        ParallelTransition pt3 = AnimationMethods.createParallel(new Transition[]{moveA,moveB,sbDisappear});


        st.getChildren().addAll(pt1,pt2,
                AnimationMethods.pauseSeconds(5),pt3);
    }

    //animation method for making the equation change to have numbers
    public static void encryptEquation(SequentialTransition st, Text eq1, Text eq2, Text pk1, Text pk2, Pane p){
        Speechbubble sb = AnimationMethods.invisSpeechbubble(RSA_Controller.getHereEquation(),900,275,"tc",250);
        p.getChildren().add(sb.getSp());

        FadeTransition pk1Disappear = AnimationMethods.fadeAway(pk1);
        FadeTransition pk2Disappear = AnimationMethods.fadeAway(pk2);
        FadeTransition bubbleAppear = AnimationMethods.fadeInto(sb.getSp());

        ParallelTransition pt1 = AnimationMethods.createParallel(new Transition[]{pk1Disappear,pk2Disappear,bubbleAppear});

        FadeTransition eq1Disappear = AnimationMethods.fadeAway(eq1);
        FadeTransition eq2Disappear = AnimationMethods.fadeAway(eq2);
        eq1Disappear.setOnFinished(event->eq1.setText(RSA_Controller.getNoEncryptEq()));
        eq2Disappear.setOnFinished(event->eq2.setText(RSA_Controller.getNoEncryptEq()));

        ParallelTransition pt2 = AnimationMethods.createParallel(new Transition[]{eq1Disappear,eq2Disappear});

        FadeTransition eq1Appear = AnimationMethods.fadeInto(eq1);
        FadeTransition eq2Appear = AnimationMethods.fadeInto(eq2);
        FadeTransition bubbleDisappear = AnimationMethods.fadeAway(sb.getSp());

        ParallelTransition pt3 = AnimationMethods.createParallel(new Transition[]{eq1Appear,eq2Appear,bubbleDisappear});
        st.getChildren().addAll(pt1,pt2,
                AnimationMethods.pauseSeconds(5),pt3);
    }

    //animation method for making the papers appear, move and change to encrypted papers
    public static void encryptPapers(SequentialTransition st, Pane p){
        Paper e1 = new Paper("n",125,175,125);
        Paper e2 = new Paper("n",125,175,350);
        Speechbubble sb = AnimationMethods.invisSpeechbubble(RSA_Controller.getEncryption(),900,275,"tc",250);

        p.getChildren().addAll(e1.getView(),e2.getView(),sb.getSp());

        FadeTransition e1Appear = AnimationMethods.fadeInto(e1.getView());
        FadeTransition e2Appear = AnimationMethods.fadeInto(e2.getView());
        FadeTransition sbAppear = AnimationMethods.fadeInto(sb.getSp());

        ParallelTransition pt1 = AnimationMethods.createParallel(new Transition[]{e1Appear,e2Appear,sbAppear});

        TranslateTransition e1Move = AnimationMethods.moveNode(e1.getView(),180,0,5);
        TranslateTransition e2Move = AnimationMethods.moveNode(e2.getView(),180,0,5);

        ParallelTransition pt2 = AnimationMethods.createParallel(new Transition[]{e1Move,e2Move});

        FadeTransition e1Disappear = AnimationMethods.fadeAway(e1.getView());
        FadeTransition e2Disappear =AnimationMethods.fadeAway(e2.getView());
        e1Disappear.setOnFinished(event->e1.changeToEncrypt());
        e2Disappear.setOnFinished(event->e2.changeToEncrypt());

        ParallelTransition pt3 = AnimationMethods.createParallel(new Transition[]{e1Disappear,e2Disappear});

        FadeTransition e1Changed = AnimationMethods.fadeInto(e1.getView());
        FadeTransition e2Changed = AnimationMethods.fadeInto(e2.getView());

        ParallelTransition pt4 = AnimationMethods.createParallel(new Transition[]{e1Changed,e2Changed});

        FadeTransition sbDisappear = AnimationMethods.fadeAway(sb.getSp());

        SequentialTransition temp = new SequentialTransition();
        temp.getChildren().addAll(pt1,pt2,pt3,pt4,sbDisappear);

        st.getChildren().addAll(temp);


    }

    //animation method that makes the final speech bubble appear
    public static void finalBubble(SequentialTransition st, Pane p){
        Speechbubble sb = AnimationMethods.invisSpeechbubble(RSA_Controller.getNextStep2(),900,275,"tc",250);
        p.getChildren().add(sb.getSp());

        FadeTransition sbAppear =AnimationMethods.fadeInto(sb.getSp());

        st.getChildren().addAll(sbAppear, AnimationMethods.pauseSeconds(5));

    }




}
