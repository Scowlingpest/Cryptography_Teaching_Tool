package tool.Models;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 13/10/2015.
 */
//Header object
public class Header {
    private String title;

    /*Header constructor, creates a holder for the title piece of text
    parameters: t- text to set title to
    returns: null
     */
    public Header(String t) {
        this.title=t;
    }

    //getter and setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
