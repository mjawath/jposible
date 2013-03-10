/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fx.window.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import jfxtras.labs.scene.control.BeanPathAdapter;
import org.dept.model.Client;
import org.fx.window.TabController;

/**
 *
 * @author d
 */
public class ClientController extends TabController {

    @FXML
    TableView<Client> tableView;
    @FXML
    TextField txtname;
    @FXML
    TextField textid;
    @FXML
    Button button;
    Client client;
    BeanPathAdapter<Client> adapter ;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        client = new Client("asdf", "name x", "address");
        System.out.println("***********client contrller initialised");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                printTracer(event);
                addToTable();
                
            }
        });

        adapter = new BeanPathAdapter<>(client);
        adapter.bindBidirectional("name",txtname.textProperty());
        adapter.bindBidirectional("id", textid.textProperty());

        ObservableList<TableColumn<Client, ?>> columns = tableView.getColumns();

        ((TableColumn) columns.get(0)).setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
        ((TableColumn) columns.get(1)).setCellValueFactory(new PropertyValueFactory<Client, String>("id"));


//        setOBJ(client);
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Client>() {
            @Override
            public void changed(ObservableValue<? extends Client> observable, Client oldValue, Client newValue) {
                setSeletedTableItem();
                
            }
        });
//        tableView.getItems().add(client);
        
        

    }

    public void setOBJ(Client client) {
//        txtname.setText(client.getName());
//        textid.setText(client.getId());

    }

    public Client getOBJ(Client cl) {
        cl.setName(txtname.getText());
        cl.setId(textid.getText());
        cl.setId(textid.getText());
        return cl;
    }

    public void addToTable() {

//            client = getOBJ(new Client());
            tableView.getItems().add(client);
            client = new Client();
            adapter.setBean(client);
                    

        printTracer(" obj " + client + "added to table , Table contains #" + tableView.getItems().size());
    }

//    public void updateTable(){
//     Client obj= (Client) getOBJ();
//     tableView.getItems().add(obj);
//     
//     
//     printTracer(" obj "+obj +  "added to table , Table contains #"+ tableView.getItems().size());
//    
//    }
    public void setSeletedTableItem() {
        Client client = tableView.getSelectionModel().getSelectedItem();
//        setOBJ(client);
        adapter.setBean(client);

    }
}
