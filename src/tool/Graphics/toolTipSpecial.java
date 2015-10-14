package tool.Graphics;

import javafx.scene.control.Tooltip;

/**
 * Created by Phillipa on 10/10/2015.
 */
public class toolTipSpecial {
    Tooltip tooltip;
    String textA;
    String textB;

    public toolTipSpecial(String[] tool) {
        this.tooltip = new Tooltip();
        setTooltipText(tool[0]);
        this.textA=tool[1];
        this.textB=tool[2];

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

    public void setTextA(String textA) {
        this.textA = textA;
    }

    public void setTextB(String textB) {
        this.textB = textB;
    }
}
