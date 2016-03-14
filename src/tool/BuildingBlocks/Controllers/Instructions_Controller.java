package tool.BuildingBlocks.Controllers;

import tool.Models.Header;
import tool.Models.Paragraph;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 13/12/2015.
 */
//controller class for the instructions - not technically a building block but still
//also used for the welcome screen as originally the welcome screen and instructions were the same screen
public class Instructions_Controller extends BuildingBlockController {

    //variables needed
    static final int bubbleSize=300;
    static final int x1 = 200;
    static final int x2 = 700;
    static final int y1 = 0;
    static final int y2 = 350;
    static final int y3 = 400;

    static final String helloEncrypt = "Hi!! And Welcome to the Cryptography Teaching tool. I'm Encrypt and i'm the twin sister of Decrypt."+
            "We're going to be teaching you all about various different cryptography methods and some additional information " +
            "you might want to learn called Building blocks.";
    static final String helloDecrypt = "Hello! I'm Decrypt and i'm the twin brother of Encrypt. This is the Welcome page and you can see the instructions via "+
            "via the File menu at the top, or by pressing Ctrl+I. You'll see us throughout the tool explaining everything and helping you learn!";


    static final Header instructions    = new Header("Welcome to the Instructions");
    static final Header menu            = new Header("Menus");
    static final Header buildingBlocks  = new Header("Building Blocks");
    static final Header cryptography    = new Header("Cryptography Methods");
    static final Header animation       = new Header("Animation Controls");

    static final Header[] pageTitles = new Header[]{instructions,menu,buildingBlocks,cryptography,animation};

    static final Paragraph welcome      = new Paragraph("These are the instructions. Down the bottom is a selector and some arrows. Use these to go between the instruction pages. The instructions on offer are as follows:\n"+
    "\n\t * 2) Menus: information on how to navigate the menus.\n"+
    "\n\t * 3) Building Blocks: information on the building blocks.\n"+
    "\n\t * 4) Cryptography Methods: information on the cryptography methods.\n"+
    "\n\t * 5) Animation controls: information on how to control the animations.\n"+
    "\nSelect a page below to get started.");

    static final Paragraph menuInfo = new Paragraph("There are three menus along the top of the screen. Each of these menus allow you to access different parts of the program. They also list the keyboard shortcuts to all of the parts of the program if you prefer to use them. Here is a list of each of the menus and their contents:\n"+
    "\n\t * File: This menu contains the Instructions and the Exit to the program. You'll have already accessed this menu to get here!\n"+
    "\n\t * Building Blocks: This is additional information you might be interested in. It varies from things you might need to know to understand the cryptography methods, and other interesting things related to cryptography. There is a Mathematics sub menu which contains maths that are commonly used in cryptography.\n"+
    "\n\t * Cryptography Methods: This menu lets you activate the various cryptography methods animations. From here you can learn about 4 different cryptography methods and see them in action.\n."+
    "\nRemember to click below to learn more about the building blocks and animations.");

    static final Paragraph blockInfo = new Paragraph("There are 8 building blocks you can choose to learn about. Some of them are concepts which will help you understand the cryptography animations better. Others are there so you can learn about other areas of cryptography. Most of them include an interactive element to help you learn. If if does then the element will be explained on the page so make sure to read everything so you get the full experience! Here's a list of building blocks:\n"+
    "\n\t * Prime Numbers: This is in the Mathematics sub menu. It will explain what a prime number is and has a block of numbers where you can test your understanding of what a prime number is.\n"+
    "\n\t * Generator: This is in the Mathematics sub menu, it explains what a generator is and has an interactive animation that will let you test a number to see if it is a generator.\n"+
    "\n\t * Inverted Modulus: This is the final thing in the Mathematics submenu. It explains inverted modulus and includes an interactive animation that lets you test a number to see if it is an inverted modulus.\n"+
    "\n\t * Asymmetric vs Symmetric: This explains the difference between Asymmetric methods and Symmetric methods. It includes an interactive element where you can select types of keys and see how that affects the method type,\n"+
    "\n\t * Encryption and Decryption: This explains the difference between Encryption and Decryption. It alo includes an interactive element where you can encrypt and decrypt your name using a caeser cipher.\n"+
    "\n\t * Stream vs Block: This explains the difference between Stream and Block encryption. It includes animations which show the difference between the two methods in a graphical format.\n"+
    "\n\t * Vigenère cipher: This explains the Vigenère cipher, a popular and fun type of cryptography. It does not contain an interactive element, but gives you the table so you can practice it yourself!\n"+
    "\n\t * Diffusion vs Transformation: This explains Diffusion and Transformation method of message encryption. It contains animations which help demonstrate the two concepts.");

    static final Paragraph methodInfo = new Paragraph("There are 4 cryptography methods covered in this tool. You can access them using the menu above or the keyboard shortcuts. You will be taken to a blank screen with the file menu selector at the top and a new menu bar along the bottom. For details on the mnu bar go onto the last set of instructions. The 4 cryptography methods are:\n"+
    "\n\t * RSA: A common method of cryptography. This has 4 steps and will teach you the basics of RSA.\n"+
    "\n\t * Diffie Hellman: A key exchange method divided into 2 steps, the first is an example of diffie hellman while the second lets you pick the numbers!\n"+
    "\n\t * El Gamal: A cryptography method based on diffie hellman. There are multiple ways of doing this method, but here we are using the diffie hellman method as it the easiest to understand.\n"+
    "\n\t * AES: A common method of cryptography which can be quite complicated. It is divided into 4 steps and aims to give you an understanding of AES without getting confused by the maths\n"+
    "\nRemember to click the last page of the instructions to learn about the animation control bar!");

    static final Paragraph animationControl = new Paragraph("When you select a cryptography method to load, a blank screen will appear and a new collection of buttons will appear at the bottom. The Step buttons will let you play a part of the animation. The play/pause button will let you start, pause and play the animation from where you last paused. The speed buttons will let you change the speed of the animation as it plays. When you pause the animation the speed will be reset to 1. There is also a small list called Building Blocks Used, which lets you know what building blocks are especially useful for the step you are playing. Finally is a Current speed indicator which will tell you when you the current speed of your animation. If you want to play the step from the beginning, then press play when it ends or click on the step and then press play.");

    static final Paragraph[] pageContents = new Paragraph[]{welcome,menuInfo,blockInfo,methodInfo,animationControl};


    //getters for all variables
    public static Header[] getPageTitles() {
        return pageTitles;
    }

    public static Paragraph[] getPageContents() {
        return pageContents;
    }

    public static Header getPageTitlebyIndex(int i){
        return pageTitles[i];
    }

    public static Paragraph getPageContentsbyIndex(int i){
        return pageContents[i];
    }

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

    public static String getHelloEncrypt() {
        return helloEncrypt;
    }

    public static String getHelloDecrypt() {
        return helloDecrypt;
    }


}
