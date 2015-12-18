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
public class EG_Controller extends CryptoMethodsController {

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
    private static int m = 3;
    private static double A=((Math.pow(q,a))%p); private static double B = (Math.pow(q,b)%p);
    private static double Ka=(Math.pow(B,a)%p); private static double Kb=(Math.pow(A,b)%p);
    private static double c = (Ka*m)%p;
    private static double inv =findInverse();
    private static double d = (inv*c) % p;


    private static final String eqA="(q^a)mod p"; private static final String eqB="(q^b)mod p";
    private static final String eqKa="(B^a)mod p"; private static final String eqKb ="(A^b)mod p";
    private static final String eqC = "c=(K*m)mod p"; private static final String eqD ="(InvK*c)mod p";

    private static String eqANo="("+q+"^"+a+")"+"mod "+p;
    private static String eqBNo="("+q+"^"+b+")"+"mod "+p;

    private static String eqKaNo ="("+B+"^"+a+")"+"mod "+p;
    private static String eqKbNo ="("+A+"^"+b+")"+"mod "+p;

    private static String eqCNo = "("+Ka+"*"+m+")"+"mod "+p;
    private static String eqDNo = "("+inv+"*"+c+")"+"mod "+p;


    private static final String step1welcome = "Welcome to El Gamal, this is based on Diffie Hellman and is very similar. This includes. Click play to start. ";
    private static final String step2welcome = "Welcome to El Gamal. This is a chance for you to try your own numbers. Select the p,q,a and b and click play. P and q should be prime, with q being a generator. The numbers a and b should be smaller than p to make it work, but see what happens when they aren't!";
    private static final String pandQ = "First we have to decide on two prime numbers p and q, with q being a generator of p. It doesn't matter if anyone else knows these so we can discuss them publicly. ";
    private static String prime = "We've decided on p="+getP()+" and q="+getQ()+". Now we both need to do some maths. The third party at the bottom knows about them as well but it doesn't matter. We're each going to pick another number, a and b. These can be any number smaller than p.";
    private static String secretNum = "We've chosen secret a="+geta()+" and secret b ="+getb()+". These can be any number but we have to keep them secret, even from each other. We'll use these with p and q to work out some numbers";
    private static String workingAandB ="We calculate A and B. A="+getEqA()+" and B="+getEqB()+". This turns out as A="+getEqANo()+" and B="+getEqBNo()+" so A="+getA()+" and B="+getB();
    private static String tradeAB = "We now send A and B to each other. We share these publicly so the third party person will get them, but will not know what secret a and secret b are, so they will be unable to do anything with them";
    private static String calculateSecret ="Now we have exchanged A and B we can both figure out the secret key K. We will both work this out using the figure we've got. I'll work out K as "+getEqKa()+" and Decrypt will work K out as "+getEqKb();
    private static String finalKCalc="This means that K="+getEqKaNo()+" and K="+getEqKbNo()+" so K="+getKa()+" = "+getKb();
    private static final String encryption="Now we are going to exchange a message. El Gamal is suited for small messages or key encryption as you have to use Stream cipher with this. So we will demonstrate this with C for Cryptography. So our message, m=3 will now be encrypted.";
    private static String encryptM = "To encrypt our message we simply do k*m mod p. Some people will tell you to calculate the key now, as apposed to earlier, but for simplicity we have both calculated it at the same time. So we get "+ getEqCNo()+" which gives us c="+getC();
    private static final String sendMessage ="Now Encrypt will send her message across. I have the key as well so I need to take the modular inverse of the key which is "+getInv()+" so we then take the inverse and multiply it by the encrypted message to get "+ getEqD();
    private static String decryptMessage="The equation works out as "+ getEqDNo()+" which gives the result of "+getD()+" that should be the same message as we started with!(m=3 for those of you who have forgotten)";
    private static final String finished= "And that's it decrypted! El Gamal has numerous variations but this is the easiest to understand. If we want a new key we just need to choose a new a and b, and work it out from there.";
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
    private static final String cTooltip = "This is the encrypted message, worked out from (K*m) mod p";
    private static final String mTooltip = "This is the message that we will encrypt";
    private static final String eqCTooltip = "This is the equation for C, using the key and the message";
    private static final String dTooltip = "This is the encrypted message decrypted";
    private static final String dEqTooltip ="This is the equation for decrypting the message, it's (inv*c) mod p";
    private static final String invTooltip =" This is the invert modulus of the key";

    public static void setUpAnimation(int p, int q, int a, int b){
        setP(p); setQ (q);
        seta(a); setb(b);
        setA();setB();
        setKa();setKb();
        setC(); setInv();
        setD();

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
        encryptM = "To encrypt our message we simply do k*m mod p. Some people will tell you to calculate the key now, as apposed to earlier, but for simplicity we have both calculated it at the same time. So we get "+ getEqCNo()+" which gives us c="+getC();
        decryptMessage="The decrypt equation works out as "+ getEqDNo()+" which works out as "+getD()+" which should be the same message as we started with!";

    }

    private static double findInverse(){
        double d;

        double c =EG_Controller.getKa();
        double p =EG_Controller.getP();
        for(int i=1;i<p;i++){
            d= (c*i)%p;
            if (d==1){
                return i;
            }
            i++;
        }
        return 0;
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

    public static int getM() {
        return m;
    }

    public static double getC() {
        return c;
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
        EG_Controller.p = p;
    }

    public static void setQ(int q) {
        EG_Controller.q = q;
    }

    public static void seta(int a) {
        EG_Controller.a = a;
    }

    public static void setb(int b) {
        EG_Controller.b = b;
    }

    public static void setA() {
        EG_Controller.A = (Math.pow(getQ(),geta())%getP());
    }

    public static void setB() {
        EG_Controller.B = (Math.pow(getQ(),getb())%getP());
    }

    public static void setKa() {
        EG_Controller.Ka = (Math.pow(getB(),geta()))%getP();
    }

    public static void setKb() {
        EG_Controller.Kb = (Math.pow(getA(),getb()))%getP();
    }

    public static void setEqANo() {
        EG_Controller.eqANo="("+ getQ() +"^"+ geta() +")"+"mod "+ getP();
    }

    public static void setEqBNo() {
        EG_Controller.eqBNo="("+ getQ() +"^"+ getb() +")"+"mod "+ getP();
    }

    public static void setEqKaNo() {
        EG_Controller.eqKaNo = "("+ getB()+"^"+ geta() +")"+"mod "+ getP();
    }

    public static void setEqKbNo() {
        EG_Controller.eqKbNo = "("+ getA()+"^"+ getb() +")"+"mod "+ getP();
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

    public static double getInv() {
        return inv;
    }

    public static String getEqCNo() {
        return eqCNo;
    }

    public static String getEncryption() {
        return encryption;
    }

    public static String getEncryptM() {
        return encryptM;
    }

    public static String getEqC() {
        return eqC;
    }

    public static String getEqD() {
        return eqD;
    }

    public static String getEqDNo() {
        return eqDNo;
    }

    public static String getSendMessage() {
        return sendMessage;
    }

    public static double getD() {
        return d;
    }

    public static String getDecryptMessage() {
        return decryptMessage;
    }

    public static String getcTooltip() {
        return cTooltip;
    }

    public static String getmTooltip() {
        return mTooltip;
    }

    public static String getEqCTooltip() {
        return eqCTooltip;
    }

    public static String getdTooltip() {
        return dTooltip;
    }

    public static String getdEqTooltip() {
        return dEqTooltip;
    }

    public static String getInvTooltip() {
        return invTooltip;
    }

    //Setters for the non-final things


    public static void setA(int a) {
        EG_Controller.a = a;
    }

    public static void setB(int b) {
        EG_Controller.b = b;
    }

    public static void setM(int m) {
        EG_Controller.m = m;
    }

    public static void setA(double a) {
        A = a;
    }

    public static void setB(double b) {
        B = b;
    }

    public static void setC() {
        EG_Controller.c = (getKa()*getM())% getP();
    }

    public static void setInv() {
        EG_Controller.inv = findInverse();
    }

    public static void setD() {
        EG_Controller.d = (getInv()*getC())%getP();
    }


    public static void setEqCNo() {
        EG_Controller.eqCNo = "("+getKa()+"*"+getM()+")"+"mod "+getP();
    }

    public static void setEqMNo() {
        EG_Controller.eqDNo = "("+getInv()+"*"+getM()+")"+"mod "+getP();;
    }

    public static void setPrime(String prime) {
        EG_Controller.prime = prime;
    }

    public static void setSecretNum(String secretNum) {
        EG_Controller.secretNum = secretNum;
    }

    public static void setWorkingAandB(String workingAandB) {
        EG_Controller.workingAandB = workingAandB;
    }

    public static void setTradeAB(String tradeAB) {
        EG_Controller.tradeAB = tradeAB;
    }

    public static void setCalculateSecret(String calculateSecret) {
        EG_Controller.calculateSecret = calculateSecret;
    }

    public static void setFinalKCalc(String finalKCalc) {
        EG_Controller.finalKCalc = finalKCalc;
    }

    public static void setEncryptM(String encryptM) {
        EG_Controller.encryptM = encryptM;
    }

    public static void setDecryptMessage(String decryptMessage) {
        EG_Controller.decryptMessage = decryptMessage;
    }

    private static final String[] stepUsed = new String[]{"Prime Numbers","Generators","Inverted Modulus"};

    public static String[] getStepUsed() {
        return stepUsed;
    }

}
