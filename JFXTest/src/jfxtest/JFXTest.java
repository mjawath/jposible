/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jfxtest;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author d
 */
public class JFXTest extends JFrame {
    
    private static final int JFXPANEL_WIDTH_INT = 300;
    private static final int JFXPANEL_HEIGHT_INT = 250;
    private static JFXPanel fxContainer;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (Exception e) {
                }
                
                
                JFXTest applet = new JFXTest();
                applet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                
                applet.init();
                
                applet.add(fxContainer);
                
                applet.pack();
                applet.setLocationRelativeTo(null);
                applet.setVisible(true);
                
                
            }
        });
    }
    
    
    public void init() {
        fxContainer = new JFXPanel();
        fxContainer.setPreferredSize(new Dimension(JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT));
        add(fxContainer, BorderLayout.CENTER);
        // create JavaFX scene
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                createScene();
            }
        });
    }
    
    private void createScene() {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
       Parent root=null;
        try {
            root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
       Scene scene = new Scene(root); //scene.getStylesheets().set();
//        root.getChildren().add(btn);
        fxContainer.setScene(scene);
    }
}
