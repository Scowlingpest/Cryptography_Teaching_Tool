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
import tool.CryptoMethods.Views.AES;
import tool.CryptoMethods.Views.Diffie_Hellman;
import tool.CryptoMethods.Views.El_Gamal;
import tool.CryptoMethods.Views.RSA;
import tool.Models.MonitoringMap;

import java.io.*;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Created: 06/10/2015
 */
public class Main extends Application {
    // monitoring map for recording what users click
    MonitoringMap monitor=new MonitoringMap();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        primaryStage.setTitle("Cryptography Teaching Tool");

        BorderPane borderPane =setupMenu();
        borderPane.setBackground(new Background(new BackgroundFill(Color.web("#ebebeb"),CornerRadii.EMPTY,Insets.EMPTY)));
        Scene scene = new Scene(borderPane, 1200, 700);
        scene.getStylesheets().add("Files/Style.css");

        Instructions.start(borderPane,false);


        primaryStage.setScene(scene);
        primaryStage.show();

    }

    //this setups the borderpane and the main menu
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
            monitor.incrementValue("PN");
            Prime_Numbers.start(root);
        });

        MenuItem asymmetricVsSymmetric = new MenuItem("Asymmetric vs Symmetric");
        asymmetricVsSymmetric.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        asymmetricVsSymmetric.setOnAction(e -> {
            clearBorderPane(root);
            monitor.incrementValue("AS");
            Asymmetric_vs_Symmetric.start(root);
        });

        MenuItem encryptDecrypt = new MenuItem("Encryption and Decryption");
        encryptDecrypt.setAccelerator(KeyCombination.keyCombination("Ctrl+D"));
        encryptDecrypt.setOnAction(e -> {
            clearBorderPane(root);
            monitor.incrementValue("ED");
            Encrypt_Decrypt.start(root);
        });

        MenuItem streamBlock = new MenuItem("Stream vs Block");
        streamBlock.setAccelerator(KeyCombination.keyCombination("Ctrl+B"));
        streamBlock.setOnAction(event -> {
            clearBorderPane(root);
            monitor.incrementValue("SB");
            Stream_vs_Block.start(root);
        });

        MenuItem vigenèreCipher = new MenuItem("Vigenère Cipher");
        vigenèreCipher.setAccelerator(KeyCombination.keyCombination("Ctrl+V"));
        vigenèreCipher.setOnAction(event -> {
            clearBorderPane(root);
            monitor.incrementValue("VC");
            Vigenère_Cipher.start(root);
        });

        MenuItem diffTrans = new MenuItem("Diffusion vs Transformation");
        diffTrans.setAccelerator(KeyCombination.keyCombination("Ctrl+T"));
        diffTrans.setOnAction(event->{
            clearBorderPane(root);
            monitor.incrementValue("DT");
            Diffusion_vs_Transformation.start(root);
        });

        MenuItem generator = new MenuItem("Generator");
        generator.setAccelerator(KeyCombination.keyCombination("Ctrl+G"));
        generator.setOnAction(event -> {
            clearBorderPane(root);
            monitor.incrementValue("GR");
            Generator.start(root);
        });

        MenuItem invMod = new MenuItem("Inverted Modulus");
        invMod.setAccelerator(KeyCombination.keyCombination("Ctrl+M"));
        invMod.setOnAction(event->{
            clearBorderPane(root);
            monitor.incrementValue("IM");
            Inv_Mod.start(root);
        });

        Menu maths = new Menu("Mathematics");
        maths.getItems().addAll(primeNo,generator,invMod);
        blocks.getItems().addAll(maths,asymmetricVsSymmetric,encryptDecrypt,streamBlock,vigenèreCipher,diffTrans);
    }

    public void setUpCryptographyMenu(Menu methods, final BorderPane root){
        MenuItem rsa = new MenuItem("RSA");
        rsa.setAccelerator(KeyCombination.keyCombination("Ctrl+R"));
        rsa.setOnAction(e -> {
            clearBorderPane(root);
            RSA.start(root,monitor);
        });
        MenuItem dh = new MenuItem("Diffie Hellman");
        dh.setAccelerator((KeyCombination.keyCombination("Ctrl+H")));
        dh.setOnAction(event -> {
            clearBorderPane(root);
            Diffie_Hellman.start(root,monitor);
        });

        MenuItem eg = new MenuItem("El Gamal");
        eg.setAccelerator((KeyCombination.keyCombination("Ctrl+E")));
        eg.setOnAction(event->{
            clearBorderPane(root);
            El_Gamal.start(root,monitor);
        });
        MenuItem aes = new MenuItem("AES");
        aes.setAccelerator(KeyCombination.keyCombination("Ctrl+A"));
        aes.setOnAction(e -> {
            clearBorderPane(root);
            AES.start(root,monitor);
        });

        methods.getItems().addAll(rsa,dh,eg,aes);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop(){
        try{
            String path = System.getProperty("user.home") + File.separator + "Documents";
            path += File.separator + "Cryptography Teaching Tool";
            File customDir = new File(path);
            path+=File.separator+"Monitoring.csv";

            if(customDir.exists()){
                BufferedWriter output = new BufferedWriter(new FileWriter(new File(path), true));
                output.write(monitor.lineGenerate()+"\n");
                output.close();
            }
            else {
                File file = new File(path);
                file.getParentFile().mkdirs();
                file.createNewFile();

                InputStream url =getClass().getResourceAsStream("/Files/Monitoring.csv");
                BufferedReader reader =new BufferedReader(new InputStreamReader(url));
                BufferedWriter output = new BufferedWriter(new FileWriter(file));
                String line = reader.readLine();
                while(line!=null){
                    output.write(line);
                    line=reader.readLine();
                }
                output.write(monitor.lineGenerate()+"\n");
                output.close();
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
