package tool.Graphics;

import javafx.scene.control.Tooltip;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 10/10/2015.
 */
public class toolTipSpecial {
    Tooltip tooltip;
    String textA;
    String textB;
    String baseText;

    public toolTipSpecial(String[] tool) {
        this.tooltip = new Tooltip();
        setTooltipText(tool[0]);
        this.textA=tool[1];
        this.textB=tool[2];
        this.baseText=tool[0];

    }

    public Tooltip getTooltip() {
        return tooltip;
    }

    public void setTooltipText(String text) {
        this.tooltip.setText(text);
    }

    public void useTextA(){
        setTooltipText(this.textA);
    }

    public void useTextB(){
        setTooltipText(this.textB);
    }

    public void useBaseText(){ setTooltipText(this.baseText);}

}
