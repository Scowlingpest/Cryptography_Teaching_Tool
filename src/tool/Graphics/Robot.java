package tool.Graphics;

import javafx.event.EventHandler;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Created by Phillipa on 11/10/2015.
 */
public class Robot {

    Image image;
    ImageView view = new ImageView();
    Color color;
    int X;
    int Y;
    toolTipSpecial toolTip;
    Text title;

    public Robot(String robot,Color c,int x, int y, String tool, String hello) {
        image=new Image(robot);
        view.setImage(image);
        view.setPreserveRatio(true);
        view.setSmooth(true);
        view.setCache(true);



        color= c;
        X=x;
        Y=y;
        toolTip=new toolTipSpecial(tool,"","");
        Tooltip.install(view,toolTip.getTooltip());
        title= new Text(hello);


    }

    public Text getTitle() {
        return title;
    }

    public void setTitle(Text title) {
        this.title = title;
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

    public Color getColor() {
        return color;
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

    public void setColor(Color color) {
        this.color = color;
    }



}
