package tool.Graphics;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/** Author : Phillipa Russell
 *  Created: 30/10/2015
 */

//paper class, used to create paper graphic on screen
public class Paper {

    Image imageA;
    Image imageEncrypt;
    Image imageDecrypt;
    Image imageSingle;
    ImageView view = new ImageView();
    toolTipSpecial ttS;

    public Paper(String s, int size, int x, int y){
        //four images, plain stack of paper, encrypted stack, decrypted stack and a single sheet
        this.imageA=new Image("Files/Images/paper.png");
        this.imageEncrypt = new Image("Files/Images/encryptedPaper.png");
        this.imageDecrypt = new Image("Files/Images/decryptedPaper.png");
        this.imageSingle  = new Image("Files/Images/paper_sheet.png");

        //tool tip for paper
        this.ttS=new toolTipSpecial(new String[]{"Message before encryption","Message after Encryption","Message once it's been decrypted"});
        Tooltip.install(this.view,this.ttS.getTooltip());

        //decide which image is needed
        viewSetup(s,size, x ,y);
    }

    //decide which image should be used and setup view
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

    //change image and tooltip to encrypt
    public void changeToEncrypt(){
        this.view.setImage(this.imageEncrypt);
        this.ttS.useTextA();
    }

    //change image and tooltip to decrypt
    public void changeToDecrypt(){
        this.view.setImage(this.imageDecrypt);
        this.ttS.useTextB();
    }

    //change image to a single sheet of paper and change the tooltip
    public void changeToSingle(){
        this.view.setImage(this.imageSingle);
        Tooltip.install(this.view,new Tooltip("A message block of 128 bits"));
    }

    //change to the 'default', a plain stack of paper
    public void changeToBase(){
        this.view.setImage(this.imageA);
        this.ttS.useBaseText();
    }


}
