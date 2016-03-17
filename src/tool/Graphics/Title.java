package tool.Graphics;

import javafx.scene.text.Text;
import tool.Models.Header;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 15/10/2015.
 */
//Title graphical object
public class Title {
    private Text title;

    /*Title constructor, makes a title style text object
    parameters: h - a header object containing the text
    returns: null
     */
    public Title(Header h) {
        this.title=new Text(h.getTitle());
        this.title.getStyleClass().add("text-title");

    }

    //getter
    public Text getTitle() {
        return title;
    }

}
