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

    static final String background = "A generator is a special type of modulus root in which the root is a prime number. The modulus root of number x is a number which when raised to the power of 1 up to x-1 and had the modulus taken of x, will result in a unique value of 1 to x-1."+
            " So, for example, 3 is a modulus root of "+getStringMod()+" which is also prime, as such 3 is a generator. This is shown in the example to the side. Change the number and see if it is a generator or not.";
    static final String generator = "This number is a generator. This is because when you raise it to the power of 1<=x<="+getStringMod()+"-1 you get a result. When you take the modulus "+getStringMod()+" of all these results you end up with one instance of all numbers from 0 to n-1. It is a generator because it is a prime modulus root.+" +
            " When a number is a generator, we say it is the generator for that number, so 3 is a generator for 17.";
    static final String notGenerator = "This number is not a generator or a modulus root. This is because when you raise it to the power of 1<=x<="+getStringMod()+"-1, and then do modulus "+getStringMod()+" on the result, you end up with duplicate values which are highlighted in red. This does not mean that the number you picked is not a generator for "+
            "another number (another x) it just means that it is not a generator for this number ("+getStringMod()+").";
    static final String notPrimeGenerator ="This number is not a generator or a modulus root. This is because when you raise it to the power of 1<=x<="+getStringMod()+"-1, and then do modulus "+getStringMod()+" on the result, you end up with duplicate values which are highlighted in red. The number you picked is also not a generator because "+
            "it is not a prime number. A generator needs to be a prime number, any number can be a modulus root. But a generator is a type of modulus root which is also prime.";
    static final String modulus ="This number is a modulus root but is not a generator. This is because when you raise it to the power of 1<=x<="+getStringMod()+"-1, and then do modulus n on the result, you end up with unique values which are highlighted in green. This means that the number you picked is a modulus root of "+getStringMod()+" but is not "+
            "a generator because a generator has to be a prime number. A prime number remember is a number which can only be divided equally by itself.";
    static final String tooltip ="This is a simple n^x mod k equation";

    static final int mod = 17;
    static final int number = mod-1;
    static Text[] results = new Text[number];
    static Double[] equationAnswer = new Double[number];
    static int input = 3;
    static int[] used = new int[number];
    static Text[] equation = new Text[number];

    public static void setupEquation(VBox vb){
        for(int i =0;i<16;i++){
            HBox hb = new HBox();
            equationAnswer[i]=Math.pow(input,(i+1));
            //used[i]=equationAnswer[i]%11;
            results[i] = AnimationMethods.equationSetup(String.format("%.0f", equationAnswer[i] % 17),0,0,null);


            equation[i]=AnimationMethods.equationSetup((Integer.toString(input)+"^"+Integer.toString((i+1))+"="+'\t'+
                    String.format("%.0f", equationAnswer[i])+"(mod 17) = "),0,0,null);

            hb.getChildren().addAll(equation[i],results[i]);
            vb.getChildren().add(hb);
        }
    }


    public static void changeColour(ArrayList<Integer> indexes,Color color,SequentialTransition st){
        for(int i:indexes) {
            FillTransition ft = new FillTransition(Duration.seconds(1), results[i], (Color) results[i].getFill(), color);
            st.getChildren().add(ft);
        }
    }

    public static void playTransition(int i, VBox vb, HBox hb){
        vb.getChildren().clear();
        SequentialTransition st = setupTransition(i,vb);
        st.playFromStart();
        vb.getChildren().add(hb);
    }

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

    public static String getStringMod(){
        return Integer.toString(mod);
    }
}
