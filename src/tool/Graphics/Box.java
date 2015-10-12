package tool.Graphics;

import javafx.event.EventHandler;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

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

    public Box(String n, Boolean b){
        number = new Text(n);
        number.setStyle("-fx-font-size:32");
        prime=b;
        sp = new StackPane();
    }

    public void drawBox(int x, int y, String tool, String a, String b){
        setUpBox(x,y);
        
        tip = new toolTipSpecial(tool,a,b);

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

    public void setUpBox(int x, int y){
        box=new Rectangle(x,y,90,90);
        box.setFill(Color.web("#c7b691"));
        box.setStroke(Color.web("#57464e"));
        box.setStrokeWidth(10);
        box.setArcHeight(20);
        box.setArcWidth(20);
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
                    box.setStroke(Color.GREEN);
                    box.setFill(Color.LIGHTGREEN);
                    tip.useTextA();

                }
                else{
                    box.setStroke(Color.RED);
                    box.setFill(Color.LIGHTPINK);
                    tip.useTextB();
                }
            }

        });
    }
}
