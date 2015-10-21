package tool.Graphics;

import javafx.scene.text.Text;
import tool.Models.Paragraph;

/**
 * Created by Phillipa on 15/10/2015.
 */
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

    public void setPara(Text para) {
        this.para = para;
    }
}
