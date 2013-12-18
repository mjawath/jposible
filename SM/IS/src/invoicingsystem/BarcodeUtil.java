/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invoicingsystem;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import javax.swing.ImageIcon;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

/**
 *
 * @author d
 */
public class BarcodeUtil {
   
    public static void getBarcode(){
       
   //Create the barcode bean
        File outputFile = null;
        OutputStream out = null;
        try {

            Code39Bean bean = new Code39Bean();
            final int dpi = 150;
            //Configure the barcode generator
            bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi)); //makes the narrow bar
            //width exactly one pixel
            bean.setWideFactor(3);
            bean.doQuietZone(false);
            //Open output file
            outputFile = new File(System.currentTimeMillis() + "out.png");
            out = new FileOutputStream(outputFile);

            //Set up the canvas provider for monochrome PNG output
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                    out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
            //Generate the barcode
//            generation happens here
//            bean.generateBarcode(canvas, tt.getText());
//            cLabel1.setIcon(new ImageIcon(canvas.getBufferedImage()));
            //Signal end of generation
            canvas.finish();
        } catch (Exception e) {
        } finally {
            try {
                out.close();
            } catch (Exception ex) {
            }
        }

   } 
}
