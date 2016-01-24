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
import tool.Graphics.Para_Text;
import tool.Models.Paragraph;

import java.util.ArrayList;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 13/12/2015.
 */
public class Inv_Mod_Controller extends BuildingBlockController {
    final static int TextWidth = 300;

    final static Paragraph leftPara =new Paragraph("An inverse modulus is a type of modulus arithmetic. Modulus arithmetic are special laws of maths which only occur when "+
            "the modulus is being taken, for example a+b is different from a+b(mod c). An inverse modulus is written as x^-1. In normal maths this means to raise x to the power of -1 " +
            "but in modulus arithmetic it means to find the inverse. The inverse is the number which when multiplied by x will give 1. In other words (x * x^-1)mod n =1."+
            " The only way to find out what value x^-1 holds, is to do trial and error until we end up with a number which will give 1 when mod  is applied to it. Only x numbers who "+
            "share no prime number factors with n can have a modular inverse.");

    final static Paragraph rightPara = new Paragraph("There are 2 steps for calculating an inverse modular:\n" +
            "Step 1. Calculate A * B mod C for B values 0 through C-1\n" +
            "\n" +
            "Step 2. The modular inverse of A mod C is the B value that makes A * B mod C = 1\n"+
            "\n" +
            "If you find a B where the result is 1 you can stop there as you've found the inverse.\n"+
            "Remember A needs to be a coprime of C, that means that the only number that evenly divides both of them is 1, for example 14 and 15 are coprimes because the only number that can divide them both equally is 1, however 14 and 21 are not coprime because both can be divided by 7.");

    static final String tooltip ="This is a simple n*x mod k equation, if the result is 1 then x is the inverse of n";

    static final int mod = 16;
    static final int number = mod-1;
    static Text[] results = new Text[number];
    static Text[] finished = new Text[number];
    static final Text modString = new Text("(mod "+mod);
    static Double[] equationAnswer = new Double[number];
    static int input = 3;
    static int[] used = new int[number];
    static Text[] equation = new Text[number];
    static boolean found = false;

    public static void setupEquation(VBox vb){
        for(int i =0;i<number;i++){
            HBox hb = new HBox();
            equationAnswer[i]=input*(i+1.0);
            results[i] = AnimationMethods.equationSetup(String.format("%.0f", equationAnswer[i] % mod),0,0,null);


            equation[i]=AnimationMethods.equationSetup((Integer.toString(input)+"*"+Integer.toString((i+1))+'\t'+" ="+
                    String.format("%.0f", equationAnswer[i])+"(mod 16) = "+'\t'),0,0,null);

            hb.getChildren().addAll(equation[i],results[i]);
            vb.getChildren().add(hb);
        }
    }
    private static SequentialTransition setupTransition(int i,VBox vb) {
        input=i;
        SequentialTransition st = new SequentialTransition();
        setupEquation(vb);
        ArrayList<Integer> changes = new ArrayList<>();
        Color c;

        for (int j=0;j<number;j++){
            c=Color.RED;changes.clear();

            ParallelTransition pt = AnimationMethods.createParallel(new Transition[]{
                    AnimationMethods.fadeInto(equation[j], 2), AnimationMethods.fadeInto(results[j], 2)
            });
            final int finalJ = j;
            pt.setOnFinished(event->{
                Tooltip.install(equation[finalJ], new Tooltip(tooltip));
                Tooltip.install(results[finalJ],new Tooltip(tooltip));
            });
            st.getChildren().add(pt);

            if(Integer.valueOf(results[j].getText())==1){
                c=Color.GREEN;
                found=true;
            }

            changeColour(j, c, st);


        }


        return st;
    }

    public static void changeColour(int i,Color color,SequentialTransition st){
        if (color==Color.GREEN){
            FillTransition green = new FillTransition(Duration.seconds(1), equation[i], (Color) equation[i].getFill(), color);
            st.getChildren().add(green);
        }
        FillTransition ft = new FillTransition(Duration.seconds(1), results[i], (Color) results[i].getFill(), color);
        st.getChildren().add(ft);

    }

    public static void playTransition(int i, VBox vb, HBox hb){
        found = false;
        vb.getChildren().clear();
        vb.setSpacing(2);
        SequentialTransition st = setupTransition(i,vb);
        st.playFromStart();
        Paragraph output = new Paragraph("This number has no inverted modulus because it is not a coprime of 16, try picking a coprime next time and see the difference!");
        if(found){
            output=new Paragraph("This number has an inverted modulus because it is a coprime of 16. The green line is the inverted number for the number you picked.");
        }
        Para_Text pt = new Para_Text(output,475);
        vb.getChildren().addAll(hb, pt.getPara());
    }

    public static Paragraph getLeftPara() {
        return leftPara;
    }

    public static Paragraph getRightPara() {
        return rightPara;
    }

    public static int getTextWidth() {
        return TextWidth;
    }

    public static String getTooltip() {
        return tooltip;
    }
}
