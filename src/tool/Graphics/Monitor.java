package tool.Graphics;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by Phillipa on 02/11/2015.
 */
public class Monitor {
    ImageView image = new ImageView();

    public Monitor(int width, int x, int y) {
        this.image.setImage(new Image("tool/Files/Images/monitor.png"));
        this.image.setFitWidth(width);
        this.image.setPreserveRatio(true);
        this.image.setLayoutX(x);
        this.image.setLayoutY(y);
        Tooltip.install(this.getImage(),new Tooltip("I'm a computer"));

    }

    public ImageView getImage() {
        return image;
    }

    public void changeWidth(int w){
        this.image.setFitWidth(w);
    }
}