/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fx.window;

import java.net.URL;
import java.util.ResourceBundle;
import org.system.util.Tracer;

/**
 *
 * @author d
 */
public class TabController implements ITabController{

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
    }
    
    public void printTracer(String message ){
    Tracer.printTracer(message);
    }
    
}
