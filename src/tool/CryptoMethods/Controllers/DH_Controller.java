package tool.CryptoMethods.Controllers;

/**
 * Created by Phillipa on 09/11/2015.
 */
public class DH_Controller extends CryptoMethodsController {

    //Step 1 variables needed

    private static final String step1welcome = "Welcome to Diffie Hellman. This is a method of symmetric key exchange. The keys can then be used to encrypt and decrypt messages. Click play to start. ";
    private static final String step1PandQ = "First we have to decide on two prime numbers p and q. It doesn't natter if anyone else knows these so we can disuss them publicly. We'll keep things small for this example.";
    private static final String step1Prime = "We've decided on p=5 and q=7. Now we both need to do some maths";

    public static String getStep1welcome() {
        return step1welcome;
    }
}
