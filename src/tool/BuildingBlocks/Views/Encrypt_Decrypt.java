package tool.BuildingBlocks.Views;


import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import tool.BuildingBlocks.Controllers.Encrypt_Decrypt_Controller;
import tool.Graphics.Arrow;
import tool.Graphics.Para_Text;
import tool.Graphics.Robot;
import tool.Graphics.Title;
import tool.Models.Paragraph;


/** Author : Phillipa Russell
 *  Created: 10/10/2015
 */
//encrypt decrypt building block view class
public class Encrypt_Decrypt {
    //variables needed
    private static TextField name;
    private static TextField encrypted;
    private static TextField decrypted;

    /*start method, setups the borderpane
    parameters : bp- borderpane to setup
    returns: null
    */
    public static void start(BorderPane bp) {

        setUpLeft(bp);
        setUpBottom(bp);

    }


    /*setUpLeft,setups the left side, which has both robots and their information,
    actually covers from left to right due to bp rules
    parameters: bp - borderpane to add to
    returns: null
     */
    private static void setUpLeft(BorderPane bp){

        HBox hb = new HBox();
        hb.setPadding(new Insets(20,10,0,20));
        hb.getChildren().addAll(encryptInfo(), decryptInfo());
        bp.setLeft(hb);


    }


    /*encryptInfo,organises Encrypts paragraph speech on the left side of screen
    parameters: null
    returns: stackpane with the robot, information and header on a rectangle
     */
    private static StackPane encryptInfo(){
        Robot encrypt = Encrypt_Decrypt_Controller.getEncrypt();
        StackPane sp = new StackPane();
        Paragraph encryptPara = Encrypt_Decrypt_Controller.getEncryptPara();
        return info(sp,encrypt,encryptPara);
    }

    /*decryptInfo,organises Decrypts paragraph speech on the right side of screen
    parameters: null
    returns: stackpane with the robot, information and header on a rectangle
     */
    private static StackPane decryptInfo() {
        Robot decrypt =Encrypt_Decrypt_Controller.getDecrypt();
        StackPane sp = new StackPane();
        Paragraph decryptPara = Encrypt_Decrypt_Controller.getDecryptPara();

        return info(sp,decrypt,decryptPara);
    }

    /*info,puts the robot, heading and paragraph into a stackpane
    parameters: sp- stackpane to add to, robot- robot to add, information - text to add
    returns: stackpane with the robot, information and header on a rectangle
     */
    private static StackPane info(StackPane sp, Robot robot, Paragraph information){
        Encrypt_Decrypt_Controller.backgroundSetup(550, 325, sp, robot.getStyle());

        HBox hb = new HBox();
        hb.getStyleClass().add("hbox");


        hb.getChildren().add(robot.getView());
        VBox vb = Encrypt_Decrypt_Controller.organiseText(robot.getTitle(), information,Encrypt_Decrypt_Controller.getTEXTWIDTH());

        hb.getChildren().add(vb);
        sp.getChildren().add(hb);
        return sp;
    }


    /*setUpBottom, setups the bottom of the screen with the interactive element
    parameters: bp- borderpane to add too
    returns: null
     */
    private static void setUpBottom(BorderPane bp){
        StackPane sp = new StackPane();
        sp.getStyleClass().add("stack");
        Encrypt_Decrypt_Controller.backgroundSetup(1180, 300, sp, "rectangle-neither");



        GridPane gp=encryptToDecrypt();
        sp.getChildren().add(gp);

        bp.setBottom(sp);

    }

    /*encryptToDecrypt, organises the gridpane at the bottom
    parameters: null
    returns: gridpane with interactive element
     */
    private static GridPane encryptToDecrypt(){
        GridPane gp= new GridPane();
        gp.getStyleClass().add("grid");
        gp.setPadding(new Insets(20,0,20,100));
        gp.setAlignment(Pos.CENTER_LEFT);

        //each row of the gridpane is setup in a seperate method
        organiseFirstRow(gp);
        organiseEncryptionRow(gp);
        organiseFinalRow(gp);
        instructionRow(gp);

        //adds information about caesar cipher at side
        Para_Text right=new Para_Text(Encrypt_Decrypt_Controller.getCaesar(),230);
        StackPane sp = new StackPane();
        Encrypt_Decrypt_Controller.backgroundSetup(250,275,sp,"rectangle-speech");
        sp.getChildren().add(right.getPara());

        gp.add(sp,5,0);
        GridPane.setRowSpan(sp,7);

        return gp;
    }

    /*organiseFirstRow, setups the first row of the gridpane with headers
    parameters: gp- gridpane to add to
    returns: null
     */
    private static void organiseFirstRow(GridPane gp){
        Title encrypt =new Title(Encrypt_Decrypt_Controller.getEncryption());
        GridPane.setColumnSpan(encrypt.getTitle(), 2);

        Title decrypt =new Title(Encrypt_Decrypt_Controller.getDecryption());
        GridPane.setColumnSpan(decrypt.getTitle(), 2);


        gp.add(encrypt.getTitle(),0,Encrypt_Decrypt_Controller.getTitleRow());
        gp.add(decrypt.getTitle(),2,Encrypt_Decrypt_Controller.getTitleRow());

    }

    /*organiseEncryptionRow, setups the row of arrows and textfields
    parameters: gp- gridpane to add to
    returns: null
     */
    private static void organiseEncryptionRow(GridPane gp){
        int n =Encrypt_Decrypt_Controller.getTextFieldsRow();

        name = new TextField();
        name.setText(Encrypt_Decrypt_Controller.getEnterName());
        GridPane.setHalignment(name, HPos.CENTER);

        Arrow arrow =new Arrow(0,15,120,15);
        gp.add(name,0,n);
        gp.add(arrow.getC(),1,n);

        encrypted = new TextField();
        encrypted.setDisable(true);
        Arrow next = new Arrow(0,15,120,15);

        gp.add(encrypted,2,n);
        gp.add(next.getC(),3,n);

        decrypted = new TextField();
        decrypted.setDisable(true);
        gp.add(decrypted,4,n);

    }

    /*organiseFinalRow,setups the final row of the gridpane with the button and combobox
    parameters: gp- gridpane to add to
    returns: null
     */
    private static void organiseFinalRow(GridPane gp){
        int f = Encrypt_Decrypt_Controller.getFinalRow();
        ComboBox<Integer> comboBox = new ComboBox<>(Encrypt_Decrypt_Controller.getOptions());
        gp.add(comboBox,1,f);
        comboBox.setValue(1);

        Button clicky = new Button(Encrypt_Decrypt_Controller.getClicky());
        clicky.setOnAction(e -> {

            String entry =name.getText();
            if(!entry.equals(Encrypt_Decrypt_Controller.getEnterName())){
                clickyPressed(entry, comboBox.getValue());
            }


        });
        gp.add(clicky,3,f);

        for (Node child:gp.getChildren()){
            GridPane.setHalignment(child,HPos.CENTER);
        }
    }

    /*instructionRow, creates the instructions for the interactive element
    parameters: gp- gridpane to add to
    returns: null
     */
    private static void instructionRow(GridPane gp){
        int g = Encrypt_Decrypt_Controller.getInstructionRow();
        Para_Text pt =new Para_Text(Encrypt_Decrypt_Controller.getInstructions(),800);
        GridPane.setColumnSpan(pt.getPara(),5);
        gp.add(pt.getPara(),0,g);
    }

    /*clickyPressed, method for when the button is clicked, calls the encryption and decryption on the inputted word
    parameters: gp- gridpane to add to
    returns: null
     */
    private static void clickyPressed(String text, int n){
        String crypted = Encrypt_Decrypt_Controller.encryptText(text,n);
        encrypted.setText(crypted);

        String result = Encrypt_Decrypt_Controller.decryptText(crypted,n);
        decrypted.setText(result);


    }

}
