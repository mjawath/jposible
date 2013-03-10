/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.system.util;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import org.fx.window.ui.ClientController;

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
    public static URL getURLForResources(Class classType, String packageName, String fileName) {

        String path = null;
        if (packageName == null || packageName.trim().isEmpty()) {
            path = fileName;//  watch out for  /   \ mismetch
        } else {
            packageName = packageName.replace(".", "/");
            path = packageName + "/" + fileName;
        }
        URL url = classType.getResource(path);
        return url;
    }
    
    public static URL getURLForResources(Class classType) {
        String tr=classType.getSimpleName();
        tr=tr.replace("Controller", "Tab.fxml");
        URL url = classType.getResource(tr);
        return url;
    }
    
    public static Node getNode(Class classType, String packageName, String fileName) throws IOException {
        ClientController controller=new ClientController();
        URL url=getURLForResources(classType, packageName, fileName);
        FXMLLoader fxml =  new FXMLLoader(url);        
        fxml.setController(controller);
        Node node = (Parent) fxml.load();
        return node;
    }
    
    public static Node getNode(Class classType) throws IOException {
        URL url=getURLForResources(classType);
        FXMLLoader fxml =  new FXMLLoader(url);        
        try {
            fxml.setController(classType.newInstance());
        } catch (InstantiationException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        Node node = (Parent) fxml.load();
        return node;
    }

    
    
    public static String getOnlyFileName(String filenameWithExtetion){
                int index= filenameWithExtetion.lastIndexOf(".");
               String filename= filenameWithExtetion.substring(index, filenameWithExtetion.length());
               return filename;
    }
}
