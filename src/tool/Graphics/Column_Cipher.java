package tool.Graphics;

import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import tool.CryptoMethods.Views.AnimationMethods;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 20/12/2015.
 */
//Column cipher animation object
public class Column_Cipher {

    private final VBox[] columns;
    private final int size;
    private final int gap;
    private final Pane p;
    private final TextField encrypted;
    private SequentialTransition animation;

    /*Column cipher constructor, makes the column cipher animation object
    parameters: null
    returns: null
     */
    public Column_Cipher(){
        this.columns = new VBox[3];
        columns[0]=new VBox(); columns[1]=new VBox(); columns[2]= new VBox();
        this.size= 65;
        this.gap=this.size+10;
        String message ="BoaColumnsXX";
        encrypted = new TextField("Columns");
        encrypted.setDisable(true);

        //draws columns using boxes
        for (int i =0;i<12;i++){
            Box temp =new Box(message.substring(i,i+1));
            temp.drawBox("This is one of the columns",this.size,this.size);
            columns[i%3].getChildren().add(temp.getSp());
        }
        //add boxes in columns
        p=new Pane();
        columns[0].setLayoutX(0);columns[0].setLayoutY(40);
        columns[1].setLayoutX(this.gap); columns[1].setLayoutY(40);
        columns[2].setLayoutX(this.gap*2); columns[2].setLayoutY(40);
        encrypted.setLayoutX(20);

        p.getChildren().addAll(encrypted,columns[0],columns[1],columns[2]);
        animateColumn();

    }

    /*animateColumn, animates the column movement
    parameters: null
    return: null
     */
    private void animateColumn(){
        this.animation=new SequentialTransition();
        //move first animation to last, and move other two over
        ParallelTransition pt1 = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.moveNode(columns[1],(-this.gap)    ,0,4),
                AnimationMethods.moveNode(columns[2],(-this.gap)    ,0,4),
                AnimationMethods.moveNode(columns[0],(this.gap*2)   ,0,4)});

        //move second column to end and move other two over
        ParallelTransition pt2 = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.moveNode(columns[2],(-this.gap*2)  ,0,4),
                AnimationMethods.moveNode(columns[0],(this.gap)     ,0,4),
                AnimationMethods.moveNode(columns[1],(this.gap)     ,0,4)
        });

        //restart column locations
        ParallelTransition pt3 = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.moveNode(columns[0],0,0,2),
                AnimationMethods.moveNode(columns[1],0,0,2),
                AnimationMethods.moveNode(columns[2],0,0,2),

        });

        //during animation change displayed text to encrypted, then change back at end
        pt2.setOnFinished(event->
        this.encrypted.setText("lco num xsx"));

        pt3.setOnFinished(event->
        this.encrypted.setText("Columns"));

        this.animation.getChildren().addAll(pt1, pt2,
                AnimationMethods.pauseSeconds(5),pt3);
    }

    //getters
    public SequentialTransition getAnimation() {
        return animation;
    }

    public Pane getP() {
        return p;
    }

}
