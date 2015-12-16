package tool.BuildingBlocks.Controllers;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 13/12/2015.
 */
public class Instructions_Controller extends BuildingBlockController {

    static final int bubbleSize=300;
    static final int x1 = 200;
    static final int x2 = 700;
    static final int y1 = 0;
    static final int y2 = 350;
    static final int y3 = 400;

    static final String helloEncrypt = "Hi!! And Welcome to the Cryptography Teaching tool. I'm Encrypt and i'm the twin sister of Decrypt."+
            "We're going to be teaching you all about various different cryptography methods and some additional information " +
            "you might want to learn called Building blocks.";
    static final String helloDecrypt = "Hello! I'm Decrypt and i'm the twin brother of Encrypt. This is the instructions page and you can "+
            "return to it at any time via the File menu at the top, or by pressing Ctrl+I. We're going to explain to you how to "+
            "use this tool so you can get the most out of it!";
    static final String topControls = "At the top is three menus, File, Building Blocks and Cryptography Methods.  The File menu lets you exit "+
            "and see these instructions again. The Building Blocks menu lets you learn some additional things about cryptography, "+
            "while the Cryptography methods menu lets you start an animation of a cryptography method. Decrypt will tell you more "+
            "about the Building blocks over to your right.";
    static final String bottomControls = "When you load an animation a bar will appear along the bottom. These buttons are how you navigate the "+
            "animation. The step buttons let you select which step of the animation you wish to see. The speed buttons let you change the "+
            "speed of the animation, even in play! The play pause buttons will let you pause the animation and play it again. The Building blocks used "+
            "lets you see what building blocks relate to the step you are playing.";
    static final String buildingBlocks = "The building blocks cover a wide range of subjects to help you learn about cryptography. There is a submenu called "+
            "Mathematics that lets you learn about the advanced maths that are used by cryptography methods. The other building blocks cover "+
            "other interesting stuff that you might like to learn about";
    static final String interactive = "Almost all of the building blocks have an interactive element to help you learn, so don't worry you won't just be reading. "+
            "There are some animations to play, and some interactive elements that are explained on the page. Please have fun with these as they will help "+
            "you understand the topics. You can access the building blocks from the top menu or from the keyboard shortcuts.";

    public static int getBubbleSize() {
        return bubbleSize;
    }

    public static int getX1() {
        return x1;
    }

    public static int getX2() {
        return x2;
    }

    public static int getY1() {
        return y1;
    }

    public static int getY2() {
        return y2;
    }

    public static int getY3() {
        return y3;
    }

    public static String getHelloEncrypt() {
        return helloEncrypt;
    }

    public static String getHelloDecrypt() {
        return helloDecrypt;
    }

    public static String getTopControls() {
        return topControls;
    }

    public static String getBottomControls() {
        return bottomControls;
    }

    public static String getBuildingBlocks() {
        return buildingBlocks;
    }

    public static String getInteractive() {
        return interactive;
    }
}
