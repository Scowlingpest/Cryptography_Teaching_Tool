package tool.CryptoMethods.Controllers;

/**
 * Author : Phillipa Russell
 * Created: 31/10/2015
 */
//RSA controller class
public class RSA_Controller extends CryptoMethodsController {

    //step 1 strings
    private static final String primeP =          "p = 11";
    private static final String primeQ =          "q = 3";
    private static final String equationN =       "n = p*q = 3 * 11";
    private static final String modulusN =        "n = 33";
    private static final String totientEquation = "z = (p-1) * (q-1) = (2 * 10)";
    private static final String totientZ =        "z = 20";
    private static final String primeK =          "k = 7";
    private static final String secretJEq =       "k * j = 1(mod z)";
    private static final String secretJNo =       "7 * j = 1(mod 20)";
    private static final String secretJ =         "j = 3";

    private static final String welcome =      "Welcome to RSA! This is an Asymmetric method of encryption. To start off with we need a lot of numbers that I will be calculating. Click play and we'll begin";
    private static final String eInfo =        "RSA encryption uses 2 keys, a public key and a secret key. Decrypt will keep the secret key and I will receive the public key in the next step.";
    private static final String pqExpn =       "Firstly I select two prime numbers p and q. We'll keep them relatively small in this case but in real life the numbers should all be large. ";
    private static final String nExp =         "Then I calculate n, an important number which is p and q multiplied together.";
    private static final String totientExp =   "Next is the totient, which we're calling z for simplicity, often is it written as φ(n) for reasons we won't go into here.";
    private static final String kExp =         "We need another prime number which z cannot be divided by called k, we'll use 7 to keep things small";
    private static final String jExp =         "To finish off our calculations, we need j ,the inverse modular of k. J is calculated as k*j=1(mod z) so in other words, (k*j)/z gives us something with a remainder of 1. We don't care about the something, just the fact that it gives us the remainder of 1. ";
    private static final String calculations = "And thats the initial calculations finished! I have to keep all these numbers safe and secret, except for n and k which are the public key. I can now send this to people so they can encrypt messages for me!";
    private static final String step1next =    "Click on 2 to see the next step!";

    private static final String tooltipP =  "Prime number p used for key calculation";
    private static final String tooltipQ =  "Prime number q used for key calculation";
    private static final String tooltipNEq= "Equation for n, which is an important part of the key";
    private static final String tooltipN=   "Value for n, which is p*q";
    private static final String tooltipZEq= "Equation for the totient, which is needed to calculate the key";
    private static final String tooltipZ=   "Value for z, which is (p-1) multiplied by (q-1)";
    private static final String tooltipK=   "Prime number k which is a number z cannot be divided by";
    private static final String tooltipSecretJ = "Equation to calculate j, we want a j such that (k*j)/z gives a remainder of 1";
    private static final String tooltipJNo = "The equation for j with letters replaced with numbers";
    private static final String tooltipJ =  "The secret number J worked out for these values";

    //getters for step 1
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

    //step 2 strings
    private static final String publicKey = "(n,k)";
    private static final String encryptEq = "m^k mod n";
    private static final String noPublicKey = "(33,7)";
    private static final String noEncryptEq ="m^7 mod 33";

    private static final String heyStep2 = "Hey welcome to Step 2. Click play to see how to encrypt using RSA";
    private static final String step2Welcome =  "Now we have calculated everything we need, so I will send the public key to Encrypt. As this is a public key, I can send it to as many people as I like and it does not change.";
    private static final String step2KeyReceived = "Now that I have the public key, I can use this to encrypt my message that i'm sending to Decrypt.";
    private static final String step2Equation = "For each letter in our message, we will transform the letter into a number, which we'll call m. The encrypted letter is calculated by m^k mod n";
    private static final String reminder = "To remind you, n=33 and k=7. These were calculated in Step 1. We'll change our public key to show this and then combine it with the equation.";
    private static final String hereEquation ="Here's the equation with n and k replaced with their values. For each letter in the message, the letter is converted to a number and put in the equation in the place of m.";
    private static final String encryption ="Now they are going to apply the equation to their message. Their message is the pile of paper. When the paper is encrypted it will have a lock on it, to show it is encrypted.";
    private static final String nextStep2 = "Click Step 3 to see how I decrypt the message";

    private static final String tooltipPK = "The public key calculated previously, in case you've forgotten n=33 and k=7";
    private static final String tooltipEq = "The equation used for encryption, m^k mod n, with m being the letter as a number";


    //step 2 getters
    public static String getHeyStep2() {
        return heyStep2;
    }

    public static String getPublicKey() {
        return publicKey;
    }

    public static String getEncryptEq() {
        return encryptEq;
    }

    public static String getNoPublicKey() {
        return noPublicKey;
    }

    public static String getNoEncryptEq() {
        return noEncryptEq;
    }

    public static String getStep2Welcome() {
        return step2Welcome;
    }

    public static String getStep2KeyReceived() {
        return step2KeyReceived;
    }

    public static String getStep2Equation() {
        return step2Equation;
    }

    public static String getReminder() {
        return reminder;
    }

    public static String getHereEquation() {
        return hereEquation;
    }

    public static String getEncryption() {
        return encryption;
    }

    public static String getNextStep2() {
        return nextStep2;
    }

    public static String getTooltipPK() {
        return tooltipPK;
    }

    public static String getTooltipEq() {
        return tooltipEq;
    }

    //step 3 strings
    private static final String equationStep3 = "c^j mod n";
    private static final String secretKey = "(j,n)";
    private static final String secretKeyNo ="(3,33)";
    private static final String equationStep3No = "c^3 mod 33";
    private static final String unknownEqStep3 = "c^? mod 33";

    private static final String welcomeStep3 = "Welcome to step 3. Here we'll be sending our encrypted messages to Decrypt and showing you how they get decrypted.";
    private static final String firstStep3 = "First we need to send the encrypted messages to Decrypt. You can see how we encrypted them in Step 2.";
    private static final String transferStep3 ="The two of us are going to send our encrypted message to Decrypt. If someone else gets our message then they won't be able to read our message because it is encrypted";
    private static final String interceptionStep3 ="We've both sent our messages off. The first one arrived safely but my message has been intercepted by someone before it reached Decrypt.";
    private static final String secretKeyStep3 = "In Step 1 we calculated some numbers. K and n became the public key, but we also calculated (j,n) which make up the secret key";
    private static final String decryptStep3 = "The equation c^j mod n is applied to each letter of the encrypted message. We should end up with a decrypted file, which will be identical to the original message";
    private static final String unknownStep3 = "The person who intercepted my message is trying to decrypt it. However she does not have the secret key, so she cannot decrypt it, meaning the message is meaningless.";
    private static final String resendStep3 = "It doesn't matter that they have my message, because they can't read it. When I manage to send my message to Decrypt, he will be able to decrypt it the same as the other message";
    private static final String nextStep3 = "That is the basics of RSA, but click on Step 4 if you would like to learn about using RSA for authentication.";

    private static final String tooltipStep3SK = "The secret key used by Decrypt. To remind you, j=3, and n=33.";
    private static final String tooltipStep3Eq = "Decryption equation, c^j mod n with c being the encrypted letter as a number";

    //getters for third step
    public static String getEquationStep3() {
        return equationStep3;
    }

    public static String getSecretKey() {
        return secretKey;
    }

    public static String getSecretKeyNo() {
        return secretKeyNo;
    }

    public static String getEquationStep3No() {
        return equationStep3No;
    }

    public static String getUnknownEqStep3() {
        return unknownEqStep3;
    }

    public static String getUnknownStep3() {
        return unknownStep3;
    }

    public static String getResendStep3() {
        return resendStep3;
    }

    public static String getNextStep3() {
        return nextStep3;
    }

    public static String getWelcomeStep3() {
        return welcomeStep3;
    }

    public static String getFirstStep3() {
        return firstStep3;
    }

    public static String getTransferStep3() {
        return transferStep3;
    }

    public static String getInterceptionStep3() {
        return interceptionStep3;
    }

    public static String getSecretKeyStep3() {
        return secretKeyStep3;
    }

    public static String getDecryptStep3() {
        return decryptStep3;
    }

    public static String getTooltipStep3SK() {
        return tooltipStep3SK;
    }

    public static String getTooltipStep3Eq() {
        return tooltipStep3Eq;
    }

    //Step 4 strings
    private static final String encryptStep4 = "m^j mod n";
    private static final String encryptNoStep4 = "m^3 mod 33";
    private static final String decryptStep4 = "c^k mod n";
    private static final String decryptNoStep4 ="c^7 mod 33";

    private static final String welcomeStep4 = "Welcome to Step 4. We will show you how RSA can be used for authentication. Click play to see!";
    private static final String firstStep4 = "First Decrypt uses his secret key to encrypt his message. He does this using the decrypt algorithm from Step 3 with the secret key values.";
    private static final String sendEncryptStep4 = "Now the message is encrypted, Decrypt will send it to me. Once it arrives I can decrypt it using the public key I received in Step 2. If I can decrypt it then I know it's from Decrypt.";
    private static final String wrongStep4 = "I know the message was safe, because I could successfully decrypt it. If someone else sends me a message, I won't be able to decrypt it because they won't have encrypted it with the secret key.";
    private static final String finishStep4 = "And that's RSA finished. Feel free to go back over the other steps or move onto one of the other Cryptography methods. Any areas your unsure about can be double checked in the Building Blocks";

    private static final String tooltipStep4Eq = "Equation for encryption, it's the same as the decryption equation from step 3 with the secret key only with m instead of c";
    private static final String tooltipStep4decrypt = "Equation for decryption, it's the same as the encryption equation from step 2 with the public key only with c instead of m";


    //getters for step 4
    public static String getEncryptStep4() {
        return encryptStep4;
    }

    public static String getEncryptNoStep4() {
        return encryptNoStep4;
    }

    public static String getDecryptStep4() {
        return decryptStep4;
    }

    public static String getDecryptNoStep4() {
        return decryptNoStep4;
    }


    public static String getWelcomeStep4() {
        return welcomeStep4;
    }

    public static String getFirstStep4() {
        return firstStep4;
    }
    public static String getSendEncryptStep4() {
        return sendEncryptStep4;
    }

    public static String getWrongStep4() {
        return wrongStep4;
    }

    public static String getFinishStep4() {
        return finishStep4;
    }

    public static String getTooltipStep4Eq() {
        return tooltipStep4Eq;
    }

    public static String getTooltipStep4decrypt() {
        return tooltipStep4decrypt;
    }


    //steps used lists
    private static final String[] step1Used = new String[]{"Prime Numbers","Diffusion vs Transformation","Stream vs Block","Asymmetric vs Symmetric","Inverse Modular"};
    private static final String[] step2Used = new String[]{"Prime Numbers","Encryption & Decryption","Diffusion vs Transformation","Stream vs Block"};
    private static final String[] step3Used = new String[]{"Prime Numbers","Encryption & Decryption","Diffusion vs Transformation"};
    private static final String[] step4Used = new String[]{"Prime Numbers","Encryption & Decryption","Diffusion vs Transformation"};

    //steps used getters
    public static String[] getStep1Used() {
        return step1Used;
    }

    public static String[] getStep2Used() {
        return step2Used;
    }

    public static String[] getStep3Used() {
        return step3Used;
    }

    public static String[] getStep4Used() {
        return step4Used;
    }
}
