package tool.CryptoMethods.Views.AES_Scenes;

import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import tool.CryptoMethods.Controllers.AES_Controller;
import tool.CryptoMethods.Views.AnimationMethods;
import tool.Graphics.Arrow;
import tool.Graphics.Box;
import tool.Graphics.Robot;
import tool.Graphics.Speechbubble;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 17/12/2015.
 */
//AES Step 1 class, basics of AES
public class AES_Step_1 {
    private static Speechbubble bubble;
    private static Robot encrypt ;

    /*createPane,creates the pane needed for the animation
    parameters: root - pane to add items to
    returns: null
    */
    public static void createPane(Pane root){
        encrypt = AES_Controller.getEncrypt();

        //Pane root = new Pane();
        root.setPrefSize(1100,600);
        background(root);

        AnimationMethods.placeRobots(encrypt,root,50,50);
        encrypt.setImageWidth(150);

        bubble = new Speechbubble("tl", AES_Controller.getStep_1_welcome(),275);
        bubble.getSp().setLayoutX(175);
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
        Box[] keys =aesSizes(st,root);
        keys=aesRounds(st,root,keys);
        keySchedule(st,root,keys);
        roundDetail(st,root);



        return st;


    }

    /*startingPoint, the first method for creating the animation
    parameters: st - sequential transition to add animations to
    returns: null
     */
    private static void startingPoint(SequentialTransition st){
        AnimationMethods.changeBubble(st, bubble, AES_Controller.getStep_1_detail());
        st.getChildren().add(AnimationMethods.pauseSeconds(10));
    }

    /*aesSizes, animates the key sizes appearance and moving
    parameters: st - sequential transition to add animations to, p - pane to add objects to
    returns: the box objects which hold the three types of keys
     */
    private static Box[] aesSizes(SequentialTransition st, Pane p){
        AnimationMethods.changeBubble(st,bubble,AES_Controller.getStep_1_sizes());

        //create key boxes
        Box size128 = new Box("128");
        Box size192 = new Box("192");
        Box size256 = new Box("256");

        //add keys to screen
        size128.drawBox(new String[]{"This is one of the key sizes","",""});
        size128.getSp().setLayoutX(500);size128.getSp().setLayoutY(300);
        size128.getSp().setOpacity(0);

        size192.drawBox(new String[]{"This is one of the key sizes","",""});
        size192.getSp().setLayoutX(650);size192.getSp().setLayoutY(300);
        size192.getSp().setOpacity(0);

        size256.drawBox(new String[]{"This is one of the key sizes","",""});
        size256.getSp().setLayoutX(800);size256.getSp().setLayoutY(300);
        size256.getSp().setOpacity(0);

        p.getChildren().addAll(size128.getSp(),size192.getSp(),size256.getSp());

        //makes all three keys appear at same time
        SequentialTransition boxes = AnimationMethods.createSequential(new Transition[]{
                AnimationMethods.fadeInto(size128.getSp()),
                AnimationMethods.fadeInto(size192.getSp()),
                AnimationMethods.fadeInto(size256.getSp())
        });

        //moves keys up in preparation for rounds
        ParallelTransition pt = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.moveNode(size128.getSp(),0,-225,3),
                AnimationMethods.moveNode(size192.getSp(),0,-225,3),
                AnimationMethods.moveNode(size256.getSp(),0,-225,3)
        });
        st.getChildren().addAll(boxes, AnimationMethods.pauseSeconds(4),pt);
        AnimationMethods.changeBubble(st, bubble,AES_Controller.getRounds());

        return new Box[]{size128,size192,size256};

    }

    /*aesRounds,animates the rounds, 1,2,...,final in rows
    parameters:st - sequential transition to add animations to, p - pane to add objects to
                boxes - array of the keys from aesSizes
    returns: key boxes with additional box for the generic key
     */
    private static Box[] aesRounds(SequentialTransition st, Pane p,Box[] boxes){
        //setup the rounds
        Text[] ones = roundAdd(st,p,boxes,220,"1");
        Text[] twos = roundAdd(st,p,boxes,275,"2");
        Text[] firstDot =roundAdd(st,p,boxes,325,".");
        Text[] secondDot =roundAdd(st,p,boxes,375,".");
        Text[] thirdDot =roundAdd(st,p,boxes,425,".");
        Text[] lastRound =finalAdd(st, p, boxes, 475, new String[]{"10","12","14"});

        st.getChildren().add(AnimationMethods.pauseSeconds(3));
        //make rounds fade
        removeRounds(st,p,ones);        removeRounds(st,p,twos);
        removeRounds(st,p,firstDot);    removeRounds(st,p,secondDot);
        removeRounds(st,p,thirdDot);    removeRounds(st,p,lastRound);

        AnimationMethods.changeBubble(st,bubble,AES_Controller.getKeys());

        //add key box to array of keys
        boxes = new Box[]{boxes[0],boxes[1],boxes[2],new Box("Key")};
        boxes[3].drawBox("This is the key we input to the key scheduler",90,90);
        boxes[3].getSp().setLayoutX(200);boxes[3].getSp().setLayoutY(400);
        boxes[3].getSp().setOpacity(0);


        p.getChildren().add(boxes[3].getSp());

        st.getChildren().add(AnimationMethods.fadeInto(boxes[3].getSp()));
        return boxes;

    }

    /*roundAdd,used in aesRounds, used to make the counting rounds appear, calls finalAdd with a string array of s
    parameters:st - sequential transition to add animations to, p - pane to add objects to
                boxes - array of the keys from aesSizes, y - y coordinate for text,
                s - what the text should say
    returns: the created text object
     */
    private static Text[] roundAdd(SequentialTransition st, Pane p, Box[] boxes,int y,String s){
        return finalAdd(st,p,boxes,y,new String[]{s,s,s});
    }

    /*finalAdd,used in aesRounds, used to make the counting rounds appear for each key
    parameters: st - sequential transition to add animations to, p - pane to add objects to
                boxes - array of the keys from aesSizes, y - y coordinate for text,
                s - string array of what the text should say
    returns: the text objects created as an array
     */
    private static Text[] finalAdd(SequentialTransition st, Pane p, Box[] boxes,int y,String[] s){
        //create three text objects at different x coordinates
        Text[] temp = new Text[3];
        temp[0] = AnimationMethods.textSetup(s[0],(int)boxes[0].getSp().getLayoutX()+45,y,"This is one of the rounds");
        temp[1] = AnimationMethods.textSetup(s[1],(int)boxes[1].getSp().getLayoutX()+45,y,"This is one of the rounds");
        temp[2] = AnimationMethods.textSetup(s[2],(int)boxes[2].getSp().getLayoutX()+45,y,"This is one of the rounds");

        p.getChildren().addAll(temp[0],temp[1],temp[2]);

        //amke all three objects appear at once
        ParallelTransition pt1 = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeInto(temp[0]),
                AnimationMethods.fadeInto(temp[1]),
                AnimationMethods.fadeInto(temp[2])
        });

        st.getChildren().add(pt1);
        return temp;

    }

    /*removeRounds,used in aesRounds, removes the round text from the screen and pane
    parameters:st - sequential transition to add animations to, p - pane to remove objects from
                    text - array of text objects to be removed
    returns:null
     */
    private static void removeRounds(SequentialTransition st, Pane p, Text[] text){
        ParallelTransition pt = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeAway(text[0]),
                AnimationMethods.fadeAway(text[1]),
                AnimationMethods.fadeAway(text[2])
        });
        pt.setOnFinished(event-> p.getChildren().removeAll(text[0],text[1],text[2]));
        st.getChildren().add(pt);
    }

    /*keySchedule,makes the key->equation->new key flow chart appear, also makes everything disappear
    parameters: st - sequential transition to add animations to, p - pane to add objects too
                boxes - list of keys to get rid of
    returns:null
     */
    private static void keySchedule(SequentialTransition st,Pane p, Box[] boxes){
        //add middle bit of flow chart
        Box rijndael =new Box("Rijndael key scheduler");
        rijndael.drawBox("This is the key scheduler",90,360);
        rijndael.getSp().setLayoutX(400);rijndael.getSp().setLayoutY(400);
        rijndael.getSp().setOpacity(0); rijndael.boxColor();

        //draw arrow
        Arrow arrowGo= new Arrow(10,45,100,45);
        arrowGo.getC().setLayoutX(300);arrowGo.getC().setLayoutY(400);
        arrowGo.getC().setOpacity(0);

        //make middle bit and arrow appear
        ParallelTransition scheduleAppear = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeInto(rijndael.getSp()),
                AnimationMethods.fadeInto(arrowGo.getC())
        });

        //draw second arrow
        Arrow arrowFrom=new Arrow(10,45,100,45);
        arrowFrom.getC().setLayoutX(760);arrowFrom.getC().setLayoutY(400);
        arrowFrom.getC().setOpacity(0);

        //make last bit of flow chart
        Box result = new Box("New Key");
        result.drawBox(new String[]{"This is the key put through the key scheduler","",""},90,180);
        result.getSp().setLayoutX(880);result.getSp().setLayoutY(400);
        result.getSp().setOpacity(0);

        //make second arrow and last bit appear
        ParallelTransition resultAppear = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeInto(result.getSp()),
                AnimationMethods.fadeInto(arrowFrom.getC())
        });

        //add everthing created to pane and change bubble
        p.getChildren().addAll(rijndael.getSp(), arrowGo.getC(), arrowFrom.getC(), result.getSp());
        st.getChildren().addAll(scheduleAppear);
        AnimationMethods.changeBubble(st,bubble,AES_Controller.getMakingKeys());
        st.getChildren().add(resultAppear);
        numberRounds(st,p);

        //make everything disappear and remove everything from screen
        ParallelTransition fadeEverything = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeAway(boxes[0].getSp()),
                AnimationMethods.fadeAway(boxes[1].getSp()),
                AnimationMethods.fadeAway(boxes[2].getSp()),
                AnimationMethods.fadeAway(boxes[3].getSp()),
                AnimationMethods.fadeAway(arrowFrom.getC()),
                AnimationMethods.fadeAway(arrowGo.getC()),
                AnimationMethods.fadeAway(rijndael.getSp()),
                AnimationMethods.fadeAway(result.getSp())
        });
        fadeEverything.setOnFinished(event-> p.getChildren().removeAll(boxes[0].getSp(),boxes[1].getSp(),boxes[2].getSp(),
                boxes[3].getSp(),rijndael.getSp(),result.getSp(),arrowFrom.getC(),arrowGo.getC()));

        st.getChildren().add(fadeEverything);

    }

    /*numberRounds,used in keySchedule, makes the options appear as part of the equation
    parameters: st - sequential transition to add animations to, p - pane to add objects too
    returns:null
     */
    private static void numberRounds(SequentialTransition st, Pane p){
        //create string needed
        String[] number = new String[]{"128","192","256",""};
        String[] rounds = new String[]{"x 10","x 12","x 14",""};

        Text keySize = AnimationMethods.textSetup("Key Size:",35,425,null);

        st.getChildren().add(AnimationMethods.fadeInto(keySize));

        Text start  = AnimationMethods.textSetup("128",140,455,null);
        Text end    = AnimationMethods.textSetup("x 10",1075,455,null);
        p.getChildren().addAll(keySize,start,end);

        //for each key
        for(int i =0;i<3;i++){
            //make text appear
            ParallelTransition fadeIn = AnimationMethods.createParallel(new Transition[]{
                    AnimationMethods.fadeInto(start),
                    AnimationMethods.fadeInto(end)
            });
            //make text fade
            ParallelTransition fadeAway = AnimationMethods.createParallel(new Transition[]{
                    AnimationMethods.fadeAway(start),
                    AnimationMethods.fadeAway(end)
            });
            //make text equal next key
            final int finalI = i;
            fadeAway.setOnFinished(event->{
                start.setText(number[finalI +1]);end.setText(rounds[finalI +1]);
            });
            st.getChildren().addAll(fadeIn,AnimationMethods.pauseSeconds(3),fadeAway);
        }
        FadeTransition pause = AnimationMethods.fadeAway(keySize);
        pause.setOnFinished(event-> p.getChildren().removeAll(keySize,start,end));
        st.getChildren().add(pause);
    }

    /*roundDetail,makes the round stages appear with tooltips, also makes mix column disappear and the others fill the space
    parameters: st - sequential transition to add animations to, p - pane to add objects too
    returns:null
     */
    private static void roundDetail(SequentialTransition st, Pane p){
        AnimationMethods.changeBubble(st,bubble,AES_Controller.getStages());

        Text heading =AnimationMethods.textSetup("All rounds except the first and the \nlast one have these stages",600,200,null);
        p.getChildren().add(heading);
        st.getChildren().add(AnimationMethods.fadeInto(heading));


        String[] stages ={"Sub\nBytes","Shift\nRows","Mix\nColumns","XOR\nKey"};
        Box[] boxes =new Box[4];
        String[] tooltips ={"Uses a table of values and substitutes the bytes in the message block with a table value",
                            "Each row of the block is shifted to the right, first row by 0, second row by 1, third by 2 etc.",
                            "Performs matrix multiplication on each column in the message, hard to explain and perform",
                            "Each value in the message is XOR with the equivalent value in the key for this round"};
        int x = 475;
        int y = 275;
        //draw stages on screen
        for (int i =0;i<4;i++){
            boxes[i]=new Box(stages[i]);
            boxes[i].drawBox(tooltips[i],150,150);
            boxes[i].getSp().setLayoutX(x+(i*175));boxes[i].getSp().setLayoutY(y);
            boxes[i].getSp().setOpacity(0);
            p.getChildren().add(boxes[i].getSp());
            st.getChildren().addAll(AnimationMethods.fadeInto(boxes[i].getSp()),
                    AnimationMethods.pauseSeconds(2));
        }

        //remove the mix columns stage
        AnimationMethods.changeBubble(st,bubble,AES_Controller.getNextStep1());
        ParallelTransition remove3 = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeAway(boxes[2].getSp()),
                AnimationMethods.fadeAway(heading)
        });

        remove3.setOnFinished(event->{
            heading.setText("Final Round, Mix columns is removed");
            p.getChildren().remove(boxes[2].getSp());
        });

        //make remaining stages bigger to fill gap
        ParallelTransition move =AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeInto(heading),
                AnimationMethods.changeSize(boxes[0].getSp(),1.4,4),
                AnimationMethods.changeSize(boxes[1].getSp(),1.4,4),
                AnimationMethods.changeSize(boxes[3].getSp(),1.4,4),
                AnimationMethods.moveNode(boxes[1].getSp(),63,0,4),
                AnimationMethods.moveNode(boxes[3].getSp(),-50,0,4)
        });

        st.getChildren().addAll(remove3, move);

    }







}
