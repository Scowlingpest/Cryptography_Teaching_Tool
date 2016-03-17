package tool.BuildingBlocks.Controllers;

import tool.Models.Paragraph;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 12/11/2015.
 */
//controller class for the stream vs block building block
public class Stream_vs_Block_Controller extends BuildingBlockController {

    //variables needed
    private final static Paragraph streamPara= new Paragraph("Stream cipher is when the message is transformed bit by bit, or character by character. A key is given to an encryption algorithm and the algorithm is applied to each bit "+
    "in order to encrypt the entire message. This is also how it is decrypted. Stream ciphers can be difficult to implement due to the individuality of messages, but are great for when you don't know the size of "+
    "your message or you are receiving the data in a continuous stream. Check out the example below to see a stream cipher in action. Each box is a bit that makes up a message. When the box flashes green the change "+
    "is being applied. Watch and you'll see it change for encryption(green) and decryption(purple)!");

    private final static Paragraph blockPara= new Paragraph("Block cipher is when a message is divided into same sized block chunks. If the message doesn't exactly fit into the block then padding can be added, in this case the padding "+
    "is X but it depends on the cipher. The encryption/decryption algorithms are applied to the blocks. This is good for when you know how big your message is, and can also provide message authentication, but block "+
    "ciphers can be slower than stream ciphers. Check out the example below. Since our example message is smaller than the block, we have padded the end with X's. Watch and see the block become encrypted (green) and decrypted(purple).");

    private final static int rectWidth =550;
    private final static int rectHeight =600;

    private final static int textWidth = 500;

    //getters
    public static int getTextWidth() {
        return textWidth;
    }

    public static int getRectWidth() {
        return rectWidth;
    }

    public static int getRectHeight() {
        return rectHeight;
    }

    public static Paragraph getBlockPara() {
        return blockPara;
    }

    public static Paragraph getStreamPara() {
        return streamPara;
    }
}
