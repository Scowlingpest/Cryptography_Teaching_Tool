package tool.Graphics;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


/**
 * Created by Phillipa on 31/10/2015.
 */
public class Speechbubble {
    ImageView bubble;
    Text speech;
    StackPane sp = new StackPane();
    int width;
    String type;


    public Speechbubble(String type, String input, int width) {
        this.bubble=new ImageView(new Image("tool/Files/Images/speechbubbles/speechbubble_"+type+".png"));

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
        this.speech.setWrappingWidth(width-40);
        this.speech.setTextAlignment(TextAlignment.JUSTIFY);

        if(type.charAt(0)=='t'){

            StackPane.setMargin(this.speech,new Insets(75,25,15,15));
        }
        else{
            StackPane.setMargin(this.speech, new Insets(15,25,75,15));
        }
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

    public void setSpeech(String speech) {
        this.speech = new Text(speech);
        speechSettings();
        sp.getChildren().remove(1);
        sp.getChildren().add(this.speech);
        sp.setOpacity(0);
    }

    public StackPane getSp() {
        return sp;
    }

    public void setSp(StackPane sp) {
        this.sp = sp;
    }
}
