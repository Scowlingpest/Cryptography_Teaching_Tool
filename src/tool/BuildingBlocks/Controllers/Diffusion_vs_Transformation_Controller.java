package tool.BuildingBlocks.Controllers;

import tool.Models.Paragraph;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 20/12/2015.
 */

//Controller class for the diffusion vs transformation building block
public class Diffusion_vs_Transformation_Controller extends BuildingBlockController{

    //variables needed
    private static final Paragraph diffusion = new Paragraph("Diffusion is the art of mixing up a message without changing the actual content. So performing diffusion on a message is like cutting it up and making a mosaic out of it. "+
    "There is an example below of a column cipher which is a good example of diffusion. The top row is the key Boa which we will put into alphabetical order to arrange the columns. We have some blank squares so we will fill them in with X's. The result will be in 'chunks' of 3, with order of the letters changed. Above the animation is the message and how it is changed due to the cipher. Press play and see it for yourself.");
    private static final Paragraph transformation = new Paragraph("Transformation is the art of transforming the contents of a message, such as changing the letters. For example changing Hi to Km is transformation. Below is an example "+
    "of transformation using colours to help you understand why transformation is so effective. Press play to see it go.");

    private final static int rectWidth =550;
    private final static int rectHeight =600;

    private final static int textWidth = 500;

    //getters
    public static Paragraph getDiffusion() {
        return diffusion;
    }

    public static Paragraph getTransformation() {
        return transformation;
    }

    public static int getRectWidth() {
        return rectWidth;
    }

    public static int getRectHeight() {
        return rectHeight;
    }

    public static int getTextWidth() {
        return textWidth;
    }
}
