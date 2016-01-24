package tool.BuildingBlocks.Controllers;

import tool.Models.Paragraph;
import tool.Models.Prime_Num;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 13/10/2015.
 */
public class Prime_Numbers_Controller extends BuildingBlockController {

    final static String[] tooltip = {"Think this is a prime number? Click and find out",
            "Well done! This is a prime as \n it can only be divided by itself \n and 1!",
            "Nope! Sorry, this number can be divided\n by something other than itself and 1.\n Try again"};

    final static Paragraph leftPara =new Paragraph("A prime number is a number greater than 1 which can only be divided by itself and 1. " +
            "They are often used in maths and are used in a lot of cryptography methods. "+
            "If a number can be divided by anything other than itself and 1, then it is " +
            "not a prime number. For example 6 is not a prime number because it can be "+
            "divided by 1,2,3, & 6. 7 is a prime number because it can only be divided by "+
            "itself (7) and 1.");

    final static Paragraph rightPara = new Paragraph("Click the numbered boxes and see if you can guess which are prime. "+
            " If the box turns red then you've clicked a number which is not prime. "+
            "If the box turns green then you've clicked a prime number. Hover your mouse"+
            "over the boxes to get a small explanation of why they are/aren't prime.");

    final static int TextWidth = 250;

    public Prime_Numbers_Controller() {
    }

    final static Prime_Num[] primes = setUpPrimes();

    private static Prime_Num[] setUpPrimes(){
        Prime_Num[] temp = new Prime_Num[25];
        for(int k=2;k<27;k++){
            temp[k-2]=new Prime_Num(k);
        }
        return temp;
    }

    public static Prime_Num getPrime(int index){
        return primes[index];
    }

    public static Paragraph getRightPara() {
        return rightPara;
    }

    public static int getTextWidth() {
        return TextWidth;
    }

    public static Paragraph getLeftPara() {
        return leftPara;
    }

    public static String[] getTooltip() {
        return tooltip;
    }

}
