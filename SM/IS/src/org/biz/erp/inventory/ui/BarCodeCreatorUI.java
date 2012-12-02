/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BarCodeCreator.java
 *
 * Created on Mar 27, 2012, 8:56:44 PM
 */
package org.biz.erp.inventory.ui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import javax.swing.ImageIcon;
import org.krysalis.barcode4j.HumanReadablePlacement;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

/**
 *
 * @author nnjj
 */
public class BarCodeCreatorUI extends javax.swing.JPanel {

    /** Creates new form BarCodeCreator */
    public BarCodeCreatorUI() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tt = new org.components.controls.CTextField();
        cButton2 = new org.components.controls.CButton();
        cLabel1 = new org.components.controls.CLabel();

        cButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton2ActionPerformed(evt);
            }
        });

        cLabel1.setBackground(new java.awt.Color(255, 102, 0));
        cLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cLabel1.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(cButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tt, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(148, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addComponent(cLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(190, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton2ActionPerformed
        //Create the barcode bean
        File outputFile = null;
        OutputStream out = null;
        try {
            
            Code39Bean bean = new Code39Bean();
            final int dpi = 150;
            //Configure the barcode generator
            bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi)); //makes the narrow bar
            //width exactly one pixel
            bean.setWideFactor(2);
            bean.doQuietZone(false);
            bean.setBarHeight(3);//height of the barcode
            
            //Open output file
            outputFile = new File(System.currentTimeMillis() + "out.png");
            out = new FileOutputStream(outputFile);
            
            //Set up the canvas provider for monochrome PNG output
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                    out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
            /*
             
            //Set up the canvas provider for out put of onley image icon
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                    dpi,  BufferedImage.TYPE_BYTE_BINARY, false, 0);
             */
            
            //Generate the barcode
            bean.setMsgPosition(HumanReadablePlacement.HRP_BOTTOM);
            bean.generateBarcode(canvas, tt.getText());
            
            cLabel1.setIcon(new ImageIcon(canvas.getBufferedImage()));
            //Signal end of generation
            canvas.finish();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (Exception ex) {
            }
        }
}//GEN-LAST:event_cButton2ActionPerformed

    
    public void createBarcode(){
    
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CButton cButton2;
    private org.components.controls.CLabel cLabel1;
    private org.components.controls.CTextField tt;
    // End of variables declaration//GEN-END:variables
}
