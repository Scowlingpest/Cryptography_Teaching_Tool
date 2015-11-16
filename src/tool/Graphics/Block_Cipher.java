package tool.Graphics;

import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.StrokeTransition;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import tool.CryptoMethods.Views.AnimationMethods;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 13/11/2015
 */
public class Block_Cipher {
    Pane block = new Pane();
    Rectangle[] boxes = new Rectangle[16];
    Text[] letters = new Text[16];
    SequentialTransition animation;
    int size;
    int gap ;

    public Block_Cipher(int s){
        this.size=s;
        this.gap = this.size+17;
        String message ="Hello EveryoneXX";
        for (int i =0;i<16;i++){
            this.boxes[i] = new Rectangle((i%4 * gap),((i<4)? 0:(i<8)? gap:(i<12)? gap*2:gap*3),
                    size,size);
            this.boxes[i].setStroke(Color.BLACK);
            this.boxes[i].setFill(Color.WHITE);
            this.boxes[i].setStrokeWidth(7);

            int temp = Math.floorDiv(size,2);
            this.letters[i] = new Text((String.valueOf(message.charAt(i))));
            this.letters[i].setLayoutX(temp+(i%4*gap));
            this.letters[i].setLayoutY((i < 4) ? temp : (i < 8) ? (gap + temp) : (i < 12) ? (gap * 2 + temp) : (gap * 3 + temp));

            this.block.getChildren().addAll(this.boxes[i],this.letters[i]);
        }

        this.animation=new SequentialTransition();
        blockAnimate();
    }

    public Pane getBlock() {
        return block;
    }

    public void blockAnimate(){
        ParallelTransition pt1 = new ParallelTransition();
        this.onSelect(5, Color.GREEN, pt1);

        ParallelTransition pt2 = new ParallelTransition();
        this.onSelect(-5,Color.PURPLE,pt2);

        this.animation.getChildren().addAll(pt1, AnimationMethods.pauseSeconds(5),pt2);

    }

    public void onSelect(int value,Color c, ParallelTransition p){
        for (int i =0;i<16;i++){
            StrokeTransition fill1 = new StrokeTransition(Duration.seconds(3),this.boxes[i],Color.BLACK,c);
            final int finalI = i;
            fill1.setOnFinished(event -> changeText(value,finalI));
            StrokeTransition fill2 = new StrokeTransition(Duration.seconds(3),this.boxes[i],c,Color.BLACK);

            SequentialTransition st = AnimationMethods.createSequential(new Transition[]{fill1, AnimationMethods.pauseSeconds(4),fill2});
            p.getChildren().add(st);
        }
    }

    public void changeText(int value, int i){
        this.block.getChildren().remove(this.letters[i]);
        char temp = this.letters[i].getText().charAt(0);
        int next = (int) temp + value;
        this.letters[i].setText(String.valueOf((char) next));
        this.block.getChildren().add(this.letters[i]);
    }

    public SequentialTransition getAnimation() {
        return animation;
    }
}
