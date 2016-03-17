package tool.CryptoMethods.Views.El_Gamal_Scenes;

import javafx.animation.*;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import tool.CryptoMethods.Controllers.EG_Controller;
import tool.CryptoMethods.Views.AnimationMethods;
import tool.Graphics.Speechbubble;
import tool.Models.DataRow;


/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 19/11/2015.
 */
//El Gamal Animation class, setups the el gamal animation for both steps
class EG_Animation {
    private static TableView<DataRow> encryptDetails = new TableView<>();
    private static TableView<DataRow> decryptDetails = new TableView<>();

    /*animationCreate, start method which calls all other methods
    parameters: root - pane to add objects to, st - sequential transition to add animations to,
                step - what step it is (1 or 2), bubble - the speechbubble being used
    returns: null
     */
    public static void animationCreate(Pane root, SequentialTransition st, int step, Speechbubble bubble) {
        //setups the tables
        setupTableViews(root, decryptDetails, 50, EG_Controller.getDataD());
        setupTableViews(root, encryptDetails, 900, EG_Controller.getDataE());

        //calls the animations methods in order to set up the Sequential Transition
        Text[] pQ = pandQDiscussion(st, bubble, root);
        secretNumberCalc(st, bubble, pQ);
        calculateAB(st, bubble, pQ);
        exchangeAB(st,root, bubble, pQ);
        secretK(st,bubble,pQ);
        encryption(st,bubble,pQ);
        sendCandDecrypt(st,root,bubble,pQ);
        finishedStep(st,bubble,step);

    }

    /*setupTableViews, creates the two tables used to contain values
        parameters: p - pane to add objects tp, tb - table to add to, x - x coordinate,
                    data - data to put in the table
        returns: null
         */
    private static void setupTableViews(Pane p, TableView<DataRow> tb, int x, ObservableList<DataRow> data) {
        //creates the table size, and clears the table
        tb.setFixedCellSize(25);
        tb.getItems().clear();
        tb.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tb.setMaxHeight(100);
        tb.getColumns().clear();

        //sets up the table column headings
        TableColumn<DataRow, String> name = new TableColumn<>("Item");
        TableColumn<DataRow, String> value = new TableColumn<>("Value");
        //noinspection unchecked
        tb.getColumns().addAll(name, value);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        value.setCellValueFactory(new PropertyValueFactory<>("value"));
        tb.setItems(data);
        Label temp = new Label("Known Values");

        //adds table and label to pane
        VBox vb = new VBox();
        vb.setLayoutX(x);
        vb.setLayoutY(375);
        vb.getChildren().addAll(temp, tb);
        p.getChildren().add(vb);
    }

    /*pandQDiscussion, moves p and q back and forth between the two robots, sets value and adds to table
    parameters: st - sequential transition to add animations to, bubble - speechbubble to change,
                root- pane to add objects to
    returns: array of text objects
     */
    private static Text[] pandQDiscussion(SequentialTransition st, Speechbubble bubble, Pane root) {
        //displays bubble explaining p and q
        AnimationMethods.changeBubble(st, bubble, EG_Controller.getPandQ());

        //creates p and q
        Text p = AnimationMethods.textSetup("P", 200, 250, EG_Controller.getpTooltip());
        Text q = AnimationMethods.textSetup("Q", 975, 250, EG_Controller.getqTooltip());

        //creates 'evil' p and q which will be sent to the evil hacker
        Text evilP = AnimationMethods.textSetup("P=" + p.getText(), 560, 250, EG_Controller.getpTooltip());
        Text evilQ = AnimationMethods.textSetup("Q=" + q.getText(), 560, 295, EG_Controller.getqTooltip());
        root.getChildren().addAll(p, q, evilP, evilQ);

        //makes p and q appear, swap round, disappear and then move back to their original person
        ParallelTransition textAppear = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeInto(p), AnimationMethods.fadeInto(q)
        });
        ParallelTransition textMove = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.moveNode(p, +775, 0, 5), AnimationMethods.moveNode(q, -775, 0, 5)
        });
        ParallelTransition textDisappear = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeAway(p), AnimationMethods.fadeAway(q)
        });
        ParallelTransition textReappear = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeInto(p), AnimationMethods.fadeInto(q)
        });
        ParallelTransition textMoveBack = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.moveNode(p, 0, 0, 5), AnimationMethods.moveNode(q, 0, 0, 5)
        });

        //makes p and q disappear, change to their numbers, adds them to the tables and displays them
        ParallelTransition textChange = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeAway(p), AnimationMethods.fadeAway(q)
        });
        textChange.setOnFinished(event -> {
            p.setText(String.valueOf(EG_Controller.getP()));
            q.setText(String.valueOf(EG_Controller.getQ()));
            EG_Controller.addToTable("P", EG_Controller.getP(), EG_Controller.getDataE());
            EG_Controller.addToTable("Q", EG_Controller.getQ(), EG_Controller.getDataE());
            EG_Controller.addToTable("P", EG_Controller.getP(), EG_Controller.getDataD());
            EG_Controller.addToTable("Q", EG_Controller.getQ(), EG_Controller.getDataD());
        });
        ParallelTransition finished = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeInto(p), AnimationMethods.fadeInto(q)
        });
        finished.setOnFinished(event1 -> {
            evilP.setText("P=" + p.getText());
            evilQ.setText("Q=" + q.getText());
        });

        //makes the 'evil' p and q appear and go to the listener
        ParallelTransition evilAppear = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeInto(evilP), AnimationMethods.fadeInto(evilQ)
        });
        ParallelTransition evilMove = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.moveNode(evilP, 0, 200, 5), AnimationMethods.moveNode(evilQ, 0, 200, 5),
        });
        ParallelTransition evilDisappear = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeAway(evilP), AnimationMethods.fadeAway(evilQ)
        });
        evilDisappear.setOnFinished(event -> {
            root.getChildren().remove(evilP);
            root.getChildren().remove(evilQ);

        });

        st.getChildren().addAll(textAppear, textMove, textDisappear, textReappear, textMoveBack, textChange, finished,
                evilAppear, evilMove, evilDisappear);
        return new Text[]{p, q};


    }

    /*secretNumberCalc, animation for picking the secret numbers
    parameters: st -sequential transition to add animations to, bubble - speechbubble to change,
                texts - text objects needed
    returns: null
     */
    private static void secretNumberCalc(SequentialTransition st, Speechbubble bubble, Text[] texts) {
        AnimationMethods.changeBubble(st, bubble, EG_Controller.getPrime());
        ParallelTransition changeText = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeAway(texts[0]), AnimationMethods.fadeAway(texts[1])
        });
        //change text to a and b
        changeText.setOnFinished(event -> {
            texts[0].setText("a");
            Tooltip.install(texts[0], new Tooltip(EG_Controller.getaTooltip()));
            texts[1].setText("b");
            Tooltip.install(texts[1], new Tooltip(EG_Controller.getbTooltip()));
        });
        //make the secret values appear then fade and change to actual values
        ParallelTransition secretAppear = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeInto(texts[0]), AnimationMethods.fadeInto(texts[1])
        });
        ParallelTransition secretChange = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeAway(texts[0]), AnimationMethods.fadeAway(texts[1])
        });
        secretChange.setOnFinished(event -> {
            texts[0].setText(String.valueOf(EG_Controller.geta()));
            texts[1].setText(String.valueOf(EG_Controller.getb()));

            EG_Controller.addToTable("b", EG_Controller.getb(), EG_Controller.getDataE());
            EG_Controller.addToTable("a", EG_Controller.geta(), EG_Controller.getDataD());
        });
        ParallelTransition numbersAppear = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeInto(texts[0]), AnimationMethods.fadeInto(texts[1])
        });

        st.getChildren().addAll(changeText, secretAppear, AnimationMethods.pauseSeconds(5), secretChange, numbersAppear);
        AnimationMethods.changeBubble(st, bubble, EG_Controller.getSecretNum());
        st.getChildren().add(AnimationMethods.pauseSeconds(5));

    }

    /*calculateAB, animation for calculating A and B
    parameters: st -sequential transition to add animations to, bubble - speechbubble to change,
                texts - text objects needed
    returns: null
     */
    private static void calculateAB(SequentialTransition st, Speechbubble bubble, Text[] texts) {
        AnimationMethods.changeBubble(st, bubble, EG_Controller.getWorkingAandB());
        ParallelTransition textChange = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeAway(texts[0]), AnimationMethods.fadeAway(texts[1])
        });
        //display equations
        textChange.setOnFinished(event -> {
            texts[0].setText(EG_Controller.getEqA());
            Tooltip.install(texts[0],new Tooltip(EG_Controller.getEqATooltip()));
            texts[0].setX(-30);

            texts[1].setText(EG_Controller.getEqB());
            Tooltip.install(texts[1],new Tooltip(EG_Controller.getEqBTooltip()));
            texts[1].setX(-150);
        });

        ParallelTransition textReappear = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeInto(texts[0]), AnimationMethods.fadeInto(texts[1])
        });
        ParallelTransition equationChange = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeAway(texts[0]), AnimationMethods.fadeAway(texts[1])
        });
        //results of equations
        equationChange.setOnFinished(event -> {
            texts[0].setText(EG_Controller.getEqANo());
            texts[1].setText(EG_Controller.getEqBNo());
            EG_Controller.addToTable("B", EG_Controller.getB(), EG_Controller.getDataE());
            EG_Controller.addToTable("A", EG_Controller.getA(), EG_Controller.getDataD());
        });
        ParallelTransition equationAppear = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeInto(texts[0]), AnimationMethods.fadeInto(texts[1])
        });
        ParallelTransition changeAB =AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeAway(texts[0]), AnimationMethods.fadeAway(texts[1])
        });
        //show A and B values
        changeAB.setOnFinished(event -> {
            texts[0].setText(String.valueOf(EG_Controller.getA()));
            Tooltip.install(texts[0],new Tooltip(EG_Controller.getAtooltip()));

            texts[1].setText(String.valueOf(EG_Controller.getB()));
            Tooltip.install(texts[1],new Tooltip(EG_Controller.getBtooltip()));

            texts[0].setX(25);texts[1].setX(30);
                });

        ParallelTransition ABReappear = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeInto(texts[0]), AnimationMethods.fadeInto(texts[1])
        });
        st.getChildren().addAll(textChange, textReappear, AnimationMethods.pauseSeconds(5),
                equationChange, equationAppear,AnimationMethods.pauseSeconds(4),changeAB,ABReappear);
    }

    /*exchangeAB, animation for exchanging A and B
    parameters: st -sequential transition to add animations to, p - pane to add objects to
                bubble - speechbubble to change, texts - text objects needed
    returns: null
     */
    private static void exchangeAB(SequentialTransition st,Pane p, Speechbubble bubble, Text[] texts) {
        Text evilA = AnimationMethods.textSetup("A=" + String.valueOf(EG_Controller.getA()), 560, 250, EG_Controller.getAtooltip());
        Text evilB = AnimationMethods.textSetup("B=" + String.valueOf(EG_Controller.getB()), 560, 295, EG_Controller.getBtooltip());
        p.getChildren().addAll(evilA,evilB);

        AnimationMethods.changeBubble(st, bubble, EG_Controller.getTradeAB());
        ParallelTransition moveAB = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.moveNode(texts[0], 725, 0, 5), AnimationMethods.moveNode(texts[1], -725, 0, 5)
        });
        ParallelTransition fadeAB = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeAway(texts[0]), AnimationMethods.fadeAway(texts[1])
        });

        fadeAB.setOnFinished(event -> {
            EG_Controller.addToTable("B", EG_Controller.getB(), EG_Controller.getDataD());
            EG_Controller.addToTable("A", EG_Controller.getA(), EG_Controller.getDataE());
        });

        ParallelTransition appearAB = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeInto(texts[0]), AnimationMethods.fadeInto(texts[1])
        });
        ParallelTransition moveBack = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.moveNode(texts[0], 0, 0, 5), AnimationMethods.moveNode(texts[1], 0, 0, 5)
        });

        ParallelTransition evilAppear = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeInto(evilA), AnimationMethods.fadeInto(evilB)});

        ParallelTransition evilMove = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.moveNode(evilA, 0, 200, 5), AnimationMethods.moveNode(evilB, 0, 200, 5),
        });
        ParallelTransition evilDisappear = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeAway(evilA), AnimationMethods.fadeAway(evilB)
        });
        evilDisappear.setOnFinished(event -> {
            p.getChildren().remove(evilA);
            p.getChildren().remove(evilB);

        });

        st.getChildren().addAll(moveAB,fadeAB,appearAB,moveBack,evilAppear,evilMove,evilDisappear);
        AnimationMethods.changeBubble(st,bubble,EG_Controller.getCalculateSecret());


    }

    /*secretK, animation for calculating the key on both sides
   parameters:st-sequential transition to add animations to, bubble - speechbubble to change,
               texts - text objects needed
   returns: null
    */
    private static void secretK(SequentialTransition st,Speechbubble bubble,Text[] texts){
        ParallelTransition secretChange = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeAway(texts[0]), AnimationMethods.fadeAway(texts[1])
        });
        //change displayed text to the equations for the key
        secretChange.setOnFinished(event -> {
            texts[0].setText(EG_Controller.getEqKa());
            Tooltip.install(texts[0], new Tooltip(EG_Controller.getEqKaTooltip()));
            texts[0].setX(-30);

            texts[1].setText(EG_Controller.getEqKb());
            Tooltip.install(texts[1], new Tooltip(EG_Controller.getEqKbTooltip()));
            texts[1].setX(-125);
        });

        ParallelTransition appearKs = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeInto(texts[0]), AnimationMethods.fadeInto(texts[1])
        });
        st.getChildren().addAll(secretChange,appearKs,AnimationMethods.pauseSeconds(4));

        AnimationMethods.changeBubble(st,bubble,EG_Controller.getFinalKCalc());

        //change equation to equation with values
        ParallelTransition eqChange = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeAway(texts[0]), AnimationMethods.fadeAway(texts[1])
        });
        eqChange.setOnFinished(event -> {
            texts[0].setText(EG_Controller.getEqKaNo());
            texts[1].setText(EG_Controller.getEqKbNo());
        });

        ParallelTransition appearEq = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeInto(texts[0]), AnimationMethods.fadeInto(texts[1])
        });

        //change equation with values to result of equation and add to table
        ParallelTransition noChange = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeAway(texts[0]), AnimationMethods.fadeAway(texts[1])
        });
        noChange.setOnFinished(event -> {
            texts[0].setText(String.valueOf(EG_Controller.getKa()));
            Tooltip.install(texts[0],new Tooltip(EG_Controller.getKaTooltip()));
            texts[0].setX(0);

            texts[1].setText(String.valueOf(EG_Controller.getKb()));
            Tooltip.install(texts[1],new Tooltip(EG_Controller.getKbTooltip()));
            texts[1].setX(0);


            EG_Controller.addToTable("K", EG_Controller.getKa(), EG_Controller.getDataE());
            EG_Controller.addToTable("K", EG_Controller.getKb(), EG_Controller.getDataD());
        });

        ParallelTransition appearNo = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeInto(texts[0]), AnimationMethods.fadeInto(texts[1])
        });
        st.getChildren().addAll(eqChange,appearEq,AnimationMethods.pauseSeconds(4),noChange,appearNo);

    }

    /*encryption, shows how enceyption happens using el Gamal
    parameters:st -sequential transition to add animations to, bubble - speechbubble to change,
                texts - text objects to change
    returns: null
     */
    private static void encryption(SequentialTransition st, Speechbubble bubble, Text[] texts){
        AnimationMethods.changeBubble(st,bubble,EG_Controller.getEncryption());
        ParallelTransition removeKs = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeAway(texts[0]), AnimationMethods.fadeAway(texts[1])
        });
        //add message to screen (m=3)
        removeKs.setOnFinished(event->{
            texts[1].setText("m=3");
            EG_Controller.addToTable("M", EG_Controller.getM(),EG_Controller.getDataE());
            Tooltip.install(texts[1],new Tooltip(EG_Controller.getmTooltip()));
            Tooltip.install(texts[0],null);

        });

        FadeTransition mAppear=AnimationMethods.fadeInto(texts[1]);

        st.getChildren().addAll(removeKs,mAppear,AnimationMethods.pauseSeconds(3));
        AnimationMethods.changeBubble(st,bubble, EG_Controller.getEncryptM());

        //change to the encryption equation
        FadeTransition changeM =AnimationMethods.fadeAway(texts[1]);
        changeM.setOnFinished(event->{
            texts[1].setText(EG_Controller.getEqC());
            Tooltip.install(texts[1],new Tooltip(EG_Controller.getEqCTooltip()));
            texts[1].setX(-120);
        });
        FadeTransition cEqAppear = AnimationMethods.fadeInto(texts[1]);
        FadeTransition cEqDisappear = AnimationMethods.fadeAway(texts[1]);
        cEqDisappear.setOnFinished(event-> texts[1].setText(EG_Controller.getEqCNo()));

        //change to numbered equation
        FadeTransition numEqAppear = AnimationMethods.fadeInto(texts[1]);
        FadeTransition numEqDisappear = AnimationMethods.fadeAway(texts[1]);
        numEqDisappear.setOnFinished(event->{
            texts[1].setText("C = "+EG_Controller.getC());
            Tooltip.install(texts[1],new Tooltip(EG_Controller.getcTooltip()));
            EG_Controller.addToTable("C", EG_Controller.getC(), EG_Controller.getDataE());
            texts[1].setX(-25);

        });

        FadeTransition cAppear = AnimationMethods.fadeInto(texts[1]);

        st.getChildren().addAll(changeM,cEqAppear,AnimationMethods.pauseSeconds(3),
                cEqDisappear,numEqAppear, AnimationMethods.pauseSeconds(3),numEqDisappear,cAppear);
    }

    /*sendCandDecrypt, send the ciphertext and decrypt it
    parameters: st - sequential transition to add animations to, p - pane to add objects to,
                bubble - speechbubble to change, texts - text objects to change
    returns: null
     */
    private static void sendCandDecrypt(SequentialTransition st,Pane p, Speechbubble bubble, Text[] texts){
        Text evilC = AnimationMethods.textSetup("C=" + String.valueOf(EG_Controller.getC()), 560, 250, EG_Controller.getcTooltip());
        p.getChildren().add(evilC);

        //change bubble text to start sending of C
        AnimationMethods.changeBubble(st,bubble,EG_Controller.getSendMessage());

        //send C across to the other person
        TranslateTransition sendC = AnimationMethods.moveNode(texts[1],-725,0,5);
        sendC.setOnFinished(event-> EG_Controller.addToTable("C", EG_Controller.getC(), EG_Controller.getDataD()));
        FadeTransition fadeC = AnimationMethods.fadeAway(texts[1]);
        fadeC.setOnFinished(event -> {
            texts[1].setText("Inv ="+EG_Controller.getInv());
            Tooltip.install(texts[1],new Tooltip(EG_Controller.getInvTooltip()));
            EG_Controller.addToTable("Inv",EG_Controller.getInv(),EG_Controller.getDataD());

        });

        //make the 'evil' c appear and go onto the evil persons monitor
        FadeTransition evilAppear = AnimationMethods.fadeInto(evilC);
        TranslateTransition evilMove = AnimationMethods.moveNode(evilC, 0, 200, 5);
        FadeTransition evilDisappear = AnimationMethods.fadeAway(evilC);
        evilDisappear.setOnFinished(event -> p.getChildren().remove(evilC));

        //display inv and make it disappear
        FadeTransition invAppear = AnimationMethods.fadeInto(texts[1]);
        FadeTransition invFade   = AnimationMethods.fadeAway(texts[1]);

        st.getChildren().addAll(sendC,fadeC,evilAppear,evilMove,evilDisappear,invAppear,AnimationMethods.pauseSeconds(3),invFade);

        //change bubble to start decryption bit
        AnimationMethods.changeBubble(st,bubble,EG_Controller.getDecryptMessage());
        invFade.setOnFinished(event-> texts[1].setText(EG_Controller.getEqD()));

        //make the equation of D appear
        FadeTransition eqAppear = AnimationMethods.fadeInto(texts[1]);
        FadeTransition eqFade   = AnimationMethods.fadeAway(texts[1]);
        eqFade.setOnFinished(event-> texts[1].setText(EG_Controller.getEqDNo()));

        //make Ds numbered equation appear
        FadeTransition numEqAppear = AnimationMethods.fadeInto(texts[1]);
        FadeTransition numEqFade   = AnimationMethods.fadeAway(texts[1]);
        numEqFade.setOnFinished(event->{
            texts[1].setText("M ="+EG_Controller.getD());
            Tooltip.install(texts[1],new Tooltip(EG_Controller.getdTooltip()));
            EG_Controller.addToTable("M",EG_Controller.getD(),EG_Controller.getDataD());
        });

        //make the decrypted message appear and tell people it's finished
        FadeTransition finalAppear = AnimationMethods.fadeInto(texts[1]);
        st.getChildren().addAll(eqAppear,AnimationMethods.pauseSeconds(3),eqFade,
                numEqAppear,AnimationMethods.pauseSeconds(3),numEqFade,finalAppear);
        AnimationMethods.changeBubble(st,bubble,EG_Controller.getFinished());
    }

    /*finishedStep, changes the bubble to the final bubble based on which step it is
    parameters:st -sequential transition to add animations to, bubble - speechbubble to change,
                step - indicator for which step it is
    returns:null
    */
    private static void finishedStep(SequentialTransition st, Speechbubble bubble, int step){
        st.getChildren().addAll(AnimationMethods.pauseSeconds(5));
        if (step==2){
            AnimationMethods.changeBubble(st,bubble,EG_Controller.getStep2Next());
        }
        else{
            AnimationMethods.changeBubble(st,bubble,EG_Controller.getStep1Next());
        }
    }
}

