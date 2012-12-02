/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.app.ui.util;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author mjawath
 */
public class Validator {

    public static boolean isEmptyOrNull(String txt) {
        if (txt == null) {
            return true;
        }
        if (txt.trim().isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean isLessthenRange(String txt, long lenth) {
        if (isEmptyOrNull(txt) || txt.length() < lenth) {
            return true;
        }
        return false;
    }

    public static boolean isNumberLess(Object obj, double key) {
        if (obj == null) {
            return true;
        }
        BigDecimal bd = new BigDecimal(obj.toString());
        if (bd.compareTo(new BigDecimal(key)) == -1) {
            return true;
        }
        return false;
    }

    public static boolean isNumberLessOrEqual(Object obj, double key) {
        if (obj == null) {
            return true;
        }
        BigDecimal bd = new BigDecimal(obj.toString());
        if (bd.compareTo(new BigDecimal(key)) == -1 || bd.compareTo(new BigDecimal(key)) == 0) {
            return true;
        }
        return false;

    }

    public static boolean isNumberGreaterThen(Object obj, double key) {
        if (obj == null) {
            return false;
        }
        BigDecimal bd = new BigDecimal(obj.toString());
        if (bd.compareTo(new BigDecimal(key)) == 1) {
            return true;
        }
        return false;
    }

    public static boolean isNumberGreaterOrEqual(Object obj, double key) {
        if (obj == null) {
            return false;
        }
        BigDecimal bd = new BigDecimal(obj.toString());
        if (bd.compareTo(new BigDecimal(key)) == 1 || bd.compareTo(new BigDecimal(key)) == 0) {
            return true;
        }
        return false;
    }
//
//    public static  boolean   isNumberLess(Number  obj,double  key){
//
//        if (obj == null || obj < key) {
//            return true;
//        }
//        return false;
//    }
//
//    public static  boolean   isNumberLessOrEqual(Double  obj,double  key){
//        if (obj ==null || obj <=  key) {
//            return true;
//        }
//        return false;
//
//    }
//
//    public static  boolean   isNumberGreaterThen(Double  obj,double  key){
//        if (obj == null || obj > key) {
//            return true;
//        }
//        return false;
//    }
//
//    public static  boolean   isNumberGreaterOrEqual(Double  obj,double  key){
//        if (obj ==null || obj >= key) {
//            return true;
//        }
//        return false;
//    }
}
