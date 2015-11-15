package tool;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tool.BuildingBlocks.Views.Asymmetric_vs_Symmetric;
import tool.BuildingBlocks.Views.Encrypt_Decrypt;
import tool.BuildingBlocks.Views.Prime_Numbers;
import tool.BuildingBlocks.Views.Stream_vs_Block;
import tool.CryptoMethods.Views.Diffie_Hellman;
import tool.CryptoMethods.Views.RSA;

/** Author : Phillipa Russell
 *  Created: 06/10/2015
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        primaryStage.setTitle("Cryptography Teaching Tool");

        BorderPane borderPane =setupMenu();
        borderPane.setBackground(new Background(new BackgroundFill(Color.web("#ebebeb"),CornerRadii.EMPTY,Insets.EMPTY)));
        Scene scene = new Scene(borderPane, 1200, 700);
        scene.getStylesheets().add("tool/Files/Style.css");


        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public BorderPane setupMenu(){
        final BorderPane root = new BorderPane();
        VBox topContainer = new VBox();
        MenuBar menuBar = new MenuBar();


        topContainer.getChildren().add(menuBar);

        root.setTop(topContainer);

        Menu menuFile = new Menu("File");
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                Platform.exit();
            }
        });


        menuFile.getItems().add(exitMenuItem);

        Menu menuBuildingBlocks = new Menu("Building Blocks");
        setUpBuildingBlocks(menuBuildingBlocks,root);



        Menu menuMethods = new Menu("Cryptography methods");
        setUpCryptographyMenu(menuMethods,root);

        menuBar.getMenus().addAll(menuFile,menuBuildingBlocks,menuMethods);

        return root;

    }


    public void clearBorderPane(BorderPane bp){
        bp.setLeft(null);
        bp.setRight(null);
        bp.setCenter(null);
        bp.setBottom(null);
    }

    public void setUpBuildingBlocks(Menu blocks, final BorderPane root){
        MenuItem primeNo = new MenuItem("Prime Numbers");
        primeNo.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                clearBorderPane(root);
                Prime_Numbers.start(root);
            }
        });

        MenuItem asymmetricVsSymmetric = new MenuItem("Asymmetric vs Symmetric");
        asymmetricVsSymmetric.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                clearBorderPane(root);
                Asymmetric_vs_Symmetric.start(root);
            }
        });

        MenuItem encryptDecrypt = new MenuItem("Encryption and Decryption");
        encryptDecrypt.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                clearBorderPane(root);
                Encrypt_Decrypt.start(root);
            }
        });

        MenuItem streamBlock = new MenuItem("Stream vs Block");
        streamBlock.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clearBorderPane(root);
                Stream_vs_Block.start(root);
            }
        });

        blocks.getItems().addAll(primeNo,asymmetricVsSymmetric,encryptDecrypt,streamBlock);
    }

    public void setUpCryptographyMenu(Menu methods, final BorderPane root){
        MenuItem rsa = new MenuItem("RSA");
        rsa.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                clearBorderPane(root);
                RSA.start(root);
            }
        });
        MenuItem dh = new MenuItem("Diffie Hellman");
        dh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clearBorderPane(root);
                Diffie_Hellman.start(root);
            }
        });

        methods.getItems().addAll(rsa,dh);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
