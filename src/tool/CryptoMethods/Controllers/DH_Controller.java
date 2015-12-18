package tool.CryptoMethods.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tool.Models.DataRow;

import java.util.HashMap;


/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 09/11/2015.
 */
public class DH_Controller extends CryptoMethodsController {

    //Step 1 variables needed
    static ObservableList<DataRow> dataE = FXCollections.observableArrayList();
    static ObservableList<DataRow> dataD =FXCollections.observableArrayList();

    static HashMap<Integer,Integer[]> generators = setupMap();

    private static HashMap<Integer,Integer[]> setupMap(){
        HashMap<Integer,Integer[]> primes = new HashMap<>();
        primes.put(7,new Integer[]{3,5});
        primes.put(9,new Integer[]{2,5});
        primes.put(11,new Integer[]{2,7});
        primes.put(13,new Integer[]{2,7,11});
        primes.put(17,new Integer[]{3,5,7,11});
        primes.put(19,new Integer[]{3,13});
        //primes.put(23,new Integer[]{5,7,11,17,19});
        //primes.put(29,new Integer[]{2,3,11,19});
        //primes.put(31,new Integer[]{3,11,13,17});
        //primes.put(37,new Integer[]{2,5,13,17,19});
        return primes;
    }
    public static HashMap<Integer, Integer[]> getGenerators() {
        return generators;
    }

    private static int p =17; private static int q=3;
    private static int a =15; private static int b=13;
    private static double A=((Math.pow(q,a))%p); private static double B = (Math.pow(q,b)%p);
    private static double Ka=(Math.pow(B,a)%p); private static double Kb=(Math.pow(A,b)%p);

    private static final String eqA="(q^a)mod p"; private static final String eqB="(q^b)mod p";
    private static final String eqKa="(B^a)mod p"; private static final String eqKb ="(A^b)mod p";

    private static String eqANo="("+q+"^"+a+")"+"mod "+p;
    private static String eqBNo="("+q+"^"+b+")"+"mod "+p;

    private static String eqKaNo ="("+B+"^"+a+")"+"mod "+p;
    private static String eqKbNo ="("+A+"^"+b+")"+"mod "+p;

    private static final String step1welcome = "Welcome to Diffie Hellman. This is a method of symmetric key exchange. The keys can then be used to encrypt and decrypt messages. Click play to start. ";
    private static final String step2welcome = "Welcome to Diffie Hellman. This is a chance for you to try your own methods. Select the p,q,a and b and click play. P and q should be prime, with q being a generator. The numbers a and b should be smaller than p to make it work, but see what happens when they aren't!";
    private static final String pandQ = "First we have to decide on two prime numbers p and q, with q being a generator of p. It doesn't matter if anyone else knows these so we can discuss them publicly. ";
    private static  String prime = "We've decided on p="+getP()+" and q="+getQ()+". Now we both need to do some maths. The third party at the bottom knows about them as well but it doesn't matter. We're each going to pick another number, a and b. These can be any number smaller than p.";
    private static  String secretNum = "We've chosen secret a="+geta()+" and secret b ="+getb()+". These can be any number but we have to keep them secret, even from each other. We'll use these with p and q to work out some numbers";
    private static  String workingAandB ="We calculate A and B. A="+getEqA()+" and B="+getEqB()+". This turns out as A="+getEqANo()+" and B="+getEqBNo()+" so A="+getA()+" and B="+getB();
    private static  String tradeAB = "We now send A and B to each other. We share these publicly so the third party person will get them, but will not know what secret a and secret b are, so they will be unable to do anything with them";
    private static  String calculateSecret ="Now we have exchanged A and B we can both figure out the secret key K. We will both work this out using the figure we've got. I'll work out K as "+getEqKa()+" and Decrypt will work K out as "+getEqKb();
    private static  String finalKCalc="This means that K="+getEqKaNo()+" and K="+getEqKbNo()+" so K="+getKa()+" = "+getKb();
    private static final String finished= "And that's it! Both of us have the key without ever having told the other what it is. Now we can use the key to encrypt and decrypt information all we like";
    private static final String step1Next ="Want to try it for yourself? Click Step 2, choose your own numbers and see it for yourself!";
    private static final String step2Next ="Step 2 is now finished. Click Step 1 to see the example again or click Step 2 to input your own values again";

    private static final String pTooltip ="This is the publicly known prime number P";
    private static final String qTooltip ="This is the publicly known generator Q";
    private static final String aTooltip ="This is the secret number a";
    private static final String bTooltip ="This is the secret number b";
    private static final String eqATooltip="This is the equation for A";
    private static final String eqBTooltip="This is the equation for B";
    private static final String Atooltip ="This is the public number A calculated from (q^a)mod p";
    private static final String Btooltip ="This is the public number B calculated from (q^b)mod p";
    private static final String eqKaTooltip = "This is the equation for K using B and a";
    private static final String eqKbTooltip = "This is the equation for K using A and b";
    private static final String KaTooltip = "This is K, calculated from (B^a)mod p";
    private static final String KbTooltip = "This is K, calculated from (A^b)mod p";

    public static void setUpAnimation(int p, int q, int a, int b){
        setP(p); setQ (q);
        seta(a); setb(b);
        setA();setB();
        setKa();setKb();

        setEqANo();setEqBNo();
        setEqKaNo(); setEqKbNo();
        stringSetup();
    }

    public static void stringSetup(){
        prime = "We've decided on p="+getP()+" and q="+getQ()+". Now we both need to do some maths. The third party at the bottom knows about them as well but it doesn't matter. We're both going to pick another number, a and b. These can be any number but again we'll keep them small.";
        secretNum = "We've chosen secret a="+geta()+" and secret b ="+getb()+". These can be any number but we have to keep them secret, even from each other. We'll use these with p and q to work out some numbers";
        workingAandB ="We calculate A and B. A="+getEqA()+" and B="+getEqB()+". This turns out as A="+getEqANo()+" and B="+getEqBNo()+" so A="+getA()+"and B="+getB();
        tradeAB = "We now send A and B to each other. We share these publicly so the third party person will get them, but will not know what secret a and secret b are, so they will be unable to do anything with them";
        calculateSecret ="Now we have exchanged A and B we can both figure out the secret key K. We will both work this out using the figure we've got. I'll work out K as "+getEqKa()+" and Decrypt will work K out as "+getEqKb();
        finalKCalc="This means that K="+getEqKaNo()+" and K="+getEqKbNo()+" so K="+getKa()+" = "+getKb();
    }

    //getters for everything above, setters for non-final values

    public static ObservableList<DataRow> getDataE() {
        return dataE;
    }

    public static ObservableList<DataRow> getDataD() {
        return dataD;
    }

    public static int getP() {
        return p;
    }

    public static int getQ() {
        return q;
    }

    public static int geta() {
        return a;
    }

    public static int getb() {
        return b;
    }

    public static double getA() {
        return A;
    }

    public static double getB() {
        return B;
    }

    public static double getKa() {
        return Ka;
    }

    public static double getKb() {
        return Kb;
    }

    public static String getEqA() {
        return eqA;
    }

    public static String getEqB() {
        return eqB;
    }

    public static String getEqKa() {
        return eqKa;
    }

    public static String getEqKb() {
        return eqKb;
    }

    public static String getEqANo() {
        return eqANo;
    }

    public static String getEqBNo() {
        return eqBNo;
    }

    public static String getEqKaNo() {
        return eqKaNo;
    }

    public static String getEqKbNo() {
        return eqKbNo;
    }

    public static void setP(int p) {
        DH_Controller.p = p;
    }

    public static void setQ(int q) {
        DH_Controller.q = q;
    }

    public static void seta(int a) {
        DH_Controller.a = a;
    }

    public static void setb(int b) {
        DH_Controller.b = b;
    }

    public static void setA() {
        DH_Controller.A = (Math.pow(getQ(),geta())%getP());
    }

    public static void setB() {
        DH_Controller.B = (Math.pow(getQ(),getb())%getP());
    }

    public static void setKa() {
        DH_Controller.Ka = (Math.pow(getB(),geta()))%getP();
    }

    public static void setKb() {
        DH_Controller.Kb = (Math.pow(getA(),getb()))%getP();
    }

    public static void setEqANo() {
        DH_Controller.eqANo="("+ getQ() +"^"+ geta() +")"+"mod "+ getP();
    }

    public static void setEqBNo() {
        DH_Controller.eqBNo="("+ getQ() +"^"+ getb() +")"+"mod "+ getP();
    }

    public static void setEqKaNo() {
        DH_Controller.eqKaNo = "("+ getB()+"^"+ geta() +")"+"mod "+ getP();
    }

    public static void setEqKbNo() {
        DH_Controller.eqKbNo = "("+ getA()+"^"+ getb() +")"+"mod "+ getP();
    }

    public static String getPandQ() {
        return pandQ;
    }

    public static String getPrime() {
        return prime;
    }

    public static String getSecretNum() {
        return secretNum;
    }

    public static String getWorkingAandB() {
        return workingAandB;
    }

    public static String getStep1welcome() {
        return step1welcome;
    }

    public static String getStep2welcome() {
        return step2welcome;
    }

    public static String getTradeAB() {
        return tradeAB;
    }

    public static String getCalculateSecret() {
        return calculateSecret;
    }

    public static String getFinalKCalc() {
        return finalKCalc;
    }

    public static String getFinished() {
        return finished;
    }

    public static String getStep1Next() {
        return step1Next;
    }

    public static String getStep2Next() {
        return step2Next;
    }

    public static String getpTooltip() {
        return pTooltip;
    }

    public static String getqTooltip() {
        return qTooltip;
    }

    public static String getaTooltip() {
        return aTooltip;
    }

    public static String getbTooltip() {
        return bTooltip;
    }

    public static String getEqATooltip() {
        return eqATooltip;
    }

    public static String getEqBTooltip() {
        return eqBTooltip;
    }

    public static String getAtooltip() {
        return Atooltip;
    }

    public static String getBtooltip() {
        return Btooltip;
    }

    public static String getEqKaTooltip() {
        return eqKaTooltip;
    }

    public static String getEqKbTooltip() {
        return eqKbTooltip;
    }

    public static String getKaTooltip() {
        return KaTooltip;
    }

    public static String getKbTooltip() {
        return KbTooltip;
    }

    private static final String[] stepUsed = new String[]{"Prime Numbers","Generators"};

    public static String[] getStepUsed() {
        return stepUsed;
    }
}
