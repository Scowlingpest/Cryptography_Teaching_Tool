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
 * Creation: 18/12/2015.
 */
//AES step 2 class, encryption using AES
public class AES_Step_2 {
    private static Speechbubble bubble;

    /*createPane,creates the pane needed for the animation
    parameters: root - pane to add items to
    returns: null
    */
    public static void createPane(Pane root){
        Robot encrypt = AES_Controller.getEncrypt();
        root.setPrefSize(1100,600);
        background(root);

        AnimationMethods.placeRobots(encrypt, root, 975, 50);
        encrypt.setImageWidth(150);

        bubble = new Speechbubble("tr", AES_Controller.getStep_2_welcome(),275);
        bubble.getSp().setLayoutX(800);
        bubble.getSp().setLayoutY(125);

        root.getChildren().add(bubble.getSp());
        root.setPadding(new Insets(0,100,0,100));

    }

    /*background, setups the background of the animation
    parameters: p -pane to add backgrounds too
    returns: null
    */
    private static void background(Pane p){
        Rectangle left = new Rectangle(25,25,1150,600);
        left.getStyleClass().add("rectangle-encrypt");


        p.getChildren().add(left);

    }

    /*createTransition, creates the animation, calls other methods to create each bit
    parameters: root- pane to add elements to
    returns: sequential transition with all the animations
    */
    public static SequentialTransition createTransition(Pane root) {
        SequentialTransition st = new SequentialTransition();


        startingPoint(st);
        Box[] rounds =roundStages(st,root);
        paperCreate(st,root);
        Paper[] sheets =changeRounds(st,root,rounds);
        next2Sheets(sheets,root,st);



        return st;

    }

    /*startingPoint, the first method for creating the animation
    parameters: st - sequential transition to add animations to
    returns: null
     */
    private static void startingPoint(SequentialTransition st){
        AnimationMethods.changeBubble(st,bubble,AES_Controller.getStep_2_rounds());
        st.getChildren().add(AnimationMethods.pauseSeconds(10));

    }

    /*roundStages, creates the three round boxes on screen
    parameters: st - sequential transition to add animations to, p - pane to add objects to
    returns: the created round boxes
     */
    private static Box[] roundStages(SequentialTransition st, Pane p){
        int width = AES_Controller.getBOXWIDTH();
        //creates round boxes
        Box[] boxes = new Box[3];
        boxes[0]= new Box("Round : 1");
        boxes[1]= new Box("Rounds: 2-9");
        boxes[2]= new Box("Round : 10");

        //adds boxes to screen
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

    /*paperCreate, creates the stack of paper to represent the message
    parameters: st - sequential transition to add animations to, p - pane to add objects to
    returns: paper object
     */
    private static Paper paperCreate(SequentialTransition st, Pane p){
        Paper stack = new Paper("neither",100,AES_Controller.getStartLocX(),AES_Controller.getStartLocY());
        stack.getView().setOpacity(0);
        p.getChildren().add(stack.getView());

        AnimationMethods.changeBubble(st,bubble,AES_Controller.getStep_2_message());
        st.getChildren().addAll(AnimationMethods.fadeInto(stack.getView()),
                AnimationMethods.pauseSeconds(4));


        return stack;
    }

    /* changeRounds, changes the rounds text and moves the first sheet of paper through rounds
    parameters: st - sequential transition to add animations to, p - pane to add objects to,
                boxes - the array of round boxes
    returns: an array of paper objects
     */
    private static Paper[] changeRounds(SequentialTransition st,Pane p, Box[] boxes){
        AnimationMethods.changeBubble(st,bubble, AES_Controller.getStep_2_first());

        //create paper sheets used later on to represent each block of the message
        Paper[] sheets = new Paper[3];
        sheets[0] = new Paper("single",100,AES_Controller.getStartLocX(),AES_Controller.getStartLocY());
        sheets[1] = new Paper("single",100,AES_Controller.getStartLocX(),AES_Controller.getStartLocY());
        sheets[2] = new Paper("single",100,AES_Controller.getStartLocX(),AES_Controller.getStartLocY());
        Paper encrypt = new Paper("encrypt",100,AES_Controller.getEndLocX(),AES_Controller.getEndLocY());
        p.getChildren().add(encrypt.getView());

        //changes round 1 to XOR round key
        FadeTransition changeRound1 = AnimationMethods.fadeAway(boxes[0].getTextFromSp());
        changeRound1.setOnFinished(event-> boxes[0].changeText("XOR Round Key"));

        FadeTransition displayRound1 =AnimationMethods.fadeInto(boxes[0].getTextFromSp());
        displayRound1.setOnFinished(event->{
            p.getChildren().addAll(sheets[0].getView(),sheets[1].getView(),sheets[2].getView());
            sheets[0].getView().setOpacity(1);
            sheets[1].getView().setOpacity(1);
            sheets[2].getView().setOpacity(1);
        });

        st.getChildren().addAll(changeRound1,displayRound1);

        //moves paper into round 1
        movePaperRound1(sheets[0],st,1);

        //changes round 2 text
        FadeTransition changeRound2 = AnimationMethods.fadeAway(boxes[1].getTextFromSp());
        changeRound2.setOnFinished(event->{
            boxes[1].changeText("Sub Byte/Shift Row/Mix Column/XOR");
            boxes[1].getSp().getChildren().get(1).setStyle("-fx-font-size:25");
        });
        FadeTransition displayRound2 =AnimationMethods.fadeInto(boxes[1].getTextFromSp());
        st.getChildren().addAll(changeRound2,displayRound2);

        //moves sheet of paper through round 2
        movePaperRoundMiddle(sheets[0],st,1);

        //changes final round text
        FadeTransition changeRoundFinal = AnimationMethods.fadeAway(boxes[2].getTextFromSp());
        changeRoundFinal.setOnFinished(event-> boxes[2].changeText("Sub Byte/Shift Row/XOR"));

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

    /*movePaperRound1, moves the sheet of paper through the first round
    parameters: sheet- paper object to move, st - sequential transition to add animations to,
                t - number of paper sheet it is
    returns: null
     */
    private static void movePaperRound1(Paper sheet,SequentialTransition st,int t){
        //move to the text, fade in and out, then leave the round
        TranslateTransition moveSheet = AnimationMethods.moveNode(sheet.getView(), AES_Controller.getHalfway(), 0, 4);
        FadeTransition fadePage = AnimationMethods.fadeAway(sheet.getView());
        FadeTransition appearFade = AnimationMethods.fadeInto(sheet.getView());
        TranslateTransition leaveRound = AnimationMethods.moveNode(sheet.getView(),AES_Controller.getDifference(),0,4);

        st.getChildren().addAll(moveSheet,fadePage,
                AnimationMethods.pauseSeconds(2),
                appearFade,leaveRound);

        //if it is the first sheet of paper, change the bubble text
        if(t==1){AnimationMethods.changeBubble(st, bubble, AES_Controller.getStep_2_middle2());}

    }

    /*movePaperRoundMiddle, moves a sheet of paper through the middle rounds
    parameters: sheet- paper object to move, st - sequential transition to add animations to,
                t - number of paper sheet it is
    returns: null
     */
    private static void movePaperRoundMiddle(Paper sheet,SequentialTransition st, int t){
        //move sheet down from the end of the first round to the start of the middle round
        TranslateTransition moveDown = AnimationMethods.moveNode(sheet.getView(),AES_Controller.getDifference(),100,3);
        TranslateTransition moveBack = AnimationMethods.moveNode(sheet.getView(),0,100,8);
        TranslateTransition moveDownAgain = AnimationMethods.moveNode(sheet.getView(),0,200,3);
        st.getChildren().addAll(moveDown,moveBack,moveDownAgain);

        //make sheet move across round, fading in and out on each 'stage'
        int x=120;
        for(int i=1;i<5;i++){
            st.getChildren().addAll(
                    AnimationMethods.moveNode(sheet.getView(),x*i,200,2),
                    AnimationMethods.fadeAway(sheet.getView()),
                    AnimationMethods.pauseSeconds(2),
                    AnimationMethods.fadeInto(sheet.getView())
            );
            //if it is the first sheet of paper and the second stage, change the bubble
            if(t==1 && i==2){
                AnimationMethods.changeBubble(st,bubble,AES_Controller.getStep_2_endLast());
            }
        }
        st.getChildren().add(AnimationMethods.moveNode(sheet.getView(),AES_Controller.getDifference(),
                200,2));
    }

    /*movePaperFinalRound,moves a sheet of paper through the final round
    parameters: sheet- paper object to move, st - sequential transition to add animations to
    returns: null
     */
    private static void movePaperFinalRound(Paper sheet,SequentialTransition st){
        //moves sheet down from end of middle round to start of final round
        TranslateTransition moveDown = AnimationMethods.moveNode(sheet.getView(),AES_Controller.getDifference(),300,3);
        TranslateTransition moveBack = AnimationMethods.moveNode(sheet.getView(),0,300,8);
        TranslateTransition moveDownAgain = AnimationMethods.moveNode(sheet.getView(),0,
                400,3);
        st.getChildren().addAll(moveDown,moveBack,moveDownAgain);

        //move sheet across, fading in and out on each stage
        int x=150;
        for(int i=1;i<4;i++){
            st.getChildren().addAll(
                    AnimationMethods.moveNode(sheet.getView(),x*i,400,2),
                    AnimationMethods.fadeAway(sheet.getView()),
                    AnimationMethods.pauseSeconds(2),
                    AnimationMethods.fadeInto(sheet.getView())
            );
        }
        st.getChildren().add(AnimationMethods.moveNode(sheet.getView(), AES_Controller.getDifference(),
                400, 2));

    }

    /*next2Sheets, makes the next 2 sheets of paper go through the rounds
    parameters: sheets - sheets of paper to move, p - pane to add objects to,
                st - sequential transition to add animations to
    returns: null
     */
    private static void next2Sheets(Paper[] sheets, Pane p, SequentialTransition st){
        //moves second sheet through the rounds, once finished remove from screen
        AnimationMethods.changeBubble(st,bubble,AES_Controller.getStep_2_encrypt());
        movePaperRound1(sheets[1],st,2);
        movePaperRoundMiddle(sheets[1],st,2);
        movePaperFinalRound(sheets[1],st);
        FadeTransition remove2 = AnimationMethods.fadeAway(sheets[1].getView());
        remove2.setOnFinished(event-> p.getChildren().remove(sheets[1]));
        st.getChildren().add(remove2);

        //move final sheet through the rounds, once finished remove from screen and change bubble
        movePaperRound1(sheets[2],st,3);
        movePaperRoundMiddle(sheets[2],st,3);
        movePaperFinalRound(sheets[2],st);
        FadeTransition remove3 = AnimationMethods.fadeAway(sheets[2].getView());
        remove3.setOnFinished(event-> p.getChildren().remove(sheets[2]));
        st.getChildren().add(remove3);

        AnimationMethods.changeBubble(st,bubble,AES_Controller.getStep_2_next());
    }
}
