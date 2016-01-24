package tool.Graphics;

import javafx.scene.text.Text;
import tool.Models.Paragraph;

/** Author : Phillipa Russell
 *  Created: 15/10/2015
 */
//graphical form of the paragraph object
public class Para_Text {

    Text para;

    public Para_Text(Paragraph p, int n) {
        this.para=new Text(p.getText());
        this.para.getStyleClass().add("text-main");
        this.para.setWrappingWidth(n);
    }

    public Text getPara() {
        return para;
    }

}
