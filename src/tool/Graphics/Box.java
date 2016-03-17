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
//Box graphical object class, a box with text in the middle of it
public class Box {

    private Rectangle box;
    private final Text text;
    private final Boolean prime;
    private final StackPane sp;
    private toolTipSpecial tip;

    /*Box constructor, creates a box with the value in the center (prime num version)
    parameters: n - a prime num object
    returns: null
     */
    public Box(Prime_Num n){
        text = new Text(n.getNumber());
        text.getStyleClass().add("box-text");
        prime=n.getPrime();
        sp = new StackPane();
    }

    /*Box constructor, creates a box with the value in the center(string version)
    parameters: s - a string
    returns: null
     */
    public Box(String s){
        text = new Text(s);
        text.getStyleClass().add("box-text");
        prime=false;
        sp = new StackPane();
    }

    /*drawBox, draws the box on screen with three tool tips(mutli tooltip version)
    parameters: tool - array of 3 different tooltips
    returns: null
     */
    public void drawBox(String[] tool){
        drawBox(tool,90,90);

    }

    /*drawBox, draws the box on screen with a single tooltip (one tooltip version)
    parameters: tool - tooltip to add, height - height of box wanted, width - width of box wanted
    returns: null
     */
    public void drawBox(String tool,int height,int width){
        drawBox(new String[]{tool,null,null},height,width);
    }

    /*drawBox, draws the box on screen with 3 tooltips and a specified height/width, (used by all others)
    parameters : tool - array of tooltips to install, height - height of box, width - width of box
    returns: null
     */
    public void drawBox(String[] tool,int height,int width){
        box = new Rectangle(0,0,width,height);
        box.getStyleClass().add("box-base");

        tip = new toolTipSpecial(tool);

        this.text.setBoundsType(TextBoundsType.VISUAL);
        sp.getChildren().addAll(this.box,this.text);
        Tooltip.install(sp,tip.getTooltip());
    }

    /*boxColor, resets the box colour
    parameters: null
    returns: null
     */
    public void boxColor(){
        this.box.getStyleClass().add("box-robot-neither");
    }

    /*setUpForPrime, makes the boxes change to green or red upon click if value is prime/not prime
    parameters: null
    returns: null
     */
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

    //getter
    public StackPane getSp() {
        return sp;
    }

    //setter
    public void changeText(String s){
        this.text.setText(s);
    }

    //getter for box text
    public Text getTextFromSp(){
        return (Text) this.sp.getChildren().get(1);
    }
}
