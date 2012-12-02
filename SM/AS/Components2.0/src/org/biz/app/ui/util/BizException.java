/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.app.ui.util;

/**
 *
 * @author nnjj
 */
public class BizException extends RuntimeException{

    String appended;
    public BizException(String message,Throwable  e) {
        super(message,e);
    }
    public BizException(String message) {
        super(message);
    }

    public BizException() {
    }
    
    
}
