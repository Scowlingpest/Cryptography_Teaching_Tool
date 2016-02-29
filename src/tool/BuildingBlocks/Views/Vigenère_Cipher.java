package tool.BuildingBlocks.Views;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import tool.BuildingBlocks.Controllers.Vigenère_Cipher_Controller;
import tool.Graphics.Para_Text;
import tool.Graphics.Robot;
import tool.Graphics.Title;
import tool.Models.Header;
import tool.Models.Paragraph;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 15/11/2015.
 */
public class Vigenère_Cipher {

    //start method for the block
    public static void start(BorderPane bp){
        setUpLeft(bp);
        setUpRight(bp);
    }

    //sets up the left hand side of the screen with the robots
    public static void setUpLeft(BorderPane bp){
        Robot encrypt = Vigenère_Cipher_Controller.getEncrypt();
        Robot decrypt = Vigenère_Cipher_Controller.getDecrypt();

        VBox vb = new VBox();
        StackPane encryptSays =setUpInformation(encrypt,Vigenère_Cipher_Controller.getInformation(),250);
        StackPane decryptSays =setUpInformation(decrypt,Vigenère_Cipher_Controller.getHowItWorks(),375);

        vb.getChildren().addAll(encryptSays, decryptSays);
        vb.setSpacing(5);
        vb.setPadding(new Insets(10,0,0,0));

        bp.setLeft(vb);


    }

    //creates the information on screen for each robot
    public static StackPane setUpInformation(Robot robot, Paragraph p,int size){
        StackPane sp =new StackPane();
        Vigenère_Cipher_Controller.backgroundSetup(625,size,sp,robot.getStyle());


        HBox hb = new HBox();
        hb.getChildren().add(robot.getView());

        VBox vb = Vigenère_Cipher_Controller.organiseText(robot.getTitle(),
                p, Vigenère_Cipher_Controller.getTEXTWIDTH());

        hb.setPadding(new Insets(10,5,0,30));

        hb.getChildren().add(vb);
        sp.getChildren().add(hb);
        return sp;
    }

    //sets up the right hand side of the screen, adds the title, graph and information
    public static void setUpRight(BorderPane bp){
        StackPane sp = new StackPane();
        Vigenère_Cipher_Controller.backgroundSetup(530, 650, sp, "rectangle-neither");

        Title graphHeader = new Title(Vigenère_Cipher_Controller.getGraph());
        ImageView graph = graphSetUp();

        VBox vb = new VBox();
        vb.getChildren().addAll(graphHeader.getTitle(),graph);
        vb.setPadding(new Insets(30,20,10,20));

        informationSetUp(vb);

        sp.getChildren().addAll(vb);
        sp.setPadding(new Insets(0,20,0,0));
        bp.setRight(sp);
    }


    //creates the graph image view and applies the settings
    private static ImageView graphSetUp(){
        ImageView graph = new ImageView(new Image("Files/Images/VigenèreGraph.png"));
        graph.setPreserveRatio(true);
        graph.setFitWidth(400);
        graph.setSmooth(true);
        graph.setCache(true);
        return graph;
    }

    //creates the information under the graph
    private static void informationSetUp(VBox vb){
        Title key= new Title(new Header("Key:\t\t\t"+Vigenère_Cipher_Controller.getKey()));
        Title message = new Title(new Header("Message:\t"+Vigenère_Cipher_Controller.getMessage()));
        Title noSpaces = new Title(new Header("No spaces:\t"+Vigenère_Cipher_Controller.getNoSpaces()));
        Title keyword = new Title(new Header("Keyword:\t"+Vigenère_Cipher_Controller.getKeyword()));
        Title encrypted = new Title(new Header("Encrypted:\t"+Vigenère_Cipher_Controller.getEncrypted()));
        Para_Text tryThisOut=new Para_Text(Vigenère_Cipher_Controller.getNotice(),450);

        vb.getChildren().addAll(key.getTitle(),message.getTitle(),noSpaces.getTitle(),keyword.getTitle(),encrypted.getTitle(),
                tryThisOut.getPara());
    }
}
