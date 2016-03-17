package tool.Graphics;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import tool.Models.Header;
import tool.Models.Header_Paragraph;
import tool.Models.Paragraph;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 13/10/2015.
 */
//Titled_Information graphical object class
public class Titled_Information {
    private VBox vb;
    private Title header;
    private Para_Text paragraph;

    /*Titled_Information constructor, makes a paragraph with a title on top
    parameters: hp - header_paragraph object with title text and paragraph text, n - width of text
    returns: null
     */
    public Titled_Information(Header_Paragraph hp, int n) {
        this.vb=new VBox();
        this.vb.getStyleClass().add("vbox");

        this.header=new Title(hp.getHeader());

        this.paragraph=new Para_Text(hp.getParagraph(),n);

        this.vb.getChildren().addAll(this.header.getTitle(),this.paragraph.getPara());

    }

    /*Titled_Information constructor, works with a separate header and paragraph
    parameters: h- header text, p - paragraph text, n - width of text
    returns: null
     */
    public Titled_Information(Header h, Paragraph p,int n){
        this(new Header_Paragraph(h,p),n);
    }

    //getters
    public VBox getVb() {
        return vb;
    }

    public Text getHeader() {
        return header.getTitle();
    }

    public Text getParagraph() {
        return paragraph.getPara();
    }


}
