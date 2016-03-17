package tool.Graphics;

import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import tool.CryptoMethods.Views.AnimationMethods;
import tool.Models.Paragraph;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 20/12/2015.
 */
//Transformation animation object
public class Transformation {

    private Pane p;
    private Rectangle first;
    private Rectangle second;
    private Rectangle solution;
    private SequentialTransition animate;
    private Text info = new Text(0,200,"");
    private Text[] question = new Text[]{new Text(35,60,"?"),new Text(220,60,"?")};

    private Paragraph mix = new Paragraph("We've mixed the two colours so we have a result, an encrypted message. " +
                                  "However numerous colours could be mixed to get the same result,so just "+
                                  "having the result doesn't mean you can figure out what shades were mixed");
    private Paragraph results = new Paragraph("Even if you know the two colours used, it's hard to get the exact shade "+
                                      "that the  result is. For example for our two colours you could get all of "+
                                      " the following shades depending on how much of each you use.");

    private ImageView shades = new ImageView("Files/Images/Colours.png");


    /*Transformation constructor, makes the Transformation object and setups the animation
    parameters: null
    returns: null
     */
    public Transformation(){
        p=new Pane();
        p.setPrefSize(300,200);

        //first colour block setup
        first=new Rectangle(40,40);
        first.setFill(Color.web("#00FF00"));
        first.setLayoutX(0);first.setLayoutY(0);

        //second colour block setup
        second= new Rectangle(40,40);
        second.setFill(Color.web("#FF0000"));
        second.setLayoutX(225);second.setLayoutY(0);

        //final colour block setup
        solution = new Rectangle(40,40);
        solution.setFill(Color.web("#748B00"));
        solution.setOpacity(0);
        solution.setLayoutX(110);solution.setLayoutY(60);

        //question mark placement
        this.question[0].setOpacity(0);
        this.question[1].setOpacity(0);

        //shade image placement
        this.shades.setLayoutX(105);
        this.shades.setFitWidth(100); this.shades.setFitHeight(175);
        this.shades.setOpacity(0);

        p.getChildren().addAll(first, second, solution,info,question[0],question[1],this.shades);
        animateColours();

    }

    /*animateColours, the transformation animation setup
    parameters: null
    returns: null
     */
    private void animateColours(){
        animate=new SequentialTransition();

        //move colours
        ParallelTransition pt1 = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.moveNode(this.first,110,60,5),
                AnimationMethods.moveNode(this.second,-115,60,5)
        });

        //*mix* colours
        ParallelTransition pt2 = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeAway(this.first),
                AnimationMethods.fadeAway(this.second),
                AnimationMethods.fadeInto(this.solution)
        });

        pt2.setOnFinished(event->{
            info.setText(mix.getText());
            info.setWrappingWidth(300);
            this.first.setFill(Color.BLUE);
            this.second.setFill(Color.BEIGE);
        });

        //make the different colours appear
        this.animate.getChildren().addAll(pt1,pt2);
        tryColours(Color.CHARTREUSE,Color.MEDIUMVIOLETRED);
        tryColours(Color.LIGHTSALMON,Color.BISQUE);
        tryColours(Color.DARKGREY,Color.SIENNA);
        tryColours(Color.web("#00FF00"),Color.web("#FF0000"));

        //show the different shades that could happen
        ParallelTransition pt3 = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeAway(this.solution),
                AnimationMethods.fadeInto(this.first),
                AnimationMethods.fadeInto(this.second)
        });
        pt3.setOnFinished(event-> info.setText(results.getText()));

        ParallelTransition pt4 = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.moveNode(this.first,0,0,3),
                AnimationMethods.moveNode(this.second,0,0,3),
                AnimationMethods.fadeInto(this.shades)
        });

        PauseTransition wait = AnimationMethods.pauseSeconds(4);
        wait.setOnFinished(event->{
            this.shades.setOpacity(0);
            this.info.setText("");
        });

        this.animate.getChildren().addAll(pt3,pt4,wait);



    }

    //getters
    public Pane getP() {
        return p;
    }

    public SequentialTransition getAnimation() {
        return animate;
    }


    /*tryColours, animation for moving blocks out, adding question marks and resetting
    parameters: one,two - the colours to change the blocks to
    returns: null
     */
    private void tryColours(Color one, Color two){
        //move blocks to side
        ParallelTransition pt1 = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeInto(this.first),
                AnimationMethods.fadeInto(this.second),
                AnimationMethods.moveNode(this.first,20,60,5),
                AnimationMethods.moveNode(this.second,-25,60,5)

        });
        //show question marks
        pt1.setOnFinished(event->{
            this.question[0].setOpacity(1);
            this.question[1].setOpacity(1);
        });

        //hide question mark and blocks
        ParallelTransition pt2 = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeAway(this.first),
                AnimationMethods.fadeAway(this.second),
                AnimationMethods.fadeAway(this.question[0]),
                AnimationMethods.fadeAway(this.question[1])

        });
        //move blocks to start and change to given colours for next time
        pt2.setOnFinished(event->{
            this.first.setTranslateX(110);
            this.second.setTranslateX(-115);
            this.first.setFill(one);
            this.second.setFill(two);
        });

        this.animate.getChildren().addAll(pt1,pt2);
    }
}
