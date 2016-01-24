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
 * Creation: 06/11/2015.
 */
public class RSA_Step_4 {
    static Monitor m1 = new Monitor(200,750,200);
    static Monitor m2 = new Monitor(200,175,100);
    static Monitor m3 = new Monitor(200,500,300);

    public static void createPane(Pane root){
        Robot encrypt = RSA_Controller.getEncrypt();
        Robot decrypt = RSA_Controller.getDecrypt();
        m3.getImage().setOpacity(0);



        root.setPrefSize(1100,600);
        background(root);


        placeRobotsFirst(encrypt,decrypt,root);
        root.getChildren().addAll(m1.getImage(),m2.getImage(),m3.getImage());

    }

    public static void background(Pane p){
        Rectangle right = new Rectangle(450,50,700,550);
        right.getStyleClass().add("rectangle-decrypt");

        Rectangle left = new Rectangle(30,50,400,550);
        left.getStyleClass().add("rectangle-encrypt");

        p.getChildren().addAll(left, right);

    }

    public static void placeRobotsFirst(Robot e, Robot d, Pane p){
        AnimationMethods.placeRobots(d, p, 950, 175);
        AnimationMethods.placeRobots(e, p, 35, 50);
    }

    public static SequentialTransition createTimeLine(Pane p){
        SequentialTransition st = new SequentialTransition();
        Speechbubble bubble =new Speechbubble("tl",RSA_Controller.getWelcomeStep4(),225,125,175);

        p.getChildren().addAll(bubble.getSp());
        encryptPaper(p,st,bubble);

        return st;
    }

    private static void encryptPaper(Pane p, SequentialTransition st, Speechbubble bubble){
        Paper p1 = new Paper("n",100,800,225);
        p1.getView().setOpacity(0);

        p.getChildren().add(p1.getView());

        FadeTransition paperAppear = AnimationMethods.fadeInto(p1.getView(),3);
        FadeTransition bubbleChange = AnimationMethods.fadeAway(bubble.getSp(),3);
        bubbleChange.setOnFinished(event->
        bubble.setSpeech(RSA_Controller.getFirstStep4()));

        ParallelTransition pt1 = AnimationMethods.createParallel(new Transition[]{paperAppear,bubbleChange});

        FadeTransition bubbleAppear = AnimationMethods.fadeInto(bubble.getSp(),3);

        st.getChildren().addAll(pt1,bubbleAppear);
        secretEncryption(p,st,bubble,p1);
    }

    private static void secretEncryption(Pane p,SequentialTransition st, Speechbubble bubble, Paper p1){
        Text equation = AnimationMethods.textSetup(RSA_Controller.getEncryptStep4(),765,400,RSA_Controller.getTooltipStep4Eq());
        p.getChildren().add(equation);

        FadeTransition equationAppear = AnimationMethods.fadeInto(equation,3);
        FadeTransition equationChange=AnimationMethods.fadeAway(equation,3);
        equationChange.setOnFinished(event-> equation.setText(RSA_Controller.getEncryptNoStep4()));

        FadeTransition equationReappear =AnimationMethods.fadeInto(equation,3);
        TranslateTransition moveEquation = AnimationMethods.moveNode(equation,0,-135,5);

        FadeTransition paperChange = AnimationMethods.fadeAway(p1.getView(),3);
        paperChange.setOnFinished(event-> p1.changeToEncrypt());

        FadeTransition paperAppear = AnimationMethods.fadeInto(p1.getView(),3);

        st.getChildren().addAll(equationAppear,AnimationMethods.pauseSeconds(4),equationChange,equationReappear,
                moveEquation,paperChange,paperAppear);

        sendToEncrypt(p,st,bubble,p1);

    }

    private static void sendToEncrypt(Pane p,SequentialTransition st, Speechbubble bubble, Paper p1){
        AnimationMethods.changeBubble(st,bubble,RSA_Controller.getSendEncryptStep4());

        TranslateTransition movePaper =AnimationMethods.moveNode(p1.getView(),-575,-100,6);

        Text decryptEquation = AnimationMethods.textSetup(RSA_Controller.getDecryptStep4(),200,155,RSA_Controller.getTooltipStep4decrypt());
        p.getChildren().add(decryptEquation);

        FadeTransition eqAppear = AnimationMethods.fadeInto(decryptEquation,3);
        FadeTransition eqChange = AnimationMethods.fadeAway(decryptEquation,3);
        eqChange.setOnFinished(event-> decryptEquation.setText(RSA_Controller.getDecryptNoStep4()));

        FadeTransition eqReappear = AnimationMethods.fadeInto(decryptEquation,3);

        FadeTransition paperChange =AnimationMethods.fadeAway(p1.getView(),3);
        paperChange.setOnFinished(event-> p1.changeToDecrypt());
        FadeTransition paperAppear =AnimationMethods.fadeInto(p1.getView(),3);


        st.getChildren().addAll(movePaper,eqAppear,eqChange,eqReappear,paperChange,paperAppear);
        wrongPerson(p,st,bubble,p1);

    }

    private static void wrongPerson(Pane p, SequentialTransition st, Speechbubble bubble,Paper p1){
        AnimationMethods.changeBubble(st,bubble,RSA_Controller.getWrongStep4());
        FadeTransition m3Appear = AnimationMethods.fadeInto(m3.getImage(),3);
        FadeTransition p1Away = AnimationMethods.fadeAway(p1.getView(),3);

        Paper p2 =new Paper("encrypt",100,550,325);
        p.getChildren().add(p2.getView());

        FadeTransition p2Appear = AnimationMethods.fadeInto(p2.getView(),3);

        ParallelTransition pt1 = AnimationMethods.createParallel(new Transition[]{m3Appear,p1Away,p2Appear});

        TranslateTransition movePaper =AnimationMethods.moveNode(p2.getView(),-325,-200,5);
        FadeTransition changePaper =AnimationMethods.fadeAway(p2.getView(),3);
        FadeTransition paperChangeFailed=AnimationMethods.fadeInto(p2.getView(),3);

        SequentialTransition paperStuff =AnimationMethods.createSequential(new Transition[]{movePaper,changePaper,AnimationMethods.pauseSeconds(3),paperChangeFailed});

        st.getChildren().addAll(pt1, paperStuff);

        finishStep4(st, bubble);
    }

    private static void finishStep4(SequentialTransition st, Speechbubble bubble){
        AnimationMethods.changeBubble(st,bubble,RSA_Controller.getFinishStep4());
    }
}
