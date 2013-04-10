/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invoicingsystem;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author d
 */
public class FormService {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Test Form");
                frame.setSize(800, 600);
                frame.setVisible(true);
                FormService service= new FormService();                
                FormBase formUI = new FormBase(service);
                frame.getContentPane().add(formUI);
            }
        });

    }

    void save() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    void clear() {
        
    }

    void delete() {
        
    }

    void update() {
        
    }

    void save(Object obj) {
        
    }

    void update(Object obj) {
        
    }

    void delete(Object obj) {
        
    }
}
