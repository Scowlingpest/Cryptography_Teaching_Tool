package tool.Graphics;

import javafx.scene.text.Text;
import tool.Models.Header;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 15/10/2015.
 */
public class Title {
    Text title;

    public Title(Header h) {
        this.title=new Text(h.getTitle());
        this.title.getStyleClass().add("text-title");

    }

    public Text getTitle() {
        return title;
    }

}
