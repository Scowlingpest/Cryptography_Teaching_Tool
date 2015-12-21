package tool.CryptoMethods.Views.AES_Scenes;

import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import tool.CryptoMethods.Controllers.AES_Controller;
import tool.CryptoMethods.Views.AnimationMethods;
import tool.Graphics.Box;
import tool.Graphics.Paper;
import tool.Graphics.Robot;
import tool.Graphics.Speechbubble;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 20/12/2015.
 */
public class AES_Step_4 {

    static Speechbubble bubble;
    static Robot decrypt;

    public static void createPane(Pane root){
        decrypt = AES_Controller.getDecrypt();
        //Pane root = new Pane();
        root.setPrefSize(1100,600);
        background(root);

        AnimationMethods.placeRobots(decrypt, root, 975, 50);
        decrypt.setImageWidth(150);

        bubble = new Speechbubble("tr", AES_Controller.getStep_4_welcome(),275);
        bubble.getSp().setLayoutX(800);
        bubble.getSp().setLayoutY(125);

        root.getChildren().add(bubble.getSp());
        root.setPadding(new Insets(0,100,0,100));
        //return root;

    }

    private static void background(Pane p){
        Rectangle left = new Rectangle(25,25,1150,600);
        left.getStyleClass().add("rectangle-decrypt");


        p.getChildren().add(left);

    }

    public static SequentialTransition createTimeLine(Pane root) {
        SequentialTransition st = new SequentialTransition();

        robotHello(st);
        Box[] rounds =roundStages(st,root);
        paperCreate(st,root);
        Paper[] sheets =changeRounds(st,root,rounds);
        next2Sheets(sheets,root,st);



        return st;


    }

    private static void robotHello(SequentialTransition st){
        AnimationMethods.changeBubble(st,bubble,AES_Controller.getStep_4_rounds());
        st.getChildren().add(AnimationMethods.pauseSeconds(10));
    }


    private static Box[] roundStages(SequentialTransition st, Pane p){
        int width = 450;
        Box[] boxes = new Box[3];
        boxes[0]= new Box("Round : 1");
        boxes[1]= new Box("Rounds: 2-9");
        boxes[2]= new Box("Round : 10");

        boxes[0].drawBox("This is the first round",90,width);boxes[0].getSp().setOpacity(0);
        boxes[0].getSp().setLayoutX(AES_Controller.getBoxX());boxes[0].getSp().setLayoutY(AES_Controller.getBoxY1());

        boxes[1].drawBox("This is all the rounds between the first and last round",90,width);boxes[1].getSp().setOpacity(0);
        boxes[1].getSp().setLayoutX(AES_Controller.getBoxX());boxes[1].getSp().setLayoutY(AES_Controller.getBoxY2());

        boxes[2].drawBox("This is the last round",90,width);boxes[2].getSp().setOpacity(0);
        boxes[2].getSp().setLayoutX(AES_Controller.getBoxX());boxes[2].getSp().setLayoutY(AES_Controller.getBoxY3());


        p.getChildren().addAll(boxes[0].getSp(),boxes[1].getSp(),boxes[2].getSp());

        st.getChildren().addAll(AnimationMethods.fadeInto(boxes[0].getSp()),
                AnimationMethods.fadeInto(boxes[1].getSp()),AnimationMethods.fadeInto(boxes[2].getSp()));




        return boxes;
    }

    private static Paper paperCreate(SequentialTransition st,Pane p){
        Paper stack = new Paper("encrypt",100,AES_Controller.getStartLocX(),AES_Controller.getStartLocY());
        stack.getView().setOpacity(0);
        p.getChildren().add(stack.getView());

        AnimationMethods.changeBubble(st,bubble,AES_Controller.getStep_4_stages());
        st.getChildren().addAll(AnimationMethods.fadeInto(stack.getView()),
                AnimationMethods.pauseSeconds(4));


        return stack;
    }

    private static Paper[] changeRounds(SequentialTransition st,Pane p, Box[] boxes){
        AnimationMethods.changeBubble(st,bubble, AES_Controller.getStep_4_first());

        //create paper sheets used later on
        Paper[] sheets = new Paper[3];
        sheets[0] = new Paper("single",100,AES_Controller.getStartLocX(),AES_Controller.getStartLocY());
        sheets[1] = new Paper("single",100,AES_Controller.getStartLocX(),AES_Controller.getStartLocY());
        sheets[2] = new Paper("single",100,AES_Controller.getStartLocX(),AES_Controller.getStartLocY());
        Paper encrypt = new Paper("decrypt",100,AES_Controller.getEndLocX(),AES_Controller.getEndLocY());
        p.getChildren().add(encrypt.getView());

        //changes round 1 to XOR round key
        FadeTransition changeRound1 = AnimationMethods.fadeAway(boxes[0].getTextFromSp());
        changeRound1.setOnFinished(event-> {
            boxes[0].changeText(" XOR  /Inv Shift Row/Inv Sub Byte");
            boxes[0].getSp().getChildren().get(1).setStyle("-fx-font-size:28");

        });

        FadeTransition displayRound1 =AnimationMethods.fadeInto(boxes[0].getTextFromSp());
        displayRound1.setOnFinished(event->{
            p.getChildren().addAll(sheets[0].getView(),sheets[1].getView(),sheets[2].getView());
            sheets[0].getView().setOpacity(1);
            sheets[1].getView().setOpacity(1);
            sheets[2].getView().setOpacity(1);
        });

        st.getChildren().addAll(changeRound1,displayRound1);

        //moves paper into round 1
        movePaperRound1(sheets[0],st);
        AnimationMethods.changeBubble(st, bubble, AES_Controller.getStep_4_intermediary());

        //changes round 2 text
        FadeTransition changeRound2 = AnimationMethods.fadeAway(boxes[1].getTextFromSp());
        changeRound2.setOnFinished(event->{
            boxes[1].changeText("XOR /Inv Mix Column/Inv Shift Row/Inv Sub Byte");
            boxes[1].getSp().getChildren().get(1).setStyle("-fx-font-size:18");
        });
        FadeTransition displayRound2 =AnimationMethods.fadeInto(boxes[1].getTextFromSp());
        st.getChildren().addAll(changeRound2,displayRound2);

        //moves sheet of paper through round 2
        movePaperRoundMiddle(sheets[0],st,1);

        //changes final round text
        FadeTransition changeRoundFinal = AnimationMethods.fadeAway(boxes[2].getTextFromSp());
        changeRoundFinal.setOnFinished(event-> boxes[2].changeText("XOR First Key"));

        FadeTransition displayRoundFinal =AnimationMethods.fadeInto(boxes[2].getTextFromSp());
        st.getChildren().addAll(changeRoundFinal,displayRoundFinal);

        //moves paper sheet through final round
        movePaperFinalRound(sheets[0],st);

        //encrypts paper and removes sheet
        ParallelTransition encryptAppear = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeInto(encrypt.getView()),
                AnimationMethods.fadeAway(sheets[0].getView())
        });
        encryptAppear.setOnFinished(event->p.getChildren().remove(sheets[0].getView()));

        st.getChildren().add(encryptAppear);


        return sheets;
    }

    private static void movePaperRound1(Paper sheet,SequentialTransition st){
        int x=150;
        for(int i=0;i<3;i++){
            st.getChildren().addAll(
                    AnimationMethods.moveNode(sheet.getView(),100+(x*i),0,2),
                    AnimationMethods.fadeAway(sheet.getView()),
                    AnimationMethods.pauseSeconds(2),
                    AnimationMethods.fadeInto(sheet.getView())
            );
        }

        st.getChildren().add(AnimationMethods.moveNode(sheet.getView(), AES_Controller.getDifference(),
                0, 2));



    }

    private static void movePaperRoundMiddle(Paper sheet,SequentialTransition st, int t){
        TranslateTransition moveDown = AnimationMethods.moveNode(sheet.getView(),AES_Controller.getDifference(),100,3);
        TranslateTransition moveBack = AnimationMethods.moveNode(sheet.getView(),0,100,8);
        TranslateTransition moveDownAgain = AnimationMethods.moveNode(sheet.getView(),0,200,3);
        st.getChildren().addAll(moveDown,moveBack,moveDownAgain);
        int x=120;
        for(int i=0;i<4;i++){
            st.getChildren().addAll(
                    AnimationMethods.moveNode(sheet.getView(),100+(x*i),200,2),
                    AnimationMethods.fadeAway(sheet.getView()),
                    AnimationMethods.pauseSeconds(2),
                    AnimationMethods.fadeInto(sheet.getView())
            );
            if(i==2 && t==1){
                AnimationMethods.changeBubble(st,bubble,AES_Controller.getStep_4_final());
            }
        }
        st.getChildren().add(AnimationMethods.moveNode(sheet.getView(),AES_Controller.getDifference(),
                200,2));
    }

    private static void movePaperFinalRound(Paper sheet,SequentialTransition st){
        TranslateTransition moveDown = AnimationMethods.moveNode(sheet.getView(),AES_Controller.getDifference(),300,3);
        TranslateTransition moveBack = AnimationMethods.moveNode(sheet.getView(),0,300,8);
        TranslateTransition moveDownAgain = AnimationMethods.moveNode(sheet.getView(),0,
                400,3);
        st.getChildren().addAll(moveDown,moveBack,moveDownAgain);

        TranslateTransition moveSheet = AnimationMethods.moveNode(sheet.getView(), AES_Controller.getHalfway(), 400, 4);
        FadeTransition fadePage = AnimationMethods.fadeAway(sheet.getView());
        FadeTransition appearFade = AnimationMethods.fadeInto(sheet.getView());
        TranslateTransition leaveRound = AnimationMethods.moveNode(sheet.getView(),AES_Controller.getDifference(),400,4);

        st.getChildren().addAll(moveSheet,fadePage,
                AnimationMethods.pauseSeconds(2),
                appearFade,leaveRound);

        //st.getChildren().add(AnimationMethods.moveNode(sheet.getView(), AES_Controller.getDifference(),400, 2));

    }

    private static void next2Sheets(Paper[] sheets, Pane p, SequentialTransition st){
        //AnimationMethods.changeBubble(st,bubble,AES_Controller.getStep_2_encrypt());

        movePaperRound1(sheets[1],st);
        movePaperRoundMiddle(sheets[1],st,2);
        movePaperFinalRound(sheets[1],st);
        FadeTransition remove2 = AnimationMethods.fadeAway(sheets[1].getView());
        remove2.setOnFinished(event-> p.getChildren().remove(sheets[1]));
        st.getChildren().add(remove2);

        movePaperRound1(sheets[2],st);
        movePaperRoundMiddle(sheets[2],st,3);
        movePaperFinalRound(sheets[2],st);
        FadeTransition remove3 = AnimationMethods.fadeAway(sheets[2].getView());
        remove3.setOnFinished(event-> p.getChildren().remove(sheets[2]));
        st.getChildren().add(remove3);

        //AnimationMethods.changeBubble(st,bubble,AES_Controller.getStep_4_final());
    }
}