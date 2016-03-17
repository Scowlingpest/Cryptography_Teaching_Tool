package tool.Graphics;

import javafx.animation.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 12/11/2015.
 */
//Stream_cipher animation object
public class Stream_Cipher {
    private Pane stream = new Pane();
    private Rectangle[] boxes = new Rectangle[9];
    private Text[] numbers = new Text[9];
    private int size;
    private int gap ;
    private SequentialTransition animate= new SequentialTransition();

    /*Stream_cipher constructor, makes the stream cipher animation
    parameters:null
    returns: null
     */
    public Stream_Cipher(){
        int binary = 1;
        this.size= 75;
        this.gap = size+ 20;
        //make the boxes that make up the stream cipher, puts them in a grid
            for (int i = 0; i<9;i++){
                this.boxes[i]=new Rectangle((i%3 * gap),((i<3)? 0:(i<6)? gap:gap*2),
                        size,size);
                this.boxes[i].setStroke(Color.BLACK);
                this.boxes[i].setFill(Color.WHITE);
                this.boxes[i].setStrokeWidth(10);

                int temp = Math.floorDiv(size,2);
                this.numbers[i] = new Text(Integer.toString((binary == 0) ? 1 : 0));
                this.numbers[i].setLayoutX(temp+(i%3*gap));
                this.numbers[i].setLayoutY((i<3)? temp:(i<6)? (gap+temp):(gap*2 + temp) );

                this.stream.getChildren().addAll(boxes[i], numbers[i]);
                binary= (binary==1)? 0:1;
            }
        animateStream();
    }

    /*animateStream, creates the stream animation which makes each block change value and colour
    parameters: null
    returns: sequential transition with stream animation
     */
    private SequentialTransition animateStream(){
        animate = new SequentialTransition();
        Color temp = null;
        for(int j=0;j<2;j++) {
            //if colour is green make it purple, otherwise make it green
            temp=(temp==Color.GREEN)? Color.PURPLE:Color.GREEN;
            for (int i = 0; i < 9; i++) {
                //for each box, change colour and change value to encrypted/decrypted
                StrokeTransition colour = new StrokeTransition(Duration.seconds(2), this.boxes[i], Color.BLACK, temp);
                final int finalI = i;
                colour.setOnFinished(event -> numbers[finalI].setText((numbers[finalI].getText().equals("1")) ? "0" : "1"));

                StrokeTransition black = new StrokeTransition(Duration.seconds(1), this.boxes[i], temp, Color.BLACK);
                PauseTransition pause = new PauseTransition(Duration.seconds(1
                ));
                animate.getChildren().addAll(colour, black, pause);

            }
        }
        return animate;
    }

    //getters
    public SequentialTransition getStreamAnimation() {
        return animate;
    }

    public Pane getStream() {
        return stream;
    }


}
