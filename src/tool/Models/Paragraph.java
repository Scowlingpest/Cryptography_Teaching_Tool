package tool.Models;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 13/10/2015.
 */
//Paragraph object, object used for a paragraph of text, helps with text settings
public class Paragraph {
    private String text;

    /*Paragraph constructor, takes in text and stores in a paragraph
    parameters: t - text to store
    returns - null
     */
    public Paragraph(String t) {
        this.text=t;
    }

    //getter and setter
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
