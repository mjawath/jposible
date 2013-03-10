/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fx.window;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * @author d
 */
public class SimpleCrudController extends TabController{
     
    @FXML
    Button btnSave;
    @FXML
    Button btnClear;    
    @FXML
    Button btnNew;
    @FXML
    Button btnCopy;
    @FXML
    Button btnDelete;

    @Override
    public void init(URL location, ResourceBundle resources) {
        super.init(location, resources);
    }

       
    
    public void addItem(){
    
    
    }
    
    @FXML
    public  void save(){
        System.out.println(" ############### saved ---------");
    }
    
    @FXML
    public  void delete(){
        System.out.println("-----------Deleted----------------");
    }
    
    @FXML
    public  void copy(){
        System.out.println("-----------Cloned----------------");
    }
    
    @FXML
    public  void clear(){
        System.out.println("-----------Cleared ----------------");
    }
    
    
    @FXML
    public  void newEntity(){
    
    }
    
    public Object getEntity(){
    return null;
    }
    
    public void setEntity(){    
    }
    
    public void setObjectToUI(){
    
    }
    
    public void setUIToObject(){
        
    }

}
