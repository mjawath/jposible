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
import org.biz.erp.inventory.dao.InventoryJournalDAO;
import org.biz.erp.inventory.ui.BarCodeCreatorUI;
import org.biz.invoicesystem.dao.master.ItemDAO;
import org.biz.invoicesystem.entity.master.Customer;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.master.ItemVariation;
import org.biz.invoicesystem.entity.transactions.SalesInvoiceLineItem;

/**
 *
 * @author d
 */
public class TestMain {

    public static void main(String[] args) {
////  first set of problem      
//        InventoryJournalDAO id=new InventoryJournalDAO();
//        String sel = " select   i, ijls.uom , sum(ijls.qty)  "
//                     + " from InventoryJournal  c left join c.lines ijls left join  ijls.item i   ";
//        String qr = " group by  i ,ijls.uom ";
//        String wh = " where i.code like 'f%'";
    
        
          
        InventoryJournalDAO id=new InventoryJournalDAO();
        String sel = " select   i, c.uom , sum(c.qty)  "
                     + " from InventoryJournal ij  join InventoryJournalLine  c join  left join  c.item i   ";
        String qr = " group by  i ,ijls.uom ";
        String wh = " where i.code like 'xx%'";
        List lst=id.executeQuery(sel+wh);
        
    }

    private static void createManyToMany() {
        ItemDAO dao = new ItemDAO();

        Item item = new Item();
//        item.setId("123idxx");
        for (int i = 0; i < 10; i++) {

//            ItemVariation va = (ItemVariation)dao.findGeneric("1234sx" + i,ItemVariation.class);//new ItemVariation();
            ItemVariation va = new ItemVariation();//)dao.findGeneric("1234sx" + i,ItemVariation.class);//new ItemVariation();
//            va.setId("1234xx" + i);
            item.addVarient(va);

        }
        item.setUoms(null);

        dao.save(item.getVariations());
        for (int i = 0; i < 5; i++) {

            ItemVariation va = (ItemVariation) dao.findGeneric("1234xx" + i, ItemVariation.class);//new ItemVariation();
//            ItemVariation va =new  ItemVariation();//)dao.findGeneric("1234sx" + i,ItemVariation.class);//new ItemVariation();
//            va.setId("1234xx" + i);
            va.setDescription("1234xxdes" + i);
            item.addVarient(va);

        }

        dao.update(item);
    }

    public static void getsum() {
        InventoryJournalDAO dao = new InventoryJournalDAO();

        System.out.println("test dao " + dao.getSummery("123", "123"));

    }

    public static void mxin(String[] args) {

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

    void setList(List xx) {
        this.xx = xx;
    }

    public void createList() {
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
