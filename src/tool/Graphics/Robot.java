package tool.Graphics;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import tool.Models.Header;


/** Author : Phillipa Russell
 *  Created: 11/10/2015
 */
//class for creating the two robots that appear on screen
// been written so that additional robots can be added later if needed
public class Robot {

    private Image image;
    private Image image1;
    private Image image2;
    private ImageView view = new ImageView();
    private String style;
    private int X;
    private int Y;
    private toolTipSpecial toolTip;
    private Header title;
    private Timeline tl;

    /*Robot constructor, constructor for the robot
    parameters: file - image file to use, s - style to use, tool - tooltips to install, h - robots header
    returns: null
     */
    public Robot(String file, String s, String[] tool, Header h) {

        //three images needed for the waving animation
        this.image=new Image("Files/Images/robots/" +file+"1.png");
        this.image1=new Image("Files/Images/robots/" +file+"2.png");
        this.image2=new Image("Files/Images/robots/" +file+"3.png");

        //the read in values will either be for encrypt or decrypt
        this.style=s;
        this.toolTip=new toolTipSpecial(tool);
        this.title = h;

        //sets up the image on screen
        this.view.setImage(image);
        this.view.setPreserveRatio(true);
        this.view.setSmooth(true);
        this.view.setCache(true);

        //installs the 'hello' tooltip
        Tooltip.install(this.view,this.toolTip.getTooltip());

        this.setImageWidth(175);

        this.X= 0;
        this.Y= 0;

        //adds the animation to the robot
        animate();



    }

    //getters
    public Header getTitle() {
        return title;
    }

    private Image getImage() {
        return image;

    }
    public ImageView getView() {
        return view;
    }

    public String getStyle() {
        return style;
    }

    public int getX() {
        return X;
    }

    private Timeline getTl() {
        return tl;
    }

    private Image getImage1() {
        return image1;
    }

    private Image getImage2() {
        return image2;
    }

    //setters
    public void setImageWidth(int x){
        this.view.setFitWidth(x);
    }

    public void setX(int x) {
        X = x;
    }

    /*animate, creates the waving animation using a timeline
    parameters:null
    returns:null
     */
    private void animate() {
        //animation created used a timeline since it's a small simple animation
        //four keyframes that change between the images and makes it pause
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

        //creates the timeline and adds the four keyframes, makes it play endlessly
        this.tl = new Timeline();
        this.getTl().getKeyFrames().addAll(kf1,kf2,kf3,kf4);
        this.tl.setCycleCount(Timeline.INDEFINITE);

        this.tl.playFromStart();
    }

}
