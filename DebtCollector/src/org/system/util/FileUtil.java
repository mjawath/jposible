/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.system.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import org.fx.window.MainWindowFXController;

/**
 *
 * @author d
 */
public class FileUtil {
    
    public static String getFilePath(Class classType, String packageName){
        return "";
    } 
    
    public static String fullPackagePath(Class classType ){
                        
        String userDirPath= System.getProperty("user.dir");
//        classType.getName()                       

        return "";
    }
    
    /**
     * 
     * @param classType
     * @param packageName name of the package eg: org.ui.mywindow
     * @param fileName file name eg : table.css
     * @return 
     */
    public static URL getURLForResources(Class classType,String packageName,String fileName){
    
          String path = null;
        if (packageName == null || packageName.trim().isEmpty()) {
            path = fileName;//  watch out for  /   \ mismetch
        } else {
            packageName=packageName.replace(".", "/");
            path = packageName + "/"+ fileName;
        }
        System.out.println(path);
        URL url= classType.getResource(path);
        return url;
    }
    
    public static Node getNode(Class classType, String packageName, String fileName) throws IOException {
        
        FXMLLoader fxml =  new FXMLLoader(getURLForResources(classType, packageName, fileName));
        Node node = (Parent) fxml.load();
        return node;
    }
}
