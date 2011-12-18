/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.xhubacubi.jarhalla.client.ui.components;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author rugi
 */
public class JLabelFileChooser extends JPanel {

    private JTextField input;
    private JFileChooser chooser;    
    private JButton button;

    
    public JLabelFileChooser(){
        this("");
    }
    
    public JLabelFileChooser(String input){
        this(new BorderLayout(), new JTextField(input), new JFileChooser());
    }
    

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

    private void initComponents() {        
        button = new JButton("[...]");
        button.setToolTipText("Seleccione el directorio.");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    input.setText(chooser.getCurrentDirectory().getAbsolutePath());                                        
                } else {
                    //
                }
            }
        });
        this.add(input);
        this.add(button);
        this.validate(); 
        
        
    }
}

