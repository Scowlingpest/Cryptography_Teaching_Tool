package tool.CryptoMethods.Views.RSAScenes;

import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import tool.CryptoMethods.Controllers.RSA_Controller;
import tool.CryptoMethods.Views.RSA;
import tool.Graphics.Robot;
import tool.Graphics.Speechbubble;

/** Author : Phillipa Russell
 *  Created: 31/10/2015
 */
public class RSA_Step_1 {
    static Speechbubble welcome;
    static Interpolator ip = Interpolator.DISCRETE;

    public static void createPane(Pane root){
        Robot encrypt = RSA_Controller.getEncrypt();
        Robot decrypt = RSA_Controller.getDecrypt();

        //Pane root = new Pane();
        root.setPrefSize(1000,600);
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
        placeRobots(d,p,950,175);
        placeRobots(e,p,35,50);
    }

    public static void placeRobots(Robot r, Pane p,int x, int y){
        r.getView().setLayoutX(x);
        r.getView().setLayoutY(y);
        r.setImageWidth(125);

        p.getChildren().add(r.getView());
    }


    public static Timeline createTimeLine(Pane root){
        Timeline tl = new Timeline();
        int x=375;
        int y=150;

        KeyFrame kf1 = new KeyFrame(Duration.seconds(3),new KeyValue(welcome.getSp().opacityProperty(),0));
        tl.getKeyFrames().add(kf1);

        Text p = textSetup(RSA_Controller.getPrimeP(),x,y,RSA_Controller.getTooltipP());
        Text q =textSetup(RSA_Controller.getPrimeQ(), x, (y+=35), RSA_Controller.getTooltipQ());
        Text nEq = textSetup(RSA_Controller.getEquationN(),x,(y+=35),RSA_Controller.getTooltipNEq());
        Text n = textSetup(RSA_Controller.getModulusN(), x, (y+=35), RSA_Controller.getTooltipN());
        Text zEq =textSetup(RSA_Controller.getTotientEquation(),x,(y+=35),RSA_Controller.getTooltipZEq());
        Text z = textSetup(RSA_Controller.getTotientZ(), x, (y+=35), RSA_Controller.getTooltipZ());
        Text k = textSetup(RSA_Controller.getPrimeK(),x,(y+=35),RSA_Controller.getTooltipK());
        Text jSecret = textSetup(RSA_Controller.getSecretJEq(),x,(y+=35),RSA_Controller.getTooltipSecretJ());
        Text jNo = textSetup(RSA_Controller.getSecretJNo(),x,(y+=35),RSA_Controller.getTooltipJNo());
        Text j = textSetup(RSA_Controller.getSecretJ(),x,(y+=35),RSA_Controller.getTooltipJ());

        Speechbubble encrypts = invisSpeechbubble(RSA_Controller.geteInfo(),50,180,"tl");
        root.getChildren().add(encrypts.getSp());
        KeyFrame e = new KeyFrame(Duration.seconds(3),new KeyValue(encrypts.getSp().opacityProperty(),1));
        tl.getKeyFrames().add(e);

        bubbleAnd2Text(RSA_Controller.getPqExpn(), 3, tl, root, p, q);
        bubbleAnd2Text(RSA_Controller.getnExp(), 21, tl, root, nEq, n);
        bubbleAnd2Text(RSA_Controller.getTotientExp(), 40, tl, root, zEq, z);
        bubbleAnd1Text(RSA_Controller.getkExp(),60,tl,root,k);
        bubbleAnd2Text(RSA_Controller.getjExp(),74,tl,root,jSecret,jNo);
        bubbleAnd1Text(RSA_Controller.getCalculations(),94,tl,root,j);

        Speechbubble next=setUpBubble(RSA_Controller.getStep1next());
        root.getChildren().add(next.getSp());
        KeyFrame end1 = new KeyFrame(Duration.seconds(108),new KeyValue(next.getSp().opacityProperty(),1,ip));
        tl.getKeyFrames().add(end1);

        return tl;
    }

    public static Text textSetup(String text, int x, int y, String tool){
        Text t = new Text(text);
        t.getStyleClass().add("text-animate");
        t.setLayoutX(x);
        t.setLayoutY(y);
        t.setOpacity(0);
        Tooltip.install(t,new Tooltip(tool));
        return t;
    }

    public static void bubbleAnd2Text(String bubble, int time, Timeline tl, Pane p, Text a, Text b){
        Speechbubble speechB = setUpBubble(bubble);
        p.getChildren().add(speechB.getSp());


        KeyFrame kf1 = new KeyFrame(Duration.seconds(time),new KeyValue(speechB.getSp().opacityProperty(),1, ip));

        p.getChildren().add(a);
        KeyFrame kf2 = new KeyFrame(Duration.seconds(time+3),new KeyValue(a.opacityProperty(),1,ip));

        p.getChildren().add(b);
        KeyFrame kf3 = new KeyFrame(Duration.seconds(time+6),new KeyValue(b.opacityProperty(),1,ip));
        KeyFrame kf4 = new KeyFrame(Duration.seconds(time+20),new KeyValue(speechB.getSp().opacityProperty(),0,ip));

        tl.getKeyFrames().addAll(kf1,kf2,kf3,kf4);
    }

    public static void bubbleAnd1Text(String bubble, int time, Timeline tl, Pane p, Text a){
        Speechbubble speechB = setUpBubble(bubble);
        p.getChildren().add(speechB.getSp());

        KeyFrame kf1 = new KeyFrame(Duration.seconds(time),new KeyValue(speechB.getSp().opacityProperty(),1, ip));

        p.getChildren().add(a);
        KeyFrame kf2 = new KeyFrame(Duration.seconds(time+3),new KeyValue(a.opacityProperty(),1,ip));
        KeyFrame kf3 = new KeyFrame(Duration.seconds(time+14),new KeyValue(speechB.getSp().opacityProperty(),0,ip));

        tl.getKeyFrames().addAll(kf1,kf2,kf3);

    }

    public static Speechbubble setUpBubble(String bubble){
        return invisSpeechbubble(bubble,750,250,"tr");
    }

    public static Speechbubble invisSpeechbubble(String bubble, int x, int y, String type){
        Speechbubble speechB = new Speechbubble(type,bubble,250);
        speechB.getSp().setLayoutX(x);
        speechB.getSp().setLayoutY(y);
        speechB.getSp().setOpacity(0);
        return speechB;
    }


}
