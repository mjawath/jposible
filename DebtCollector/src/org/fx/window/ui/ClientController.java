/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fx.window.ui;

import java.net.URL;
import java.util.ResourceBundle;
import org.fx.window.TabController;

/**
 *
 * @author d
 */
public class ClientController  extends TabController{

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        printTracer("***********client contrller initialised");
    }
    
    
}
