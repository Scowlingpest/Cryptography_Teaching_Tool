package tool.Graphics;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;


/**
 * Created by Phillipa on 31/10/2015.
 */
public class Speechbubble {
    ImageView bubble;
    Text speech;
    StackPane sp = new StackPane();


    public Speechbubble(String type, String input, int width) {
        this.bubble=new ImageView(new Image("tool/Files/Images/speechbubbles/speechbubble_"+type+".png"));
        this.bubble.setFitWidth(width);


        this.speech = new Text(input);
        this.speech.setWrappingWidth(width-50);

        if(type.charAt(0)=='t'){

            StackPane.setMargin(this.speech,new Insets(90,25,25,25));
        }
        else{
            StackPane.setMargin(this.speech, new Insets(25, 25, 25, 25));
        }

        sp.getChildren().addAll(this.bubble,this.speech);

    }

    public ImageView getBubble() {
        return bubble;
    }

    public void setBubble(ImageView bubble) {
        this.bubble = bubble;
    }

    public Text getSpeech() {
        return speech;
    }

    public void setSpeech(Text speech) {
        this.speech = speech;
    }

    public StackPane getSp() {
        return sp;
    }

    public void setSp(StackPane sp) {
        this.sp = sp;
    }
}
