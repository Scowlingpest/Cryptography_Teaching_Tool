package tool;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tool.BuildingBlocks.Views.*;
import tool.CryptoMethods.Views.Diffie_Hellman;
import tool.CryptoMethods.Views.RSA;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Created: 06/10/2015
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
        exitMenuItem.setOnAction(e -> Platform.exit());


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
        primeNo.setOnAction(e -> {
            clearBorderPane(root);
            Prime_Numbers.start(root);
        });

        MenuItem asymmetricVsSymmetric = new MenuItem("Asymmetric vs Symmetric");
        asymmetricVsSymmetric.setOnAction(e -> {
            clearBorderPane(root);
            Asymmetric_vs_Symmetric.start(root);
        });

        MenuItem encryptDecrypt = new MenuItem("Encryption and Decryption");
        encryptDecrypt.setOnAction(e -> {
            clearBorderPane(root);
            Encrypt_Decrypt.start(root);
        });

        MenuItem streamBlock = new MenuItem("Stream vs Block");
        streamBlock.setOnAction(event -> {
            clearBorderPane(root);
            Stream_vs_Block.start(root);
        });

        MenuItem vigenèreCipher = new MenuItem("Vigenère Cipher");
        vigenèreCipher.setOnAction(event -> {
            clearBorderPane(root);
            Vigenère_Cipher.start(root);
        });

        MenuItem generator = new MenuItem("Generator");
        generator.setOnAction(event -> {
            clearBorderPane(root);
            Generator.start(root);
        });

        blocks.getItems().addAll(primeNo,asymmetricVsSymmetric,encryptDecrypt,streamBlock,vigenèreCipher,
                generator);
    }

    public void setUpCryptographyMenu(Menu methods, final BorderPane root){
        MenuItem rsa = new MenuItem("RSA");
        rsa.setOnAction(e -> {
            clearBorderPane(root);
            RSA.start(root);
        });
        MenuItem dh = new MenuItem("Diffie Hellman");
        dh.setOnAction(event -> {
            clearBorderPane(root);
            Diffie_Hellman.start(root);
        });

        methods.getItems().addAll(rsa,dh);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
