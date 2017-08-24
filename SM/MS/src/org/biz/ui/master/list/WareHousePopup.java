/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.ui.master.list;

import com.components.custom.PopupListner;
import com.components.custom.TextFieldWithPopUP;
import java.util.List;
import org.biz.invoicesystem.entity.master.Warehouse;
import org.biz.invoicesystem.service.master.WareHouseService;

/**
 *
 * @author jawath
 */
public class WareHousePopup extends TextFieldWithPopUP<Warehouse>{
    
    public WareHousePopup(){
    
//        super();
//        initPopup(Warehouse.class, new Class[]{String.class, String.class}, new String[]{"id", "code"}, "code",
//                new PopupListner() {
//                    @Override
//                    public List searchItem(Object searchQry) {
//                        List items = WareHouseService.getDAO().getAll();
//                        return items;
//                    }
//
//                    @Override
//                    public Object[] getTableData(Object obj) {
//                        Warehouse item = (Warehouse) obj;
//                        return new Object[]{item, item.getId(), item.getCode()};
//                    }
//                });
//        System.out.println( " ");
    }

}
