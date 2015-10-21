package tool.Graphics;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import tool.Models.Header;

/**
 * Created by Phillipa on 15/10/2015.
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

    public void setTitle(Text title) {
        this.title = title;
    }

    public void changeSize(int n){
        //this.title.setFont("-fx-font:25px Arial;");
    }
}
