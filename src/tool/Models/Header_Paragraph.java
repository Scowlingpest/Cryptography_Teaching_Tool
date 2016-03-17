package tool.Models;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 13/10/2015.
 */
//Header_Paragraph object, takes in a header and a paragraph to combine them, for mapping to Titled_Information
//Used mainly to help with text settings upon mapping
public class Header_Paragraph {
    private Header header;
    private Paragraph paragraph;

    /*Header_Paragraph constructor, creates a holder for a title and related paragraph
    paragraph: h- title text, p - paragraph text
    returns: null
     */
    public Header_Paragraph(String h, String p) {
        this.header = new Header(h);
        this.paragraph= new Paragraph(p);
    }

    /*Header_Paragraph constructor, creates a holder for a title and related paragraph
    paragraph: h- title text(header), p - paragraph text(paragraph)
    returns: null
     */
    public Header_Paragraph(Header h, Paragraph p){
        this.header=h;
        this.paragraph=p;
    }

    //getters and setters
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

