package tool.Graphics;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by Phillipa on 30/10/2015.
 */
public class Paper {

    Image imageA;
    Image imageEncrypt;
    Image imageDecrypt;
    ImageView view = new ImageView();
    toolTipSpecial ttS;

    public Paper(String s){
        this.imageA=new Image("tool/Files/Images/paper.png");
        this.imageEncrypt = new Image("tool/Files/Images/encryptedPaper.png");
        this.imageDecrypt = new Image("tool/Files/Images/decryptedPaper.png");

        this.ttS=new toolTipSpecial(new String[]{"Message before encryption","Message after Encryption","Message once it's been decrypted"});
        Tooltip.install(this.view,this.ttS.getTooltip());

        viewSetup(s);
    }

    private void viewSetup(String s){
        if(s.equals("encrypt")){
            changeToEncrypt();
        }
        else if (s.equals("decrypt")){
            changeToDecrypt();
        }
        else{
            changeToBase();
        }
        view.setPreserveRatio(true);
        view.setSmooth(true);
        view.setCache(true);
    }

    public void changeToEncrypt(){
        this.view.setImage(this.imageEncrypt);
        this.ttS.useTextA();
    }

    public void changeToDecrypt(){
        this.view.setImage(this.imageDecrypt);
        this.ttS.useTextB();
    }

    public void changeToBase(){
        this.view.setImage(this.imageA);
        this.ttS.useBaseText();
    }

}
