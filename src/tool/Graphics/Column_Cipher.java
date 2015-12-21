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
public class Column_Cipher {

    VBox[] columns;
    int size;
    int gap;
    Pane p;
    TextField encrypted;
    SequentialTransition animation;

    public Column_Cipher(int s){
        this.columns = new VBox[3];
        columns[0]=new VBox(); columns[1]=new VBox(); columns[2]= new VBox();
        this.size=s;
        this.gap=this.size+10;
        String message ="ColumnCipher";
        encrypted = new TextField("ColumnCipher");
        encrypted.setDisable(true);

        for (int i =0;i<12;i++){
            Box temp =new Box(message.substring(i,i+1));
            temp.drawBox("This is one of the columns",this.size,this.size);
            columns[i%3].getChildren().add(temp.getSp());
        }
        p=new Pane();
        columns[0].setLayoutX(0);columns[0].setLayoutY(40);
        columns[1].setLayoutX(this.gap); columns[1].setLayoutY(40);
        columns[2].setLayoutX(this.gap*2); columns[2].setLayoutY(40);
        encrypted.setLayoutX(20);

        p.getChildren().addAll(encrypted,columns[0],columns[1],columns[2]);
        animateColumn();

    }

    public void animateColumn(){
        this.animation=new SequentialTransition();
        ParallelTransition pt1 = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.moveNode(columns[1],(-this.gap)    ,0,4),
                AnimationMethods.moveNode(columns[2],(-this.gap)    ,0,4),
                AnimationMethods.moveNode(columns[0],(this.gap*2)   ,0,4)});

        ParallelTransition pt2 = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.moveNode(columns[2],(-this.gap*2)  ,0,4),
                AnimationMethods.moveNode(columns[0],(this.gap)     ,0,4),
                AnimationMethods.moveNode(columns[1],(this.gap)     ,0,4)
        });

        ParallelTransition pt3 = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.moveNode(columns[0],0,0,2),
                AnimationMethods.moveNode(columns[1],0,0,2),
                AnimationMethods.moveNode(columns[2],0,0,2),

        });
        pt2.setOnFinished(event->
        this.encrypted.setText("lconumpcirhe"));

        pt3.setOnFinished(event->
        this.encrypted.setText("ColumnCipher"));

        this.animation.getChildren().addAll(pt1, pt2,
                AnimationMethods.pauseSeconds(5),pt3);
    }

    public SequentialTransition getAnimation() {
        return animation;
    }

    public Pane getP() {
        return p;
    }

}
