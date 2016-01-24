package tool.Graphics;

import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import tool.Models.Prime_Num;


/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 07/10/2015.
 */
public class Box {

    private Rectangle box;
    private Text text;
    private Boolean prime;
    private StackPane sp;
    private toolTipSpecial tip;

    public Box(Prime_Num n){
        text = new Text(n.getNumber());
        text.getStyleClass().add("box-text");
        prime=n.getPrime();
        sp = new StackPane();
    }

    public Box(String s){
        text = new Text(s);
        text.getStyleClass().add("box-text");
        prime=false;
        sp = new StackPane();
    }

    public void drawBox(String[] tool){
        drawBox(tool,90,90);

    }

    public void drawBox(String tool,int height,int width){
        drawBox(new String[]{tool,null,null},height,width);
    }

    public void drawBox(String[] tool,int height,int width){
        box = new Rectangle(0,0,width,height);
        box.getStyleClass().add("box-base");

        tip = new toolTipSpecial(tool);

        this.text.setBoundsType(TextBoundsType.VISUAL);
        sp.getChildren().addAll(this.box,this.text);
        Tooltip.install(sp,tip.getTooltip());
    }

    public void boxColor(String s){
        this.box.getStyleClass().add(s);
    }

    public void setUpForPrime(){
        sp.setOnMouseClicked(event -> {
            if(prime){
                box.getStyleClass().add("box-prime");
                tip.useTextA();

            }
            else{
                box.getStyleClass().add("box-not-prime");
                tip.useTextB();
            }
        });
    }

    public StackPane getSp() {
        return sp;
    }

    public void changeText(String s){
        this.text.setText(s);
    }

    public Text getTextFromSp(){
        return (Text) this.sp.getChildren().get(1);
    }
}
