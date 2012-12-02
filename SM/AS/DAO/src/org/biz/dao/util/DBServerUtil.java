/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.dao.util;

import java.net.InetAddress;
import org.apache.derby.drda.NetworkServerControl;
import org.dao.util.JPAUtil;

/**
 *
 * @author mjawath
 */
public class DBServerUtil {

    private static NetworkServerControl nsc;

    static {
    }

    public void init() {
        try {
            //how to start derby database
            nsc = new NetworkServerControl(InetAddress.getByName("localhost"), 1527);
//          System.setProperty("derby.system.home", "C:\\dist\\xyz");

        } catch (Throwable e) {
            new ExceptionInInitializerError(e);
        }
    }

    public void startServer() {
        try {
            nsc.ping();
            return;
        } catch (Exception e) {
        
        }

        try {
            nsc.start(null);
            JPAUtil.startPersistence();
        } catch (Exception e) {
            throw  new ExceptionInInitializerError(e);
        }
    }

    public void stop() {
        try {
            nsc.ping();
            JPAUtil.shutDownPersistenceFactory();
            nsc.shutdown();
        } catch (Exception ex) {
             throw  new ExceptionInInitializerError(ex);
        }
    }
}
