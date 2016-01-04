package tool.BuildingBlocks.Views;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Callback;
import tool.BuildingBlocks.Controllers.Instructions_Controller;
import tool.CryptoMethods.Views.AnimationMethods;
import tool.Graphics.Robot;
import tool.Graphics.Speechbubble;
import tool.Graphics.Titled_Information;
import tool.Models.Header_Paragraph;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 13/12/2015.
 */
public class Instructions {

    public static void start(BorderPane bp,Boolean button){
        Pane p = new Pane();
        p.setPrefSize(1150,650);

        Robot encrypt = Instructions_Controller.getEncrypt();
        Robot decrypt = Instructions_Controller.getDecrypt();

        AnimationMethods.placeRobots(decrypt, p, 1000, 175);
        AnimationMethods.placeRobots(encrypt, p, 50, 175);

        encrypt.setImageWidth(175);decrypt.setImageWidth(175);

        if (button==false){
            setupBubbles(p);
            setupText(button,p);
            bp.setCenter(p);
        }
        else {
            paginationSetup(bp);
        }


    }

   private static void setupBubbles(Pane p){
        Speechbubble helloEncrypt=new Speechbubble("bl",
                Instructions_Controller.getHelloEncrypt(), Instructions_Controller.getBubbleSize(),
                Instructions_Controller.getX1(),Instructions_Controller.getY1());
       /* Speechbubble topControls=new Speechbubble("tl",
                Instructions_Controller.getTopControls(), Instructions_Controller.getBubbleSize(),
                Instructions_Controller.getX1()+100,Instructions_Controller.getY2());
        Speechbubble bottomControls=new Speechbubble("tc",
                Instructions_Controller.getBottomControls(), Instructions_Controller.getBubbleSize()+20,
                0,Instructions_Controller.getY3());
*/
        Speechbubble helloDecrypt=new Speechbubble("br",
                Instructions_Controller.getHelloDecrypt(), Instructions_Controller.getBubbleSize(),
                Instructions_Controller.getX2(),Instructions_Controller.getY1());
       /*
        Speechbubble buildingBlocks=new Speechbubble("tr",
                Instructions_Controller.getBuildingBlocks(), Instructions_Controller.getBubbleSize(),
                Instructions_Controller.getX2()-100,Instructions_Controller.getY2());
        Speechbubble interactive=new Speechbubble("tc",
                Instructions_Controller.getInteractive(), Instructions_Controller.getBubbleSize(),
                900,Instructions_Controller.getY3());
                */


        p.getChildren().addAll(helloEncrypt.getSp(), helloDecrypt.getSp());

    }

    private static void setupText(Boolean button, Pane p){
        Rectangle rect = new Rectangle(375,250,500,100);
        rect.getStyleClass().add("rectangle-neither");

        Text intro = new Text("Cryptography Teaching \n Tool");
        intro.setLayoutX(400);intro.setLayoutY(300);
        intro.getStyleClass().add("text-welcome");

        p.getChildren().addAll(rect,intro);
    }


    private static void paginationSetup(BorderPane bp){
        Pagination pagination = new Pagination(5);
        pagination.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer pageIndex) {
                return createPage(pageIndex);
            }
        });
        bp.setCenter(pagination);


    }

    private static Node createPage(Integer pageIndex) {
        StackPane page = new StackPane();
        Header_Paragraph hp = new Header_Paragraph(Instructions_Controller.getPageTitlebyIndex(pageIndex),
                Instructions_Controller.getPageContentsbyIndex(pageIndex));
        Titled_Information temp = new Titled_Information(hp,1050);

        temp.getVb().getStyleClass().clear();
        temp.getVb().setPadding(new Insets(20,20,10,80));
        temp.getVb().setSpacing(25);

        Instructions_Controller.backgroundSetup(1100,600,page,"rectangle-speech");

        page.getChildren().add(temp.getVb());
        return page;
    }

}
