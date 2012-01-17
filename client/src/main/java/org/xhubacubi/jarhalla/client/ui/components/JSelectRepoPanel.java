/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xhubacubi.jarhalla.client.ui.components;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author rugi
 */
public class JSelectRepoPanel extends JPanel {

    private Object selectedOption;
    private List<String> items;
    private JButton[] buttons;
    private JLabel header;
    private JLabel footer;
    private JPanel panelRepos;

    public JSelectRepoPanel(List<String> items) {
        super();
        this.items = items == null ? new ArrayList<String>() : items;
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new BorderLayout());
        panelRepos = new JPanel();
        panelRepos.setLayout(new GridLayout(items.size(), 0));
        header = new JLabel("Repositorios encontrados:");
        footer = new JLabel("Haga click sobre la carpeta deseada.");
        this.add(header, BorderLayout.NORTH);
        buttons = new JButton[items.size()];
        StringBuilder path = new StringBuilder();
        for (int k = 0; k < items.size(); k++) {
            path.delete(0, path.length());
            path.append(items.get(k));
            buttons[k] = new JButton(path.toString());
            final int k1 = k;
            buttons[k].addActionListener(new ActionListener() {
                //--

                @Override
                public void actionPerformed(ActionEvent ae) {
                    //--
                    int n = JOptionPane.showConfirmDialog(
                            SwingUtilities.getWindowAncestor(header),
                            "¿Desea revisar la carpeta para usarla como repositorio?",
                            "Se agregará la carpeta como repositorio",
                            JOptionPane.YES_NO_OPTION);

                    //--
                    if (n == 0) {
                        if (SwingUtilities.getWindowAncestor(header) != null) {
                            selectedOption = items.get(k1);
                            SwingUtilities.getWindowAncestor(header).dispose();
                        }
                    }
                }
            });
            panelRepos.add(buttons[k]);
        }//
        this.add(panelRepos, BorderLayout.CENTER);
        this.add(footer, BorderLayout.SOUTH);
    }

    /**
     * @return the getSelectedOption
     */
    public Object getSelectedOption() {
        return selectedOption;
    }
}
