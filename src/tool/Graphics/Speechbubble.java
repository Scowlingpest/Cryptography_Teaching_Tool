package tool.Graphics;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 31/10/2015.
 */
//Speechbubble graphical object
public class Speechbubble {
    private ImageView bubble;
    private Text speech;
    private StackPane sp = new StackPane();
    private int width;
    private String type;


    /*Speechbubble constructor, makes a speechbubble object
    parameters: type - type of bubble to draw, input - text to put in bubble, width - width of bubble
    returns: null
     */
    public Speechbubble(String type, String input, int width) {
        this.bubble=new ImageView(new Image("Files/Images/speechbubbles/speechbubble_" +type+".png"));

        this.bubble.setFitWidth(width);
        this.bubble.setPreserveRatio(true);
        this.width=width;
        this.type=type;

        this.speech = new Text(input);
        speechSettings();

        sp.getChildren().addAll(this.bubble, this.speech);

    }

    /*Speechbubble constructor, lets you make a constructor at a coordinate
    parameters: type - type of bubble to draw, input - text to put in bubble, width - width of bubble,
                x,y - coordinates
   returns: null
     */
    public Speechbubble (String type, String input, int width, int x, int y){
        this(type,input,width);
        this.getSp().setLayoutX(x);
        this.getSp().setLayoutY(y);

    }

    /*speechSettings, sets the text layout based on the type of bubble
    parameters: null
    returns: null
     */
    private void speechSettings(){
        this.speech.setWrappingWidth(width-45);
        this.speech.setTextAlignment(TextAlignment.JUSTIFY);
        this.speech.getStyleClass().add("text-bubble");

        //if bubble is a top one, move text down, otherwise move text up
        if(type.charAt(0)=='t'){

            StackPane.setMargin(this.speech,new Insets(75,30,15,15));
        }
        else{
            StackPane.setMargin(this.speech, new Insets(15,25,75,15));
        }
    }


    //setter
    public void setSpeech(String speech) {
        this.speech.setText(speech);
        sp.setOpacity(0);
    }

    //getter
    public StackPane getSp() {
        return sp;
    }

}
