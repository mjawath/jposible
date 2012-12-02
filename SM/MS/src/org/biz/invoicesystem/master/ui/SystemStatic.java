package org.biz.invoicesystem.master.ui;

import org.components.util.Sessions;
import org.components.windows.MainWindow;

public abstract class SystemStatic {

    public final static String ITEM_IMAGE_PATH = "images//item//";
    public final static int GRID_LIST_SIZE = 50;

    static {
    Sessions.create();
    }
    
    public abstract void keyListeners();

    public abstract Object uiToEntity(Object i);

    //generic class in the level of method not in the level of class 
    //two models in generic 
    //
    
    public   static  <T,E,X>  T entity2Ui(E i,X ii){
    return null;}

    public static MainWindow getMainWindow() {
        MainWindow jf = (MainWindow) Sessions.getObj("mainui");
        return jf;
    }
}