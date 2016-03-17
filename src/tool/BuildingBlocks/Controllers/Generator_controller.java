package tool.BuildingBlocks.Controllers;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import tool.CryptoMethods.Views.AnimationMethods;

import java.util.ArrayList;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 02/12/2015.
 */
public class Generator_controller extends BuildingBlockController {

    //variables needed
    private static final String background = "A generator is a special type of modulus root in which the root is a prime number. The modulus root of number x is a number which when raised to the power of 1 up to x-1 and had the modulus taken of x, will result in a unique value of 1 to x-1."+
            " So, for example, 3 is a modulus root of "+getStringMod()+" which is also prime, as such 3 is a generator. This is shown in the example to the side. Change the number and see if it is a generator or not.";
    private static final String generator = "This number is a generator. This is because when you raise it to the power of 1<=x<="+getStringMod()+"-1 you get a result. When you take the modulus "+getStringMod()+" of all these results you end up with one instance of all numbers from 0 to n-1. It is a generator because it is a prime modulus root." +
            " When a number is a generator, we say it is the generator for that number, so 3 is a generator for 17.";
    private static final String notGenerator = "This number is not a generator or a modulus root. This is because when you raise it to the power of 1<=x<="+getStringMod()+"-1, and then do modulus "+getStringMod()+" on the result, you end up with duplicate values which are highlighted in red. This does not mean that the number you picked is not a generator for "+
            "another number (another x) it just means that it is not a generator for this number ("+getStringMod()+").";
    private static final String notPrimeGenerator ="This number is not a generator or a modulus root. This is because when you raise it to the power of 1<=x<="+getStringMod()+"-1, and then do modulus "+getStringMod()+" on the result, you end up with duplicate values which are highlighted in red. The number you picked is also not a generator because "+
            "it is not a prime number. A generator needs to be a prime number.";
    private static final String modulus ="This number is a modulus root but is not a generator. This is because when you raise it to the power of 1<=x<="+getStringMod()+"-1, and then do modulus n on the result, you end up with unique values which are highlighted in green. This means that the number you picked is a modulus root of "+getStringMod()+" but is not "+
            "a generator because a generator has to be a prime number. A prime number remember is a number which can only be divided equally by itself.";
    private static final String tooltip ="This is a simple n^x mod k equation";

    private static final int mod = 17;
    private static final int number = mod-1;
    private static Text[] results = new Text[number];
    private static Double[] equationAnswer = new Double[number];
    private static int input = 3;
    private static int[] used = new int[number];
    private static Text[] equation = new Text[number];

    /*setupEquation
    parameters: vb-to contain equations
    returns: null
    Creates the equations and gets the answers, adds to the provided vbox
     */
    private static void setupEquation(VBox vb){
        for(int i =0;i<16;i++){
            HBox hb = new HBox();
            equationAnswer[i]=Math.pow(input,(i+1));
            results[i] = AnimationMethods.equationSetup(String.format("%.0f", equationAnswer[i] % 17));


            equation[i]=AnimationMethods.equationSetup((Integer.toString(input)+"^"+Integer.toString((i+1))+"="+'\t'+
                    String.format("%.0f", equationAnswer[i])+"(mod 17) = "));

            hb.getChildren().addAll(equation[i],results[i]);
            vb.getChildren().add(hb);
        }
    }


    /*changeColour
    parameters: indexes- the indexes that need changed, color- color to change to,
                st- sequential transition to add animations too
    returns: null
    Goes through the indexes and changes the colour of the result at that index to the provided colour
     */
    private static void changeColour(ArrayList<Integer> indexes, Color color, SequentialTransition st){
        for(int i:indexes) {
            FillTransition ft = new FillTransition(Duration.seconds(1), results[i], (Color) results[i].getFill(), color);
            st.getChildren().add(ft);
        }
    }

    /* playTransition
    parameters: i- number user has selected to test, vb- vbox to contain equations,
                hb- contains buttons to put at bottom of equations
    returns: null
    Creates the animation using the selected value and plays
     */
    public static void playTransition(int i, VBox vb, HBox hb){
        vb.getChildren().clear();
        SequentialTransition st = setupTransition(i,vb);
        st.playFromStart();
        vb.getChildren().add(hb);
    }

    /*setupTransition
    parameters: i- selected value for animation, vb- vbox to contain equations
    returns: the animation for the building block
    Gets the equations, goes through them and makes them appear in order, adds the tooltips and adds the colour changes
     */
    private static SequentialTransition setupTransition(int i,VBox vb) {
        input=i;
        SequentialTransition st = new SequentialTransition();
        setupEquation(vb);
        ArrayList<Integer> changes = new ArrayList<>();
        Color c;

        for (int j=0;j<number;j++){
            c=Color.GREEN;changes.clear();

            ParallelTransition pt = AnimationMethods.createParallel(new Transition[]{
                    AnimationMethods.fadeInto(equation[j],2),AnimationMethods.fadeInto(results[j],2)
            });
            final int finalJ = j;
            pt.setOnFinished(event->{
                Tooltip.install(equation[finalJ],new Tooltip(tooltip));
                Tooltip.install(results[finalJ],new Tooltip(tooltip));
            });
            st.getChildren().add(pt);
            used[j]=Integer.valueOf(results[j].getText());
            for(int n=0;n<j;n++){
                if(used[n]==used[j]){
                    changes.add(n);
                    c=Color.RED;
                }
            }
            changes.add(j);
            changeColour(changes, c, st);


        }


        return st;
    }


    //more getters
    public static String getBackground() {
        return background;
    }

    public static String getGenerator() {
        return generator;
    }

    public static String getNotGenerator() {
        return notGenerator;
    }

    public static String getNotPrimeGenerator() {
        return notPrimeGenerator;
    }

    public static String getModulus() {
        return modulus;
    }

    private static String getStringMod(){
        return Integer.toString(mod);
    }
}
