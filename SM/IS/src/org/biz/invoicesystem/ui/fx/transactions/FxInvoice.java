/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.ui.fx.transactions;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import jfxtras.labs.scene.control.BeanPathAdapter;
import org.biz.invoicesystem.entity.master.Customer;
import org.biz.invoicesystem.ui.fx.transactions.vm.CustomerFX;
import org.components.windows.TabPanelUI;

/**
 *
 * @author jawath
 */
public class FxInvoice extends TabPanelUI implements Initializable {

    SimpleObjectProperty<CustomerFX> cfx = new  SimpleObjectProperty<>() ;            
    CustomerFX customer = new CustomerFX();
    Customer cust=new Customer();
    /**
     * Creates new form FxInvoice
     */
    Scene scene = null;
    Parent root = null;

    @FXML TextField txtid; 
    @FXML TextField txtCode; 
    @FXML TableView<CustomerFX> tableView;
    @FXML TableColumn<CustomerFX, String> idColumn;
    @FXML TableColumn<CustomerFX, String> codeColumn;
    @FXML Button btn;
    public FxInvoice() {
        initComponents();
      
//column.setCellFactory(new  Callback<TableColumn<Object, Object>, TableCell<Object, Object>>);
//column.setOnEditStart(null);
//        root.getChildren().add(btn);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTabName() {
        return "";
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("initi");
        ObservableList ob=tableView.getItems();
        ob.addAll(getList());
        idColumn.setCellValueFactory(new PropertyValueFactory<CustomerFX ,String>("id"));
        codeColumn.setCellValueFactory(new PropertyValueFactory<CustomerFX ,String>("code"));
        System.out.println("end int");
        
//        txtid.textProperty().bind(cfx.get().id);//tableView.getSelectionModel().getSelectedItem().id);
//        txtCode.textProperty().bind(cfx.get().code);//tableView.getSelectionModel().getSelectedItem().code);
        
        
        //binding tests
        //1
                cfx.bind(tableView.getSelectionModel().selectedItemProperty()); //works finebut onw way binding
        
//        BeanPathAdapter
     /*
        txtid.textProperty().bindBidirectional(customer.id);
        txtCode.textProperty().bindBidirectional(customer.code);
        customer.setId("dd");
        customer.setCode("code");
    
    */ //direct mapping
        cust.setId("dasf");
        cust.setCode("yyy");
        BeanPathAdapter<Customer> adapter = new BeanPathAdapter<>(cust);
        
        adapter.bindBidirectional("code", txtCode.textProperty());
        adapter.bindBidirectional("id", txtid.textProperty());
        
    
    }

   
    
    public ObservableList getList() {

        ObservableList<CustomerFX> customers = FXCollections.observableArrayList();
        for (int i = 0; i < 100; i++) {
            CustomerFX cus = new CustomerFX();
            cus.setId("" + i);
            cus.setCode("name "+i);
            customers.add(cus);
        }
        return customers;
    }
    @FXML
    public void btnAction(){
//        cust=new Customer();
        System.out.println("id "+cust.getId());
        cust.setId("xxxxxxxx");
    }
    
}


/**
 * 
 * this is a test app
 * pupose is to test the behaviour of the tableview
 * ui design 
 * 
 * 
 * 
 * 
 * 
 */