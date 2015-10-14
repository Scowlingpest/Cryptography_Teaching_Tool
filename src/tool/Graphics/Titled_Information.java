package tool.Graphics;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import tool.Models.Header;
import tool.Models.Header_Paragraph;
import tool.Models.Paragraph;

/**
 * Created by Phillipa on 13/10/2015.
 */
public class Titled_Information {
    VBox vb;
    Text header;
    Text paragraph;

    public Titled_Information(Header_Paragraph hp, int n) {
        this.vb=new VBox();
        this.vb.getStyleClass().add("vbox");

        this.header=new Text(hp.getHeader().getTitle());
        this.header.getStyleClass().add("text-title");

        this.paragraph=new Text(hp.getParagraph().getText());
        this.paragraph.getStyleClass().add("text-main");
        this.paragraph.setWrappingWidth(n);

        this.vb.getChildren().addAll(this.header,this.paragraph);

    }

    public Titled_Information(Header h, Paragraph p,int n){
        this(new Header_Paragraph(h,p),n);
    }

    public VBox getVb() {
        return vb;
    }


    public Text getHeader() {
        return header;
    }

    public Text getParagraph() {
        return paragraph;
    }


}
