package tool.Graphics;

import javafx.event.EventHandler;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import tool.Models.Prime_Num;

import javax.tools.Tool;


/**
 * Created by Phillipa on 07/10/2015.
 */
public class Box {

    private Rectangle box;
    private Text number;
    private Boolean prime;
    private StackPane sp;
    private toolTipSpecial tip;

    public Box(Prime_Num n){
        number = new Text(n.getNumber());
        number.setStyle("-fx-font-size:32");
        prime=n.getPrime();
        sp = new StackPane();
    }

    public void drawBox(int x, int y, String[] tool){
        box = new Rectangle(x,y,90,90);
        box.getStyleClass().add("box-base");
        
        tip = new toolTipSpecial(tool);

        this.number.setBoundsType(TextBoundsType.VISUAL);
        sp.getChildren().addAll(this.box,this.number);
        Tooltip.install(sp,tip.getTooltip());

    }

    protected void changeText(String newText){
        this.number.setText(newText);
    }

    protected void moveBox(int x, int y){
        this.box.setX(x);
        this.box.setY(y);
    }


    public StackPane getSp() {
        return sp;
    }




    public Rectangle getBox() {
        return box;
    }

    public toolTipSpecial getTip() {
        return tip;
    }

    public Boolean getPrime() {
        return prime;
    }

    public void setUpForPrime(){
        sp.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(prime){
                    box.getStyleClass().add("box-prime");
                    tip.useTextA();

                }
                else{
                    box.getStyleClass().add("box-not-prime");
                    tip.useTextB();
                }
            }

        });
    }
}
