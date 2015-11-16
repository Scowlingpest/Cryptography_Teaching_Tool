package tool.Graphics;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import tool.Models.Header;

import java.sql.Time;


/** Author : Phillipa Russell
 *  Created: 11/10/2015
 */

public class Robot {

    Image image;
    Image image1;
    Image image2;
    ImageView view = new ImageView();
    String style;
    int X;
    int Y;
    toolTipSpecial toolTip;
    Header title;
    Timeline tl;

    public Robot(String file,String s,String[] tool,Header h, int x, int y) {

        this.image=new Image(file+"1.png");
        this.image1=new Image(file+"2.png");
        this.image2=new Image(file+"3.png");

        this.style=s;
        this.toolTip=new toolTipSpecial(tool);
        this.title = h;


        this.view.setImage(image);
        this.view.setPreserveRatio(true);
        this.view.setSmooth(true);
        this.view.setCache(true);

        Tooltip.install(this.view,this.toolTip.getTooltip());

        this.setImageWidth(175);

        this.X=x;
        this.Y=y;

        animate();



    }

    public toolTipSpecial getToolTip() {
        return toolTip;
    }

    public Header getTitle() {
        return title;
    }

    public Image getImage() {
        return image;

    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setImageWidth(int x){
        this.view.setFitWidth(x);
    }
    public ImageView getView() {
        return view;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public void animate() {

        KeyFrame kf1 = new KeyFrame(Duration.millis(400), event -> {
            getView().setImage(getImage1());

        }
        );
        KeyFrame kf2 = new KeyFrame(Duration.millis(800), event -> {
            getView().setImage(getImage2());
        }
        );
        KeyFrame kf3 = new KeyFrame(Duration.millis(1200), event -> {
            getView().setImage(getImage1());
        }
        );

        KeyFrame kf4 = new KeyFrame(Duration.millis(1600), event -> {
            getView().setImage(getImage());
        }
        );

        this.tl = new Timeline();
        this.getTl().getKeyFrames().addAll(kf1,kf2,kf3,kf4);
        this.tl.setCycleCount(Timeline.INDEFINITE);

        this.tl.playFromStart();
    }

    public Timeline getTl() {
        return tl;
    }

    public Image getImage1() {
        return image1;
    }

    public Image getImage2() {
        return image2;
    }
}
