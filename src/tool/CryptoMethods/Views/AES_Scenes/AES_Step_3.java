package tool.CryptoMethods.Views.AES_Scenes;

import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import tool.CryptoMethods.Controllers.AES_Controller;
import tool.CryptoMethods.Controllers.RSA_Controller;
import tool.CryptoMethods.Views.AnimationMethods;
import tool.Graphics.Monitor;
import tool.Graphics.Paper;
import tool.Graphics.Robot;
import tool.Graphics.Speechbubble;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 19/12/2015.
 */
public class AES_Step_3 {
    static Speechbubble bubble;
    static Robot encrypt;
    static Robot decrypt;
    static Monitor[] screens= new Monitor[3];

    public static void createPane(Pane root){
        encrypt = RSA_Controller.getEncrypt();
        decrypt = AES_Controller.getDecrypt();
        //Pane root = new Pane();
        root.setPrefSize(1100,600);
        background(root);

        AnimationMethods.placeRobots(encrypt, root, 40, 200);
        AnimationMethods.placeRobots(decrypt, root, 975, 200);

        bubble = new Speechbubble("tc", AES_Controller.getStep_3_welcome(),275);
        bubble.getSp().setLayoutX(850);
        bubble.getSp().setLayoutY(350);

        screens[0]= new Monitor(150,825,250);
        screens[1]= new Monitor(150,175,250);
        screens[2]= new Monitor(150,500,500);

        root.getChildren().addAll(bubble.getSp(),screens[0].getImage(),
                screens[1].getImage(),screens[2].getImage());
        root.setPadding(new Insets(0,100,0,100));
        //return root;

    }

    private static void background(Pane p){
        Rectangle left = new Rectangle(25,25,550,600);
        left.getStyleClass().add("rectangle-encrypt");
        Rectangle right= new Rectangle(600,25,550,600);
        right.getStyleClass().add("rectangle-decrypt");

        p.getChildren().addAll(left, right);

    }

    public static SequentialTransition createTimeLine(Pane root) {
        SequentialTransition st = new SequentialTransition();
        AnimationMethods.changeBubble(st,bubble,AES_Controller.getStep_3_key_encrypt());

        ImageView key = encryptKey(st,root);
        moveKey(st,root,key);
        sendMessage(st,root);


        return st;


    }

    private static ImageView encryptKey(SequentialTransition st, Pane p){
        Text key = AnimationMethods.textSetup("Key",230,305,"This is the key before encryption");
        p.getChildren().add(key);

        ImageView locked =new ImageView("tool/Files/Images/closedLock.png");
        locked.setOpacity(0);locked.setLayoutX(230);locked.setLayoutY(270);
        locked.setPreserveRatio(true);locked.setFitWidth(35);
        p.getChildren().add(locked);

        FadeTransition keyAppear =AnimationMethods.fadeInto(key);
        FadeTransition keyChange = AnimationMethods.fadeAway(key);
        keyChange.setOnFinished(event->{
            p.getChildren().remove(key);

        });
        FadeTransition reappear = AnimationMethods.fadeInto(locked);
        st.getChildren().addAll(keyAppear,AnimationMethods.pauseSeconds(3),
                                keyChange,reappear);
        return locked;

    }

    private static void moveKey(SequentialTransition st, Pane p, ImageView key){
        AnimationMethods.changeBubble(st,bubble,AES_Controller.getStep_3_send_key());

        ImageView copy =new ImageView(key.getImage());copy.setFitWidth(35); copy.setPreserveRatio(true);
        copy.setLayoutX(575);copy.setLayoutY(270);copy.setOpacity(0);
        PauseTransition briefPause = AnimationMethods.pauseSeconds(1);
        briefPause.setOnFinished(event->p.getChildren().add(copy));


        TranslateTransition moveKey = AnimationMethods.moveNode(key,650,0,8);

        SequentialTransition evilCopy = AnimationMethods.createSequential(new Transition[]{
                AnimationMethods.pauseSeconds(4),
                AnimationMethods.fadeInto(copy,1),
                AnimationMethods.moveNode(copy,0,250,4)
        });

        ParallelTransition pt = AnimationMethods.createParallel(new Transition[]{
                moveKey,evilCopy});

        FadeTransition evilGone= AnimationMethods.fadeAway(copy);
        evilGone.setOnFinished(event->{
            p.getChildren().remove(copy);
        });

        FadeTransition keyChange=AnimationMethods.fadeAway(key);
        keyChange.setOnFinished(event->{
            key.setImage(new Image("tool/Files/Images/openLock.png"));
            key.setFitWidth(50);
        });
        FadeTransition keyAppear = AnimationMethods.fadeInto(key);
        FadeTransition keyGone=AnimationMethods.fadeAway(key);
        keyGone.setOnFinished(event->{
            p.getChildren().remove(key);
        });


        st.getChildren().addAll(briefPause,pt, evilGone,keyChange,keyAppear,
                AnimationMethods.pauseSeconds(4),keyGone);

    }

    private static void sendMessage(SequentialTransition st, Pane p){
        AnimationMethods.changeBubble(st,bubble,AES_Controller.getStep_3_send_message());
        Paper stack = new Paper("encrypt",100,210,250);
        stack.getView().setOpacity(0);
        p.getChildren().add(stack.getView());

        Paper[] sheets = new Paper[3];
        sheets[0] = new Paper("single",100,210,250);
        sheets[1] = new Paper("single",100,210,250);
        sheets[2] = new Paper("single",100,210,250);

        FadeTransition stackAppear = AnimationMethods.fadeInto(stack.getView());
        stackAppear.setOnFinished(event->{
            p.getChildren().addAll(sheets[0].getView(),sheets[1].getView(),sheets[2].getView());
            stack.getView().toFront();
        });
        st.getChildren().add(stackAppear);

        for(Paper paper:sheets){
            st.getChildren().addAll(
                    AnimationMethods.moveNode(paper.getView(),650,0,7),
                    AnimationMethods.fadeAway(paper.getView()),
                    AnimationMethods.moveNode(paper.getView(),300,0,1),
                    AnimationMethods.fadeInto(paper.getView()),
                    AnimationMethods.moveNode(paper.getView(),300,250,3),
                    AnimationMethods.fadeAway(paper.getView())
            );
        }

        FadeTransition moveStack=AnimationMethods.fadeAway(stack.getView());
        moveStack.setOnFinished(event->{
            stack.getView().setLayoutX(860);
        });
        FadeTransition stackSent = AnimationMethods.fadeInto(stack.getView());

        st.getChildren().addAll(moveStack,stackSent);
        AnimationMethods.changeBubble(st,bubble,AES_Controller.getStep_3_finish());

    }
}
