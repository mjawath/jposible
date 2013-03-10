/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fx.window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import org.biz.invoicesystem.entity.master.Staff;
import org.biz.invoicesystem.service.master.StaffService;
import org.fx.window.ui.ProductController;
import org.system.util.FileUtil;

/**
 * FXML Controller class
 *
 * @author d
 */
public class MainWindowFXController implements Initializable {

    Staff staff;
    StaffService staffService;
    @FXML
    AnchorPane ap;
    @FXML
    TabPane tabpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        staff = new Staff();
//        staffService = new StaffService();
        GoTo("ui", "ClientTab.fxml");

    }

    @FXML
    public void onLogin() {
        System.out.println("-------addeding-----");
        // load thead and work there

        // get forms valudes 
        //construct the object
        Staff user = new Staff();
        //check the database
        //

        GoTo("tabk.fxml");


//        FXUtil.executeOnFXTask(this, "execute");
        System.out.println("------------");
        //
        Task task = new Task() {
            final Staff staff = MainWindowFXController.this.staff;

            @Override
            protected Object call() throws Exception {

                return "";
            }
        };
//        new Thread(task).start();

        MyService service = new MyService() {
        };
        Callback cc = new Callback() {
            @Override
            public Object call(Object param) {
                return "";
            }
        };
        service.setOnSucceeded(new EventHandler() {
            @Override
            public void handle(Event event) {
            }
        });
//        service.start();
    }

    public Object[] execute() {
        System.out.println("ececuting event method ");
        return null;
    }

    public void setOBJB(String ss) {
    }

    public void MToV() {
    }

    public Object VToM() {
        Staff staff = new Staff();
//        staffService.set()
        return null;

    }

    public void addTab(Tab tab) {
        tabpane.getTabs().add(tab);
        System.out.println("tab is added....... ");
    }

    public static void bringToFront(TabPane pane, Tab tab) {
        //find the scene and bring it to the front of the window
//        mainScene.

        pane.getSelectionModel().select(tab);
    }

    public void GoTo(Class classes) {
        //loop existing conrollers for any
        //if found bring it to front
        // or create new one

        // loop (class)
        // found = find
        //    or create new ui 
        // 

        Tab tab =findTab(classes,tabpane);
        Node node=null;
        if (tab == null) {
            tab = new Tab();
            try {
                node = FileUtil.getNode(classes);
            } catch (IOException ex) {
                Logger.getLogger(MainWindowFXController.class.getName()).log(Level.SEVERE, null, ex);
            }
            tab.setId(classes.getSimpleName());
            tab.setContent(node);
            tab.setText(classes.getSimpleName());
            tabpane.getTabs().add(tab);
        }
        bringToFront(tabpane, tab);
    }

    public void GoTo(String filePath, String tabname) {
        System.out.println("ading to window ....");
//        mainStage.
//        addto stage
        Tab tab = findTab(tabname, tabpane);
        if (tab == null) {
            tab = new Tab();
            Node node = getNode(filePath, tabname);
            tab.setId(tabname);
            tab.setContent(node);
            tab.setText(tabname);
            tabpane.getTabs().add(tab);
        }
        bringToFront(tabpane, tab);
    }

    public void Goto(String filePath, String tabname, Class classes) {
        Tab tab = findTab(tabname, tabpane);
        if (tab == null) {
            tab = new Tab();
            Node node = getNode(filePath, tabname);
            tab.setId(tabname);
            tab.setContent(node);
            tab.setText(tabname);
            tabpane.getTabs().add(tab);
        }
        bringToFront(tabpane, tab);
    }

    public void GoTo(String tabname) {
//        GoTo(null, tabname);
    }

    private static Node getNode(String filePath, String nodeID) {
        try {
//            URL url = new URL("file:","localhost", filePath);
            Node scene = FileUtil.getNode(MainWindowFXController.class, filePath, nodeID);//(Parent) fxml.load();

            return scene;
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static Tab findTab(String tabID, TabPane tabPane) {
        for (Tab tab : tabPane.getTabs()) {
            if (tabID.equals(tab.getId())) {
                return tab;
            }
        }
        return null;
    }

    private static Tab findTab(Class cls, TabPane tabPane) {
        for (Tab tab : tabPane.getTabs()) {
            if (cls.getSimpleName().equals(tab.getId())) {
                return tab;
            }
        }
        return null;
    }

    @FXML
    public void onProduct() {
        System.out.println("-----");
        GoTo( ProductController.class);
    }
}

class MyService<T> extends Service {

    public T taskAction() {
        return null;

    }
    Callback<T, T> event;

    @Override
    protected Task<T> createTask() {
        Task task = new Task() {
            @Override
            protected T call() throws Exception {

//               event.call(param);
                return taskAction();
            }
        };
        return task;
    }
}