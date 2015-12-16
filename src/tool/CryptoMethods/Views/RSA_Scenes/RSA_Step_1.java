package tool.CryptoMethods.Views.RSA_Scenes;

import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import tool.CryptoMethods.Controllers.RSA_Controller;
import tool.CryptoMethods.Views.AnimationMethods;
import tool.Graphics.Robot;
import tool.Graphics.Speechbubble;

/** Author : Phillipa Russell
 *  Created: 31/10/2015
 */
public class RSA_Step_1 {
    static Speechbubble welcome;

    public static void createPane(Pane root){
        Robot encrypt = RSA_Controller.getEncrypt();
        Robot decrypt = RSA_Controller.getDecrypt();

        //Pane root = new Pane();
        root.setPrefSize(1100,600);
        background(root);


        placeRobotsFirst(encrypt,decrypt,root);

        welcome = new Speechbubble("tr",RSA_Controller.getWelcome(),200);
        welcome.getSp().setLayoutX(750);
        welcome.getSp().setLayoutY(250);

        root.getChildren().add(welcome.getSp());
        root.setPadding(new Insets(0,100,0,100));
        //return root;

    }

    public static void background(Pane p){
        Rectangle left = new Rectangle(30,50,260,550);
        left.getStyleClass().add("rectangle-encrypt");

        Rectangle right = new Rectangle(325,50,850,550);
        right.getStyleClass().add("rectangle-decrypt");

        Rectangle middle = new Rectangle(350,75,400,500);
        middle.getStyleClass().add("rectangle-neither");

        p.getChildren().addAll(left,right,middle);

    }


    public static void placeRobotsFirst(Robot e, Robot d, Pane p){
        AnimationMethods.placeRobots(d, p, 950, 175);
        AnimationMethods.placeRobots(e, p, 35, 50);
    }



    public static SequentialTransition createTimeLine(Pane root){
        SequentialTransition st = new SequentialTransition();
        int x=375;
        int y=150;

        FadeTransition welcomeDisappear = AnimationMethods.fadeAway(welcome.getSp());
        st.getChildren().add(welcomeDisappear);

        Text p = AnimationMethods.textSetup(RSA_Controller.getPrimeP(), x, y, RSA_Controller.getTooltipP());
        Text q =AnimationMethods.textSetup(RSA_Controller.getPrimeQ(), x, (y += 35), RSA_Controller.getTooltipQ());
        Text nEq = AnimationMethods.textSetup(RSA_Controller.getEquationN(), x, (y += 35), RSA_Controller.getTooltipNEq());
        Text n = AnimationMethods.textSetup(RSA_Controller.getModulusN(), x, (y += 35), RSA_Controller.getTooltipN());
        Text zEq =AnimationMethods.textSetup(RSA_Controller.getTotientEquation(), x, (y += 35), RSA_Controller.getTooltipZEq());
        Text z = AnimationMethods.textSetup(RSA_Controller.getTotientZ(), x, (y += 35), RSA_Controller.getTooltipZ());
        Text k = AnimationMethods.textSetup(RSA_Controller.getPrimeK(), x, (y += 35), RSA_Controller.getTooltipK());
        Text jSecret = AnimationMethods.textSetup(RSA_Controller.getSecretJEq(), x, (y += 35), RSA_Controller.getTooltipSecretJ());
        Text jNo = AnimationMethods.textSetup(RSA_Controller.getSecretJNo(), x, (y += 35), RSA_Controller.getTooltipJNo());
        Text j = AnimationMethods.textSetup(RSA_Controller.getSecretJ(), x, y+=35, RSA_Controller.getTooltipJ());

        Speechbubble encrypts = AnimationMethods.invisSpeechbubble(RSA_Controller.geteInfo(), 100, 180, "tl", 200);
        root.getChildren().add(encrypts.getSp());

        FadeTransition encryptsAppear = AnimationMethods.fadeInto(encrypts.getSp());
        st.getChildren().add(encryptsAppear);

        bubbleAnd2Text(RSA_Controller.getPqExpn(), st, root, p, q);
        bubbleAnd2Text(RSA_Controller.getnExp(), st, root, nEq, n);
        bubbleAnd2Text(RSA_Controller.getTotientExp(), st, root, zEq, z);
        bubbleAnd1Text(RSA_Controller.getkExp(),st,root,k);
        bubbleAnd2Text(RSA_Controller.getjExp(),st,root,jSecret,jNo);
        bubbleAnd1Text(RSA_Controller.getCalculations(),st,root,j);

        Speechbubble next=setUpBubble(RSA_Controller.getStep1next());
        root.getChildren().add(next.getSp());

        FadeTransition finalBubble = AnimationMethods.fadeInto(next.getSp());
        FadeTransition finalBubbleFade = AnimationMethods.fadeAway(next.getSp());

        st.getChildren().addAll(finalBubble, AnimationMethods.pauseSeconds(5), finalBubbleFade);

        return st;
    }



    public static void bubbleAnd2Text(String bubble, SequentialTransition st, Pane p, Text a, Text b){
        Speechbubble speechB = setUpBubble(bubble);
        p.getChildren().addAll(a,b,speechB.getSp());

        FadeTransition bubbleAppear = AnimationMethods.fadeInto(speechB.getSp());
        FadeTransition bubbleDisappear = AnimationMethods.fadeAway(speechB.getSp());

        FadeTransition textAAppear = AnimationMethods.fadeInto(a);
        FadeTransition textBAppear = AnimationMethods.fadeInto(b);


        ParallelTransition pt1 = AnimationMethods.createParallel(new Transition[]{textAAppear,textBAppear});

        st.getChildren().addAll(bubbleAppear,pt1,
                AnimationMethods.pauseSeconds(7),
                bubbleDisappear);
    }

    public static void bubbleAnd1Text(String bubble, SequentialTransition st, Pane p, Text a){
        Speechbubble speechB = setUpBubble(bubble);
        p.getChildren().add(speechB.getSp());

        FadeTransition bubbleAppear = AnimationMethods.fadeInto(speechB.getSp());
        FadeTransition bubbleDisappear = AnimationMethods.fadeAway(speechB.getSp());

        FadeTransition textAppear = AnimationMethods.fadeInto(a);

        p.getChildren().add(a);

        st.getChildren().addAll(bubbleAppear,textAppear,
                AnimationMethods.pauseSeconds(7),
                bubbleDisappear);

    }

    public static Speechbubble setUpBubble(String bubble){
        return AnimationMethods.invisSpeechbubble(bubble, 750, 250, "tr", 225);
    }





}
