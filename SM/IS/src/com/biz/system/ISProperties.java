/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biz.system;

import org.util.PropertyUtil;

/**
 *
 * @author jawa
 */
public class ISProperties {
 
    public static Integer getSalesInvoiceLineDetailUI(){
        Integer property = PropertyUtil.getProperty("SalesInvoiceLineDetailUI");
        if(property==null)return 0;//default is 0
        return  property;
    }
    
}
