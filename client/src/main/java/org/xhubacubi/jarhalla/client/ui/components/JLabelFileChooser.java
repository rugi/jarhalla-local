/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.xhubacubi.jarhalla.client.ui.components;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author rugi
 */
public class JLabelFileChooser extends JPanel {

    /**
     * 
     */
    private JTextField input;
    /**
     * 
     */
    private JFileChooser chooser;
    /**
     * 
     */
    private JButton button;

    /**
     * 
     */
    public JLabelFileChooser() {
        this("");
    }

    /**
     * 
     * @param input 
     */
    public JLabelFileChooser(String input) {
        this(new BorderLayout(), new JTextField(input), new JFileChooser());
    }

    /**
     * 
     * @param layout
     * @param input
     * @param chooser 
     */
    private JLabelFileChooser(BorderLayout layout,
            JTextField input,
            JFileChooser chooser) {
        super();
        this.input = input;
        this.chooser = chooser;
        chooser.setDialogTitle("Selecciona el directorio a indexar");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        initComponents();
    }

    /**
     * 
     */
    private void initComponents() {
        button = new JButton("[...]");
        button.setToolTipText("Seleccione el directorio.");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {                    
                    input.setText(chooser.getSelectedFile().getAbsolutePath());
                } else {
                    //
                }
            }
        });
        this.setLayout(new BorderLayout());
        this.add(input, BorderLayout.CENTER);
        this.add(button, BorderLayout.EAST);
        this.validate();
    }

    /**
     * 
     * @return 
     */
    public String getDirectory() {
        return this.input.getText() == null ? "" : this.input.getText().trim();
    }
    
    public void setDirectory(String d){
        this.input.setText(d);
    }

    /**
     * 
     */
    public void enabledComponents() {
        this.input.setEnabled(true);
        this.button.setEnabled(true);
    }

    /**
     * 
     */
    public void disabledComponents() {
        this.input.setEnabled(false);
        this.button.setEnabled(false);
    }
}
