package tool.Models;

/**
 * Created by Phillipa on 13/10/2015.
 */
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
