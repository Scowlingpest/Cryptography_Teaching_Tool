package tool.Models;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 13/10/2015.
 */
//takes in a header and a paragraph to combine them, maps to Titled_Information
public class Header_Paragraph {
    Header header;
    Paragraph paragraph;

    public Header_Paragraph(String h, String p) {
        this.header = new Header(h);
        this.paragraph= new Paragraph(p);
    }

    public Header_Paragraph(Header h, Paragraph p){
        this.header=h;
        this.paragraph=p;
    }

    public Header getHeader() {
        return this.header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Paragraph getParagraph() {
        return paragraph;
    }

    public void setParagraph(Paragraph paragraph) {
        this.paragraph = paragraph;
    }
}

