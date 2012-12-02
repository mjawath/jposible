/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package research.ui.demo;

import java.util.List;
import javax.swing.JFrame;
import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
import org.biz.erp.inventory.ui.BarCodeCreatorUI;
import org.biz.invoicesystem.entity.master.Customer;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.transactions.SalesInvoiceLineItem;

/**
 *
 * @author d
 */
public class TestMain {

    public static void main(String[] args) {

        JexlEngine jexl = new JexlEngine();

        jexl.setCache(512);
        jexl.setLenient(false);
        jexl.setSilent(false);


        Expression e = jexl.createExpression("cus.code()");
        Customer cus = new Customer();
        SalesInvoiceLineItem item = new SalesInvoiceLineItem();
        Item i = new Item();
        item.setItem(i);
        JexlContext context = new MapContext();
        context.set("cus", item);
        JexlEngine ex = new JexlEngine();
        System.out.println(ex.getProperty(item, "item.carton"));

//        System.out.println( e.evaluate(context));
// cus.setCode("asshole");

        /*java beans expresions
        Expression e =new Expression(cus,"getCode",new Object[]{});
        try {
        System.out.println("value "+e.getValue());
        } catch (Exception ex) {
        Logger.getLogger(TestMain.class.getName()).log(Level.SEVERE, null, ex);
        }
         */

    }

    public void bardoe() {
        JFrame jf = new JFrame();
        jf.setSize(600, 600);

        jf.add(new BarCodeCreatorUI());

        jf.setVisible(true);


    }
    
    List xx;
    void setList( List xx){
    this.xx=xx;
    }
    public void createList(){
    xx.add(new Item());
    xx.add(new Item());
    xx.add(new Item());
    xx.add(new Item());
    xx.add(new Item());
    xx.add(new Item());
    xx.add(new Item());
    xx.add(new Item());
    xx.add(new Item());
    xx.add(new Item());
    xx.add(new Item());
    xx.add(new Item());
    xx.add(new Item());
    xx.add(new Item());
    xx.add(new Item());
    xx.add(new Item());
    xx.add(new Item());
    xx.add(new Item());
    }
}
