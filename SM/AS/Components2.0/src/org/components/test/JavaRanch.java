package org.components.test;

import java.awt.BorderLayout;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

@SuppressWarnings("serial")
public class JavaRanch extends JPanel
{
    private JLabel inputLabel;
    private NumericTextField inputField;

    public JavaRanch()
    {
        super(new BorderLayout());

        inputLabel = new JLabel("Enter value: ");
        inputField = new NumericTextField();
        inputField.setColumns(10);

        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        this.add(inputLabel, BorderLayout.WEST);
        this.add(inputField, BorderLayout.CENTER);

    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

                    JFrame frame = new JFrame("Numeric Text Field Demo");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.add(new JavaRanch());
                    frame.pack();
                    frame.setVisible(true);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        });
    }
}

@SuppressWarnings("serial")
class NumericTextField extends JTextField
{

    @Override
    protected Document createDefaultModel()
    {

        return new NumericDocument();
    }

    private static class NumericDocument extends PlainDocument
    {
        // The regular expression to match input against (zero or more digits)
        private final static Pattern DIGITS = Pattern.compile("\\d*");

        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException
        {
                      // Only insert the text if it matches the regular expression
            if (str != null && DIGITS.matcher(str).matches())
                super.insertString(offs, str, a);
        }
    }
}
