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
public class Transformation {

    Pane p;
    Rectangle first;
    Rectangle second;
    Rectangle solution;
    SequentialTransition animate;
    Text info = new Text(0,200,"");
    Text[] question = new Text[]{new Text(35,60,"?"),new Text(220,60,"?")};
    Paragraph mix = new Paragraph("We've mixed the two colours so we have a result, an encrypted message. " +
                                  "However numerous colours could be mixed to get the same result,so just "+
                                  "having the result doesn't mean you can figure out what shades were mixed");
    Paragraph results = new Paragraph("Even if you know the two colours used, it's hard to get the exact shade "+
                                      "that the  result is. For example for our two colours you could get all of "+
                                      " the following shades depending on how much of each you use.");
    ImageView shades = new ImageView("Files/Images/Colours.png");

    public Transformation(){
        p=new Pane();
        p.setPrefSize(300,200);

        first=new Rectangle(40,40);
        first.setFill(Color.web("#00FF00"));
        first.setLayoutX(0);first.setLayoutY(0);

        second= new Rectangle(40,40);
        second.setFill(Color.web("#FF0000"));
        second.setLayoutX(225);second.setLayoutY(0);

        solution = new Rectangle(40,40);
        solution.setFill(Color.web("#748B00"));
        solution.setOpacity(0);
        solution.setLayoutX(110);solution.setLayoutY(60);

        this.question[0].setOpacity(0);
        this.question[1].setOpacity(0);

        this.shades.setLayoutX(105);
        this.shades.setFitWidth(100); this.shades.setFitHeight(175);
        this.shades.setOpacity(0);

        p.getChildren().addAll(first, second, solution,info,question[0],question[1],this.shades);
        animateColours();

    }

    public void animateColours(){
        animate=new SequentialTransition();

        ParallelTransition pt1 = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.moveNode(this.first,110,60,5),
                AnimationMethods.moveNode(this.second,-115,60,5)
        });

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

        this.animate.getChildren().addAll(pt1,pt2);
        tryColours(Color.CHARTREUSE,Color.MEDIUMVIOLETRED);
        tryColours(Color.LIGHTSALMON,Color.BISQUE);
        tryColours(Color.DARKGREY,Color.SIENNA);
        tryColours(Color.web("#00FF00"),Color.web("#FF0000"));

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

    public Pane getP() {
        return p;
    }

    public SequentialTransition getAnimation() {
        return animate;
    }

    private void tryColours(Color one, Color two){
        ParallelTransition pt1 = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeInto(this.first),
                AnimationMethods.fadeInto(this.second),
                AnimationMethods.moveNode(this.first,20,60,5),
                AnimationMethods.moveNode(this.second,-25,60,5)

        });
        pt1.setOnFinished(event->{
            this.question[0].setOpacity(1);
            this.question[1].setOpacity(1);
        });

        ParallelTransition pt2 = AnimationMethods.createParallel(new Transition[]{
                AnimationMethods.fadeAway(this.first),
                AnimationMethods.fadeAway(this.second),
                AnimationMethods.fadeAway(this.question[0]),
                AnimationMethods.fadeAway(this.question[1])

        });
        pt2.setOnFinished(event->{
            this.first.setTranslateX(110);
            this.second.setTranslateX(-115);
            this.first.setFill(one);
            this.second.setFill(two);
        });

        this.animate.getChildren().addAll(pt1,pt2);
    }
}
