package tool.CryptoMethods.Controllers;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 16/12/2015.
 */
public class AES_Controller extends CryptoMethodsController {

    //Step 1 variables used
    private static final String welcome     = "Hi, and welcome to AES. AES stands for Advanced Encryption Standard and is one of the most secure encryption standards in the world, the US government even uses it! Click play below to start this step. I'll be explaining this step and the following step on my own because Decrypt is away getting the other steps ready for you!";
    private static final String detail      = "In the next step(Step 2), I will show you how we use AES to encrypt stuff, but for now I will explain the key details of it. In Step 3 i'll show you how I send the key and the encrypted message to Decrypt, who will show you how he decrypts the message in Step 4. But first I will explain the key areas of AES.";
    private static final String sizes       = "AES is a block cipher encryption method and works on message blocks of 128 bits. It works by putting the message blocks through several transformation rounds. The number of rounds depends on the size of the key. The key is also a data block of size 128, 192 or 256 bits, depending on the level of protection required.";
    private static final String rounds      = "The size of the key determines how many rounds you use. 10 rounds for 128 bit key, 12 for 192 bits and 14 for 256 bits. You can technically use less rounds, but those number of rounds are the AES standard. To date no one has managed to hack a message protected by AES following the standards, so I recommend you stick with them";
    private static final String keys        = "We start off with a single key in AES which is a block of random data. This key will be used to make a bunch of other keys using the Rijndael key schedule. You don't need to know about this in detail, it's just a fancy diffusion technique used on the key.";
    private static final String makingKeys  = "The key is put through this schedule enough times to produce multiple keys, enough for a new key to be used each round. These keys are the same size as the original. It is your key size that determines what AES you use (128, 192 or 256)";
    private static final String stages      = "To begin the first key is applied to the 128 bit message using XOR. Now the rounds start, each round has 4 stages: SubBytes, ShiftRows, Mix columns, Apply Key. For each round the message is passed through these 4 rounds. The result from the end of the first round is passed onto the second round and so on.";
    private static final String nextStep1   = "The final round only has three stages, the Mix Column stage is removed. The result from the final round is the encrypted message which can then be sent. Click Step 2 and i'll give you a demonstration. ";


    public static String getWelcome() {
        return welcome;
    }

    public static String getDetail() {
        return detail;
    }

    public static String getSizes() {
        return sizes;
    }

    public static String getRounds() {
        return rounds;
    }

    public static String getKeys() {
        return keys;
    }

    public static String getMakingKeys() {
        return makingKeys;
    }

    public static String getStages() {
        return stages;
    }

    public static String getNextStep1() {
        return nextStep1;
    }
}
