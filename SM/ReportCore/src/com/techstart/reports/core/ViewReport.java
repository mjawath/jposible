package com.techstart.reports.core;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Container;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.view.JasperViewer;
import org.util.PropertyUtil;

/**
 *
 * @author nano
// */
public class ViewReport {

    private static Connection connection;
    private static int xlocation = 0;
    private static int ylocation = 0;

    public static Connection setConnection() {
             Connection conn=null;

          try {
               String userName = PropertyUtil.getProperty("db_user_name");
               String password = PropertyUtil.getProperty("db_password");
               String url =  PropertyUtil.getProperty("db_url");
               String driver =  PropertyUtil.getProperty("jdbc_driver");
               Class.forName (driver).newInstance ();
               conn = DriverManager.getConnection (url, userName, password);
               System.out.println ("Database connection established");
           }
           catch (Exception e)
           {
               System.err.println ("Cannot connect to database server");
               e.printStackTrace();
           }
        ViewReport.connection = conn;
        return conn;
    }

//    public static Container viewReport(Map parameters) {
//        try {
//Connection conn=null;
//          try {
//               String userName = "root";
//               String password = "123";
//               String url = "jdbc:mysql://localhost/raheemiyahotel";
//               Class.forName ("com.mysql.jdbc.Driver").newInstance ();
//               conn = DriverManager.getConnection (url, userName, password);
//               System.out.println ("Database connection established");
//           }
//           catch (Exception e)
//           {
//               System.err.println ("Cannot connect to database server");
//           }
//
//            String reportName = ViewReport.class.getSimpleName();
//            InputStream jrxml = ViewReport.class.getResourceAsStream("report1.jasper");
//
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jrxml, parameters, conn);
//            JasperViewer jasperViewer = new JasperViewer(jasperPrint);
////            JasperPrintManager.printReport(jasperPrint, false);
//
//            return jasperViewer.getRootPane();
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static Container viewReport(Map parameters,String filename) {
        try {
//Connection conn=null;
//          try {
//               String userName = "root";
//               String password = "123";
//               String url = "jdbc:mysql://localhost/raheemiyahotel";
//               Class.forName ("com.mysql.jdbc.Driver").newInstance ();
//               conn = DriverManager.getConnection (url, userName, password);
//               System.out.println ("Database connection established");
//           }
//           catch (Exception e)
//           {
//               System.err.println ("Cannot connect to database server");
//           }
//            connection = JPAUtil.getNewConnection();
            String filenamewithext=filename.concat(".jasper");
            InputStream jrxml = ViewReport.class.getResourceAsStream(filenamewithext);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jrxml, parameters, connection);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint);
//            JasperPrintManager.printReport(jasperPrint, false);

            return jasperViewer.getRootPane();
        } catch (Exception e) {
        e.printStackTrace();
            throw  new RuntimeException("print errir");
        }
    }

    public static void  init() {
        try {
        Map parameters=new HashMap();
            String reportName = ViewReport.class.getSimpleName();
            InputStream jrxml = ViewReport.class.getResourceAsStream("reportbill.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jrxml, parameters, connection);
//            JasperViewer jasperViewer = new JasperViewer(jasperPrint);
//            JasperPrintManager.printReport(jasperPrint, false);

//            return jasperViewer.getRootPane();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static void printReport(Map parameters,String filename) {
     
         try {
             setConnection();
//          conne = JPAUtil.getNewConnection();
            String filenamewithext=filename.concat(".jasper");

            InputStream jrxml = ViewReport.class.getResourceAsStream(filenamewithext);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jrxml, parameters,new JREmptyDataSource());
            JasperViewer jasperViewer = new JasperViewer(jasperPrint);
            jasperViewer.setVisible(true);
//            JasperPrintManager.printReport(jasperPrint, true);
            connection.close();
//PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException(e);
           
        }finally{
            try {
                if(connection!=null)
                connection.close();
            } catch (SQLException ex) {
               ex.printStackTrace();
            }
        
        }
    }
    
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("title", "my tech report");

        ViewReport.printReport(map, "test");
    }
}
