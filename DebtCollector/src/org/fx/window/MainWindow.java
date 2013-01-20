/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fx.window;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author d
 */
public class MainWindow extends Application {
   
    public final String windowFXML="MainWindow.fxml";
    private static  Stage mainStage;
    private static  Scene mainScene;
    private static MainWindowFXController maincont;
    private static HashMap<String, Tab> viewMap=new HashMap<>();//container of tab pages
    
    @Override
    public void start(Stage stage) throws Exception {
        //fxml loader
        FXMLLoader fxmll =  new FXMLLoader(getClass().getResource(windowFXML));
//        fxmll.setController(mycontroller);       
//        fxmll.setRoot(stage);        
        Parent root = (Parent) fxmll.load();
        MainWindowFXController obj= fxmll.getController();
        maincont = obj;
        Scene scene = new Scene(root);        
        stage.setScene(scene);
        mainStage = stage;
        mainScene=scene;
        
        AnchorPane ap= (AnchorPane)root;
        
        stage.show();
    }
    

    public static void main(String[] args) {
        
        
        launch(args);
        System.out.println("closed..");
    }
    



  
}
