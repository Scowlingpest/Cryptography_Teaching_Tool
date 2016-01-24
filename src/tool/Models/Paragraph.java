package tool.Models;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 13/10/2015.
 */
//object used for a paragraph of text, helps with text settings
public class Paragraph {
    String text;

    public Paragraph(String t) {
        this.text=t;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
