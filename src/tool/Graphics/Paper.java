package tool.Graphics;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/** Author : Phillipa Russell
 *  Created: 30/10/2015
 */
public class Paper {

    Image imageA;
    Image imageEncrypt;
    Image imageDecrypt;
    Image imageSingle;
    ImageView view = new ImageView();
    toolTipSpecial ttS;

    public Paper(String s, int size, int x, int y){
        this.imageA=new Image("tool/Files/Images/paper.png");
        this.imageEncrypt = new Image("tool/Files/Images/encryptedPaper.png");
        this.imageDecrypt = new Image("tool/Files/Images/decryptedPaper.png");
        this.imageSingle  = new Image("tool/Files/Images/paper_sheet.png");

        this.ttS=new toolTipSpecial(new String[]{"Message before encryption","Message after Encryption","Message once it's been decrypted"});
        Tooltip.install(this.view,this.ttS.getTooltip());

        viewSetup(s,size, x ,y);
    }

    private void viewSetup(String s,int size, int x, int y){
        switch (s) {
            case "encrypt":
                changeToEncrypt();
                break;
            case "decrypt":
                changeToDecrypt();
                break;
            case "single":
                changeToSingle();
                break;
            default:
                changeToBase();
                break;
        }
        view.setPreserveRatio(true);
        view.setSmooth(true);
        view.setCache(true);
        view.setFitWidth(size);
        view.setLayoutX(x);
        view.setLayoutY(y);
        view.setOpacity(0);
    }

    public ImageView getView() {
        return view;
    }

    public void changeToEncrypt(){
        this.view.setImage(this.imageEncrypt);
        this.ttS.useTextA();
    }

    public void changeToDecrypt(){
        this.view.setImage(this.imageDecrypt);
        this.ttS.useTextB();
    }

    public void changeToSingle(){
        this.view.setImage(this.imageSingle);
        Tooltip.install(this.view,new Tooltip("A message block of 128 bits"));
    }
    public void changeToBase(){
        this.view.setImage(this.imageA);
        this.ttS.useBaseText();
    }


}
