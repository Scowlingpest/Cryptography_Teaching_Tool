package tool.Graphics;

import javafx.scene.control.Tooltip;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 10/10/2015.
 */
//toolTipSpecial graphical object class, stores 3 different tooltips
class toolTipSpecial {
    private Tooltip tooltip;
    private String textA;
    private String textB;
    private String baseText;

    /*toolTipSpecial constructor, makes three different tooltips for an object
    parameters: tool - the three tooltips to install
    returns: null
     */
    public toolTipSpecial(String[] tool) {
        this.tooltip = new Tooltip();
        setTooltipText(tool[0]);
        this.textA=tool[1];
        this.textB=tool[2];
        this.baseText=tool[0];

    }

    //getter
    public Tooltip getTooltip() {
        return tooltip;
    }

    //setter
    private void setTooltipText(String text) {
        this.tooltip.setText(text);
    }

    //switches between tooltips A,B and default/base
    public void useTextA(){
        setTooltipText(this.textA);
    }

    public void useTextB(){
        setTooltipText(this.textB);
    }

    public void useBaseText(){ setTooltipText(this.baseText);}

}
