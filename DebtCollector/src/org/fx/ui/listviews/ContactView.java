/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fx.ui.listviews;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.dept.model.Client;
import org.dept.model.ClientService;

/**
 *
 * @author d
 */
public class ContactView implements Initializable {

    ClientService service;
    @FXML
    TextField contactName;
    @FXML
    TableView<Client> tvContacts;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tvContacts.setItems(onSearch());
        
    }

    public void getContacts() {
        
    }

    public ObservableList onSearch() {
        String name = contactName.getText();
        ObservableList<Client> ob = FXCollections.observableArrayList();
        for (int i = 0; i < 100; i++) {
                    Client contact = new Client(""+ i,""+i,""+i);
            ob.add(contact);
        }
        ob.addAll(service.getDao().getAll());
        return ob;
    }
}
