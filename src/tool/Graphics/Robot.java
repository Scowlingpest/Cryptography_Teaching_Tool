package tool.Graphics;

import javafx.event.EventHandler;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import tool.Models.Header;

/**
 * Created by Phillipa on 11/10/2015.
 */
public class Robot {

    Image image;
    ImageView view = new ImageView();
    String style;
    int X;
    int Y;
    toolTipSpecial toolTip;
    Header title;

    public Robot(String file,String s,String[] tool,Header h, int x, int y) {

        this.image=new Image(file);
        this.style=s;
        this.toolTip=new toolTipSpecial(tool);
        this.title = h;


        view.setImage(image);
        view.setPreserveRatio(true);
        view.setSmooth(true);
        view.setCache(true);

        this.setImageWidth(175);

        X=x;
        Y=y;




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





}
