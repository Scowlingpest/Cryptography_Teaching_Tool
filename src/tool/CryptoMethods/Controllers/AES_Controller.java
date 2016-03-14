package tool.CryptoMethods.Controllers;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 16/12/2015.
 */
public class AES_Controller extends CryptoMethodsController {

    static int BOXWIDTH = 475;

    public static int getBOXWIDTH(){return BOXWIDTH;}
    //Step 1 variables used
    private static final String step_1_welcome  = "Hi, and welcome to AES. AES stands for Advanced Encryption Standard and is one of the most secure encryption standards in the world, the US government even uses it! Click play below to start this step. I'll be explaining this step and the following step on my own because Decrypt is away getting the other steps ready for you!";
    private static final String step_1_detail   = "In the next step(Step 2), I will show you how we use AES to encrypt stuff, but for now I will explain the key details of it. In Step 3 i'll show you how I send the key and the encrypted message to Decrypt, who will show you how he decrypts the message in Step 4. But first I will explain the key areas of AES.";
    private static final String step_1_sizes    = "AES is a block cipher encryption method and works on message blocks of 128 bits. It works by putting the message blocks through several transformation rounds. The number of rounds depends on the size of the key. The key is also a data block of size 128, 192 or 256 bits, depending on the level of protection required.";
    private static final String rounds          = "The size of the key determines how many rounds you use. 10 rounds for 128 bit key, 12 for 192 bits and 14 for 256 bits. You can technically use less rounds, but those number of rounds are the AES standard. To date no one has managed to hack a message protected by AES following the standards, so I recommend you stick with them";
    private static final String keys            = "We start off with a single key in AES which is a block of random data. This key will be used to make a bunch of other keys using the Rijndael key schedule. You don't need to know about this in detail, it's just a fancy diffusion technique used on the key.";
    private static final String makingKeys      = "The key is put through this schedule enough times to produce multiple keys, enough for a new key to be used each round. These keys are the same size as the original. It is your key size that determines what AES you use (128, 192 or 256)";
    private static final String stages          = "To begin the first key is applied to the 128 bit message using XOR. Now the rounds start, each round has 4 stages: SubBytes, ShiftRows, Mix columns, Apply Key. For each round the message is passed through these 4 stages. The result from the end of the first round is passed onto the second round and so on.";
    private static final String nextStep1       = "The final round only has three stages, the Mix Column stage is removed. The result from the final round is the encrypted message which can then be sent. And that's the overview of AES finished! Click Step 2 and i'll give you a demonstration. ";


    public static String getStep_1_welcome() {
        return step_1_welcome;
    }

    public static String getStep_1_detail() {
        return step_1_detail;
    }

    public static String getStep_1_sizes() {
        return step_1_sizes;
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


    //Step 2 variables used
    private static final String step_2_welcome  = "Hi, and welcome to Step 2. I'm going to how you how a message gets encrypted using AES. We will be using a key size of 128 bits, so we will have 10 rounds in this example.";
    private static final String step_2_rounds   = "Here we have the three rounds i'll be describing. Round 1 has only 1 stage. Rounds 2-9 have the same 4 stages while the final round has 3 stages. Regardless of how many rounds you have, the first and final are always this way and all other rounds have 4 stages.";
    private static final String step_2_message  = "The message we will be encrypting is the stack of paper at the side. Remember we can only encrypt a block of 128 bits, so we will need to divide the message into 128 bit chunks. Each of the chunks will be represented as a page of paper here";
    private static final String step_2_first    = "For the first round we take the message chunk and XOR it with the key. The first round key is the actual original key, all other rounds use the keys we generated in the last step. For each bit of the message we XOR it against the corresponding bit in the key, so if both are 1 or 0 then 0 is placed, if either of the bits is 1 then 1 is placed in the encrypted message.";
    private static final String step_2_middle2  = "For the middle rounds the stages are: Sub Byte, Shift Row, Mix Column and then XOR against the key for that round. For Sub Bytes we substitute each byte in the message with a byte from a lookup table. Shift row we shift each row by its position -1, so the first row we don't shift, the third row we shift by 2.";
    private static final String step_2_endLast  = "Mix Columns is hard to explain and perform. We take each column of the block and perform a matrix multiplication on it. Finally we do another XOR with a key, but this time with a key we generated for this round. We repeat these four stages for the number of rounds we need, until the final round where we don't perform the Mix Columns stage.";
    private static final String step_2_encrypt  = "We do this for each 128 bit block of the message we have, until the entire message is encrypted. And that's how you encrypt using AES. For the larger key sizes you simply increase the number of rounds as I said in the last step.";
    private static final String step_2_next     = "Next we will show you how the message gets sent to Decrypt. Click Step 3 to see that, or Step 2 to see this step again. ";

    public static String getStep_2_welcome() {
        return step_2_welcome;
    }

    public static String getStep_2_rounds() {
        return step_2_rounds;
    }

    public static String getStep_2_message() {
        return step_2_message;
    }

    public static String getStep_2_first() {
        return step_2_first;
    }

    public static String getStep_2_middle2() {
        return step_2_middle2;
    }

    public static String getStep_2_endLast() {
        return step_2_endLast;
    }

    public static String getStep_2_encrypt() {
        return step_2_encrypt;
    }

    public static String getStep_2_next() {
        return step_2_next;
    }

    //Step 2 and step 4 variables
    private static final int boxX    = 200; private static final int boxY1 = 100;
    private static final int boxY2   = 300; private static final int boxY3 = 500;
    private static final int startLocX = 100; private static final int endLocX = boxX+BOXWIDTH;
    private static final int startLocY = 100; private static final int endLocY = 500;
    private static final int difference =getEndLocX()-getStartLocX();
    private static final int halfway = (difference)/2;


    public static int getBoxX() {
        return boxX;
    }

    public static int getBoxY1() {
        return boxY1;
    }

    public static int getBoxY2() {
        return boxY2;
    }

    public static int getBoxY3() {
        return boxY3;
    }

    public static int getStartLocX() {
        return startLocX;
    }

    public static int getEndLocX() {
        return endLocX;
    }

    public static int getStartLocY() {
        return startLocY;
    }

    public static int getEndLocY() {
        return endLocY;
    }

    public static int getHalfway() {
        return halfway;
    }

    public static int getDifference() {
        return difference;
    }

    //step 3 values used
    private static final String step_3_welcome = "Welcome to Step 3! I'm here at last! Did you miss me? Here we are going to show you how Encrypt sends the encrypted message and the key over to me. I'll decrypt it in the next step. Press play and we'll get started.";
    private static final String step_3_key_encrypt = "First we need to encrypt the key. In Step 1 you saw the key go through a scheduler and make multiple keys. We are only interested in the original key here. We need to encrypt it using whatever method we like so long as it's secure!";
    private static final String step_3_send_key = "Now Encrypt will send the key over to me and I will decrypt it based on the method chosen. Anybody listening will get the encrypted key and anything else we send here, so if you are concerned you might want do more than one kind on encryption on the key, just to be safe.";
    private static final String step_3_send_message = "Encrypt will now send the message over to me. Based on the size of the message it could be sent over all at once, or in 128 bit sized chunks. Here we will show you the blocks method, I'm going to receive the message blocks Encrypt encrypted in the last step. Anyone listening will get these as well but won't be able to do anything with them.";
    private static final String step_3_finish = "And that's it! I have everything I need to start decrypting, I have the key and the message. Now I put the key through the scheduler the same as Encrypt in step 1 and get the same 10 generated keys. I'll show you how I decrypt the message in step 4.";

    public static String getStep_3_welcome() {
        return step_3_welcome;
    }

    public static String getStep_3_key_encrypt() {
        return step_3_key_encrypt;
    }

    public static String getStep_3_send_key() {
        return step_3_send_key;
    }

    public static String getStep_3_send_message() {
        return step_3_send_message;
    }

    public static String getStep_3_finish() {
        return step_3_finish;
    }

    //Step 4 values used
    private static final String step_4_welcome      = "Welcome to the final step, here i'm going to show you the steps I go through to decrypt the message. It's very similar to the encryption process. We generate the same keys and use the same number of rounds";
    private static final String step_4_rounds       = "The rounds are the same idea as the encryption process, the first and last rounds are unique while the intermediary rounds are the same. We also have similar stages within the rounds. The keys we generate should be the same keys Encrypt generated, and we should use them in the reverse order (so the first key used should now be the last).";
    private static final String step_4_stages       = "The stages are similar to the encryption process, only in reverse! XOR is the same, because the inverse of XOR is XOR. All other stages are the inverse (Inv) of their stage during encryption";
    private static final String step_4_first        = "The first round is the reverse of the last round of encryption, so we put the message block through XOR, Inv Shift Row then Inv Sub Byte. The Inv Shift Row moves the rows to the left rather than right, the Inv Sub Byte uses a different table that gives the original value from the looked up value (so if D3 gave E4, this table will have E4 giving D3)";
    private static final String step_4_intermediary = "The intermediary rounds are the reverse of the encryption rounds, so we have XOR, Inv Mix Column, Inv Shift Row then Inv Sub Byte. Remember the encryption order is sub byte,shift row, mix column and then XOR. Inv Mix column is the same idea as the first mix column only it uses an inverse matrix multiplication table. The last round is simply an XOr with the key";
    private static final String step_4_final        = "Once all the blocks have been through the rounds, I will have the completely decrypted message, which will be Encrypt's original message. Now AES is complete, for more detailed information on the round stages you can look online but hopefully this has given you a good overview of AES.";

    public static String getStep_4_welcome() {
        return step_4_welcome;
    }

    public static String getStep_4_rounds() {
        return step_4_rounds;
    }

    public static String getStep_4_stages() {
        return step_4_stages;
    }

    public static String getStep_4_first() {
        return step_4_first;
    }

    public static String getStep_4_intermediary() {
        return step_4_intermediary;
    }

    public static String getStep_4_final() {
        return step_4_final;
    }

    private static final String[] step1Used = new String[]{"Stream vs Block","Asymmetric vs Symmetric"};
    private static final String[] step2Used = new String[]{"Stream vs Block","Encryption & Decryption"};
    private static final String[] step3Used = new String[]{"Encryption & Decryption"};
    private static final String[] step4Used = new String[]{"Stream vs Block","Encryption & Decryption"};

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
