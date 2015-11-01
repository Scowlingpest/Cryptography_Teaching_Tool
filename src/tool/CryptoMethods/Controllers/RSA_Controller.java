package tool.CryptoMethods.Controllers;

import tool.Models.Header;
import tool.Models.Paragraph;

/**
 * Author : Phillipa Russell
 * Created: 31/10/2015
 */
public class RSA_Controller extends CryptoMethodsController {

    static final String primeP =          "p = 7";
    static final String primeQ =          "q = 3";
    static final String equationN =       "n = p*q = 3 * 7";
    static final String modulusN =        "n = 21";
    static final String totientEquation = "z = (p-1) * (q-1) = (2 * 6)";
    static final String totientZ =        "z = 12";
    static final String primeK =          "k = 11";
    static final String secretJEq =       "k * j = 1(mod z)";
    static final String secretJNo =       "11 * j = 1(mod 12)";
    static final String secretJ =         "j = 11";

    static final String welcome =      "Welcome to RSA! This is an Asymmetric method of encryption. To start off with we need a lot of numbers that I will be calculating. Click play and we'll begin";
    static final String eInfo =        "RSA encryption is a one way system where the user who wants to information calculates everything. 2 keys are generated, a public and secret. Decrypt will keep the secret key and I will receive the public key in the next step.";
    static final String pqExpn =       "Firstly I select two prime numbers p and q. We'll keep them relatively small in this case. ";
    static final String nExp =         "Then I calculate n, an important number which is p and q multiplied together.";
    static final String totientExp =   "Next is the totient, which we're caling z for simplicity, often is it written as φ(n) for reasons we won't go into here.";
    static final String kExp =         "We need another prime number which z cannot be divided by called k, we'll use 11 to keep things small";
    static final String jExp =         "To finish off our calculations, we need j. J is calculated as k*j=1(mod z) so in other words, (k*j)/z gives us something with a remainder of 1. We don't care about the something, just the fact that it gives us the remainder of 1.";
    static final String calculations = "And thats the initial calculations finished! I have to keep all these numbers safe and secret, except for n and k which are the public key. I can now send this to people so they can encrypt messages for me!";
    static final String step1next =    "Click on 2 to see the next step!";

    static final String tooltipP =  "Prime number p used for key calculation";
    static final String tooltipQ =  "Prime number q used for key calculation";
    static final String tooltipNEq= "Equation for n, which is an important part of the key";
    static final String tooltipN=   "Value for n, which is p*q";
    static final String tooltipZEq= "Equation for the totient, which is needed to calculate the key";
    static final String tooltipZ=   "Value for z, which is (p-1) multiplied by (q-1)";
    static final String tooltipK=   "Prime number k which is a number z cannot be divided by";
    static final String tooltipSecretJ = "Equation to calculate j, we want a j such that (k*j)/z gives a remainder of 1";
    static final String tooltipJNo = "The equation for j with letters replaced with numbers";
    static final String tooltipJ =  "The secret number J worked out for these values";

    public static String getPrimeP() {
        return primeP;
    }

    public static String getPrimeQ() {
        return primeQ;
    }

    public static String getEquationN() {
        return equationN;
    }

    public static String getModulusN() {
        return modulusN;
    }

    public static String getTotientEquation() {
        return totientEquation;
    }

    public static String getTotientZ() {
        return totientZ;
    }

    public static String getPrimeK() {
        return primeK;
    }

    public static String getSecretJEq() {
        return secretJEq;
    }

    public static String getSecretJNo() {
        return secretJNo;
    }

    public static String getSecretJ() {
        return secretJ;
    }

    public static String getPqExpn() {
        return pqExpn;
    }

    public static String getnExp() {
        return nExp;
    }

    public static String getTotientExp() {
        return totientExp;
    }

    public static String getkExp() {
        return kExp;
    }

    public static String getjExp() {
        return jExp;
    }

    public static String getWelcome() {
        return welcome;
    }

    public static String geteInfo() {
        return eInfo;
    }

    public static String getCalculations() {
        return calculations;
    }

    public static String getStep1next() {
        return step1next;
    }

    public static String getTooltipP() {
        return tooltipP;
    }

    public static String getTooltipQ() {
        return tooltipQ;
    }

    public static String getTooltipNEq() {
        return tooltipNEq;
    }

    public static String getTooltipN() {
        return tooltipN;
    }

    public static String getTooltipZEq() {
        return tooltipZEq;
    }

    public static String getTooltipZ() {
        return tooltipZ;
    }

    public static String getTooltipK() {
        return tooltipK;
    }

    public static String getTooltipSecretJ() {
        return tooltipSecretJ;
    }

    public static String getTooltipJNo() {
        return tooltipJNo;
    }

    public static String getTooltipJ() {
        return tooltipJ;
    }
}
