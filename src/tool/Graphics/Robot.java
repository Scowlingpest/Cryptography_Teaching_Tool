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
    String style;
    int X;
    int Y;
    toolTipSpecial toolTip;
    Text title;

    public Robot(String name, int x, int y) {
        String robot,tool;
        if(name.equals("encrypt")){
            robot="tool/Files/encrypt.png";
            style="rectangle-encrypt";
            tool="Hello, I'm Encrypt!";
            title = new Text("Encrypt Says:");
        }
        else{
            robot="tool/Files/decrypt.png";
            style="rectangle-decrypt";
            tool="Hello, I'm Decrypt!";
            title = new Text("Decrypt Says:");
        }
        image=new Image(robot);
        view.setImage(image);
        view.setPreserveRatio(true);
        view.setSmooth(true);
        view.setCache(true);

        X=x;
        Y=y;
        toolTip=new toolTipSpecial(tool,"","");
        Tooltip.install(view,toolTip.getTooltip());


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
