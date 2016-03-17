package tool.Graphics;

import javafx.scene.text.Text;
import tool.Models.Paragraph;

/** Author : Phillipa Russell
 *  Created: 15/10/2015
 */
//Para_text, graphical form of the paragraph object
public class Para_Text {

    private Text para;

    /*Para_Text constructor, makes a text object that is a paragraph of text
    parameters: p - paragraph of text to display, n-width of text
    returns: null
     */
    public Para_Text(Paragraph p, int n) {
        this.para=new Text(p.getText());
        this.para.getStyleClass().add("text-main");
        this.para.setWrappingWidth(n);
    }

    //getter
    public Text getPara() {
        return para;
    }

}
