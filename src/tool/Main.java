package tool;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tool.BuildingBlocks.Views.*;
import tool.CryptoMethods.Views.Diffie_Hellman;
import tool.CryptoMethods.Views.El_Gamal;
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

        Instructions.start(borderPane,false);


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
        exitMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
        exitMenuItem.setOnAction(e -> Platform.exit());

        MenuItem instructions = new MenuItem("Instructions");
        instructions.setAccelerator(KeyCombination.keyCombination("Ctrl+I"));
        instructions.setOnAction(event->{
            clearBorderPane(root);
            Instructions.start(root, true);
        });


        menuFile.getItems().addAll(instructions,exitMenuItem);

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
        primeNo.setAccelerator(KeyCombination.keyCombination("Ctrl+P"));
        primeNo.setOnAction(e -> {
            clearBorderPane(root);
            Prime_Numbers.start(root);
        });

        MenuItem asymmetricVsSymmetric = new MenuItem("Asymmetric vs Symmetric");
        asymmetricVsSymmetric.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        asymmetricVsSymmetric.setOnAction(e -> {
            clearBorderPane(root);
            Asymmetric_vs_Symmetric.start(root);
        });

        MenuItem encryptDecrypt = new MenuItem("Encryption and Decryption");
        encryptDecrypt.setAccelerator(KeyCombination.keyCombination("Ctrl+D"));
        encryptDecrypt.setOnAction(e -> {
            clearBorderPane(root);
            Encrypt_Decrypt.start(root);
        });

        MenuItem streamBlock = new MenuItem("Stream vs Block");
        streamBlock.setAccelerator(KeyCombination.keyCombination("Ctrl+B"));
        streamBlock.setOnAction(event -> {
            clearBorderPane(root);
            Stream_vs_Block.start(root);
        });

        MenuItem vigenèreCipher = new MenuItem("Vigenère Cipher");
        vigenèreCipher.setAccelerator(KeyCombination.keyCombination("Ctrl+V"));
        vigenèreCipher.setOnAction(event -> {
            clearBorderPane(root);
            Vigenère_Cipher.start(root);
        });

        MenuItem generator = new MenuItem("Generator");
        generator.setAccelerator(KeyCombination.keyCombination("Ctrl+G"));
        generator.setOnAction(event -> {
            clearBorderPane(root);
            Generator.start(root);
        });

        MenuItem invMod = new MenuItem("Inverted Modulus");
        invMod.setAccelerator(KeyCombination.keyCombination("Ctrl+M"));
        invMod.setOnAction(event->{
            clearBorderPane(root);
            Inv_Mod.start(root);
        });

        Menu maths = new Menu("Mathematics");
        maths.getItems().addAll(primeNo,generator,invMod);
        blocks.getItems().addAll(maths,asymmetricVsSymmetric,encryptDecrypt,streamBlock,vigenèreCipher);
    }

    public void setUpCryptographyMenu(Menu methods, final BorderPane root){
        MenuItem rsa = new MenuItem("RSA");
        rsa.setAccelerator(KeyCombination.keyCombination("Ctrl+R"));
        rsa.setOnAction(e -> {
            clearBorderPane(root);
            RSA.start(root);
        });
        MenuItem dh = new MenuItem("Diffie Hellman");
        dh.setAccelerator((KeyCombination.keyCombination("Ctrl+H")));
        dh.setOnAction(event -> {
            clearBorderPane(root);
            Diffie_Hellman.start(root);
        });

        MenuItem eg = new MenuItem("El Gamal");
        eg.setAccelerator((KeyCombination.keyCombination("Ctrl+E")));
        eg.setOnAction(event->{
            clearBorderPane(root);
            El_Gamal.start(root);
        });

        methods.getItems().addAll(rsa,dh,eg);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
