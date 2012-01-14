/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xhubacubi.jarhalla.client.ui.components;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author rugi
 */
public class JSelectRepoPanel extends JPanel {

    private Object selectedOption;
    private List<String> items;
    private JButton[] buttons;
    private JLabel label;
    public JSelectRepoPanel(List<String> items) {
        super();
        this.items = items == null ? new ArrayList<String>() : items;
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new GridLayout(items.size()+1, 0));
        label = new JLabel("Repositorios encontrados:");
        this.add(label);
        buttons = new JButton[items.size()];
        StringBuilder path = new StringBuilder();           
        for (int k =0;k<items.size();k++) {
            path.delete(0, path.length());
            path.append(items.get(k));
            buttons[k] = new JButton(path.toString());
            final int k1= k;
            buttons[k].addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if (SwingUtilities.getWindowAncestor(label) != null) {
                        selectedOption = items.get(k1);
                        SwingUtilities.getWindowAncestor(label).dispose();
                    }
                }
            });
            this.add(buttons[k]);            
        }//

    }

    /**
     * @return the getSelectedOption
     */
    public Object getSelectedOption() {
        return selectedOption;
    }

}
