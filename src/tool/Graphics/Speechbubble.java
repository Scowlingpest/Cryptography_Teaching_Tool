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
public class Speechbubble {
    ImageView bubble;
    Text speech;
    StackPane sp = new StackPane();
    int width;
    String type;


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

    public Speechbubble (String type, String input, int width, int x, int y){
        this(type,input,width);
        this.getSp().setLayoutX(x);
        this.getSp().setLayoutY(y);

    }

    public void speechSettings(){
        this.speech.setWrappingWidth(width-45);
        this.speech.setTextAlignment(TextAlignment.JUSTIFY);
        this.speech.getStyleClass().add("text-bubble");

        if(type.charAt(0)=='t'){

            StackPane.setMargin(this.speech,new Insets(75,30,15,15));
        }
        else{
            StackPane.setMargin(this.speech, new Insets(15,25,75,15));
        }
    }



    public void setSpeech(String speech) {
        this.speech.setText(speech);
        sp.setOpacity(0);
    }

    public StackPane getSp() {
        return sp;
    }

}
