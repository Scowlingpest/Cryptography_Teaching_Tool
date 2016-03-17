package tool.CryptoMethods.Views.Diffie_Hellman_Scenes;

import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import tool.CryptoMethods.Controllers.DH_Controller;
import tool.CryptoMethods.Views.AnimationMethods;
import tool.Graphics.Speechbubble;
import tool.Models.DataRow;


/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 19/11/2015.
 */
//Diffie Hellman Animation class, setups the diffie hellman animation for both steps
class DH_Animation {
    private static TableView<DataRow> encryptDetails = new TableView<>();
    private static TableView<DataRow> decryptDetails = new TableView<>();

    /*animationCreate, start method which calls all other methods
    parameters: root - pane to add objects to, st - sequential transition to add animations to,
                step - what step it is (1 or 2), bubble - the speechbubble being used
    returns: null
     */
    public static void animationCreate(Pane root, SequentialTransition st, int step, Speechbubble bubble) {
        setupTableViews(root, encryptDetails, 50, DH_Controller.getDataE());
        setupTableViews(root, decryptDetails, 900, DH_Controller.getDataD());
        Text[] pQ = pandQDiscussion(st, bubble, root);
        secretNumberCalc(st, bubble, pQ);
        calculateAB(st, bubble, pQ);
        exchangeAB(st,root, bubble, pQ);
        secretK(st,bubble,pQ);
        finishedStep(st,bubble,step);

    }

    /*setupTableViews, creates the two tables used to contain values
    parameters: p - pane to add objects tp, tb - table to add to, x - x coordinate,
                data - data to put in the table
    returns: null
     */
    private static void setupTableViews(Pane p, TableView<DataRow> tb, int x, ObservableList<DataRow> data) {
        //setups the table appearance
        tb.setFixedCellSize(25);
        tb.getItems().clear();
        tb.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tb.setMaxHeight(100);
        tb.getColumns().clear();

        //setups the table columns
        TableColumn<DataRow, String> name = new TableColumn<>("Item");
        TableColumn<DataRow, String> value = new TableColumn<>("Value");

        tb.getColumns().addAll(name, value);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        value.setCellValueFactory(new PropertyValueFactory<>("value"));
        tb.setItems(data);
        Label temp = new Label("Known Values");

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
        AnimationMethods.changeBubble(st, bubble, DH_Controller.getPandQ());

        //create text objects and evil text objects
        Text p = AnimationMethods.textSetup("P", 975, 250, DH_Controller.getpTooltip());
        Text q = AnimationMethods.textSetup("Q", 200, 250, DH_Controller.getqTooltip());

        Text evilP = AnimationMethods.textSetup("P=" + p.getText(), 560, 250, DH_Controller.getpTooltip());
        Text evilQ = AnimationMethods.textSetup("Q=" + q.getText(), 560, 295, DH_Controller.getqTooltip());
        root.getChildren().addAll(p, q, evilP, evilQ);

        //make p and q appear, move across screen, move back and cange to values
        ParallelTransition textAppear = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeInto(p), AnimationMethods.fadeInto(q)
        });
        ParallelTransition textMove = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.moveNode(p, -775, 0, 5), AnimationMethods.moveNode(q, 775, 0, 5)
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
        ParallelTransition textChange = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeAway(p), AnimationMethods.fadeAway(q)
        });
        //add p and q to tables
        textChange.setOnFinished(event -> {
            p.setText(String.valueOf(DH_Controller.getP()));
            q.setText(String.valueOf(DH_Controller.getQ()));
            DH_Controller.addToTable("P", DH_Controller.getP(), DH_Controller.getDataE());
            DH_Controller.addToTable("Q", DH_Controller.getQ(), DH_Controller.getDataE());
            DH_Controller.addToTable("P", DH_Controller.getP(), DH_Controller.getDataD());
            DH_Controller.addToTable("Q", DH_Controller.getQ(), DH_Controller.getDataD());
        });
        ParallelTransition finished = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeInto(p), AnimationMethods.fadeInto(q)
        });
        finished.setOnFinished(event1 -> {
            evilP.setText("P=" + p.getText());
            evilQ.setText("Q=" + q.getText());
        });

        //move evil text to listener
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
        AnimationMethods.changeBubble(st, bubble, DH_Controller.getPrime());
        ParallelTransition changeText = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeAway(texts[0]), AnimationMethods.fadeAway(texts[1])
        });
        //change text to a and b
        changeText.setOnFinished(event -> {
            texts[0].setText("a");
            Tooltip.install(texts[0], new Tooltip(DH_Controller.getaTooltip()));
            texts[1].setText("b");
            Tooltip.install(texts[1], new Tooltip(DH_Controller.getbTooltip()));
        });
        //make the secret values appear then fade and change to actual values
        ParallelTransition secretAppear = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeInto(texts[0]), AnimationMethods.fadeInto(texts[1])
        });
        ParallelTransition secretChange = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeAway(texts[0]), AnimationMethods.fadeAway(texts[1])
        });
        secretChange.setOnFinished(event -> {
            texts[0].setText(String.valueOf(DH_Controller.geta()));
            texts[1].setText(String.valueOf(DH_Controller.getb()));

            DH_Controller.addToTable("b", DH_Controller.getb(), DH_Controller.getDataE());
            DH_Controller.addToTable("a", DH_Controller.geta(), DH_Controller.getDataD());
        });
        ParallelTransition numbersAppear = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeInto(texts[0]), AnimationMethods.fadeInto(texts[1])
        });

        st.getChildren().addAll(changeText, secretAppear, AnimationMethods.pauseSeconds(5), secretChange, numbersAppear);
        AnimationMethods.changeBubble(st, bubble, DH_Controller.getSecretNum());
        st.getChildren().add(AnimationMethods.pauseSeconds(5));

    }

    /*calculateAB, animation for calculating A and B
    parameters: st -sequential transition to add animations to, bubble - speechbubble to change,
                texts - text objects needed
    returns: null
     */
    private static void calculateAB(SequentialTransition st, Speechbubble bubble, Text[] texts) {
        AnimationMethods.changeBubble(st, bubble, DH_Controller.getWorkingAandB());
        ParallelTransition textChange = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeAway(texts[0]), AnimationMethods.fadeAway(texts[1])
        });
        //display equations
        textChange.setOnFinished(event -> {
            texts[0].setText(DH_Controller.getEqA());
            Tooltip.install(texts[0],new Tooltip(DH_Controller.getEqATooltip()));
            texts[0].setX(-150);

            texts[1].setText(DH_Controller.getEqB());
            Tooltip.install(texts[1],new Tooltip(DH_Controller.getEqBTooltip()));
            texts[1].setX(-30);
        });

        ParallelTransition textReappear = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeInto(texts[0]), AnimationMethods.fadeInto(texts[1])
        });
        ParallelTransition equationChange = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeAway(texts[0]), AnimationMethods.fadeAway(texts[1])
        });
        //results of equations
        equationChange.setOnFinished(event -> {
            texts[0].setText(DH_Controller.getEqANo());
            texts[1].setText(DH_Controller.getEqBNo());
            DH_Controller.addToTable("B", DH_Controller.getB(), DH_Controller.getDataE());
            DH_Controller.addToTable("A", DH_Controller.getA(), DH_Controller.getDataD());
        });
        ParallelTransition equationAppear = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeInto(texts[0]), AnimationMethods.fadeInto(texts[1])
        });
        ParallelTransition changeAB =AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeAway(texts[0]), AnimationMethods.fadeAway(texts[1])
        });
        //show A and B values
        changeAB.setOnFinished(event -> {
            texts[0].setText(String.valueOf(DH_Controller.getA()));
            Tooltip.install(texts[0],new Tooltip(DH_Controller.getAtooltip()));

            texts[1].setText(String.valueOf(DH_Controller.getB()));
            Tooltip.install(texts[1],new Tooltip(DH_Controller.getBtooltip()));

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
        Text evilA = AnimationMethods.textSetup("A=" + String.valueOf(DH_Controller.getA()), 560, 250, DH_Controller.getpTooltip());
        Text evilB = AnimationMethods.textSetup("B=" + String.valueOf(DH_Controller.getB()), 560, 295, DH_Controller.getqTooltip());
        p.getChildren().addAll(evilA,evilB);

        AnimationMethods.changeBubble(st, bubble, DH_Controller.getTradeAB());
        ParallelTransition moveAB = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.moveNode(texts[0], -725, 0, 5), AnimationMethods.moveNode(texts[1], 725, 0, 5)
        });
        ParallelTransition fadeAB = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeAway(texts[0]), AnimationMethods.fadeAway(texts[1])
        });
        fadeAB.setOnFinished(event -> {
            DH_Controller.addToTable("B", DH_Controller.getB(), DH_Controller.getDataD());
            DH_Controller.addToTable("A", DH_Controller.getA(), DH_Controller.getDataE());
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
        AnimationMethods.changeBubble(st,bubble,DH_Controller.getCalculateSecret());


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
            texts[0].setText(DH_Controller.getEqKa());
            Tooltip.install(texts[0], new Tooltip(DH_Controller.getEqKaTooltip()));
            texts[0].setX(-125);

            texts[1].setText(DH_Controller.getEqKb());
            Tooltip.install(texts[1], new Tooltip(DH_Controller.getEqKbTooltip()));
            texts[1].setX(-30);
        });

        ParallelTransition appearKs = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeInto(texts[0]), AnimationMethods.fadeInto(texts[1])
        });
        st.getChildren().addAll(secretChange,appearKs,AnimationMethods.pauseSeconds(4));

        AnimationMethods.changeBubble(st,bubble,DH_Controller.getFinalKCalc());

        //change equation to equation with values
        ParallelTransition eqChange = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeAway(texts[0]), AnimationMethods.fadeAway(texts[1])
        });
        eqChange.setOnFinished(event -> {
            texts[0].setText(DH_Controller.getEqKaNo());
            texts[1].setText(DH_Controller.getEqKbNo());
        });

        ParallelTransition appearEq = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeInto(texts[0]), AnimationMethods.fadeInto(texts[1])
        });

        //change equation with values to result of equation and add to table
        ParallelTransition noChange = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeAway(texts[0]), AnimationMethods.fadeAway(texts[1])
        });
        noChange.setOnFinished(event -> {
            texts[0].setText(String.valueOf(DH_Controller.getKa()));
            Tooltip.install(texts[0],new Tooltip(DH_Controller.getKaTooltip()));
            texts[0].setX(0);

            texts[1].setText(String.valueOf(DH_Controller.getKb()));
            Tooltip.install(texts[1],new Tooltip(DH_Controller.getKbTooltip()));
            texts[1].setX(0);


            DH_Controller.addToTable("K", DH_Controller.getKa(), DH_Controller.getDataD());
            DH_Controller.addToTable("K", DH_Controller.getKb(), DH_Controller.getDataE());
        });

        ParallelTransition appearNo = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeInto(texts[0]), AnimationMethods.fadeInto(texts[1])
        });
        st.getChildren().addAll(eqChange,appearEq,AnimationMethods.pauseSeconds(4),noChange,appearNo);
        AnimationMethods.changeBubble(st,bubble,DH_Controller.getFinished());

    }

    /*finishedStep, changes the bubble to the final bubble based on which step it is
    parameters:st -sequential transition to add animations to, bubble - speechbubble to change,
                step - indicator for which step it is
    returns:null
     */
    private static void finishedStep(SequentialTransition st, Speechbubble bubble, int step){
        st.getChildren().addAll(AnimationMethods.pauseSeconds(5));
        if (step==2){
            AnimationMethods.changeBubble(st,bubble,DH_Controller.getStep2Next());
        }
        else{
            AnimationMethods.changeBubble(st,bubble,DH_Controller.getStep1Next());
        }
    }
}

